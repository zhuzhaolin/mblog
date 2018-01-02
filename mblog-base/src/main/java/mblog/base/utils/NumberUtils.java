package mblog.base.utils;

import org.apache.commons.lang.StringUtils;

/**
 * Created by zhuzhaolin on 2017/10/29.
 */
public class NumberUtils {
    public static int changeToInt(Object obj){
        int i = 0;
        if (obj != null && StringUtils.isNumeric(obj.toString())){
            i = ((Number) obj).intValue();
        }
        return i;
    }
}
