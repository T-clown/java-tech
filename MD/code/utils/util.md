package utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheUtil {
    private static final Cache<Integer, String> CACHE = CacheBuilder
        .newBuilder()
        .maximumSize(50)
        .expireAfterWrite(30, TimeUnit.SECONDS)
        .build();

    public static void main(String[] args) {

        String a = null;
        try {
            a = CACHE.get(1, () -> {
                String str = "获取";
                return str + "缓存数据";
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(a);
    }
}
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
package utils;

public class Constants {
    public static String imgUrl1 = "https://asset.yit.com/SDP/SHOP/bae4fa0dd0d78dc926ef6bd90a93d7e5_460X760.jpeg";
    public static String imgUrl2 = "https://asset.yit.com/SDP/SHOP/49f198ab4d1b88789760a0303687f5a7_920X1520.jpeg";
}
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
package utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

public class DateTImeUtil {

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");
    public static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");
    public static final DateTimeFormatter SHORT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");
    public static final DateTimeFormatter SHORT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmmss");
    public static final DateTimeFormatter DATETIME_FORMATTER =  DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 返回当前的日期
     * @return
     */
    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    /**
     * 返回当前时间
     * @return
     */
    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    /**
     * 返回当前日期时间
     * @return
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * yyyyMMdd
     *
     * @return
     */
    public static String getCurrentDateStr() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * yyMMdd
     *
     * @return
     */
    public static String getCurrentShortDateStr() {
        return LocalDate.now().format(SHORT_DATE_FORMATTER);
    }

    public static String getCurrentMonthStr() {
        return LocalDate.now().format(MONTH_FORMATTER);
    }

    /**
     * yyyyMMddHHmmss
     * @return
     */
    public static String getCurrentDateTimeStr() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }

    /**
     * yyMMddHHmmss
     * @return
     */
    public static String getCurrentShortDateTimeStr() {
        return LocalDateTime.now().format(SHORT_DATETIME_FORMATTER);
    }

    /**
     * HHmmss
     * @return
     */
    public static String getCurrentTimeStr() {
        return LocalTime.now().format(TIME_FORMATTER);
    }

    public static String getCurrentDateStr(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getCurrentDateTimeStr(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getCurrentTimeStr(String pattern) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate parseLocalDate(String dateStr, String pattern) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parseLocalDateTime(String dateTimeStr, String pattern) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalTime parseLocalTime(String timeStr, String pattern) {
        return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalDateTime(LocalDateTime datetime, String pattern) {
        return datetime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalTime(LocalTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate parseLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    public static LocalDateTime parseLocalDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
    }

    public static LocalTime parseLocalTime(String timeStr) {
        return LocalTime.parse(timeStr, TIME_FORMATTER);
    }

    public static String formatLocalDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    public static String formatLocalDateTime(LocalDateTime datetime) {
        return datetime.format(DATETIME_FORMATTER);
    }

    public static String formatLocalTime(LocalTime time) {
        return time.format(TIME_FORMATTER);
    }

    /**
     * 日期相隔天数
     * @param startDateInclusive
     * @param endDateExclusive
     * @return
     */
    public static int periodDays(LocalDate startDateInclusive, LocalDate endDateExclusive) {
        return Period.between(startDateInclusive, endDateExclusive).getDays();
    }

    /**
     * 日期相隔小时
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static long durationHours(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toHours();
    }

    /**
     * 日期相隔分钟
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static long durationMinutes(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toMinutes();
    }

    /**
     * 日期相隔毫秒数
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static long durationMillis(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toMillis();
    }

    /**
     * 是否当天
     * @param date
     * @return
     */
    public static boolean isToday(LocalDate date) {
        return getCurrentLocalDate().equals(date);
    }

    public static Long toEpochMilli(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
package utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.lang3.time.DateFormatUtils;

public class DateUtil {
    private static final String DATE_FORMAT_PATTEN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式化日期工具
     */
    public static final FastDateFormat FAST_DATE_FORMAT = FastDateFormat.getInstance(DATE_FORMAT_PATTEN);

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTEN );


    /**
     * 获得某天最大时间 2018-11-22 23:59:59
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获得某天最小时间 2018-11-22 00:00:00
     * @param date
     * @return
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) {
        LocalDateTime dateTime=LocalDateTime.now();
        Date date=new Date();
        System.out.println(FAST_DATE_FORMAT.format(date));
        System.out.println(DATE_TIME_FORMATTER.format(dateTime));
        System.out.println(DateFormatUtils.format(date,DATE_FORMAT_PATTEN));
    }

}
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
package utils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;

import com.google.common.collect.Lists;
import entity.WriteModel;

public class ExcelUtil {
    public static void main(String[] args) {
        try {
            writeExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void writeExcel() throws Exception {
        //文件输出位置
        OutputStream os=new FileOutputStream("D:\\test.xlsx");
        ExcelWriter  writer= EasyExcelFactory.getWriter(os);

        Sheet sheet=new Sheet(1,0, WriteModel.class);
        sheet.setSheetName("第一个sheet");
        //写数据到Writer上下文
        //入参1：创建要写入的数据模型
        //入参2：要写入的目标sheet
        writer.write(createModelList(),sheet);
        //将上下文总的最终outputStream写入到制定文件中
        writer.finish();;
        //关闭流
        os.close();
    }
    private static List<WriteModel> createModelList(){
        List<WriteModel> list= Lists.newArrayList();
        for (int i=0;i<20;i++){
            list.add(new WriteModel("战三"+i,"aaa"+i,i));
        }
        return list;
    }
}
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpRequester {
    static CloseableHttpClient httpClient = HttpClients.createDefault();

    String url = null;
    Method method = Method.GET;
    String postBody = null;
    Map<String, Object> params = new HashMap<>();
    Map<String, Object> headers = new HashMap<>();

    public static HttpRequester newRequest() {
        return new HttpRequester();
    }

    public HttpRequester setHeader(String key, Object value) {
        headers.put(key, value);
        return this;
    }

    public HttpRequester setParams(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public HttpRequester setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpRequester setMethod(Method method) {
        this.method = method;
        return this;
    }

    public HttpRequester setPostBody(String body) {
        this.postBody = body;
        return this;
    }

    public enum Method {
        GET,
        POST
    }

    /**
     * 下载数据
     */
    public byte[] downloadBytes() {

        // 构造完整url
        String paramPart = String.join("&", params.entrySet()
            .stream()
            .map((x) -> x.getKey() + "=" + x.getValue().toString())
            .collect(Collectors.toList()));
        if (!StringUtils.isBlank(paramPart)) {
            url += '?' + paramPart;
        }

        HttpRequestBase httpRequest = method == Method.GET ? new HttpGet(url) : new HttpPost(url);

        // 设置Header
        headers.entrySet()
            .forEach(x -> httpRequest.setHeader(x.getKey(), x.getValue().toString()));

        // 设置Post body
        if (method == Method.POST && postBody != null) {
            ((HttpPost)httpRequest).setEntity(new StringEntity(postBody, "utf-8"));
        }

        return execute(httpRequest);
    }

    /**
     * 下载文本内容
     */
    public String downloadString() {
        byte[] bytes = downloadBytes();
        String content = new String(bytes);
        return content;
    }

    /**
     * 执行Http请求
     */
    private byte[] execute(HttpUriRequest httpUriRequest) {
        try {
            try (CloseableHttpResponse response = httpClient.execute(httpUriRequest)) {
                HttpEntity entity = response.getEntity();
                int code = response.getStatusLine().getStatusCode();
                if (entity != null && code == 200) {
                    // 读取结果
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    entity.writeTo(stream);
                    return stream.toByteArray();
                }

                String msg = String.format("Failed pulling %s, http code: %d", httpUriRequest.getURI(), code);
                throw new IOException(msg);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
package utils;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtil {
    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * @param data
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
    public static byte[] zoomImage(byte[] data, int width, int height, String suffix) {

        ByteArrayInputStream inputStream = null;
        BufferedImage result = null;
        try {
            inputStream = new ByteArrayInputStream(data);
            BufferedImage im = ImageIO.read(inputStream);
            result = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            result.getGraphics().drawImage(im.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        } catch (IOException e) {
            logger.error("zoomImage failed! ", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("close failed!", e);
                }
            }
        }
        return transferImageToBytes(result, suffix);
    }

    public static byte[] transferImageToBytes(BufferedImage image, String suffix) {
        byte[] result = null;
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            ImageIO.write(image, suffix, out);
            result = out.toByteArray();
        } catch (IOException e) {
            logger.error("transferImageToBytes failed! ", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("close failed!", e);
                }
            }
        }
        return result;
    }

    public static byte[] getImgUrlByteArray(String imgUrl) {
        String replaceUrl = imgUrl.replace("https", "http");
        HttpRequester httpRequester = new HttpRequester();
        httpRequester.setUrl(replaceUrl);
        httpRequester.setMethod(HttpRequester.Method.GET);
        return httpRequester.downloadBytes();
    }

    /**
     * 图片裁剪成1:1
     *
     * @param imgUrl
     * @return
     */
    public static String shopImageCut(String imgUrl) {
        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(ImageUtil.getImgUrlByteArray(imgUrl));
            // 首先通过ImageIo中的方法，创建一个Image + InputStream到内存
            ImageInputStream iis = ImageIO.createImageInputStream(inputStream);
            // 再按照指定格式构造一个Reader（Reader不能new的）
            String suffix = imgUrl.substring(imgUrl.lastIndexOf(".") + 1);
            Iterator it = ImageIO.getImageReadersBySuffix(suffix);
            ImageReader imagereader = (ImageReader)it.next();
            // 再通过ImageReader绑定 InputStream
            imagereader.setInput(iis);
            // 设置感兴趣的源区域。
            ImageReadParam par = imagereader.getDefaultReadParam();
            // 图像宽度
            int width = imagereader.getWidth(0);
            // 图像高度
            int height = imagereader.getHeight(0);
            if (width > height) {
                par.setSourceRegion(new Rectangle((width - height) / 2, 0, height, height));
            } else if (height > width) {
                par.setSourceRegion(new Rectangle(0, (height - width) / 2, width, width));
            } else {
                return imgUrl;
            }
            // 从 reader得到BufferImage
            BufferedImage bi = imagereader.read(0, par);
            // 将BuffeerImage写出通过ImageIO
            ImageIO.write(bi, suffix, new File("/Users/ti93/tmp/666." + suffix));
            //byte[] resultImage = ImageUtil.transferImageToBytes(bi, suffix);
        } catch (Exception e) {
            logger.error("shop image cut failed!", imgUrl, e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("close failed!", e);
                }
            }
        }
        return imgUrl;
    }

    public static byte[] getBufferedImage(byte[] bytes, String suffix) {
        ByteArrayInputStream inputStream = null;
        BufferedImage result = null;
        try {
            inputStream = new ByteArrayInputStream(bytes);
            result = ImageIO.read(inputStream);
            result = setClip(result, 30);
        } catch (IOException e) {
            logger.error("getBufferedImage failed! ", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("close failed!", e);
                }
            }
        }
        return transferImageToBytes(result,suffix);
    }

    /**
     * 图片切圆角
     *
     * @param srcImage
     * @param radius
     * @return
     */
    public static BufferedImage setClip(BufferedImage srcImage, int radius) {
        int width = srcImage.getWidth();
        int height = srcImage.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gs = image.createGraphics();
        gs.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gs.setClip(new RoundRectangle2D.Double(0, 0, width, height, radius, radius));
        gs.drawImage(srcImage, 0, 0, null);
        gs.dispose();
        return image;
    }
}
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
package utils;

import java.util.Collections;

import redis.clients.jedis.Jedis;

public class RedisTool {
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     *
     *
     *
     *  第一个为key，我们使用key来当锁，因为key是唯一的。

        第二个为value，我们传的是requestId，很多童鞋可能不明白，有key作为锁不就够了吗，为什么还要用到value？原因就是我们在上面讲到可靠性时，分布式锁要满足第四个条件解铃还须系铃人，通过给value赋值为requestId，我们就知道这把锁是哪个请求加的了，在解锁的时候就可以有依据。requestId可以使用UUID.randomUUID().toString()方法生成。

        第三个为nxxx，这个参数我们填的是NX，意思是SET IF NOT EXIST，即当key不存在时，我们进行set操作；若key已经存在，则不做任何操作；

        第四个为expx，这个参数我们传的是PX，意思是我们要给这个key加一个过期的设置，具体时间由第五个参数决定。

        第五个为time，与第四个参数相呼应，代表key的过期时间。
        总的来说，执行上面的set()方法就只会导致两种结果：1. 当前没有锁（key不存在），那么就进行加锁操作，并对锁设置个有效期，同时value表示加锁的客户端。2. 已有锁存在，不做任何操作。
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }


    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }
}
