package com.hailey.search.api.service;

import com.hailey.search.api.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
public class SearchBlogServiceTest {
    @Autowired
    SearchBlogService service;

    @Test
    @DisplayName("[카카오] 키워드는 필수 입력 값입니다.")
    public void kakaoSearchBlogByKeywordTest_EmpKeyword() {
        log.info(">>>> SearchBlogService kakaoSearchBlogByKeyword() start.");
        GetKakaoSearchBlogParam param = new GetKakaoSearchBlogParam().builder()
                .keyword("")
                .sort("accuracy")
                .page(1)
                .size(10)
                .build();
        try {
            service.kakaoSearchBlogByKeyword(param);
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }

        log.info(">>>> SearchBlogService kakaoSearchBlogByKeyword() End.");
    }

    @Test
    @DisplayName("[카카오] 키워드는 필수 입력 값입니다.")
    public void kakaoSearchBlogByKeywordTest_ExKeyword() {
        log.info(">>>> SearchBlogService kakaoSearchBlogByKeyword() start.");
        GetKakaoSearchBlogParam param = new GetKakaoSearchBlogParam().builder()
                .sort("accuracy")
                .page(1)
                .size(10)
                .build();

        try {
            service.kakaoSearchBlogByKeyword(param);
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }

        log.info(">>>> SearchBlogService kakaoSearchBlogByKeyword() End.");
    }


    @Test
    @DisplayName("[카카오] 검색 결과에서 정확도 순으로 정렬합니다.")
    public void kakaoSearchBlogByKeywordTest_Accuracy() {
        log.info(">>>> SearchBlogService kakaoSearchBlogByKeyword() start.");
        GetKakaoSearchBlogParam param = new GetKakaoSearchBlogParam().builder()
                .keyword("test")
                .sort("accuracy")
                .page(1)
                .size(10)
                .build();
        log.info("param     : "+param.toString());

        var res = service.kakaoSearchBlogByKeyword(param);
        log.info("response totalCount   : " + res.getTotalCount());
        log.info("response count        : " + res.getCount());
        log.info("response size         : " + res.getSize());
        log.info("response page         : " + res.getPage());
        log.info("response isEnd        : " + res.getIsEnd());
        //log_(param, service.kakaoSearchBlogByKeyword(param));
        log.info(">>>> SearchBlogService kakaoSearchBlogByKeyword() End.");
    }

    @Test
    @DisplayName("[카카오] 검색 결과에서 최근 순으로 정렬합니다.")
    public void kakaoSearchBlogByKeywordTest_Recency() {
        log.info(">>>> SearchBlogService kakaoSearchBlogByKeyword() start.");
        GetKakaoSearchBlogParam param = new GetKakaoSearchBlogParam().builder()
                .keyword("test")
                .sort("recency")
                .page(1)
                .size(10)
                .build();
        log.info("param         : "+param.toString());

        var res = service.kakaoSearchBlogByKeyword(param);
        log.info("");
        log.info("items         : " + res.getItems());
        log.info("totalCount    : " + res.getTotalCount());
        log.info("count         : " + res.getCount());
        log.info("size          : " + res.getSize());
        log.info("page          : " + res.getPage());
        log.info("isEnd         : " + res.getIsEnd());

        log.info(">>>> SearchBlogService kakaoSearchBlogByKeyword() End.");
    }

    @Test
    @DisplayName("[카카오] 특정 블로그에서 키워드를 검색합니다.")
    public void kakaoSearchBlogByKeywordTest_SpecificBlog() {
        log.info(">>>> SearchBlogService kakaoSearchBlogByKeyword() start.");
        GetKakaoSearchBlogParam param = new GetKakaoSearchBlogParam().builder()
                .keyword("http://dev-whoan.xyz test")
                .sort("accuracy")
                .page(1)
                .size(2)
                .build();
        log.info("param         : "+param.toString());

        var res = service.kakaoSearchBlogByKeyword(param);
        log.info("");
        log.info("items         : " + res.getItems());
        log.info("totalCount    : " + res.getTotalCount());
        log.info("count         : " + res.getCount());
        log.info("size          : " + res.getSize());
        log.info("page          : " + res.getPage());
        log.info("isEnd         : " + res.getIsEnd());

        log.info(">>>> SearchBlogService kakaoSearchBlogByKeyword() End.");
    }

    @Test
    @DisplayName("[네이버] 검색 결과에서 정확도 순으로 정렬합니다.")
    public void NaverSearchBlogByKeywordTest_Sim() {
        log.info(">>>> SearchBlogService naverSearchBlogByKeyword() start.");
        GetNaverSearchBlogParam param = new GetNaverSearchBlogParam().builder()
                .keyword("test")
                .sort("sim")
                .display(10)
                .start(1)
                .build();
        log.info("param                 : "+param.toString());

        var res = service.naverSearchBlogByKeyword(param);
        log.info("");
        log.info("items                 : " + res.getItems());
        log.info("response totalCount   : " + res.getTotalCount());
        log.info("response count        : " + res.getCount());
        log.info("response size         : " + res.getSize());
        log.info("response page         : " + res.getPage());
        log.info("response isEnd        : " + res.getIsEnd());

        log.info(">>>> SearchBlogService naverSearchBlogByKeyword() End.");
    }
}