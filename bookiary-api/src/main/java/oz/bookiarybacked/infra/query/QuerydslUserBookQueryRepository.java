package oz.bookiarybacked.infra.query;

import static oz.bookiarybacked.domain.book.domain.model.QBook.*;
import static oz.bookiarybacked.domain.bookshelf.domain.model.QUserBook.*;

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

	private static Expression<BookSummaryDto> projectBookSummary() {
		return Projections.constructor(
			BookSummaryDto.class,
			userBook.id,
			book.title,
			book.imageUrl
		);
	}
}
