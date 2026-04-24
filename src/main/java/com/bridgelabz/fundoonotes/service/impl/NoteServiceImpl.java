package com.bridgelabz.fundoonotes.service.impl;

import com.bridgelabz.fundoonotes.dto.request.NoteRequestDto;
import com.bridgelabz.fundoonotes.dto.response.NoteResponseDto;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.exception.NoteNotFoundException;
import com.bridgelabz.fundoonotes.exception.UserNotFoundException;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Override
    public NoteResponseDto createNote(NoteRequestDto requestDto, Long userId) {
        log.info("Creating note for userId: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Note note = new Note();
        note.setTitle(requestDto.getTitle());
        note.setDescription(requestDto.getDescription());
        note.setUser(user);
        Note saved = noteRepository.save(note);
        log.debug("Note created with id: {}", saved.getId());
        return mapToResponse(saved);
    }

    @Override
    public List<NoteResponseDto> getAllNotes(Long userId) {
        log.info("Fetching all notes for userId: {}", userId);
        return noteRepository.findByUserIdAndArchivedFalseAndTrashedFalse(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NoteResponseDto togglePin(Long noteId, Long userId) {
        Note note = noteRepository.findByIdAndUserId(noteId, userId)
                .orElseThrow(() -> new NoteNotFoundException("Note not found"));
        note.setPinned(!note.isPinned());
        return mapToResponse(noteRepository.save(note));
    }

    @Override
    public NoteResponseDto toggleArchive(Long noteId, Long userId) {
        Note note = noteRepository.findByIdAndUserId(noteId, userId)
                .orElseThrow(() -> new NoteNotFoundException("Note not found"));
        note.setArchived(!note.isArchived());
        return mapToResponse(noteRepository.save(note));
    }

    @Override
    public NoteResponseDto toggleTrash(Long noteId, Long userId) {
        Note note = noteRepository.findByIdAndUserId(noteId, userId)
                .orElseThrow(() -> new NoteNotFoundException("Note not found"));
        note.setTrashed(!note.isTrashed());
        return mapToResponse(noteRepository.save(note));
    }

    public NoteResponseDto mapToResponse(Note note) {
        NoteResponseDto dto = new NoteResponseDto();
        dto.setId(note.getId());
        dto.setTitle(note.getTitle());
        dto.setDescription(note.getDescription());
        dto.setPinned(note.isPinned());
        dto.setArchived(note.isArchived());
        dto.setTrashed(note.isTrashed());
        dto.setCreatedAt(note.getCreatedAt());
        dto.setUpdatedAt(note.getUpdatedAt());
        return dto;
    }
}