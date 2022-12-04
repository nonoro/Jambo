package jambo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {

    @DisplayName("파일의 확장자 구하기")
    @ParameterizedTest(name = "{0} 파일의 확장자는 {1}입니다.")
    @CsvSource(value = {
            "test.txt:txt",
            "test.txt.exe:exe"
    }, delimiter = ':')
    void getFileExt(String fileName, String ext) {
        String[] split = fileName.split("\\.");

        assertThat(split[split.length - 1]).isEqualTo(ext);
    }
}