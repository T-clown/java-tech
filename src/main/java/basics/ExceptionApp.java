package basics;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
public class ExceptionApp {

    public static void main(String[] args) {
        try {
            int a = 1;
            int b = a / 0;
        } catch (Exception e) {
//            StringWriter stringWriter = new StringWriter();
//            e.printStackTrace(new PrintWriter(stringWriter));
//            String errorMsg = stringWriter.toString();
//            log.info(errorMsg);
            e.printStackTrace();
        }
    }


}
