package com.github.io.shortvideodroplogo.network.json;



/**
 * @author yangyinglong on 2017/8/3 11:08.
 * @Description: todo(这里用一句话描述这个类的作用)
 * @Copyright Copyright (c) 2017 Tuandai Inc. All Rights Reserved.
 */

public interface IJsonParser {
    String toJson(Object object);
    <T> T fromJson(String json, Class<T> classOfT);
}
