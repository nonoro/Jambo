package jambo.mvc.domain;

public enum CommentState {
    NORMAL_COMMENT("정상댓글"),
    REPORTED_COMMENT("신고된댓글");

    private final String name;

    CommentState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
