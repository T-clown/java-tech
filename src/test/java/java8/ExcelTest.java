package java8;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.junit.Test;
import util.DateUtil;

public class ExcelTest {

    @Test
    public void demo(){
        LocalDate firstDayOfThisMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfThisMonth);

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = firstDayOfThisMonth.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        System.out.println(date);
        System.out.println(date.getTime());

    }
}
