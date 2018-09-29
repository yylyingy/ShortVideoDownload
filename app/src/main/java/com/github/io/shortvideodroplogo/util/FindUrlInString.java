package com.github.io.shortvideodroplogo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2018/8/4 9:38
 */
public class FindUrlInString {
    /**
     *<br> Description: 这里用一句话描述这个方法的作用
     *<br> Author:      Yangyl
     *<br> Date:        2018/8/4 9:41
     * @param source 可能存在url的字符串
     * @return 返回url列表
     */
    public static List<String> parse(String source) {
        String s = "((http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z0-9]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(www.[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)";
        Pattern p= Pattern.compile(s);
        Matcher m = p.matcher(source);
        List<String> ret = new ArrayList<>();
        while (m.find()) {
            ret.add(m.group());
        }
        return ret;
    }
}
