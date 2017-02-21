package cn.vlooks.www.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.vlooks.www.app.R;
import cn.vlooks.www.app.bean.Constants;
import cn.vlooks.www.app.bean.Point;

/**
 * 自定义View
 */
public class CustomeView extends View {
    private List<Point> points = new ArrayList<Point>();
    private Paint paint;
    /**
     * 默认，该多边形有三条边
     */
    private int edge = 3;
    private Canvas mCanvas;

    public CustomeView(Context context) {
        this(context, null);
        mCanvas = new Canvas();
    }

    public CustomeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public CustomeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setTextSize(16);
    }


    public void setEdge(int edge, List<Point> pointList) {
        this.edge = edge;
        this.points = pointList;
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        paint.setColor(Color.TRANSPARENT);
        canvas.drawRect(0, 0, Constants.CustomeView_w, Constants.CustomeView_h, paint);

        paint.setColor(getResources().getColor(R.color.green_73D098));
        paint.setAlpha(150);
        Path path = new Path();
        if (points.size() > 0) {
            canvas.drawCircle(points.get(0).getX(), points.get(0).getY(), 1,
                    paint);
            path.moveTo(points.get(0).getX(), points.get(0).getY());
        } else {
            return;
        }
        for (int i = 1; i < edge && i < points.size(); i++) {
            canvas.drawCircle(points.get(i).getX(), points.get(i).getY(), 1,
                    paint);
            path.lineTo(points.get(i).getX(), points.get(i).getY());
        }
        if (points.size() == edge) {
            path.lineTo(points.get(0).getX(), points.get(0).getY());
        }
        canvas.drawPath(path, paint);
        paint.setColor(Color.GREEN);
    }


}
