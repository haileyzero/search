package com.hailey.search.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GetSearchBlogParam {
    private String keyword;

    private String sort;

    private Integer page;

    private Integer size;

    private Integer display;

    private Integer start;
}
