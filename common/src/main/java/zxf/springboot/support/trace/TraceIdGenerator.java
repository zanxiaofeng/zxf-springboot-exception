package zxf.springboot.support.trace;

public class TraceIdGenerator {
    public static String generateTraceId(String preffix) {
        return String.format("%s_%s", preffix, "1232423423");
    }
}
