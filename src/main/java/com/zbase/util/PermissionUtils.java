package com.zbase.util;

import android.Manifest;
import android.os.Build;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.request.PermissionBuilder;

public final class PermissionUtils {

    private PermissionUtils() {
    }

    public interface PermissionRequestCallback {
        void success();
    }

    public static void requestStoragePermissions(FragmentActivity activity, PermissionRequestCallback requestCallback) {
        PermissionBuilder permissionBuilder;
        if (Build.VERSION.SDK_INT >= 30) {
            permissionBuilder = PermissionX.init(activity).permissions(Manifest.permission.MANAGE_EXTERNAL_STORAGE);
        } else {
            permissionBuilder = PermissionX.init(activity)
                    .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        permissionBuilder.onExplainRequestReason((scope, deniedList) -> {
            scope.showRequestReasonDialog(deniedList, "请允许以下权限", "允许", "拒绝");
        }).request((allGranted, grantedList, deniedList) -> {
            if (allGranted) {
                requestCallback.success();
            } else {
                ToastUtils.show(activity,"请允许应用所需权限");
            }
        });
    }

    public static void requestStoragePermissions(Fragment fragment, PermissionRequestCallback requestCallback) {
        PermissionBuilder permissionBuilder;
        if (Build.VERSION.SDK_INT >= 30) {
            permissionBuilder = PermissionX.init(fragment).permissions(Manifest.permission.MANAGE_EXTERNAL_STORAGE);
        } else {
            permissionBuilder = PermissionX.init(fragment)
                    .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        permissionBuilder.onExplainRequestReason((scope, deniedList) -> {
            scope.showRequestReasonDialog(deniedList, "请允许以下权限", "允许", "拒绝");
        }).request((allGranted, grantedList, deniedList) -> {
            if (allGranted) {
                requestCallback.success();
            } else {
                ToastUtils.show(fragment.getActivity(),"请允许应用所需权限");
            }
        });
    }

}
