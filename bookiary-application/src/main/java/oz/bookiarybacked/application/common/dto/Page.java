package oz.bookiarybacked.application.common.dto;

import java.util.List;

public record Page<T>(
	int total,
	int start,
	int size,
	List<T> contents
) {

	public static <T> Page<T> of(int total, int start, int size, List<T> contents) {
		return new Page<T>(total, start, size, contents);
	}

}
