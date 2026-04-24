package com.bridgelabz.fundoonotes.repository;

import com.bridgelabz.fundoonotes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserIdAndTrashedFalse(Long userId);
    List<Note> findByUserIdAndArchivedFalseAndTrashedFalse(Long userId);
    List<Note> findByUserIdAndPinnedTrueAndTrashedFalse(Long userId);
    List<Note> findByUserIdAndArchivedTrueAndTrashedFalse(Long userId);
    List<Note> findByUserIdAndTrashedTrue(Long userId);
    Optional<Note> findByIdAndUserId(Long noteId, Long userId);
}