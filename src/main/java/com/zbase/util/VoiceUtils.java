/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.util;

import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public final class VoiceUtils {

    private VoiceUtils(){}

    private MediaPlayer mPlayer;

    private Timer mTimer;

    private TimerTask mTimerTask;

    private int mLastPlayedSeconds;

    private int mTotalMsec;

    private OnPlayStateListener mStateListener;

    public void start(String url, OnPlayStateListener listener){
        release();
        mStateListener = listener;
        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mPlayer.setDataSource(url);
            mPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
            mStateListener.onError(e);
        }
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mTotalMsec = mPlayer.getDuration();
                mStateListener.onPrepareFinish(mTotalMsec);
                mPlayer.start();
                startTimer();
            }
        });
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mStateListener.onPlayFinish();
            }
        });
    }

    public void resume(){
        if (mPlayer != null){
            mPlayer.start();
        }
        startTimer();
    }

    public void pause(){
        if (mPlayer != null) {
            mPlayer.pause();
        }
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null){
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    public void release(){
        mLastPlayedSeconds = 0;
        mStateListener = null;
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null){
            mTimerTask.cancel();
            mTimerTask = null;
        }
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.seekTo(0);
            mPlayer.reset();
            mPlayer.release();
            mPlayer = null;
        }
    }

    private void startTimer(){
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null){
            mTimerTask.cancel();
            mTimerTask = null;
        }
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mStateListener.onProgress(++mLastPlayedSeconds,getCurrentPositionInMinutes(),mPlayer.getCurrentPosition(),mTotalMsec);
            }
        };
        mTimer.schedule(mTimerTask,0,1000);
    }

    public void seekTo(int toMsec){
        if (mPlayer != null){
            mPlayer.seekTo(toMsec);
        }
    }

    public String getDurationInMinutes(){
        if (mPlayer == null){return "00:00";}
        int milliseconds = mPlayer.getDuration();
        return new SimpleDateFormat("mm:ss", Locale.CHINA).format(new Date(milliseconds));
    }

    public String getCurrentPositionInMinutes(){
        if (mPlayer == null){return "00:00";}
        int milliseconds = mPlayer.getCurrentPosition();
        return new SimpleDateFormat("mm:ss", Locale.CHINA).format(new Date(milliseconds));
    }

    public interface OnPlayStateListener{

        void onPrepareFinish(int totalMsec);

        void onProgress(int curSeconds, String curMinutes, int curMsec, int totalMsec);

        void onPlayFinish();

        void onError(Exception e);

    }

}
