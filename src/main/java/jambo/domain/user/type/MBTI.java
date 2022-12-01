package jambo.domain.user.type;

public enum MBTI {
    ISFJ,
    ISTP,
    INFJ,
    INTJ,
    ISFP,
    INFP,
    INTP,
    ESTJ,
    ESFP,
    ENFP,
    ENTP,
    ESFJ,
    ESTP,
    ENFJ,
    ENTJ,
    NOTHTING;

    public static MBTI mapping(String mbti) {
        return MBTI.valueOf(mbti.toUpperCase());
    }
}
