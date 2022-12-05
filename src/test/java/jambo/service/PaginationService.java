package jambo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PaginationService {

    @DisplayName("페이지 테스트")
    @MethodSource
    @ParameterizedTest(name = "현재 페이지 {0} 이고 총 페이지 개수가 {1} 인 경우 {2} 이다.")
    void paginationTest(int currentPageNumber, int totalPageCount, List<Integer> expected) {
        int blockSize = 3;

        int startPage = Math.max(currentPageNumber - (blockSize / 2), 0);
        int endPage = Math.min(startPage + blockSize, totalPageCount);

        List<Integer> result = IntStream.range(startPage, endPage)
                .boxed()
                .collect(Collectors.toList());

        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> paginationTest() {
        return Stream.of(
                arguments(0, 10, List.of(0, 1, 2)),
                arguments(1, 10, List.of(0, 1, 2)),
                arguments(2, 10, List.of(1, 2, 3)),
                arguments(3, 10, List.of(2, 3, 4)),
                arguments(5, 10, List.of(4, 5, 6)),
                arguments(9, 10, List.of(8, 9))
        );
    }
}
