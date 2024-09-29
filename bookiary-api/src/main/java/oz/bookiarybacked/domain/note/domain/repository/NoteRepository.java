package oz.bookiarybacked.domain.note.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import oz.bookiarybacked.domain.note.domain.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
