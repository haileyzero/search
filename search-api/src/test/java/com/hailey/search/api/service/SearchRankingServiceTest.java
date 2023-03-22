package com.hailey.search.api.service;

import com.hailey.search.api.repository.SearchRankingRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
class SearchRankingServiceTest {
    @Autowired
    SearchRankingService service;

    @Autowired
    SearchRankingRepository repository;

    @Test
    @DisplayName("키워드 'test'의 점수가 5만큼 증가 해야합니다.")
    public void incrementScoreTest() {
        log.info(">>>> SearchRankingService incrementScore() start.");

        log.info(service.reverseRangeWithScores(10).toString());

        service.incrementScore("test");
        service.incrementScore("test");
        service.incrementScore("test");
        service.incrementScore("test");
        service.incrementScore("test");

        log.info(service.reverseRangeWithScores(10).toString());
        log.info(">>>> SearchRankingService incrementScore() end.");
    }

    @Test
    @DisplayName("사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드를 제공합니다.")
    public void reverseRangeWithScoresTest() {
        log.info(">>>> SearchRankingService reverseRangeWithScores() start.");

        var maxRes = service.reverseRangeWithScores(9999);
        var res = service.reverseRangeWithScores(10);

        log.info(res.toString());
        log.info("[total size]  : " + maxRes.getItems().size());
        log.info("[size]        : "+ res.getItems().size());

        log.info(">>>> SearchRankingService reverseRangeWithScores() end.");
    }
}