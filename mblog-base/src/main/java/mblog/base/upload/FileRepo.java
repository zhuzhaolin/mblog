package mblog.base.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/7 22:35.
 */
public interface FileRepo {

    /**
     * 保存临时图片
     * - 临时图片不会生成复杂的多级目录
     * @param file
     * @param basePath
     * @return
     * @throws IOException
     */
    String temp(MultipartFile file, String basePath) throws IOException;

    /**
     * 保存压缩后的临时图片
     * 临时图片不会生成复杂的多级目录
     * @param file
     * @param basePath
     * @param maxWith
     * @return
     * @throws Exception
     */
    String tempScale(MultipartFile file, String basePath, int maxWith) throws Exception;

    String getRoot();
}
