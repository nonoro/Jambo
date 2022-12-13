package jambo.domain.board.type;

import java.util.stream.Stream;

public enum Category {
    STUDY_BOARD("스터디게시판"),
    FREE_BOARD("자유게시판"),
    NOTICE_BOARD("공지사항"),
    EVENT_BOARD("행사게시판"),
    CONTEST_BOARD("소작모게시판"),
    INFORMATION_BOARD("정보게시판"),
    NOTHING("없음");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category mapping(String category) {
        return Stream.of(Category.values()).filter(s -> s.getName().equals(category)).findFirst().orElse(Category.NOTHING);
    }
}
