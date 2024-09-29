package oz.bookiarybacked.domain.note.application;

import static oz.bookiarybacked.common.exception.ErrorMessages.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.common.exception.DbConsistencyException;
import oz.bookiarybacked.domain.bookshelf.domain.model.UserBook;
import oz.bookiarybacked.domain.bookshelf.domain.repository.UserBookRepository;
import oz.bookiarybacked.domain.note.application.dto.request.NoteCreateRequest;
import oz.bookiarybacked.domain.note.application.dto.request.NoteUpdateRequest;
import oz.bookiarybacked.domain.note.domain.model.Note;
import oz.bookiarybacked.domain.note.domain.repository.NoteOrderRepository;
import oz.bookiarybacked.domain.note.domain.repository.NoteRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoteService {
	private final NoteRepository noteRepository;
	private final UserBookRepository userBookRepository;
	private final NoteOrderRepository noteOrderRepository;

	@Transactional
	public void create(Long loginId, NoteCreateRequest request) {
		// Step 1. 도서 조회
		UserBook book = userBookRepository.findById(request.userBookId())
			.orElseThrow(() -> new EntityNotFoundException(USER_BOOK_NOT_FOUND));

		// Step 2. 노트 생성 권한 검사
		book.checkAddNotePermission(loginId);

		// Step 3. 노트 생성
		int order = noteOrderRepository.getNextOrder(book.getBookId());
		Note note = Note.of(book.getId(), request.content(), order);
		noteRepository.save(note);
	}

	@Transactional
	public void update(Long loginId, Long noteId, NoteUpdateRequest request) {
		// Step 1. 노트 조회
		Note note = noteRepository.findById(noteId)
			.orElseThrow(() -> new EntityNotFoundException(NOTE_NOT_FOUND));

		// Step 2. 노트 수정 권한 검사
		UserBook book = userBookRepository.findById(note.getUserBookId())
			.orElseThrow(() -> new DbConsistencyException(note.getId() + " 노트의 책이 존재하지 않습니다."));

		book.checkAddNotePermission(loginId);

		// Step 3. 노트 수정
		note.modifyContent(request.content());
	}
}
