package jambo.mvc.domain;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TechStackType {
    JAVA_SCRIPT("JavaScript"),
    TYPE_SCRIPT("TypeScript"),
    REACT("React"),
    VUE("Vue"),
    NODE_JS("NodeJs"),
    SPRING("Spring"),
    JAVA("Java"),
    NEXT_JS("NextJs"),
    NEST_JS("NestJs"),
    EXPRESS("Express"),
    GO("Go"),
    C("C"),
    PYTHON("Python"),
    DJANGO("Django"),
    SWIFT("Swift"),
    KOTLIN("Kotlin"),
    MY_SQL("MySql"),
    MONGO_DB("MongoDB"),
    PHP("PHP"),
    GRAPHQL("GraphQL"),
    FIREBASE("Firebase"),
    REACTNATIVE("ReactNative"),
    UNITY("Unity"),
    FLUTTER("Flutter"),
    AWS("AWS"),
    KUBERNETES("Kubernetes"),
    DOCKER("Docker"),
    GIT("Git"),
    FIGMA("Figma"),
    ZEPLIN("Zeplin");

    private final String name;

    TechStackType(String name) {
        this.name = name;
    }

    public static List<TechStackType> mapping(List<String> techStacks) {
        return Stream.of(TechStackType.values())
                .filter(type -> techStacks.contains(type.getName()))
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }
}
