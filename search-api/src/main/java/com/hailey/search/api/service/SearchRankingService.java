package com.hailey.search.api.service;

import com.hailey.search.api.converter.DtoAssembler;
import com.hailey.search.api.repository.SearchRankingRepository;
import com.hailey.search.api.dto.PagingResponse;
import com.hailey.search.api.dto.SearchKeywordRankingDto;
import com.hailey.search.api.repository.SearchRankingJpaRepository;
import com.hailey.search.core.search.model.Ranking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchRankingService {
    private final SearchRankingRepository searchRankingRepository;
    private final SearchRankingJpaRepository searchRankingJpaRepository;

    /**
     * 검색한 특정 키워드의 검색 횟수를 증가시킵니다.
     * @param keyword
     */
    public void incrementScore(String keyword) {
        try {
            searchRankingRepository.incrementScore(keyword);
        } catch (Exception e) {
            log.error("incrementScore() Fail" + e.getMessage());
        }
    }

    /**
     * 많이 검색한 순서대로 최대 10개의 검색 키워드를 제공하며, 검색어 별 검색한 횟수를 제공합니다.
     * @param rankSize
     * @return
     */
    public PagingResponse<SearchKeywordRankingDto> reverseRangeWithScores(int rankSize) {
        var rankList = searchRankingRepository.reverseRangeWithScores(rankSize);

        return PagingResponse.<SearchKeywordRankingDto>builder()
                .items(DtoAssembler.mapList(rankList, SearchKeywordRankingDto.class))
                .count(rankList.size())
                .build();
    }

    /**
     * (DB Lock 해제 시) 또는 (일정 시간 이후) 랭킹 데이터(redis)를 갱신합니다.
     * @param tempRankingList   Redis 데이터
     */
    @Transactional
    public void updateRanking(List<SearchKeywordRankingDto> tempRankingList) {
        tempRankingList.forEach(temp -> {
            searchRankingJpaRepository.findById(temp.getKeyword())
                    .ifPresentOrElse(tempRanking -> {
                        tempRanking.setScore(tempRanking.getScore() + temp.getScore());
                        searchRankingJpaRepository.save(tempRanking);
                    }, () -> {
                        var ranking = Ranking.builder()
                                .keyword(temp.getKeyword())
                                .score(temp.getScore())
                                .build();
                        searchRankingJpaRepository.save(ranking);
                    });
        });
    }
}
