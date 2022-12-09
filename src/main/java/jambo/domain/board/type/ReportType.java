package jambo.domain.board.type;

import java.util.stream.Stream;

public enum ReportType {
    SWEARS("욕설"),
    DEFAMATION("명예훼손"),
    SMUTS("음란물"),
    ADVERTISEMENT("광고"),
    OTHERS("기타");

    private final String name;

    ReportType(String name){
        this.name = name;}

    private String getName(){
        return name;
    }

    public static ReportType mapping(String reportType){
        return Stream.of(ReportType.values()).filter(s -> s.getName().equals(reportType)).findFirst().orElse(ReportType.OTHERS);
    }
}
