package jambo.mvc.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import jambo.mvc.domain.user.IconShop;


/**
 * QIconShop is a Querydsl query type for IconShop
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIconShop extends EntityPathBase<IconShop> {

    private static final long serialVersionUID = 1367180666L;

    public static final QIconShop iconShop = new QIconShop("iconShop");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public QIconShop(String variable) {
        super(IconShop.class, forVariable(variable));
    }

    public QIconShop(Path<? extends IconShop> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIconShop(PathMetadata metadata) {
        super(IconShop.class, metadata);
    }

}

