package com.hailey.search.core.search.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRanking is a Querydsl query type for Ranking
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRanking extends EntityPathBase<Ranking> {

    private static final long serialVersionUID = -511736859L;

    public static final QRanking ranking = new QRanking("ranking");

    public final StringPath keyword = createString("keyword");

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public QRanking(String variable) {
        super(Ranking.class, forVariable(variable));
    }

    public QRanking(Path<? extends Ranking> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRanking(PathMetadata metadata) {
        super(Ranking.class, metadata);
    }

}

