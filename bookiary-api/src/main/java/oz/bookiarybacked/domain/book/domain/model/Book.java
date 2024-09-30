package oz.bookiarybacked.domain.book.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import oz.bookiarybacked.common.domain.model.BaseTimeEntity;
import oz.bookiarybacked.domain.book.domain.dto.BookDto;
import oz.bookiarybacked.domain.util.LocalDateUtils;

@Entity
@Table(name = "book")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "isbn", nullable = false, unique = true)
	private String isbn;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "author", nullable = false)
	private String author;

	@Column(name = "publisher", nullable = false)
	private String publisher;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "naver_book_url")
	private String naverBookUrl;

	@Column(name = "description")
	private String description;

	@Column(name = "published_at")
	private LocalDate publishedAt;

	public static Book from(BookDto bookInfo) {
		return Book.builder()
			.isbn(bookInfo.isbn())
			.title(bookInfo.title())
			.author(bookInfo.author())
			.publisher(bookInfo.publisher())
			.imageUrl(bookInfo.imageUrl())
			.naverBookUrl(bookInfo.link())
			.description(bookInfo.description())
			.publishedAt(LocalDateUtils.toLocalDate(bookInfo.publishedAt()))
			.build();
	}
}
