# Search
SpringBoot Multi-Module 구성의 OpenAPI 블로그 검색 프로젝트 
`SpringBoot 3.0.4`, `Java 17`, `Gradle`, `h2`, `JPA`, `Redis`

## Open Source
* lombok
  * Annotation을 사용해서 반복되는 메소드를 자동으로 작성해주는 라이브러리
* embedded-redis
  * Local Server 환경 용도의 내장형 레디스
* spring-boot-starter-data-jpa
  * Spring JPA 
* spring-retry
  * 작업 실패 시 재시도 및 복구

## API 명세
### 블로그 검색
#### GET /search
카카오 OpenAPI를 이용하여 키워드를 통해 블로그를 검색합니다.
장애가 발생한 경우, 네이버 OpenAPI를 이용하여 검색합니다.
* 카카오 API의 키워드로 블로그 검색: https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-blog
* 네이버 블로그 검색 API: https://developers.naver.com/docs/serviceapi/search/blog/blog.md

##### Parameters
|Element|Description|Type|
|:---:|:---:|:---:|
|keyword|`kakao` `naver`검색을 원하는 질의어|String|
|sort|`kakao` `naver`결과 문서의 정렬 방식|String|
|page|`kakao`결과 페이지 번호|Integer|
|size|`kakao`한 페이지에 보여질 문서 수|Integer|
|display|`naver`한 페이지에 보여질 문서 수|Integer|
|start|`naver`검색 시작 위치|Integer|

##### Returns
|Element|Description|Type|
|:---:|:---:|:---:|
|items|검색 결과 객체|List<T>|
|totalCount|검색된 총 문서의 수|Integer|
|items|조회된 아이템의 수|Integer|
|items|요청한 조회 아이템의 수|Integer|
|items|요청 페이지 번호|Integer|
|items|현재 페이지가 마지막 페이지인지 여부|Boolean|

##### Response Example
###### kakao-api
```
{
  "totalCount" : 3234,
  "count" : 10,
  "size" : 10,
  "page" : 10,
  "isEnd" : false
  "documents": [
    {
    "title": "작은 <b>집</b> <b>짓기</b> 기본컨셉 - <b>집</b><b>짓기</b> 초기구상하기",    
    "contents": "이 점은 <b>집</b>을 지으면서 고민해보아야 한다. 하지만, 금액에 대한 가성비 대비 크게 문제되지 않을 부분이라 생각하여 설계로 극복하자고 생각했다. 전체 <b>집</b><b>짓기</b>의 기본방향은 크게 세 가지이다. 우선은 여가의 영역 증대이다. 현대 시대 일도 중요하지만, 여가시간 <b>집</b>에서 어떻게 보내느냐가 중요하니깐 이를 기본적...",
    "url": "https://brunch.co.kr/@tourism/91",
    "blogname": "정란수의 브런치",
    "thumbnail": "http://search3.kakaocdn.net/argon/130x130_85_c/7r6ygzbvBDc",
    "datetime": "2017-05-07T18:50:07.000+09:00"
    },
    ...
  ]
}
```
  
###### naver-api  
```
{
  "totalCount" : 3234,
  "count" : 10,
  "size" : 10,
  "page" : 10,
  "isEnd" : false
  "items": [
    {
    "title": "Naver Open API - blog ::'리뷰",    
    "link" : "http://search.naver.com",
    "description" : "명예훼손 없이 <b>리뷰</b>쓰기 우리 블로그하시는 분들께는 꽤 중요한 내용일 수도 있습니다 그것도 주로 <b>리뷰</b> 위주로 블로그를 진행하신 분이라면 더욱 더 말이죠
                오늘 포스팅은, 어떻게 하면 객관적이고 좋은 <b>리뷰</b>를... ",
    "bloggername" : "건짱의 Best Drawing World2",
    "bloggerlink" : "http://blog.naver.com/yoonbitgaram"
    "postdate" : "2016-12-08"
    },
    ...
  ]
}  
```

### 인기 검색어 목록
#### GET /rank
인기 검색어 10개를 검색된 횟수와 함께 제공합니다.

##### Parameters
|Element|Description|Type|
|:---:|:---:|:---:|

##### Returns
|Element|Description|Type|
|:---:|:---:|:---:|
|items|검색 결과 객체|List<T>|
|totalCount|검색된 총 문서의 수|Integer|
|items|조회된 아이템의 수|Integer|
|items|요청한 조회 아이템의 수|Integer|
|items|요청 페이지 번호|Integer|
|items|현재 페이지가 마지막 페이지인지 여부|Boolean|

##### Response Example
```
{
    "items": [
        {
            "keyword": "맛집",
            "score": 233
        },
        {
            "keyword": "맛집 블로그",
            "score": 25
        },
        {
            "keyword": "스터디 모집",
            "score": 5
        },
        ...
    ],
    "count" : 10
}
```
