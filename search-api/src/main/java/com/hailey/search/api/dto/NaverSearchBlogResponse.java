package com.hailey.search.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hailey.search.core.search.model.Item;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class NaverSearchBlogResponse {
    @JsonProperty("items")
    private List<Item> items;

    //검색 결과를 생성한 시간
    @JsonProperty("last_build_date")
    private String lastBuildDate;

    //한번에 표시할 검색 결과 개수
    @JsonProperty("display")
    private Integer display;

    //총 검색 결과 개수
    @JsonProperty("total")
    private Integer total;

    //검색 시작 위치
    @JsonProperty("start")
    private Integer start;
}
