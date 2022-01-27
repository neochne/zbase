/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

import java.util.HashMap;
import java.util.Map;

public interface YesHttpClient{

    Map<String,String> commonHeaderMap = new HashMap<>();

    default void addCommonHeader(String name, String value) {
        commonHeaderMap.put(name,value);
    }

    YesResponse get(YesRequest request);

    void getAsync(YesRequest request, YesCallback callback);

    YesResponse post(YesRequest request);

    void postAsync(YesRequest request, YesCallback callback);

    void downloadFileAsync(YesRequest request,YesCallback callback);

    void uploadFileAsync(YesRequest request,YesCallback callback);

}
