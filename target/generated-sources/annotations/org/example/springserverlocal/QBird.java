package org.example.springserverlocal;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBird is a Querydsl query type for Bird
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBird extends EntityPathBase<Bird> {

    private static final long serialVersionUID = 24071770L;

    public static final QBird bird = new QBird("bird");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> population = createNumber("population", Integer.class);

    public final StringPath tree = createString("tree");

    public QBird(String variable) {
        super(Bird.class, forVariable(variable));
    }

    public QBird(Path<? extends Bird> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBird(PathMetadata metadata) {
        super(Bird.class, metadata);
    }

}

