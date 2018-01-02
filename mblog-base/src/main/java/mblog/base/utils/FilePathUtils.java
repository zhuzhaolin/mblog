package mblog.base.utils;

import org.springframework.beans.BeanUtils;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/7 22:58.
 */
public class FilePathUtils {

    private final static String TEMPLATE = "%09d";
    private final static int[] GRIDS = new int[]{3 , 2 ,2 ,2};
    private final static int LENGTH = 9;

    public static String getAvatar(long key){
        String r = String.format(TEMPLATE , key);
        StringBuffer buf = new StringBuffer(32);

        int pos = 0;
        for (int t : GRIDS){
            buf.append(r.substring(pos , pos + t));
            pos += t;
            if (pos < LENGTH){
                buf.append("/");
            }
        }
        return buf.toString();
    }
}
