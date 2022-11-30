package jambo.mvc.domain.board.enumType;

import lombok.Getter;

@Getter
public enum BoardState {
    NORMAL_POST("정상게시물"),
    REPORTED_POST("신고된게시물");

    private final String name;

    BoardState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
