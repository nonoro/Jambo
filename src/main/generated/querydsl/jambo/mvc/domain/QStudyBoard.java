package jambo.mvc.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import jambo.mvc.domain.board.*;
import jambo.mvc.domain.board.enumType.*;


/**
 * QStudyBoard is a Querydsl query type for StudyBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyBoard extends EntityPathBase<StudyBoard> {

    private static final long serialVersionUID = -391834040L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyBoard studyBoard = new QStudyBoard("studyBoard");

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

    public final NumberPath<Integer> numberOfRecruits = createNumber("numberOfRecruits", Integer.class);

    public final StringPath period = createString("period");

    //inherited
    public final NumberPath<Integer> recommendation;

    public final EnumPath<StaffState> staffState = createEnum("staffState", StaffState.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final EnumPath<StudyLocation> studyLocation = createEnum("studyLocation", StudyLocation.class);

    public final EnumPath<SupportMethod> supportMethod = createEnum("supportMethod", SupportMethod.class);

    public final ListPath<TechStack, QTechStack> techStacks = this.<TechStack, QTechStack>createList("techStacks", TechStack.class, QTechStack.class, PathInits.DIRECT2);

    //inherited
    public final StringPath title;

    // inherited
    public final QUser user;

    //inherited
    public final NumberPath<Integer> views;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> writeDate;

    public QStudyBoard(String variable) {
        this(StudyBoard.class, forVariable(variable), INITS);
    }

    public QStudyBoard(Path<? extends StudyBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyBoard(PathMetadata metadata, PathInits inits) {
        this(StudyBoard.class, metadata, inits);
    }

    public QStudyBoard(Class<? extends StudyBoard> type, PathMetadata metadata, PathInits inits) {
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

