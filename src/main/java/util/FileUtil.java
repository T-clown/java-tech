package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class FileUtil {

    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static String getPath() {
        return FileUtil.class.getResource("/").getPath();
    }

    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }


    /**
     * 上传本地图片
     *
     * @return
     */
    public static String uploadLocalImg() {
        String imgPath = "C:\\Users\\YCKJ2717\\Desktop\\table\\user-head-woman.png";
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return upload(Base64.encodeBase64String(data));
        return null;
    }

    /**
     * 上传网络图片
     *
     * @param imgUrl
     * @return
     */
    public static String imgBase64(String imgUrl) {
        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(imgUrl);
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10 * 1000);

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {

            }
            InputStream inStream = conn.getInputStream();
            int len;
            byte[] data = new byte[1024];
            while ((len = inStream.read(data)) != -1) {
                outPut.write(data, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        return Base64.encodeBase64String(outPut.toByteArray());
    }

    public static String getBase64File(String base64) {
        String[] content = base64.split(",");
        if (content.length == 2) {
            base64 = content[content.length - 1];
            return base64;
        }
        return base64;
    }

    public static InputStream base64ToInputStream(String base64) {
        try {
            if (StringUtils.isNotEmpty(base64)) {
                return new ByteArrayInputStream(Base64.decodeBase64(getBase64File(base64)));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static String inputStream2base64(InputStream is) {
        try {
            byte[] bs = IOUtils.toByteArray(is);
            return Base64.encodeBase64String(bs);
        } catch (IOException e) {
            return null;
        }
    }

    public static String url2base64(String url) {
        return inputStream2base64(download(url));
    }

    public static InputStream download(String url) {
        try {
            URL u = new URL(url);
            return u.openStream();
        } catch (Exception e) {
            return null;
        }
    }

}
