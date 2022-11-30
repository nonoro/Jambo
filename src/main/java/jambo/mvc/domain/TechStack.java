package jambo.mvc.domain;

import jambo.mvc.domain.board.Board;
import jambo.mvc.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TechStack {
    @Id
    @Column(name = "tech_stack_id")
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Enumerated(EnumType.STRING)
    private TechStackType techStackType;

    public TechStack(TechStackType techStackType) {
        this.techStackType = techStackType;
    }

    public static List<TechStack> of(List<String> techStackTypes) {
        return TechStackType.mapping(techStackTypes)
                .stream()
                .map(TechStack::new)
                .collect(Collectors.toList());
    }
}
