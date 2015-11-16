package com.gatorboard.gatorboard;

/**
 * Created by sushma on 11/12/2015.
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;

/*
 * Helper class for downloading a file.
 */
public class Downloader {

    //Tag for Log statements
    private static String myTag = "StackSites";

    //Handler msg that represents we are posting a progress update.
    static final int POST_PROGRESS = 1;

    /************************************************
     * Download a file from the Internet and store it locally
     *
     * @param URL - the url of the file to download
     * @param fos - a FileOutputStream to save the downloaded file to.
     ************************************************/
    public static void DownloadFromUrl(String URL, FileOutputStream fos) {  //this is the downloader method
        try {

            URL url = new URL(URL); //URL of the file

            //keep the start time so we can display how long it took to the Log.
            long startTime = System.currentTimeMillis();
            Log.d(myTag, "download begining");

			/* Open a connection to that URL. */
            URLConnection ucon = url.openConnection();

            // this will be useful so that you can show a tipical 0-100% progress bar
            //int lenghtOfFile = ucon.getContentLength();

            Log.i(myTag, "Opened Connection");

            /************************************************
             * Define InputStreams to read from the URLConnection.
             ************************************************/
            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            Log.i(myTag, "Got InputStream and BufferedInputStream");

            /************************************************
             * Define OutputStreams to write to our file.
             ************************************************/

            BufferedOutputStream bos = new BufferedOutputStream(fos);
            Log.i(myTag, "Got FileOutputStream and BufferedOutputStream");

            /************************************************
             * Start reading the and writing our file.
             ************************************************/
            byte data[] = new byte[1024];
            //long total = 0;
            int count;
            //loop and read the current chunk
            while ((count = bis.read(data)) != -1) {
                //keep track of size for progress.
                //total += count;

                //write this chunk
                bos.write(data, 0, count);
            }
            //Have to call flush or the  file can get corrupted.
            bos.flush();
            bos.close();

            Log.d(myTag, "download ready in "
                    + ((System.currentTimeMillis() - startTime))
                    + " milisec");
        } catch (IOException e) {
            Log.d(myTag, "Error: " + e);
        }
    }

    public static class CircleIndicator extends LinearLayout implements ViewPager.OnPageChangeListener {
        private static final int SCROLL_WHAT=0x01;
        private static final int CIRCLE_STROKE_WIDTH =1;
        private static final int BITMAP_PADDING =2;


        private static final int DEFAULT_CIRCLE_SPACING = 5;
        private static final int DEFAULT_CIRCLE_COLOR= Color.WHITE;
        private static final int DEFAULT_CIRCLE_SIZE=3;
        private static final boolean DEFAULT_CIRCLE_AUTO_SCROLL=false;
        private static final int DEFAULT_CIRCLE_SCROLL_DELAY_TIME=3000;
        private static final boolean DEFAULT_CIRCLE_SCROLL_ANIMATION=true;
        private int mSpacing;
        private int mSize;
        private int mFillColor;
        private int mStrokeColor;
        private boolean mAutoScroll;
        private int mDelayTime;
        private boolean mIsAnimation;

