package com.zigabincl.com.natakar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by SPACE MARINE GENERAL on 31.5.2016.
 */
public class MizeLokacije extends View {
    private int startX;
    private int stopX;
    private int startY;
    private int stopY;
    private RelativeLayout elementVrste;

    public MizeLokacije(Context context) {
        super(context);
    }

    public MizeLokacije(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MizeLokacije(Context context, AttributeSet ats, int defaultStyle) {
        super(context, ats, defaultStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setFocusable(false);
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        //elementVrste = (RelativeLayout) this.findViewById(R.id.elementVrste);

        int kSirina=width/10;
        int kVisina=height/10;
        //int kVisina=elementVrste.getHeight()/10;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rect kvadrat=new Rect();
                Paint barva=new Paint();
                barva.setStyle(Paint.Style.FILL);
                barva.setColor(Color.GREEN);
                barva.setStyle(Paint.Style.STROKE);
                barva.setColor(Color.BLACK);
                barva.setStrokeWidth(10);
                startX =j*kSirina;
                startY =i*kVisina;
                stopX =j*kSirina+kSirina;
                stopY =i*kVisina+kVisina;
                kvadrat.set(startX, startY, stopX, stopY);
                canvas.drawRect(kvadrat,barva);
            }
        }
    }
}
