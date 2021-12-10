package patterns.template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.concurrent.Callable;
@Slf4j
public class TemplateUtil {

    public static <T> T call(Callable<T> callable, Object... params) {
        try {
           T response = callable.call();
           log.info("result:{}",response);
           return response;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
    public static <T> T call(Callable<T> callable) {
        try {
            T response = callable.call();
            log.info("result:{}",response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
