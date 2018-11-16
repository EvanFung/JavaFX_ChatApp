package util;

import java.util.Map;
import java.util.Map.Entry;

public class LoggerUtil {
    public static void iterateMap(Map map) {
        map.forEach((k, v) -> {
            System.out.println("Key:" + k + "--" + "Value:" + v);
        });
    }
}
