package mblog.base.upload.impl;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/7 22:35.
 */
@Service("fileRepo")
public class FileRepoImpl extends AbstractFileRepo{

    private final static String KEY = "absolute";

    @PostConstruct
    public void init(){
        fileRepoFactory.addRepo(KEY , this);
    }

    @Override
    public String getRoot() {
        return appContext.getRoot();
    }
}
