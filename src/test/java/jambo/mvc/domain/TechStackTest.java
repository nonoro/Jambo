package jambo.mvc.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TechStackTest {

    @Test
    void stringToObject() {
        List<String> stacks = List.of("Java", "Spring");

        List<TechStack> techStacks = TechStack.of(stacks);

        assertThat(techStacks).hasSize(2);
    }
}
