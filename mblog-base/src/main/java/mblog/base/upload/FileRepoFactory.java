package mblog.base.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/7 22:33.
 */
@Service
public class FileRepoFactory {

    private Map<String , FileRepo> fileRepos = new HashMap<>();

    @Value("${store.repo}")
    private String repo = "relative";

    public void addRepo(String key , FileRepo value){
        fileRepos.put(key , value);
    }

    public FileRepo select(){
        return fileRepos.get(repo);
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }
}
