package zxf.springboot.checking.spel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import zxf.springboot.checking.security.SecurityUtils;

import java.util.Arrays;

@Slf4j
public class SpelExtensions {
    public static Boolean isAdmin() {
        Boolean result = SecurityUtils.isAdminOfCurrentUser();
        log.info("isAdmin(), {}", result);
        return result;
    }

    public static Boolean hasRight(String right) {
        Boolean result = SecurityUtils.rightsOfCurrentUser().contains(right);
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
