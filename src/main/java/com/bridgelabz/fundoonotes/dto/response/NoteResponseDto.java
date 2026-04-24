package com.bridgelabz.fundoonotes.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponseDto {
    private Long id;
    private String title;
    private String description;
    private boolean pinned;
    private boolean archived;
    private boolean trashed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}