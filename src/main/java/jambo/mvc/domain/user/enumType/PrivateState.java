package jambo.mvc.domain.user.enumType;

public enum PrivateState {
    OPEN("동의"),
    CLOSE("비동의");

    private final String name;

    PrivateState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
