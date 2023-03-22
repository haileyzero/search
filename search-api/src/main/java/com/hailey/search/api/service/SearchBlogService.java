package com.hailey.search.api.service;

import com.hailey.search.api.config.openapi.KakaoProperties;
import com.hailey.search.api.config.openapi.NaverProperties;
import com.hailey.search.api.converter.DtoAssembler;
import com.hailey.search.api.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class SearchBlogService {
    private final RestTemplate restTemplate;
    private final KakaoProperties kakaoProperties;
    private final SearchRankingService searchRankingService;
    private final NaverProperties naverProperties;

    /**
     * 카카오 API를 통해 블로그를 검색합니다.
     * @param param
     * @return
     */
    @Retryable(maxAttempts = 1, value = RuntimeException.class)
    public PagingResponse<DocumentsDto> SearchBlogByKeyword(GetSearchBlogParam param) {
        return kakaoSearchBlogByKeyword(GetKakaoSearchBlogParam.builder()
                .keyword(param.getKeyword())
                .sort(param.getSort())
                .page(param.getPage())
                .size(param.getSize())
                .build());
    }

    /**
     * 네이버 API를 통해 블로그를 검색합니다.
     * @param param
     * @return
     */
    @Recover
    public PagingResponse<ItemDto> SearchBlogByKeywordRecover(GetSearchBlogParam param) {
        return naverSearchBlogByKeyword(GetNaverSearchBlogParam.builder()
                .keyword(param.getKeyword())
                .display(param.getDisplay())
                .start(param.getStart())
                .sort(param.getSort())
                .build());
    }

    /**
     * 카카오 API 키워드를 통해 블로그 검색한 결과를 Pagination 형태로 제공합니다.
     * @param param
     * @return
     */
    public PagingResponse<DocumentsDto> kakaoSearchBlogByKeyword(@Valid GetKakaoSearchBlogParam param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + kakaoProperties.getKey());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String httpURl = String.format("%s%s", kakaoProperties.getUrl(), kakaoProperties.getUri());
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpURl)
                .queryParam("query", param.getKeyword())
                .queryParam("page", param.getPage())
                .queryParam("size", param.getSize())
                .queryParam("sort", param.getSort())
                .build();

        KakaoSearchBlogResponse responseBody = new KakaoSearchBlogResponse();

        try {
            responseBody = restTemplate.exchange(builder.toString(), HttpMethod.GET, entity, KakaoSearchBlogResponse.class).getBody();
            searchRankingService.incrementScore(param.getKeyword());
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }

        return PagingResponse.<DocumentsDto>builder()
                .items(DtoAssembler.mapList(responseBody.getDocuments(), DocumentsDto.class))
                .totalCount(responseBody.getMeta().getTotalCount())
                .count(responseBody.getDocuments().size())
                .size(param.getSize())
                .page(param.getPage())
                .isEnd(responseBody.getMeta().isEnd())
                .build();
    }

    /**
     * 네이버 API 키워드를 통해 블로그 검색한 결과를 Pagination 형태로 제공합니다.
     * @param param
     * @return
     */
    public PagingResponse<ItemDto> naverSearchBlogByKeyword(@Valid GetNaverSearchBlogParam param) {
        String httpURl = String.format("%s%s", naverProperties.getUrl(), naverProperties.getUri());

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", naverProperties.getId());
        headers.add("X-Naver-Client-Secret", naverProperties.getSecret());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpURl)
                .queryParam("query", param.getKeyword())
                .queryParam("start", param.getStart())
                .queryParam("display", param.getDisplay())
                .queryParam("sort", param.getSort())
                .build();

        NaverSearchBlogResponse responseBody = new NaverSearchBlogResponse();

        try {
            responseBody = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, NaverSearchBlogResponse.class).getBody();
            searchRankingService.incrementScore(param.getKeyword());
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }

        return PagingResponse.<ItemDto>builder()
                .items(DtoAssembler.mapList(responseBody.getItems(), ItemDto.class))
                .totalCount(responseBody.getTotal())
                .count(responseBody.getItems().size())
                .size(param.getDisplay())
                .page(param.getStart())
                .build();
    }
}