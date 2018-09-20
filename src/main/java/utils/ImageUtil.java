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
