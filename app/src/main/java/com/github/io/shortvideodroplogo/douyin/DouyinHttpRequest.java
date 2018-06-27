package com.github.io.shortvideodroplogo.douyin;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2018/6/25 23:26
 */

public class DouyinHttpRequest {
    public static void get(String url,final DownloadCallback downloadCallback) {
        int index = url.indexOf("http");
        url = url.substring(index);
        OkHttpUtil.get(url,new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        downloadCallback.fail("解析失败");
                        System.out.println();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            System.out.println(response.request().url());
                            String body = response.body().string();
                            System.out.println(body);
                            long time = System.currentTimeMillis();
                            DouyinBean douyinBean = parseDouyinHtml(body);
                            if (douyinBean != null) {
                                String videoid;
                                String[] tmp = douyinBean.getPlayAddr().split("video_id=");
                                tmp = tmp[1].split("&line");
                                videoid = tmp[0];
                                time = System.currentTimeMillis() - time;
                                System.out.println("耗时：" + time);
                                DouyinGet.download(videoid,downloadCallback);
                            }
                        }
                    }
                });
    }

    /**
     * 解析html，找到videoid
     * @param source
     * @return
     */
    private static DouyinBean parseDouyinHtml(String source) {
        DouyinBean douyinBean;
        //寻找'{'对应的'}'，通过计数方式寻找，当'{'遇到'{'，则count++，遇到'}'，count--，count为0时则'{'
        //遇到了对应的'}'，
        int count = 0;
        //是否找到一个左花括号
        boolean findLeftBrace = false;
        int lastLeftBraceIndex = 0;
        //所有的左花括号索引，查找与之对应的右花括号，尝试进行json解析，解析成功即能找到videoid
        List<Integer> listLeftBraceIndex = findLeftBrace(source);
        for (int i = 0;i < listLeftBraceIndex.size();i ++) {
            for (int j = listLeftBraceIndex.get(i);j < source.length();j ++) {
                if (source.charAt(j) == '{') {
                    count ++;
                    lastLeftBraceIndex = j;
                    findLeftBrace = true;
                }
                if (source.charAt(j) == '}') {
                    count --;
                }
                if (findLeftBrace && count == 0) {
                    //找到了一对花括号
                    String findResult = source.substring(lastLeftBraceIndex,j < source.length() - 1 ? j +1 : j);
                    try {
                        //将这一对花括号进行json解析
                        douyinBean = new Gson().fromJson(findResult, DouyinBean.class);
                        if (douyinBean != null) {
                            //解析成功
                            return douyinBean;
                        }
                    } catch (JsonSyntaxException e) {
                        count = 0;
                        findLeftBrace = false;
                    }
                }
            }
            count = 0;
        }
        return null;
    }

    /**
     * 寻找所有的左花括号:'{'
     * @param source
     * @return
     */
    private static List<Integer> findLeftBrace(String source) {
        List<Integer> listLeftBraceIndex = new ArrayList<>();
        for (int i = 0;i < source.length();i ++) {
            if (source.charAt(i) == '{') {
                listLeftBraceIndex.add(i);
            }
        }
        return listLeftBraceIndex;
    }
}
