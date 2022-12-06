package jambo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.urlPath}")
    private String urlPath;
    @Value("${file.path}")
    private String filePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(urlPath + "/**")
                .addResourceLocations("file:" + filePath);
    }

    public String getFilePath() {
        return filePath;
    }

    public String getUrlPath() {
        return urlPath;
    }
}
