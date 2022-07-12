package com.zbase.x;

import android.content.Intent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;

public abstract class ActivityResultCallbackX implements ActivityResultCallback<ActivityResult> {

    @Override
    public void onActivityResult(ActivityResult result) {
        if (result == null) {return;}
        Intent intent = result.getData();
        if (intent == null) {return;}
        onIntentResult(result.getResultCode(),intent);
    }

    public abstract void onIntentResult(int resultCode,Intent intent);

}
