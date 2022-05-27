package com.zbase.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

public final class SignatureView extends SurfaceView implements SurfaceHolder.Callback,Runnable{

    private final SurfaceHolder mSurfaceHolder;

    private Canvas mCanvas;

    private final Paint mPaint;

    private  Path mPath;

    private float mPreviousX = 0;

    private float mPreviousY = 0;

    private boolean mIsEmpty = true;

    public SignatureView(Context context) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        /*
         * SurfaceHolder
         */
        mSurfaceHolder = getHolder();
        // SurfaceView 默认背景色是黑色，下面为变成白色
        setZOrderOnTop(true);
        mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        setBackground(new ColorDrawable(Color.WHITE));
        mSurfaceHolder.addCallback(this);

        /*
         * Paint
         */
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(20);

        /*
         * Path
         */
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                signDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                signing(event);
                break;
            case MotionEvent.ACTION_UP:
                performClick();
                signUp(event);
                break;
        }
        return true;
    }

    private void signDown(MotionEvent event) {
        mPreviousX = event.getX();
        mPreviousY = event.getY();
        mPath.moveTo(mPreviousX,mPreviousY);
    }

    private void signing(MotionEvent event) {
        final float curX = event.getX();
        final float curY = event.getY();
        final float previousX = mPreviousX;
        final float previousY = mPreviousY;
        final float dx = Math.abs(curX - previousX);
        final float dy = Math.abs(curY - previousY);
        if (dx >= 3 || dy >= 3) {
            float endX = (curX + previousX) / 2;
            float endY = (curY + previousY) / 2;
            mPath.quadTo(previousX, previousY, endX, endY);
            mPreviousX = curX;
            mPreviousY = curY;
        }
        // 每次获取 backCanvas
        mCanvas = mSurfaceHolder.lockCanvas();
        mCanvas.drawPath(mPath,mPaint);
        // 每次绘制完后需要调用 unlockCanvasAndPost 方法释放同步锁，更新 backCanvas 新绘制的内容到 frontCanvas
        mSurfaceHolder.unlockCanvasAndPost(mCanvas);
    }

    private void signUp(MotionEvent event) {
        mIsEmpty = false;
    }

    public boolean isEmpty() {
        return mIsEmpty;
    }

    public void clear() {
        mIsEmpty = true;
        mPath.reset();
        for (int i = 0; i < 3; i++) {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    public Bitmap generateBitmap() {
        Bitmap signBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas bitmapCanvas = new Canvas(signBitmap);
        draw(bitmapCanvas);
        return signBitmap;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        // can start work thread here
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
    }

    @Override
    public void run() {
        // can draw on work thread
    }

}
