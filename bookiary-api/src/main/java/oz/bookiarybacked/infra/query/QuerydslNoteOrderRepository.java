package oz.bookiarybacked.infra.query;

import static oz.bookiarybacked.domain.note.domain.model.QNote.*;

import java.util.Objects;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.domain.note.domain.repository.NoteOrderRepository;

@Repository
@RequiredArgsConstructor
public class QuerydslNoteOrderRepository implements NoteOrderRepository {
	private final JPAQueryFactory queryFactory;

	@Override
	public int getNextOrder(long userBookId) {
		return Objects.requireNonNull(
			queryFactory.select(note.id.count())
				.from(note)
				.where(note.userBookId.eq(userBookId))
				.fetchOne()
		).intValue();
	}
}
