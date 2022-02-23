package lib.tools;

import java.util.Map;

/**
 * @Author : BabyMuu
 * @File : MapTools
 * @Time : 2021/12/21 17:08
 */
public class MapTools {
    public static void mapExtend(Map<String, String> strMap, Map<String, String> extraMap) {
        if (extraMap == null) {
            return;
        }
        for (String key : extraMap.keySet()) {
            strMap.put(key, extraMap.get(key));
        }
    }
}
