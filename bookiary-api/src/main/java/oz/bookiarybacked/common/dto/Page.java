package oz.bookiarybacked.common.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "페이징 응답")
public record Page<T>(
	@Schema(description = "전체 데이터 수", example = "100")
	int total,
	@Schema(description = "시작 인덱스", example = "0")
	int start,
	@Schema(description = "페이지 크기", example = "10")
	int size,
	@Schema(description = "데이터 목록")
	List<T> contents
) {

	public static <T> Page<T> of(int total, int start, int size, List<T> contents) {
		return new Page<T>(total, start, size, contents);
	}

	public static <T> Page<T> of(int total, int start, List<T> contents) {
		return new Page<T>(total, start, contents.size(), contents);
	}
}
