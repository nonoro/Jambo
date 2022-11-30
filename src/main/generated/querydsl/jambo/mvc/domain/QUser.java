package jambo.mvc.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import jambo.mvc.domain.board.Note;
import jambo.mvc.domain.user.*;
import jambo.mvc.domain.user.enumType.MBTI;
import jambo.mvc.domain.user.enumType.PrivateState;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1335801782L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final ListPath<Alarm, QAlarm> alarms = this.<Alarm, QAlarm>createList("alarms", Alarm.class, QAlarm.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final ListPath<Icon, QIcon> icons = this.<Icon, QIcon>createList("icons", Icon.class, QIcon.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> joinDate = createDateTime("joinDate", java.time.LocalDateTime.class);

    public final StringPath mainEmotion = createString("mainEmotion");

    public final EnumPath<MBTI> mbti = createEnum("mbti", MBTI.class);

    public final StringPath name = createString("name");

    public final StringPath nickName = createString("nickName");

    public final ListPath<Note, QNote> notes = this.<Note, QNote>createList("notes", Note.class, QNote.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final QPoint point;

    public final EnumPath<PrivateState> privateState = createEnum("privateState", PrivateState.class);

    public final ListPath<TechStack, QTechStack> techStacks = this.<TechStack, QTechStack>createList("techStacks", TechStack.class, QTechStack.class, PathInits.DIRECT2);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.point = inits.isInitialized("point") ? new QPoint(forProperty("point"), inits.get("point")) : null;
    }

}

