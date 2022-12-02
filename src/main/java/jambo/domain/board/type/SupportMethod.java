package jambo.domain.board.type;

import java.util.stream.Stream;

public enum SupportMethod {
    OPEN_KAKAO_TALK("오픈카톡"),
    NAVER_FORM("네이버폼"),
    EMAIL("이메일"),
    NOTHING("없음");

    private final String name;

    SupportMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static SupportMethod mapping(String supportMethod) {
        return Stream.of(SupportMethod.values()).filter(s -> s.getName().equals(supportMethod)).findFirst().orElse(SupportMethod.NOTHING);
    }
}
