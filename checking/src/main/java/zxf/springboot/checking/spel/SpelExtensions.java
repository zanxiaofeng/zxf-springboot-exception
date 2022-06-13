package zxf.springboot.checking.spel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class SpelExtensions {
    public static Boolean isAdmin() {
        Boolean result = true;
        log.info("isAdmin(), {}", result);
        return result;
    }

    public static Boolean hasRight(String right) {
        Boolean result = myRights().contains(right);
        log.info("hasRight({}), {}", right, result);
        return result;
    }

    public static Boolean hasRights(String... rights) {
        Boolean result = Arrays.stream(rights).allMatch(SpelExtensions::hasRight);
        log.info("hasRights({}), {}", String.join(", ", rights), result);
        return result;
    }

    public static Boolean evaluate(String express) throws Exception {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.registerFunction("isAdmin", SpelExtensions.class.getDeclaredMethod("isAdmin"));
        context.registerFunction("hasRight", SpelExtensions.class.getDeclaredMethod("hasRight", String.class));
        context.registerFunction("hasRights", SpelExtensions.class.getDeclaredMethod("hasRights", String[].class));
        Boolean result = new SpelExpressionParser().parseExpression(express).getValue(context, Boolean.class);
        log.info("evaluate({}), {}", express, result);
        return result;
    }

    private static List<String> myRights() {
        return Arrays.asList("1", "2", "3", "4", "5");
    }

    public static void main(String[] args) throws Exception {
        SpelExtensions.evaluate("#isAdmin()");
        SpelExtensions.evaluate("#hasRight('1')");
        SpelExtensions.evaluate("#hasRight('9')");
        SpelExtensions.evaluate("#hasRights('1','2')");
        SpelExtensions.evaluate("#hasRights('9','8')");
        SpelExtensions.evaluate("#hasRights('9','8')");
        SpelExtensions.evaluate("#isAdmin() and #hasRights('1','2') and (#hasRight('2') or #hasRight('9'))");
        SpelExtensions.evaluate("#isAdmin() and #hasRights('1','2') and (#hasRight('8') or #hasRight('9'))");
    }
}
