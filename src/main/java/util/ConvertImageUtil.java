package util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
public class ConvertImageUtil {
    private static final String PPT = "ppt";
    private static final String PPTX = "pptx";
    private static final String PDF = "pdf";


//    public static List<ImageBO> convertPptxToImg(String fileUrl) {
//        List<ImageBO> images = Lists.newArrayList();
//        if (StringUtils.isNotBlank(fileUrl)) {
//            try (InputStream inputStream = new URL(fileUrl).openStream(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
//                XMLSlideShow pptx = new XMLSlideShow(inputStream);
//                Dimension pgSize = pptx.getPageSize();
//                List<XSLFSlide> slides = pptx.getSlides();
//                String imageFormat = "png";
//                String prefix = "image";
//                for (int i = 0; i < slides.size(); ++i) {
//                    // 字体
//                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//                    Font font = Font.createFont(Font.TRUETYPE_FONT, new File(PPT_FONT_FILE_NAME));
//                    ge.registerFont(font);
//                    // 创建图片
//                    BufferedImage img = new BufferedImage(pgSize.width, pgSize.height, BufferedImage.TYPE_INT_RGB);
//                    Graphics2D graphics = img.createGraphics();
//                    graphics.setPaint(Color.white);
//                    graphics.fill(new Rectangle2D.Float(0, 0, pgSize.width, pgSize.height));
//                    // 画图存临时文件
//                    slides.get(i).draw(graphics);
//                    String fileName = getImageName(prefix, i);
//                    javax.imageio.ImageIO.write(img, imageFormat, outputStream);
//                    pptx.write(outputStream);
//                    // 把图片上传到cos
//                    String uploadFileName = BooleanUtils.isTrue(privacy) ? fileName : COS_KEY_PUBLIC_PREFIX + fileName;
//                    CosUtil.upload(outputStream.toByteArray(), uploadFileName);
//                    outputStream.reset();
//                    images.add(new ImageBO(uploadFileName, null));
//                }
//            } catch (Exception e) {
//                LogUtil.error(log, "Failed to convert ppt into images", e);
//                throw new SystemException(AiCoachStatusCode.PPT_PARSE_FAILED, "ppt file parse error", e);
//            }
//        }
//        return images;
//    }
//
//
//    public static List<ImageBO> convertPptToImg(String fileUrl, Boolean privacy) {
//        List<ImageBO> images = Lists.newArrayList();
//        if (StringUtils.isNotBlank(fileUrl)) {
//            try (InputStream inputStream = new URL(fileUrl).openStream(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
//                HSLFSlideShow ppt = new HSLFSlideShow(inputStream);
//                Dimension pgSize = ppt.getPageSize();
//                List<HSLFSlide> slides = ppt.getSlides();
//                String imageFormat = "png";
//                String prefix = BusinessContextHolder.getTenantId() + "_"
//                        + IdUtil.generateId();
//                for (int i = 0; i < slides.size(); ++i) {
//                    // 字体
//                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//                    Font font = Font.createFont(Font.TRUETYPE_FONT, new File(PPT_FONT_FILE_NAME));
//                    ge.registerFont(font);
//                    // 创建图片
//                    BufferedImage img = new BufferedImage(pgSize.width, pgSize.height, BufferedImage.TYPE_INT_RGB);
//                    Graphics2D graphics = img.createGraphics();
//                    graphics.setPaint(Color.white);
//                    graphics.fill(new Rectangle2D.Float(0, 0, pgSize.width, pgSize.height));
//                    // 画图存临时文件
//                    slides.get(i).draw(graphics);
//                    String fileName = getImageName(prefix, i);
//                    javax.imageio.ImageIO.write(img, imageFormat, outputStream);
//                    ppt.write(outputStream);
//                    // 把图片上传到cos
//                    String uploadFileName = BooleanUtils.isTrue(privacy) ? fileName : COS_KEY_PUBLIC_PREFIX + fileName;
//                    CosUtil.upload(outputStream.toByteArray(), uploadFileName);
//                    outputStream.reset();
//                    images.add(new ImageBO(uploadFileName, null));
//                }
//            } catch (Exception e) {
//                LogUtil.error(log, "Failed to convert ppt into images", e);
//                throw new SystemException(AiCoachStatusCode.PPT_PARSE_FAILED, "ppt file parse error", e);
//            }
//        }
//        return images;
//    }
//
    public static void convertPdf2Png(String fileUrl, String prefix) {
        if (StringUtils.isNotBlank(fileUrl)) {
            String imageFormat = "png";
            try (PDDocument document = Loader.loadPDF(new File(fileUrl))) {
                PDFRenderer pdfRenderer = new PDFRenderer(document);
                PDPageTree pdPageTree = document.getPages();
                int index = 0;
                for (PDPage ignored : pdPageTree) {
                    BufferedImage bim = pdfRenderer.renderImageWithDPI(index, 500, ImageType.RGB);
                    String fileName = getImageName(prefix, ++index);
                    javax.imageio.ImageIO.write(bim, imageFormat, new File(fileName));
                }
            } catch (IOException e) {
            }
        }
    }

    public static void main(String[] args) {
        String fileUrl="/Users/hrtps/Desktop/726f0309-f3bc-4837-80c2-777ed7005697.pdf";
        String prefix="/Users/hrtps/Desktop/image/";
        convertPdf2Png(fileUrl,prefix);
    }

    private static String getImageName(String prefix, int index) {
        return prefix + "_" + index + ".png";
    }


    public static String getSuffix(String fileUrl) {
        if (StringUtils.isBlank(fileUrl)) {
            return null;
        }
        return fileUrl.substring(fileUrl.lastIndexOf(".") + 1);
    }
}
