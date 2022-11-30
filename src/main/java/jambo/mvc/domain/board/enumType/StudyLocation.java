package jambo.mvc.domain.board.enumType;

public enum StudyLocation {
    ONLINE("온라인"),
    OFLINE("오프라인");

    private final String name;

    StudyLocation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
