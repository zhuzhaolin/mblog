package mblog.core.persist.service;

import mblog.core.data.Config;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/17 19:47.
 */
public interface ConfigService {

    /**
     * 查询所有配置
     */
    List<Config> findAll();

    /**
     * 查询所有配置
     */
    Map<String , Config> findAll2Map();
}
