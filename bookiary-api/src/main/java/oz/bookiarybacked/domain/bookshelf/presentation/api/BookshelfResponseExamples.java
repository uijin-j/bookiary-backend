package oz.bookiarybacked.domain.bookshelf.presentation.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BookshelfResponseExamples {
	public static final String RETRIEVE_BOOKSHELF_200_RESPONSE = """
		{
		    "code": 200,
		    "status": "OK",
		    "message": "OK",
		    "data": {
		        "total": 1,
		        "start": 0,
		        "size": 1,
		        "contents": [
		            {
		                "id": 1,
		                "title": "Clean Code(클린 코드) (애자일 소프트웨어 장인 정신)",
		                "imageUrl": "https://shopping-phinf.pstatic.net/main_3247419/32474195676.20230718122457.jpg"
		            }
		        ]
		    }
		}
		""";

	public static final String RETRIEVE_BOOK_200_RESPONSE = """
		{   
		 	 "code": 200,
		     "status": "OK",
		     "message": "OK",
		     "data": {
		         "id": 1,
		         "title": "Clean Code(클린 코드) (애자일 소프트웨어 장인 정신)",
		         "imageUrl": "https://shopping-phinf.pstatic.net/main_3247419/32474195676.20230718122457.jpg",
		         "author": "로버트 C. 마틴",
		         "publisher": "인사이트",
		         "publishedAt": "2013-12-24",
		         "description": "프로그래머, 소프트웨어 공학도, 프로젝트 관리자, 팀 리더, 시스템 분석가에게 도움이 될\\n더 나은 코드를 만드는 책\\n\\n『Clean Code(클린 코드)』은 오브젝트 멘토(Object Mentor)의 동료들과 힘을 모아 ‘개발하며’ 클린 코드를 만드는 최상의 애자일 기법을 소개하고 있다. 소프트웨어 장인 정신의 가치를 심어 주며 프로그래밍 실력을 높여줄 것이다. 여러분이 노력만 한다면. 어떤 노력이 필요하냐고? 코드를 읽어야 한다. 아주 많은 코드를. 그리고 코드를 읽으면서 그 코드의 무엇이 옳은지, 그른지 생각도 해야 한다. 좀 더 중요하게는 전문가로서 자신이 지니는 가치와 장인으로서 자기 작품에 대한 헌신을 돌아보게 된다.",
		         "notes": [
		             {
		                 "id": 2,
		                 "content": "책을 읽으면서 느낀 점을 정리해봤습니다...",
		                 "createdAt": "2024-09-30T01:59:40.985222"
		             }
		         ]
		     }
		 }
		""";

	public static final String TAKE_BOOK_OFF_204_RESPONSE = """
		{
		    "code": 204,
		    "status": "NO_CONTENT",
		    "message": "NO_CONTENT",
		    "data": null
		}
		""";
}
