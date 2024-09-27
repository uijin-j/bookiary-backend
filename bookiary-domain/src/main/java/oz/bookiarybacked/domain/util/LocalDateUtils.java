package oz.bookiarybacked.domain.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LocalDateUtils {

	public static LocalDate toLocalDate(String yyyyMMdd) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		return LocalDate.parse(yyyyMMdd, formatter);
	}
}
