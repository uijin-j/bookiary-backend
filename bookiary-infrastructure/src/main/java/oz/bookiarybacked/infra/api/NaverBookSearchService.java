package oz.bookiarybacked.infra.api;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import oz.bookiarybacked.application.book.BookSearchService;
import oz.bookiarybacked.application.book.dto.BookDto;
import oz.bookiarybacked.application.book.dto.request.BookSearchParam;
import oz.bookiarybacked.application.common.dto.Page;
import oz.bookiarybacked.config.properties.NaverSearchProperties;
import oz.bookiarybacked.infra.api.dto.response.BookSearchRes;

/**
 * 네이버 책 검색 서비스
 * - https://developers.naver.com/docs/serviceapi/search/book/book.md#%EC%B1%85-%EA%B2%80%EC%83%89-%EA%B2%B0%EA%B3%BC-%EC%A1%B0%ED%9A%8C
 */
@Service
@RequiredArgsConstructor
public class NaverBookSearchService implements BookSearchService {
	private static final String CLIENT_ID = "X-Naver-Client-Id";
	private static final String CLIENT_SECRET = "X-Naver-Client-Secret";
	private static final RestClient REST_CLIENT = RestClient.create();

	private final NaverSearchProperties properties;

	@Override
	public Page<BookDto> search(BookSearchParam searchParam) {
		return REST_CLIENT.get()
			.uri(buildUri(searchParam))
			.header(CLIENT_ID, properties.clientId())
			.header(CLIENT_SECRET, properties.clientSecret())
			.retrieve()
			.body(BookSearchRes.class)
			.toPage();
	}

	private String buildUri(BookSearchParam param) {
		return UriComponentsBuilder
			.fromUriString(properties.searchRequestUri())
			.queryParam("query", param.getKeyword())
			.queryParam("start", param.getStart())
			.queryParam("display", param.getSize())
			.build()
			.toString();
	}
}
