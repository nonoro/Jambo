package jambo.mvc.domain.board.enumType;

public enum SupportMethod {
    OPEN_KAKAO_TALK("오픈카톡"),
    NAVER_FORM("네이버폼"),
    EMAIL("이메일");

    private final String name;

    SupportMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
