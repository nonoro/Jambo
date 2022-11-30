package jambo.mvc.domain.board.enumType;

public enum StaffState {
    RECRUITING("모집중"),
    RECRUITING_COMPLETE("모집완료");

    private final String name;

    StaffState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
