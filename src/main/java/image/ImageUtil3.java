package image;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageUtil3 {
    public static void main(String[] args) throws IOException {
        String url = "https://asset-stage.yit.com/sdp_spu_share/3d6e76c0-e1a4-4af1-b69e-3f60a6dc8126.png";
        BufferedImage avatarImage = ImageIO.read(new URL(url));
        int width = 120;
        // 透明底的图片
        BufferedImage formatAvatarImage = new BufferedImage(width, width, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = formatAvatarImage.createGraphics();
        //把图片切成一个圓
        {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //留一个像素的空白区域，这个很重要，画圆的时候把这个覆盖
            int border = 1;
            //图片是一个圆型
            Ellipse2D.Double shape = new Ellipse2D.Double(border, border, width - border * 2, width - border * 2);
            //需要保留的区域
            graphics.setClip(shape);
            graphics.drawImage(avatarImage, border, border, width - border * 2, width - border * 2, null);
            graphics.dispose();
        }
        //在圆图外面再画一个圆
        {
            ////新创建一个graphics，这样画的圆不会有锯齿
            //graphics = formatAvatarImage.createGraphics();
            //graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //int border = 3;
            ////画笔是4.5个像素，BasicStroke的使用可以查看下面的参考文档
            ////使画笔时基本会像外延伸一定像素，具体可以自己使用的时候测试
            //Stroke s = new BasicStroke(4.5F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            //graphics.setStroke(s);
            //graphics.setColor(Color.WHITE);
            //graphics.drawOval(border, border, width - border * 2, width - border * 2);
            //graphics.dispose();
        }
        try (OutputStream os = new FileOutputStream("/Users/ti93/tmp/temp-avatar.png")) {
            ImageIO.write(formatAvatarImage, "PNG", os);
        }
        image();
        {
            //url = "https://asset-stage.yit.com/sdp_spu_share/3d6e76c0-e1a4-4af1-b69e-3f60a6dc8126.png";
            //BufferedImage srcImg = ImageIO.read(new URL(url));
            ////scrImg加载完之后没有任何颜色
            //BufferedImage blankImage = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_RGB);
            //graphics = blankImage.createGraphics();
            //graphics.drawImage(srcImg, 0, 0, null);
            //
            //int x = (blankImage.getWidth() - width) / 2;
            //int y = (blankImage.getHeight() - width) / 2;
            //graphics.drawImage(formatAvatarImage, x, y, width, width, null);
            //try (OutputStream os = new FileOutputStream("/Users/ti93/tmp/temp-blank.png")) {
            //    ImageIO.write(blankImage, "PNG", os);
            //}
        }
    }


    public  static void image() throws IOException {
        BufferedImage bi1 = ImageIO.read(new File("/Users/ti93/tmp/picture.png"));
        // 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
        BufferedImage image = new BufferedImage(bi1.getWidth(),
            bi1.getHeight(), BufferedImage.TYPE_INT_RGB);

        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1
            .getHeight());

        Graphics2D g2 = image.createGraphics();
        image = g2.getDeviceConfiguration().createCompatibleImage(
            bi1.getWidth(), bi1.getHeight(), Transparency.TRANSLUCENT);
        g2 = image.createGraphics();

        g2.setPaint(new Color (255,255,255));
        g2.fill(new Rectangle(image.getWidth(), image.getHeight()));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g2.setClip(shape);
        // 使用 setRenderingHint 设置抗锯齿
        g2.drawImage(bi1, 0, 0, null);
        g2.dispose();
        try {
            ImageIO.write(image, "png", new File("/Users/ti93/tmp/2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
