package com.lihy.view.common.util;

import java.util.UUID;

/**
 * GUID工具类
 *
 * @author lihy
 * @date 2018/09/06
 */
public class GuidUtil {

    public static String getGuidNew() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成GUID
     * 生成GUID，用于数据库主键
     * @return
     */
    public static String getGuid() {
        return replace(UUID.randomUUID().toString(), "-", "");
    }

    /**
     * 替换字符
     * 替换字符
     *
     * @param strSource 原字符串
     * @param strFrom   待替换的字符串
     * @param strTo     替换的字符串
     * @return 新的字符串
     */
    private static String replace(String strSource, String strFrom, String strTo) {
        if (strSource == null) {
            return null;
        }
        int i = 0;
        if ((i = strSource.indexOf(strFrom, i)) >= 0) {
            char[] cSrc = strSource.toCharArray();
            char[] cTo = strTo.toCharArray();
            int len = strFrom.length();
            StringBuffer buf = new StringBuffer(cSrc.length);
            buf.append(cSrc, 0, i).append(cTo);
            i += len;
            int j = i;
            while ((i = strSource.indexOf(strFrom, i)) > 0) {
                buf.append(cSrc, j, i - j).append(cTo);
                i += len;
                j = i;
            }
            buf.append(cSrc, j, cSrc.length - j);
            return buf.toString();
        }
        return strSource;
    }
}
