package com.bridgelabz.fundoonotes.controller;

import com.bridgelabz.fundoonotes.dto.request.NoteRequestDto;
import com.bridgelabz.fundoonotes.dto.response.NoteResponseDto;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteResponseDto> createNote(
            @Valid @RequestBody NoteRequestDto requestDto,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(noteService.createNote(requestDto, user.getId()));
    }

    @GetMapping
    public ResponseEntity<List<NoteResponseDto>> getAllNotes(
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(noteService.getAllNotes(user.getId()));
    }

    @PatchMapping("/{noteId}/pin")
    public ResponseEntity<NoteResponseDto> togglePin(
            @PathVariable Long noteId,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(noteService.togglePin(noteId, user.getId()));
    }

    @PatchMapping("/{noteId}/archive")
    public ResponseEntity<NoteResponseDto> toggleArchive(
            @PathVariable Long noteId,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(noteService.toggleArchive(noteId, user.getId()));
    }

    @PatchMapping("/{noteId}/trash")
    public ResponseEntity<NoteResponseDto> toggleTrash(
            @PathVariable Long noteId,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(noteService.toggleTrash(noteId, user.getId()));
    }
}