package com.zbase.x.able;

import android.content.Intent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.zbase.R;
import com.zbase.x.ActivityResultCallbackX;
import com.zbase.x.ActivityX;

public interface ActivityResultAble extends ContextAble {

    default boolean withActivityResultLauncher() {
        return false;
    }

    default ActivityResultLauncher<Intent> getActivityResultLauncher() {
        return (ActivityResultLauncher<Intent>) getTag(R.id.activityx_result_launcher);
    }

    default ActivityResultCallbackX createActivityResultCallback() {
        return null;
    }

    default void registerActivityResultLauncher(ActivityResultCallbackX callback) {
        if (isActivityX()) {
            ActivityX activityX = getActivityX();
            ActivityResultLauncher<Intent> launcher = activityX.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);
            setTag(R.id.activityx_result_launcher, launcher);
        } else if (isFragmentX()) {
            ActivityResultLauncher<Intent> launcher = getFragmentX().registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);
            setTag(R.id.activityx_result_launcher, launcher);
        } else {
            throw unsupportedException();
        }
    }

    default void startActivity4Result(Intent intent) {
        getActivityResultLauncher().launch(intent);
    }

}
