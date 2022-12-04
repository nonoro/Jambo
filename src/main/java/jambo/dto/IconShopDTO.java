package jambo.dto;

import jambo.domain.user.Icon;
import jambo.domain.user.IconShop;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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

    private int quantity;

    private MultipartFile file;

    public IconShop toEntity() {
        IconShop iconShop = new IconShop(name, description, price, quantity);
        return iconShop;
    }


}
