package com.hailey.search.core.search.persistence;

import com.hailey.search.core.search.model.QRanking;
import com.hailey.search.core.search.model.Ranking;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CustomizedSearchRankingRepositoryImpl extends QuerydslRepositorySupport implements CustomizedSearchRankingRepository {
    public CustomizedSearchRankingRepositoryImpl() {
        super(Ranking.class);
    }

    private QRanking ranking = QRanking.ranking;
}
