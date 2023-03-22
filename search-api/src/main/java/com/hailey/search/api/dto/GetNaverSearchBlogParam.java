package com.hailey.search.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GetNaverSearchBlogParam {
    @NotEmpty(message = "keyword is required data field")
    private String keyword;

    @Builder.Default
    private String sort = "sim";

    @Builder.Default
    @Max(100)
    private Integer display = 10;

    @Builder.Default
    @Max(value = 100)
    private Integer start = 1;
}
