package oz.bookiarybacked.infra.api.dto.response;

import oz.bookiarybacked.domain.book.domain.dto.BookDto;

public record BookItem(
	String title,
	String link,
	String image,
	String author,
	String discount,
	String publisher,
	String pubdate,
	String isbn,
	String description
) {
	public BookDto toBookDto() {
		return BookDto.builder()
			.title(title)
			.link(link)
			.imageUrl(image)
			.author(author)
			.price(discount)
			.publisher(publisher)
			.publishedAt(pubdate)
			.isbn(isbn)
			.description(description)
			.build();
	}
}
