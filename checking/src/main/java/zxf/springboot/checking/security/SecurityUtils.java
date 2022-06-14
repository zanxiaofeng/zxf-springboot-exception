package zxf.springboot.checking.security;

import java.util.Arrays;
import java.util.List;

public class SecurityUtils {
    public static Boolean isAdminOfCurrentUser() {
        //Faked data
        return true;
    }

    public static List<String> rightsOfCurrentUser() {
        //Faked data
        return Arrays.asList("1", "2", "3", "4", "5");
    }
}
