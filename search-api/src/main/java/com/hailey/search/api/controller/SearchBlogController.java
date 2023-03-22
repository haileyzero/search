package com.hailey.search.api.controller;

import com.hailey.search.api.dto.DocumentsDto;
import com.hailey.search.api.dto.GetSearchBlogParam;
import com.hailey.search.api.dto.ItemDto;
import com.hailey.search.api.dto.PagingResponse;
import com.hailey.search.api.service.SearchBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchBlogController {
    private final SearchBlogService searchBlogService;

    @GetMapping
    public PagingResponse<DocumentsDto> SearchBlogByKeyword(@Validated GetSearchBlogParam param) {
        return searchBlogService.SearchBlogByKeyword(param);
    }
}
