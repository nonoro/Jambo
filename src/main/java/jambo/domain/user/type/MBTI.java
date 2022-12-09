package jambo.domain.user.type;

public enum MBTI {
    ISTJ,
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
    NOTHING;

    public static MBTI mapping(String mbti) {
        return MBTI.valueOf(mbti.toUpperCase());
    }
}
