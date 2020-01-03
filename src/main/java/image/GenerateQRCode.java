package image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import static com.google.zxing.client.j2se.MatrixToImageConfig.BLACK;
import static com.google.zxing.client.j2se.MatrixToImageConfig.WHITE;

public class GenerateQRCode {
    private static final MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
    private final static int WIDTH = 500;
    private final static int HEIGHT = 500;
    private final static String EXTENSION = "png";

    public static void main(String[] args) {
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + EXTENSION;
        String content
            = "http://h5-s2.yit.com/product.html?product_id=3514&_spm=1.2968.26771.0.pr-35103";
        File file = new File("/Users/ti93/tmp/" + fileName);
        //Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        //hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //hints.put(EncodeHintType.MARGIN, 0);
        // ByteArrayOutputStream outputStream = null;
        //byte[] file = null;
        try {
            //encode(content, file1, EXTENSION, BarcodeFormat.QR_CODE, 500, 500, null);
            //BitMatrix matrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, WIDTH, WIDTH, hints);
            //outputStream = new ByteArrayOutputStream();
            // MatrixToImageWriter.writeToStream(matrix, EXTENSION, outputStream);
            // file = outputStream.toByteArray();
            BitMatrix matrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
            BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < WIDTH; y++) {
                    bufferedImage.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
                }
            }
            ImageIO.write(bufferedImage, EXTENSION, file);
        } catch (IOException | WriterException e) {

        } //finally {
        //    if (outputStream != null) {
        //        try {
        //            outputStream.close();
        //        } catch (IOException e) {
        //        }
        //    }
        // }

    }

    public static void encode(String contents, File file, String filePostfix, BarcodeFormat format, int width,
                              int height, Map<EncodeHintType, ?> hints) {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, format, width, height);
            BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < 500; x++) {
                for (int y = 0; y < 500; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
                }
            }
            ImageIO.write(image, filePostfix, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
