package com.yiguohan.easyreading.UIComponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.yiguohan.easyreading.R;

/**
 * 自定义统计图
 * Created by yiguohan on 2017/7/5.
 */

public class StatisticsView extends View {

    //画XY轴
    private Paint mBorderPaint;
    //画坐标点的圆心
    private Paint circlePaint;
    //画折线
    private Paint mPathPaint;

    private Path mPath;
    //纵轴最大值
    private int maxValueY;
    //纵轴分割数量
    private int dividerCount;

    private String title = "数据统计";

    private int perValue = maxValueY / dividerCount;

    private String[] bottomStr = {};

    private float[] values = {};
    //底部横轴单位间距
    private float bottomGap;
    //左边纵轴间距
    private float leftGap;

    private Paint textPaint;

    public void setBottomStr(String[] bottomStr) {
        this.bottomStr = bottomStr;
        requestLayout();
    }

    public void setValues(float[] values) {
        this.values = values;
        invalidate();
    }

    public StatisticsView(Context context) {
        super(context);
    }

    public StatisticsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StatisticsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StatisticsView);

        maxValueY = array.getInt(R.styleable.StatisticsView_maxValueY, 100);
        dividerCount = array.getInt(R.styleable.StatisticsView_dividerCount, 10);
        title = array.getString(R.styleable.StatisticsView_title);

        int lineColor = array.getColor(R.styleable.StatisticsView_lineColor, Color.BLACK);
        int textColor = array.getColor(R.styleable.StatisticsView_textColor, Color.BLACK);

        mBorderPaint = new Paint();
        circlePaint = new Paint();
        mPathPaint = new Paint();

        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(lineColor);
        mBorderPaint.setStrokeWidth(1);
        mBorderPaint.setStyle(Paint.Style.STROKE);

        mPathPaint.setAntiAlias(true);
        mPathPaint.setStyle(Paint.Style.STROKE);
        mPathPaint.setStrokeWidth(3);

        textPaint = new TextPaint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(dip2px(context, 12));

        mPath = new Path();

        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);

        array.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, heightSize);
        } else if (widthMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, widthSize);
        } else {
            setMeasuredDimension(heightSize, heightSize);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        bottomGap = getWidth() / (bottomStr.length + 1);
        leftGap = getHeight() / (dividerCount + 2);
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (bottomStr == null || bottomStr.length == 0) {
            return;
        }
        //画左边的线
        canvas.drawLine(bottomGap, getHeight() - leftGap, bottomGap, leftGap, mBorderPaint);

        float fontHeight = (textPaint.getFontMetrics().descent - textPaint.getFontMetrics().ascent);
        //画下边的线
        canvas.drawLine(bottomGap, getHeight() - leftGap, getWidth() - bottomGap, getHeight() - leftGap, mBorderPaint);
        for (int i = 1; i <= bottomStr.length; i++) {
            canvas.drawCircle(i * bottomGap, getHeight() - leftGap, 6, circlePaint);
            canvas.drawText(bottomStr[i - 1], i * bottomGap - (textPaint.measureText(bottomStr[i - 1]) / 2), getHeight() - leftGap / 2 + fontHeight / 2, textPaint);
        }

        canvas.drawText(title, bottomGap, leftGap / 2, textPaint);
        for (int i = 1; i < dividerCount + 1; i++) {
            //画左边的字
            canvas.drawText(perValue * (i - 1) + "", bottomGap / 2 - (textPaint.measureText(perValue * (i - 1) + "") / 2), (dividerCount + 2 - i) * leftGap + fontHeight / 2, textPaint);
            //画横线
            canvas.drawLine(bottomGap, getHeight() - ((i) * leftGap), getWidth() - bottomGap, getHeight() - ((i) * leftGap), mBorderPaint);
        }

        /**
         * 画轨迹
         * y的坐标点根据y/leftGap=values[i]/perValue计算
         */

        for (int i = 0; i < values.length; i++) {
            if (i == 0) {
                mPath.moveTo(bottomGap, (dividerCount + 1) * leftGap - (values[i] * leftGap / perValue));
            } else {
                mPath.lineTo((i + 1) * bottomGap, (dividerCount + 1) * leftGap - (values[i] * leftGap / perValue));
            }

            /**
             * 画轨迹圆点
             */
            canvas.drawCircle((i + 1) * bottomGap, (dividerCount + 1) * leftGap - (values[i] * leftGap / perValue), 6, circlePaint);
        }
        canvas.drawPath(mPath, mBorderPaint);

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
