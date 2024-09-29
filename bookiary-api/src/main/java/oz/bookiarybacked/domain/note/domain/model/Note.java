package oz.bookiarybacked.domain.note.domain.model;

import java.time.LocalDateTime;

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

@Entity
@Table(name = "note")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Note extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_book_id", nullable = false)
	private Long userBookId;

	@Column(name = "content", nullable = false, columnDefinition = "TEXT")
	private String content;

	@Column(name = "note_order", nullable = false)
	private int order;

	@Column(name = "deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean deleted;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	public static Note of(Long userBookId, String content, int order) {
		return Note.builder()
			.userBookId(userBookId)
			.content(content)
			.order(order)
			.build();
	}

	public void modifyContent(String content) {
		this.content = content;
	}

	public void delete() {
		this.deleted = true;
		this.deletedAt = LocalDateTime.now();
	}
}
