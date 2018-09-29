package com.github.io.shortvideodroplogo.toutiao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2018/9/24 11:20
 */
public class FindTouTiaoVideoId {
    public static List<String> parse(String source) {
        String regex = "playerInfo:(\\s)?\\{\\s*(\\s*videoId)\\s*:\\s*'(([a-zA-Z0-9]){32})'\\s*\\}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(source);
        List<String> beans = new ArrayList<>();
        while (m.find()) {
            beans.add(m.group());
        }
        regex = "'(([a-zA-Z0-9]){32})'";
        List<String> videoIds = new ArrayList<>();
        for (String bean:beans) {
            p = Pattern.compile(regex);
            m = p.matcher(bean);
            while (m.find()) {
                videoIds.add(m.group());
            }
        }
        return videoIds;
    }
}
