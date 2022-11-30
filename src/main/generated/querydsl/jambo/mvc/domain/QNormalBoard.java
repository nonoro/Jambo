package jambo.mvc.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import jambo.mvc.domain.board.enumType.BoardState;
import jambo.mvc.domain.board.enumType.Category;
import jambo.mvc.domain.board.ImgFile;
import jambo.mvc.domain.board.NormalBoard;


/**
 * QNormalBoard is a Querydsl query type for NormalBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNormalBoard extends EntityPathBase<NormalBoard> {

    private static final long serialVersionUID = -1210695692L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNormalBoard normalBoard = new QNormalBoard("normalBoard");

    public final QBoard _super;

    //inherited
    public final EnumPath<BoardState> boardState;

    //inherited
    public final EnumPath<Category> category;

    //inherited
    public final StringPath content;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final ListPath<ImgFile, QImgFile> imgFiles;

    //inherited
    public final NumberPath<Integer> recommendation;

    //inherited
    public final StringPath title;

    // inherited
    public final QUser user;

    //inherited
    public final NumberPath<Integer> views;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> writeDate;

    public QNormalBoard(String variable) {
        this(NormalBoard.class, forVariable(variable), INITS);
    }

    public QNormalBoard(Path<? extends NormalBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNormalBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNormalBoard(PathMetadata metadata, PathInits inits) {
        this(NormalBoard.class, metadata, inits);
    }

    public QNormalBoard(Class<? extends NormalBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QBoard(type, metadata, inits);
        this.boardState = _super.boardState;
        this.category = _super.category;
        this.content = _super.content;
        this.id = _super.id;
        this.imgFiles = _super.imgFiles;
        this.recommendation = _super.recommendation;
        this.title = _super.title;
        this.user = _super.user;
        this.views = _super.views;
        this.writeDate = _super.writeDate;
    }

}

