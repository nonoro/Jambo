package jambo.mvc.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import jambo.mvc.domain.board.ImgFile;


/**
 * QImgFile is a Querydsl query type for ImgFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImgFile extends EntityPathBase<ImgFile> {

    private static final long serialVersionUID = -232353772L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QImgFile imgFile = new QImgFile("imgFile");

    public final QBoard board;

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QImgFile(String variable) {
        this(ImgFile.class, forVariable(variable), INITS);
    }

    public QImgFile(Path<? extends ImgFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QImgFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QImgFile(PathMetadata metadata, PathInits inits) {
        this(ImgFile.class, metadata, inits);
    }

    public QImgFile(Class<? extends ImgFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

