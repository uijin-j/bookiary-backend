package oz.bookiarybacked.domain.book.presentation.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BookResponseExamples {
	public static final String BOOK_SEARCH_200_RESPONSE = """
		{
		        "code": 200,
		        "status": "OK",
		        "message": "OK",
		        "data": {
		            "total": 4,
		            "start": 1,
		            "size": 4,
		            "contents": [
		                {
		                    "title": "Real MySQL",
		                    "link": "https://search.shopping.naver.com/book/catalog/32504313134",
		                    "imageUrl": "https://shopping-phinf.pstatic.net/main_3250431/32504313134.20220527043748.jpg",
		                    "author": "이성욱",
		                    "price": "45000",
		                    "publisher": "위키북스",
		                    "publishedAt": "20120508",
		                    "isbn": "9788992939003",
		                    "description": "MySQL 전문가가 전하는 MySQL 활용법!\\n\\n『Real MySQL』은 기본적인 SQL 문법 소개나 매뉴얼의 번역 수준에서 나아가, MySQL을 이용하는 애플리케이션 개발이나 운영을 통해 얻은 경험과 지식, 그리고 반드시 알고 있어야 할 주의사항을 중점적으로 다룬 책이다. 많은 그림과 벤치마크 결과를 함께 수록해 MySQL을 처음 접하는 사용자도 쉽게 이해할 수 있게 구성했다. 본문은 ‘MySQL 서버의 아키텍처와 MySQL 설치’부터 ‘인덱스의 종류와 구조 및 활용’, ‘MySQL 서버와 연동하는 프로그램 개발’, ‘데이터 모델링 및 최적의 데이터 타입 선정’ 등을 다룬다."
		                },
		                {
		                    "title": "Real MySQL 8.0 (1권) (개발자와 DBA를 위한 MySQL 실전 가이드)",
		                    "link": "https://search.shopping.naver.com/book/catalog/32443973624",
		                    "imageUrl": "https://shopping-phinf.pstatic.net/main_3244397/32443973624.20230822103818.jpg",
		                    "author": "백은빈",
		                    "price": "21800",
		                    "publisher": "위키북스",
		                    "publishedAt": "20210908",
		                    "isbn": "9791158392703",
		                    "description": "MySQL 서버를 활용하는 프로젝트에 꼭 필요한 경험과 지식을 담았습니다!\\n\\n《Real MySQL 8.0》은 《Real MySQL》을 정제해서 꼭 필요한 내용으로 압축하고, MySQL 8.0의 GTID와 InnoDB 클러스터 기능들과 소프트웨어 업계 트렌드를 반영한 GIS 및 전문 검색 등의 확장 기능들을 추가로 수록했다.\\n또한 《Real MySQL 8.0》은 단순 SQL 문법이나 쿼리 작성보다는 MySQL 서버를 활용하는 프로젝트에서 꼭 필요한 경험과 지식을 전달하는 데 집중했다.\\n이 책을 처음부터 끝까지 정독할 수 있다면 더없이 좋겠지만 필요하거나 관심 있는 내용 위주로 살펴봐도 지금까지 경험했던 수많은 문제들을 해결할 수 있을 것이다.\\n\\n★ 이 책에서 다루는 내용 ★\\n\\n◎ MySQL 설치와 서버 아키텍처\\n◎ 트랜잭션과 잠금\\n◎ 인덱스 종류의 구조 및 활용\\n◎ GTID 기반 복제 및 InnoDB 클러스터 아키텍처\\n◎ 쿼리의 실행 계획 분석 및 최적화\\n◎ 스토어드 프로그램 개발\\n◎ 데이터 모델링 및 최적의 데이터 타입 선정\\n◎ 파티션 및 데이터 압축 활용\\n◎ GIS 및 전문 검색 기능 활용\\n◎ Performance 스키마 및 Sys 스키마 활용"
		                },
		                {
		                    "title": "Real MySQL 8.0 (2권) (개발자와 DBA를 위한 MySQL 실전 가이드)",
		                    "link": "https://search.shopping.naver.com/book/catalog/32452362011",
		                    "imageUrl": "https://shopping-phinf.pstatic.net/main_3245236/32452362011.20230829085429.jpg",
		                    "author": "백은빈",
		                    "price": "28800",
		                    "publisher": "위키북스",
		                    "publishedAt": "20210908",
		                    "isbn": "9791158392727",
		                    "description": "MySQL 서버를 활용하는 프로젝트에 꼭 필요한 경험과 지식을 담았습니다!\\n\\n《Real MySQL 8.0》은 《Real MySQL》을 정제해서 꼭 필요한 내용으로 압축하고, MySQL 8.0의 GTID와 InnoDB 클러스터 기능들과 소프트웨어 업계 트렌드를 반영한 GIS 및 전문 검색 등의 확장 기능들을 추가로 수록했다.\\n또한 《Real MySQL 8.0》은 단순 SQL 문법이나 쿼리 작성보다는 MySQL 서버를 활용하는 프로젝트에서 꼭 필요한 경험과 지식을 전달하는 데 집중했다.\\n이 책을 처음부터 끝까지 정독할 수 있다면 더없이 좋겠지만 필요하거나 관심 있는 내용 위주로 살펴봐도 지금까지 경험했던 수많은 문제들을 해결할 수 있을 것이다.\\n\\n★ 이 책에서 다루는 내용 ★\\n\\n◎ MySQL 설치와 서버 아키텍처\\n◎ 트랜잭션과 잠금\\n◎ 인덱스 종류의 구조 및 활용\\n◎ GTID 기반 복제 및 InnoDB 클러스터 아키텍처\\n◎ 쿼리의 실행 계획 분석 및 최적화\\n◎ 스토어드 프로그램 개발\\n◎ 데이터 모델링 및 최적의 데이터 타입 선정\\n◎ 파티션 및 데이터 압축 활용\\n◎ GIS 및 전문 검색 기능 활용\\n◎ Performance 스키마 및 Sys 스키마 활용"
		                },
		                {
		                    "title": "Real MariaDB (MariaDB 10.0과 MySQL 5.6을 함께 배우는)",
		                    "link": "https://search.shopping.naver.com/book/catalog/32492720112",
		                    "imageUrl": "https://shopping-phinf.pstatic.net/main_3249272/32492720112.20220527050917.jpg",
		                    "author": "이성욱",
		                    "price": "31500",
		                    "publisher": "위키북스",
		                    "publishedAt": "20140418",
		                    "isbn": "9788998139537",
		                    "description": "『Real MariaDB』는 MariaDB와 MySQL에 관심을 가지고 있는 독자들을 위한 책이다. MariaDB 10.0과 MySQL 5.6의 각 고유 기능과 공통 기능들을 비교 설명하는 방식으로 구성하여 MariaDB와 MySQL의 특성과 기능 차이를 이해할 수 있도록 했다. 서비스의 특성에 맞게 (MariaDB와 MySQL중에서) 적절한 솔루션을 선택하는 안목을 키우는데 도움을 준다."
		                }
		            ]
		        }
		    }
		""";

	public static final String ADD_BOOK_TO_SHELF_201_RESPONSE = """
		{
		        "code": 201,
		        "status": "CREATED",
		        "message": "CREATED",
		        "data": null
		    }
		""";
}
