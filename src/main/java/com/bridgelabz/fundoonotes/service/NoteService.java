package com.bridgelabz.fundoonotes.service;

import com.bridgelabz.fundoonotes.dto.request.NoteRequestDto;
import com.bridgelabz.fundoonotes.dto.response.NoteResponseDto;
import java.util.List;

public interface NoteService {
    NoteResponseDto createNote(NoteRequestDto requestDto, Long userId);
    List<NoteResponseDto> getAllNotes(Long userId);
    NoteResponseDto togglePin(Long noteId, Long userId);
    NoteResponseDto toggleArchive(Long noteId, Long userId);
    NoteResponseDto toggleTrash(Long noteId, Long userId);
}