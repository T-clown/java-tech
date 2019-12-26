package image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class QRCode {
    public static void main(String[] args) {
        String content = "https://www.cnblogs.com/mark8080/p/7084955.html";
        String qrCode = getQRCode(content);
        System.out.println(qrCode);
    }

    private static final int BLACK = 0xff000000;
    private static final int WHITE = 0xFFFFFFFF;

    /**
     * 仅生成二维码，返回二维码图片网络地址，可直接扫码访问
     *
     * @param address：扫码地址
     * @return
     */
    public static String getQRCode(String address) {
        String filePostfix = "png";
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + filePostfix;
        String physicalPath = "/Users/ti93/tmp/" + fileName;//保存在本地的地址
        File file = new File(physicalPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        encode(address, file, filePostfix, BarcodeFormat.QR_CODE, 500, 500, null);
        decode(file);
        //String netUrl = SysConfig.nginxRootUrl + Globals.BAR  + Globals.BAR + fileName;
        return null;
    }

    /**
     * 生成QRCode二维码<br>
     * 在编码时需要将com.google.zxing.qrcode.encoder.Encoder.java中的<br>
     * static final String DEFAULT_BYTE_MODE_ENCODING = "ISO8859-1";<br>
     * 修改为UTF-8，否则中文编译后解析不了<br>
     *
     * @param contents    二维码的内容
     * @param file        二维码保存的路径，如：C://test_QR_CODE.png
     * @param filePostfix 生成二维码图片的格式：png,jpeg,gif等格式
     * @param format      qrcode码的生成格式
     * @param width       图片宽度
     * @param height      图片高度
     * @param hints
     */
    public static void encode(String contents, File file, String filePostfix, BarcodeFormat format, int width,
                              int height, Map<EncodeHintType, ?> hints) {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, format, width, height);
            writeToFile(bitMatrix, filePostfix, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成二维码图片<br>
     *
     * @param matrix
     * @param format 图片格式
     * @param file   生成二维码图片位置
     * @throws IOException
     */
    public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        ImageIO.write(image, format, file);
    }

    /**
     * 生成二维码内容<br>
     *
     * @param matrix
     * @return
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    /**
     * 解析QRCode二维码
     */
    @SuppressWarnings("unchecked")
    public static void decode(File file) {
        try {
            BufferedImage image;
            try {
                image = ImageIO.read(file);
                if (image == null) {
                    System.out.println("Could not decode image");
                }
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                Result result;
                @SuppressWarnings("rawtypes")
                Hashtable hints = new Hashtable();
                //解码设置编码方式为：utf-8
                hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
                result = new MultiFormatReader().decode(bitmap, hints);
                String resultStr = result.getText();
                System.out.println("解析后内容：" + resultStr);
            } catch (IOException ioe) {
                System.out.println(ioe.toString());
            } catch (ReaderException re) {
                System.out.println(re.toString());
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
