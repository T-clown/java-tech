package utils;

import org.apache.commons.lang.time.FastDateFormat;

public class DateUtil {
    private static final String DATE_FORMAT_PATTEN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式化日期工具
     */
    public static final FastDateFormat DATE_FORMAT_TOOL = FastDateFormat.getInstance(DATE_FORMAT_PATTEN);
}
