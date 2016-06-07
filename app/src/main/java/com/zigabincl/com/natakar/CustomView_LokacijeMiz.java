package com.zigabincl.com.natakar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by SPACE MARINE GENERAL on 31.5.2016.
 */
public class CustomView_LokacijeMiz extends View {
    private int startX;
    private int stopX;
    private int startY;
    private int stopY;
    private int[] poljeLokacij;
    private int lokacija;
    int kSirina;
    int kVisina;
    public boolean editMode;

    public CustomView_LokacijeMiz(Context context) {
        super(context);
    }

    public CustomView_LokacijeMiz(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView_LokacijeMiz(Context context, AttributeSet ats, int defaultStyle) {
        super(context, ats, defaultStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
          if (editMode) {
              float eventX = event.getX();
              float eventY = event.getY();

              switch (event.getAction()) {

                  case MotionEvent.ACTION_UP:
                      int y = (int) eventY / kVisina;
                      int x = (int) eventX / kSirina;

                      if(poljeLokacij[x + y * 10]!=1) //premakne mizo
                      {
                          poljeLokacij[lokacija] = 0;
                          lokacija = x + y * 10;
                          poljeLokacij[lokacija] = 2;
                      }
                      break;
                  //  default:
                  // return true;
              }
              // Schedules a repaint.
              super.onTouchEvent(event);
              invalidate();
              return true;
          }
          else
          {
              // Schedules a repaint.
              super.onTouchEvent(event);
              //invalidate();
              return true;
          }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setFocusable(false);
        int width = canvas.getWidth();
        int height = canvas.getHeight();
         kSirina=width/10;
         kVisina=height/10;

        Rect kvadrat=new Rect();
        Paint barvaFill=new Paint();
        Paint barvaStroke=new Paint();
        barvaFill.setStyle(Paint.Style.FILL);
        barvaStroke.setStyle(Paint.Style.STROKE);
        barvaStroke.setColor(Color.LTGRAY);
        barvaStroke.setStrokeWidth(4);

        int k=0; //čez polje lokacij
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(poljeLokacij[k]==0) //prazen prostor
                {
                    barvaFill.setColor(Color.WHITE);
                }
                else if(poljeLokacij[k]==1) //neka druga miza
                {
                    barvaFill.setColor(Color.BLACK);
                }
                else if(poljeLokacij[k]==2) //izbrana miza
                {
                    barvaFill.setColor(Color.RED);
                }
                startX =j*kSirina;
                startY =i*kVisina;
                stopX =j*kSirina+kSirina;
                stopY =i*kVisina+kVisina;
                kvadrat.set(startX, startY, stopX, stopY);
                canvas.drawRect(kvadrat,barvaFill);
                canvas.drawRect(kvadrat,barvaStroke);
                k++;
            }
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(parentWidth, parentHeight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public int[] getPoljeLokacij() {
        return poljeLokacij;
    }

    public void setPoljeLokacij(int[] poljeLokacij) {
        this.poljeLokacij = poljeLokacij;
    }

    public int getLokacija() {
        return lokacija;
    }

    public void setLokacija(int lokacija) {
        this.lokacija = lokacija;
    }
}
