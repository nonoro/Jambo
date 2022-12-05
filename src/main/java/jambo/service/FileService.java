package jambo.service;

import jambo.config.WebConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {

    private final WebConfig webConfig;

    public String upload(MultipartFile file) {
        String fileName = getFileName(file);

        try {
            file.transferTo(new File(webConfig.getFilePath() + "/" + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }

    private String getFileName(MultipartFile file) {
        return String.format("%s.%s", UUID.randomUUID().toString().substring(0, 7), getExt(file));
    }

    private String getExt(MultipartFile file) {
        String[] split = file.getOriginalFilename().split("\\.");
        return split[split.length - 1];
    }

    public String getUrlPath() {
        return webConfig.getUrlPath();
    }
}
