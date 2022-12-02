package jambo.dto;

import jambo.domain.user.Icon;
import jambo.domain.user.IconShop;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IconShopDTO {
    private String name;

    private String description;

    private int price;

    public IconShop toEntity() {
        return new IconShop(name, description, price);
    }
}
