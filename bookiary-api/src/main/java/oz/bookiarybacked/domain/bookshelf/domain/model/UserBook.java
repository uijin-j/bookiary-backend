package oz.bookiarybacked.domain.bookshelf.domain.model;

import static oz.bookiarybacked.common.exception.ErrorMessages.*;

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
import oz.bookiarybacked.common.exception.PermissionDeniedException;

@Entity
@Table(name = "user_book")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBook extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "book_id")
	private Long bookId;

	public static UserBook of(Long userId, Long bookId) {
		return UserBook.builder()
			.userId(userId)
			.bookId(bookId)
			.build();
	}

	public void checkReadPermission(Long userId) {
		// 책 상세 조회는 본인만 가능
		validateOwner(userId);
	}

	public void checkDeletePermission(Long loginId) {
		// 책 삭제는 본인만 가능
		validateOwner(loginId);
	}

	public void checkAddNotePermission(long userId) {
		validateOwner(userId);
	}

	public void checkUpdateNotePermission(long userId) {
		validateOwner(userId);
	}

	private void validateOwner(Long userId) {
		if (!this.userId.equals(userId)) {
			throw new PermissionDeniedException(PERMISSION_DENIED);
		}
	}

}
