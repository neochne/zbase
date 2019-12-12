/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.MediaController;
import android.widget.VideoView;

public final class VideoUtils {

    private OnPlayStateListener mListener;

    private VideoView mVideoView;

    public void start(final Context context, VideoView vv, String url, final OnPlayStateListener listener) {
        mVideoView = vv;
        mListener = listener;
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mListener.onPrepareFinish();
                mVideoView.requestFocus();
                mVideoView.start();
                MediaController controller = new MediaController(context);
                controller.setAnchorView(mVideoView);
                controller.setMediaPlayer(mVideoView);
                mVideoView.setMediaController(controller);
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mListener.onPlayFinish();
            }
        });
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mListener.onError(new Exception("video play fail"));
                return false;
            }
        });
        mVideoView.setVideoPath(url);
    }

    public void pause(){
        if (mVideoView != null){
            mVideoView.pause();
        }
    }

    public void release() {
        if (mVideoView != null) {
            mVideoView.stopPlayback();
        }
        mVideoView = null;
        mListener = null;
    }

    public interface OnPlayStateListener {

        void onPrepareFinish();

        void onProgress(int curSeconds);

        void onPlayFinish();

        void onError(Exception e);

    }

}