        private ViewPager mViewPager;
        private int mCount;
        private int mLastIndex = 0;
        private Canvas mCanvas;
        private Paint mPaint;
        private Bitmap mSelectBitmap;
        private Bitmap mUnselectBitmap;
        private Handler mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case SCROLL_WHAT:
                        scrollOnce();
                        sendScrollMessage(mDelayTime);
                        break;
                }
            }
        };
        public CircleIndicator(Context context) {
            this(context, null);
        }

        public CircleIndicator(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public CircleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initCustomParams(context, attrs);
            init();
        }

        private void initCustomParams(Context context, AttributeSet attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicator);
            try {
                mSpacing = typedArray.getDimensionPixelSize(R.styleable.CircleIndicator_circle_spacing, DEFAULT_CIRCLE_SPACING);
                mFillColor=typedArray.getColor(R.styleable.CircleIndicator_circle_fill_color,DEFAULT_CIRCLE_COLOR);
                mStrokeColor=typedArray.getColor(R.styleable.CircleIndicator_circle_stroke_color,DEFAULT_CIRCLE_COLOR);
                mSize= typedArray.getDimensionPixelSize(R.styleable.CircleIndicator_circle_radius, DEFAULT_CIRCLE_SIZE);
                mAutoScroll= typedArray.getBoolean(R.styleable.CircleIndicator_circle_auto_scroll, DEFAULT_CIRCLE_AUTO_SCROLL);
                mDelayTime=typedArray.getInt(R.styleable.CircleIndicator_circle_scroll_delay_time,DEFAULT_CIRCLE_SCROLL_DELAY_TIME);
                mIsAnimation=typedArray.getBoolean(R.styleable.CircleIndicator_circle_scroll_animation,DEFAULT_CIRCLE_SCROLL_ANIMATION);

            } finally {
                typedArray.recycle();
            }
        }

        private void init() {
            setOrientation(HORIZONTAL);

            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

            mPaint.setStrokeWidth(dip2px(CIRCLE_STROKE_WIDTH));
            mPaint.setColor(mFillColor);

            int size=dip2px(mSize+ BITMAP_PADDING + BITMAP_PADDING);
            int radius=dip2px(mSize / 2);
            int centerPoint=radius+ BITMAP_PADDING;

            mSelectBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            mUnselectBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);

            mCanvas = new Canvas();
            mCanvas.setBitmap(mSelectBitmap);


            mCanvas.drawCircle(centerPoint, centerPoint, radius, mPaint);

            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(mStrokeColor);
            mCanvas.setBitmap(mUnselectBitmap);
            mCanvas.drawCircle(centerPoint, centerPoint, radius, mPaint);

        }

        public void setViewPager(ViewPager viewPager) {
            mViewPager = viewPager;
            mViewPager.addOnPageChangeListener(this);
            if (mViewPager != null) {
                mCount = mViewPager.getAdapter().getCount();
                addIndicator(mCount);
            }

        }

        private void addIndicator(int count) {
            removeIndicator();
            if (count <= 0)
                return;
            for (int i = 0; i < count; i++) {
                ImageView imageView = new ImageView(getContext());
                LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.leftMargin = mSpacing/2;
                params.rightMargin = mSpacing/2;

                imageView.setImageBitmap(mUnselectBitmap);
                addView(imageView, params);
            }
            initIndicator();
            if(mAutoScroll){
                sendScrollMessage(mDelayTime);
            }
        }

        private void initIndicator() {
            ((ImageView) getChildAt(0)).setImageBitmap(mSelectBitmap);
            mLastIndex=0;
        }

        private void removeIndicator() {
            removeAllViews();
        }

        private void updateIndicator(int position) {

            if (position != mLastIndex) {
                ((ImageView) getChildAt(mLastIndex)).setImageBitmap(mUnselectBitmap);
                ((ImageView) getChildAt(position)).setImageBitmap(mSelectBitmap);
            }
            mLastIndex = position;

        }

        private void setAutoScroll(boolean autoScroll){
            if (autoScroll){
                sendScrollMessage(mDelayTime);
            }else{
                mHandler.removeMessages(SCROLL_WHAT);
            }
            mAutoScroll=autoScroll;

        }
        public boolean isAutoScroll() {
            return mAutoScroll;
        }

        public int getDelayTime() {
            return mDelayTime;
        }

        public void setDelayTime(int delayTime) {
            mDelayTime = delayTime;
        }

        public boolean isAnimation() {
            return mIsAnimation;
        }

        public void setIsAnimation(boolean isAnimation) {
            mIsAnimation = isAnimation;
        }

        public void scrollOnce() {
            PagerAdapter adapter = mViewPager.getAdapter();
            if (adapter == null) {
                return;
            }
            int nextIndex=mViewPager.getCurrentItem();
            ++nextIndex;
            if(nextIndex >=mCount){
                nextIndex =0;
            }
            updateIndicator(nextIndex);
            mViewPager.setCurrentItem(nextIndex, mIsAnimation);


        }

        private void sendScrollMessage(long delayTimeInMills) {
            mHandler.removeMessages(SCROLL_WHAT);
            mHandler.sendEmptyMessageDelayed(SCROLL_WHAT, delayTimeInMills);
        }
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int position) {
            updateIndicator(position);
        }


        @Override
        public void onPageScrollStateChanged(int i) {

        }

        private int dip2px(int dip) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        }
    }
}
