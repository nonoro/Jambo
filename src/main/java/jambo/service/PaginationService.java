package jambo.service;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PaginationService {
    public List<Integer> pagination(int currentPageNumber, int totalPageCount) {
        int blockSize = 5;

        int startPage = Math.max(currentPageNumber - (blockSize / 2), 0);
        int endPage = Math.min(startPage + blockSize, totalPageCount);

        List<Integer> result = IntStream.range(startPage, endPage)
                .boxed()
                .collect(Collectors.toList());
        return result;
    }
}
