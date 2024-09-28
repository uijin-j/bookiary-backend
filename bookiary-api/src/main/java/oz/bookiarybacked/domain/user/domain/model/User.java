package oz.bookiarybacked.domain.user.domain.model;

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
import oz.bookiarybacked.common.BaseTimeEntity;
import oz.bookiarybacked.domain.book.domain.model.Book;
import oz.bookiarybacked.domain.bookshelf.domain.model.UserBook;

@Entity
@Table(name = "users")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nickname")
	private String nickname;

	public static User signUp() {
		return User.builder()
			.build();
	}

	public UserBook addBook(Book book) {
		// 책을 책장에 추가하는 비즈니스 로직
		return UserBook.of(this.id, book.getId());
	}
}
