/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

public interface Callback {

    void onStart();

    void onSuccess(Rep response);

    void onError(Rep response, Exception e);

    void onFinish();

}
