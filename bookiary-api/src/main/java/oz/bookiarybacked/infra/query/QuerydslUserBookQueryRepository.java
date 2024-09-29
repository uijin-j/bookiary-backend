package oz.bookiarybacked.infra.query;

import static oz.bookiarybacked.domain.book.domain.model.QBook.*;
import static oz.bookiarybacked.domain.bookshelf.domain.model.QUserBook.*;
import static oz.bookiarybacked.domain.note.domain.model.QNote.*;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.common.dto.Page;
import oz.bookiarybacked.common.dto.PageParam;
import oz.bookiarybacked.domain.bookshelf.domain.dto.BookDetailDto;
import oz.bookiarybacked.domain.bookshelf.domain.dto.BookSummaryDto;
import oz.bookiarybacked.domain.bookshelf.domain.dto.NoteDto;
import oz.bookiarybacked.domain.bookshelf.domain.dto.response.RetrieveBookRes;
import oz.bookiarybacked.domain.bookshelf.domain.repository.UserBookQueryRepository;

@Repository
@RequiredArgsConstructor
public class QuerydslUserBookQueryRepository implements UserBookQueryRepository {
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<BookSummaryDto> retrieveBookshelf(Long userId, PageParam pageParam) {
		List<BookSummaryDto> results = queryFactory
			.select(projectBookSummary())
			.from(userBook)
			.join(book)
			.on(userBook.bookId.eq(book.id))
			.where(userBook.userId.eq(userId))
			.offset(pageParam.getStart())
			.limit(pageParam.getSize())
			.fetch();

		int total = Objects.requireNonNull(
			queryFactory
				.select(userBook.id.count())
				.from(userBook)
				.where(userBook.userId.eq(userId))
				.fetchOne()
		).intValue();

		return Page.of(total, pageParam.getStart(), results);
	}

	@Override
	public RetrieveBookRes retrieveBook(Long userBookId) {
		BookDetailDto bookInfo = queryFactory
			.select(projectBookDetail())
			.from(userBook)
			.join(book)
			.on(userBook.bookId.eq(book.id))
			.where(userBook.id.eq(userBookId))
			.fetchOne();

		List<NoteDto> notes = queryFactory
			.select(projectNote())
			.from(note)
			.where(note.userBookId.eq(userBookId))
			.orderBy(note.order.asc())
			.fetch();

		return RetrieveBookRes.of(bookInfo, notes);
	}

	private static Expression<BookSummaryDto> projectBookSummary() {
		return Projections.constructor(
			BookSummaryDto.class,
			userBook.id,
			book.title,
			book.imageUrl
		);
	}

	private Expression<BookDetailDto> projectBookDetail() {
		return Projections.constructor(
			BookDetailDto.class,
			userBook.id,
			book.title,
			book.imageUrl,
			book.author,
			book.publisher,
			book.publishedAt,
			book.description
		);
	}

	private static Expression<NoteDto> projectNote() {
		return Projections.constructor(
			NoteDto.class,
			note.id,
			note.content,
			note.createdAt
		);
	}
}
