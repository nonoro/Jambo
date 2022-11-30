package jambo.mvc.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTechStack is a Querydsl query type for TechStack
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTechStack extends EntityPathBase<TechStack> {

    private static final long serialVersionUID = -2015122297L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTechStack techStack = new QTechStack("techStack");

    public final QBoard board;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<TechStackType> techStackType = createEnum("techStackType", TechStackType.class);

    public final QUser user;

    public QTechStack(String variable) {
        this(TechStack.class, forVariable(variable), INITS);
    }

    public QTechStack(Path<? extends TechStack> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTechStack(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTechStack(PathMetadata metadata, PathInits inits) {
        this(TechStack.class, metadata, inits);
    }

    public QTechStack(Class<? extends TechStack> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

