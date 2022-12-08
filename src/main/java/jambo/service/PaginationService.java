package jambo.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PaginationService {

    private final static int BLOCK_SIZE = 5;

    public List<Integer> pagination(int currentPageNumber, int totalPageCount) {
        int startPage = Math.max(currentPageNumber - (BLOCK_SIZE / 2), 0);
        int endPage = Math.min(startPage + BLOCK_SIZE, totalPageCount);

        return IntStream.range(startPage, endPage)
                .boxed()
                .collect(Collectors.toList());
    }
}
