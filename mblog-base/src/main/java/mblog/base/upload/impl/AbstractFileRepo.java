package mblog.base.upload.impl;

import mblog.base.context.AppContext;
import mblog.base.exception.MtonsException;
import mblog.base.upload.FileRepo;
import mblog.base.upload.FileRepoFactory;
import mblog.base.upload.ImageHandleUtils;
import mtons.modules.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/7 22:36.
 */
public abstract class AbstractFileRepo implements FileRepo {

    @Autowired
    protected AppContext appContext;

    @Autowired
    protected FileRepoFactory fileRepoFactory;

    // 文件允许格式
    protected String[] allowFiles = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };

    protected void validateFile(MultipartFile file){
        if (file == null || file.isEmpty()) {
            throw new MtonsException("文件不能为空");
        }

        if (!checkFileType(file.getOriginalFilename())){
            throw new MtonsException("文件格式不支持");
        }
    }

    protected String getExt(String filename){
        int pos = filename.lastIndexOf(".");
        return filename.substring(pos + 1);
    }

    protected void checkDirAndCreate(File file){
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
    }

    /**
     * 文件类型判断
     *
     * @param fileName
     * @return
     */
    protected boolean checkFileType(String fileName) {
        Iterator<String> type = Arrays.asList(this.allowFiles).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String temp(MultipartFile file, String basePath) throws IOException {
        validateFile(file);

        String root = getRoot();

        String name = FileNameUtils.genFileName(getExt(file.getOriginalFilename()));
        String path = basePath + "/" + name;
        File temp = new File(root + path);
        checkDirAndCreate(temp);
        file.transferTo(temp);
        return path;
    }

    @Override
    public String tempScale(MultipartFile file, String basePath, int maxWith) throws Exception {
        validateFile(file);

        String root = getRoot();

        String name = FileNameUtils.genFileName(getExt(file.getOriginalFilename()));
        String path = basePath + "/" + name;

        //存储临时文件
        File temp = new File(root + path);
        checkDirAndCreate(temp);

        try {
            file.transferTo(temp);

            //根据临时文件生成缩列图
            String scaleName = FileNameUtils.genFileName(getExt(file.getOriginalFilename()));
            String dest = root + basePath + "/" + scaleName;

            ImageHandleUtils.scaleImageByWidth(temp.getAbsolutePath() , dest , maxWith);

            path = basePath + "/" + scaleName;
        }catch (Exception e){
            throw e;
        }finally {
            if (temp != null){
                temp.delete();
            }
        }
        return path;
    }
}
