package com.github.io.shortvideodroplogo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 暂未完成
 */
public class FindJsonInString {
    public static List<String> parse(String source) {
        String s = "";
        Pattern p= Pattern.compile(s);
        Matcher m = p.matcher(source);
        List<String> ret = new ArrayList<>();
        while (m.find()) {
            ret.add(m.group());
        }
        return ret;
    }
}
