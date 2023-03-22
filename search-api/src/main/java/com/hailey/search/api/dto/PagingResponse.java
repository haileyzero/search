package com.hailey.search.api.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PagingResponse<T> {
    private List<T> items;

    private Integer totalCount; //검색된 총 문서의 수(Meta)
    private Integer count;      //조회된 아이템의 수(Documents.size())
    private Integer size;       //요청한 조회 아이템의 수 (default:10)

    private Integer page;       //요청 페이지 번호(현재 페이지 번호를 의미)
    private Boolean isEnd;      //현재 페이지가 마지막 페이지인지 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음
}