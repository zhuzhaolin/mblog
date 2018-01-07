package mblog.core.persist.service.impl;

import mblog.base.context.AppContext;
import mblog.core.data.Config;
import mblog.core.persist.dao.ConfigDao;
import mblog.core.persist.entity.ConfigPO;
import mblog.core.persist.service.ConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/17 19:49.
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigDao configDao;

    @Autowired
    private AppContext appContext;

    @Override
    public List<Config> findAll() {
        List<ConfigPO> configs = configDao.findAll();
        List<Config> rets = new ArrayList<>();

        for (ConfigPO po : configs){
            Config r = new Config();
            BeanUtils.copyProperties(po , r);
            rets.add(r);
        }
        return rets;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Config> findAll2Map() {
        List<Config> list = findAll();
        Map<String , Config> ret = new LinkedHashMap<>();

        for (Config c : list){
            ret.put(c.getKey() , c);
        }
        return ret;
    }
}
