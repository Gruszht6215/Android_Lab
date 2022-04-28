package th.ac.ku.multitouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    class MyView extends View {
        Paint paint = new Paint();
        private float[] objects = {};
        float xPos = 0;
        float yPos = 0;

        public MyView(Context context) {
            super(context);
        }

        public void setObjects(float[] objects) {
            this.objects = objects;
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            String s = event.getPointerCount() + "";
            for (int i = 0; i < event.getPointerCount();i++){
                xPos = event.getX(i);
                yPos = event.getY(i);
                s = event.getX(i)+","+ event.getY(i);
            }
            invalidate();
            int eventaction = event.getAction();
            switch (eventaction) {
                case MotionEvent.ACTION_UP:
                    objects = addX(objects.length,objects,xPos);
                    objects = addX(objects.length,objects,yPos);
                    setObjects(objects);
                    invalidate();
                    Log.i("vac", "STOP");
                    break;
            }

            Log.i("vac", "onTouchEvent: " + s);
            return true;
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setColor(Color.rgb(0, 0, 0));
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);

            float start;
            if (xPos > yPos){
                start = xPos-yPos;
            }else{
                start = yPos-xPos;
            }
            canvas.drawRect(start,start,xPos, yPos, paint);

            for (int i = 0; i < objects.length; i+=2) {
                xPos = objects[i];
                yPos = objects[i+1];
                String s = xPos+","+ yPos;
                Log.i("vac", "DrawingXY: "+ s);
                int io = i+1;
                Log.i("vac", "X+Y: "+ i+"," + io);


                if (xPos > yPos){
                    start = xPos-yPos;
                }else{
                    start = yPos-xPos;
                }
                canvas.drawRect(start,start,xPos, yPos, paint);
            }

            Log.i("vac", "Drawing");
        }

        public float[] addX(int n, float arr[], float x)
        {
            int i;
            float newarr[] = new float[n + 1];
            for (i = 0; i < n; i++)
                newarr[i] = arr[i];
            newarr[n] = x;
            return newarr;
        }
    }
}