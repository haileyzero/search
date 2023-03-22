package com.hailey.search.api.repository;

import com.hailey.search.core.search.model.Ranking;
import com.hailey.search.core.search.persistence.CustomizedSearchRankingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRankingJpaRepository extends JpaRepository<Ranking, String>, CustomizedSearchRankingRepository {

}