/*
 * Copyright (c) 2017 Cosmin Cojocea
 *
 * This project was created and submitted by Cosmin Cojocea as part of the Google Challenge Scholarship: Android Basics for Udacity.
 *
 * According to Udacity's Honor code, your submissions must be your own work, hence submitting this project as your own might cause you to earn the suspension of your account.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Except as contained in this notice, the name(s) of the above copyright holders shall not be used in advertising or otherwise to promote the sale, use or other dealings in this Software without prior written authorization.
 *
 *
 *
 *
 */

package com.example.android.ccojocea.tenniscounter;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by ccojo on 11/30/2017.
 */

    public class VerticalTextView extends TextView{
        final boolean topDown;

        public VerticalTextView(Context context, AttributeSet attrs){
            super(context, attrs);
            final int gravity = getGravity();
            if(Gravity.isVertical(gravity) && (gravity&Gravity.VERTICAL_GRAVITY_MASK) == Gravity.BOTTOM) {
                setGravity((gravity&Gravity.HORIZONTAL_GRAVITY_MASK) | Gravity.TOP);
                topDown = false;
            }else
                topDown = true;
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
            super.onMeasure(heightMeasureSpec, widthMeasureSpec);
            setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
        }

        @Override
        protected void onDraw(Canvas canvas){
            TextPaint textPaint = getPaint();
            textPaint.setColor(getCurrentTextColor());
            textPaint.drawableState = getDrawableState();

            canvas.save();

            if(topDown){
                canvas.translate(getWidth(), 0);
                canvas.rotate(90);
            }else {
                canvas.translate(0, getHeight());
                canvas.rotate(-90);
            }


            canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());

            getLayout().draw(canvas);
            canvas.restore();
        }
    }
