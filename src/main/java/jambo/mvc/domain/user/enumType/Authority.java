package jambo.mvc.domain.user.enumType;

public enum Authority {
    USER("유저"),
    ADMIN("관리자"),
    CORPARATION("기업");

    private final String name;

    Authority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
