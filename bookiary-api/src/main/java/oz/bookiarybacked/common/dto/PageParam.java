package oz.bookiarybacked.common.dto;

import static java.util.Objects.*;

import lombok.Getter;

@Getter
public final class PageParam {
	private static final int DEFAULT_START = 0;
	private static final int DEFAULT_SIZE = 10;

	private final int start;
	private final int size;

	public PageParam(Integer start, Integer size) {
		this.start = isNull(start) ? DEFAULT_START : start;
		this.size = isNull(size) ? DEFAULT_SIZE : size;
	}
}
