package jambo.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class LoginDTO {
    private String email;
    private String password;
}
