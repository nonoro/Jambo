package jambo.domain.user;

import jambo.domain.TechStack;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTechStack {
    @Id
    @Column(name = "user_tech_join_id")
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_stack_id")
    private TechStack techStack;

    public UserTechStack(TechStack techStack) {
        this.techStack = techStack;
    }

    public static List<UserTechStack> of(List<TechStack> techStacks) {
        return techStacks.stream().map(UserTechStack::new).collect(Collectors.toList());
    }

    public void setUser(User user) {
        this.user = user;
    }
}
