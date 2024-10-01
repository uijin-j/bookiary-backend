package oz.bookiarybacked.auth.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthResponseExamples {
	public static final String LOGIN_PAGE_200_RESPONSE = """
		{
		    "code": 200,
		    "status": "OK",
		    "message": "OK",
		    "data": {
		        "loginPageUrl": "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=dcd8e26ff2448fded098dd320d89bf16&redirect_uri=http://localhost:8080"
		    }
		}
		""";

	public static final String LOGIN_200_RESPONSE = """
		{
		    "code": 200,
		    "status": "OK",
		    "message": "OK",
		    "data": {
		        "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJib29raWFyeSIsImlhdCI6MTcyNzYzMzIwOSwiZXhwIjoxNzI3NjM2ODA5LCJ1c2VyX2lkIjoxfQ.uKqgeqLvVdlKRrtYO6hY788OydA_ofZJoWgt_FxSjUNknmAFuGI9C2pIK8NL8PZ5QVb8OKrmJzZ1uZx7H2YsyA",
		        "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJib29raWFyeSIsImlhdCI6MTcyNzYzMzIwOSwiZXhwIjoxNzI4MjM4MDA5LCJ1c2VyX2lkIjoxfQ.FG9DaP55WS8yMp-YzBcJ48qDjHQpJUquhTmMaUTkxoGkYB25atZh-WsojRJoywwdjGOufUanj4c_SvsKJ_W9Gg"
		    }
		}
		""";
}
