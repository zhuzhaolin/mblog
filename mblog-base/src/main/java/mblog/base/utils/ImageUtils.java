package mblog.base.utils;


import mtons.modules.utils.GMagickUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.im4java.core.IM4JavaException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/11 22:21.
 */
public class ImageUtils extends GMagickUtils {

    /**
     * 根据最大宽度图片压缩
     *
     * @param ori     原图位置
     * @param dest    目标位置
     * @param maxSize 指定压缩后最大边长
     * @return boolean
     * @throws IOException
     */
    public static boolean scaleImageByWidth(String ori, String dest, int maxSize) throws IOException {
        File oriFile = new File(ori);
        GMagickUtils.validate(oriFile, dest);

        BufferedImage src = ImageIO.read(oriFile); // 读入文件
        int w = src.getWidth();
        int h = src.getHeight();


        int size = (int) Math.max(w, h);
        int tow = w;
        int toh = h;

        if (size > maxSize) {
            if (w > maxSize) {
                tow = maxSize;
                toh = h * maxSize / w;
            } else {
                tow = w * maxSize / h;
                toh = maxSize;
            }
        }
        scale(ori, dest, tow, toh);
        return true;
    }


    public static void scale(String ori, String dest, int width, int height) throws IOException {
        File destFile = new File(dest);
        if (destFile.exists()) {
            destFile.delete();
        }
        Thumbnails.of(ori).size(width, height).toFile(dest);
    }

    /**
     * 图片压缩,各个边按比例压缩
     *
     * @param ori     原图位置
     * @param dest    目标位置
     * @param maxSize 指定压缩后最大边长
     * @return boolean
     * @throws IOException
     */
    public static boolean scaleImage(String ori, String dest, int maxSize) throws IOException {
        File oriFile = new File(ori);
        validate(oriFile, dest);

        BufferedImage src = ImageIO.read(oriFile); // 读入文件
        int w = src.getWidth();
        int h = src.getHeight();

        int size = (int) Math.max(w, h);
        int tow = w;
        int toh = h;

        if (size > maxSize) {
            if (w > maxSize) {
                tow = maxSize;
                toh = h * maxSize / w;
            } else {
                tow = w * maxSize / h;
                toh = maxSize;
            }
        }


        scale(ori, dest, tow, toh);

        return true;
    }


    /**
     * 裁剪图片
     *
     * @param ori  源图片路径
     * @param dest 处理后图片路径
     * @param x    起始X坐标
     * @param y    起始Y坐标
     * @param width  裁剪宽度
     * @param height  裁剪高度
     * @return boolean
     *
     * @throws IOException io异常
     * @throws IM4JavaException    im4j 异常
     * @throws InterruptedException 中断异常
     */
    public static boolean truncateImage(String ori, String dest, int x, int y, int width, int height) throws IOException, InterruptedException, IM4JavaException {
        File oriFile = new File(ori);

        validate(oriFile, dest);

        Thumbnails.of(ori).sourceRegion(x, y, width, height).size(width,height).keepAspectRatio(false).toFile(dest);

        return true;
    }

    public static boolean truncateImage(String ori, String dest, int x, int y, int size) throws IOException, InterruptedException, IM4JavaException {
        return truncateImage(ori, dest, x, y, size, size);
    }
}
