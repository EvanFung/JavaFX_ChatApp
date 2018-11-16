package util;

import java.io.File;

public class PathUtil {
    private PathUtil(){}
    public static String combination(String prefix, String suffix) {
        if (StringUtil.isEmpty(prefix)) return suffix;
        if (StringUtil.isEmpty(suffix)) return prefix;

        if (prefix.endsWith(File.separator)) {
            prefix = prefix.substring(0, prefix.length() - 1);
        }
        if (suffix.startsWith(File.separator)) {
            suffix = suffix.substring(1);
        }
        return prefix + File.separator + suffix;
    }
}
