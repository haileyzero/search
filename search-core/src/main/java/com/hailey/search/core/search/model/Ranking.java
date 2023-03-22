package com.hailey.search.core.search.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity(name = "RANKING")
@Table(name="RANKING")
public class Ranking {
    @Id
    @Column(name = "KEYWORD")
    private String keyword;

    @Builder.Default
    @Setter
    @Column(name = "SCORE")
    private Integer score = 0;
}
