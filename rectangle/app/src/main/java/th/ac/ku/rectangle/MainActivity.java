package th.ac.ku.rectangle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Rect> arr = new ArrayList<>();
    public class MultiTouchView extends View {

        // In this test, handle maximum of 2 pointer
        final int MAX_POINT_CNT = 2;

        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        float[] x = new float[MAX_POINT_CNT];
        float[] y = new float[MAX_POINT_CNT];
        boolean[] isTouch = new boolean[MAX_POINT_CNT];

        public MultiTouchView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }

        @Override
        protected void onDraw(Canvas canvas) {

            float left = x[0];
            float top = y[0];
            float right = x[1];
            float bottom = y[1];
            for (Rect a:arr) {
                canvas.drawRect(a,paint);
            }
            // TODO Auto-generated method stub
            if (isTouch[0] || isTouch[1]) {
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(1);
                paint.setColor(Color.RED);
            }else {

                Rect rec = new Rect((int)left,(int)top,(int)right,(int)bottom);
                arr.add(rec);
            }
            paint.setColor(Color.rgb(0, 0, 0));
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(left,top,right,bottom,paint);



        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            // TODO Auto-generated method stub
            setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                    MeasureSpec.getSize(heightMeasureSpec));
        }

        @Override
        public boolean onTouchEvent(MotionEvent motionEvent) {
            // TODO Auto-generated method stub

            int pointerIndex = ((motionEvent.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            int pointerId = motionEvent.getPointerId(pointerIndex);
            int action = (motionEvent.getAction() & MotionEvent.ACTION_MASK);
            int pointCnt = motionEvent.getPointerCount();

            if (pointCnt <= MAX_POINT_CNT) {
                if (pointerIndex <= MAX_POINT_CNT - 1) {

                    for (int i = 0; i < pointCnt; i++) {
                        int id = motionEvent.getPointerId(i);
                        x[id] = (int) motionEvent.getX(i);
                        y[id] = (int) motionEvent.getY(i);
                    }

                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            isTouch[pointerId] = true;
                            break;
                        case MotionEvent.ACTION_POINTER_DOWN:
                            isTouch[pointerId] = true;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            isTouch[pointerId] = true;
                            break;
                        case MotionEvent.ACTION_UP:
                            isTouch[pointerId] = false;
                            break;
                        case MotionEvent.ACTION_POINTER_UP:
                            isTouch[pointerId] = false;
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            isTouch[pointerId] = false;
                            break;
                        default:
                            isTouch[pointerId] = false;
                    }
                }
            }

            invalidate();
            return true;
        }
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MultiTouchView(this));
    }
}