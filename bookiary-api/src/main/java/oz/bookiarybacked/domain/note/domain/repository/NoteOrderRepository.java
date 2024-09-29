package oz.bookiarybacked.domain.note.domain.repository;

public interface NoteOrderRepository {

	int getNextOrder(long userBookId);
}
