package jambo.mvc.domain.user.enumType;

public enum AlarmState {
    READ("읽음"),
    UNREAD("안읽음");

    private final String name;

    AlarmState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
