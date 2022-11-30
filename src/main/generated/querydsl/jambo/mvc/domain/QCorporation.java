package jambo.mvc.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import jambo.mvc.domain.user.Corporation;


/**
 * QCorporation is a Querydsl query type for Corporation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCorporation extends EntityPathBase<Corporation> {

    private static final long serialVersionUID = 2050074045L;

    public static final QCorporation corporation = new QCorporation("corporation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> joinDate = createDateTime("joinDate", java.time.LocalDateTime.class);

    public final StringPath mainIcon = createString("mainIcon");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public QCorporation(String variable) {
        super(Corporation.class, forVariable(variable));
    }

    public QCorporation(Path<? extends Corporation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCorporation(PathMetadata metadata) {
        super(Corporation.class, metadata);
    }

}

