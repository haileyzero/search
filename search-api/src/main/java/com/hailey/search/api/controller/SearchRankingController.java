package com.hailey.search.api.controller;

import com.hailey.search.api.service.SearchRankingService;
import com.hailey.search.api.dto.PagingResponse;
import com.hailey.search.api.dto.SearchKeywordRankingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor
public class SearchRankingController {
    private final SearchRankingService searchRankingService;

    @GetMapping
    public PagingResponse<SearchKeywordRankingDto> SearchRankList() {
        return searchRankingService.reverseRangeWithScores(10);
    }
}
