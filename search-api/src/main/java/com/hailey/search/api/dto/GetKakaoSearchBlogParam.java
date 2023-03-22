package com.hailey.search.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GetKakaoSearchBlogParam {
    @NotEmpty(message = "keyword is required data field")
    private String keyword;

    @Builder.Default
    private String sort = "accuracy";

    @Builder.Default
    @Min(1)
    private Integer page = 1;

    @Builder.Default
    @Max(value = 50)
    private Integer size = 10;
}
