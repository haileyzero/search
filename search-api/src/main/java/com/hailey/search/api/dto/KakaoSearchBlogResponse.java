package com.hailey.search.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hailey.search.core.search.model.Documents;
import com.hailey.search.core.search.model.Meta;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class KakaoSearchBlogResponse {
    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("documents")
    private List<Documents> documents;
}
