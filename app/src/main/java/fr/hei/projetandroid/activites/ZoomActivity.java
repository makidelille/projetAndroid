package fr.hei.projetandroid.activites;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import fr.hei.projetandroid.R;

import java.io.File;

/**
 * Created by julien on 09/12/16.
 */
public class ZoomActivity extends Activity {

    public static final String EXTRA_IMG = "image";

    //begz

    ImageView imageView;
    Matrix matrix = new Matrix();
    Float scale = 1f;
    ScaleGestureDetector Detector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_zoom);

        Intent it = getIntent();
        String img = it.getStringExtra(EXTRA_IMG);

        File image = new File(img);
        if(!image.canRead()) return;


        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
        bitmap = Bitmap.createBitmap(bitmap);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
        Detector = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale (ScaleGestureDetector detector){
                scale = scale*detector.getScaleFactor();
                scale = Math.max(0.1f, Math.min(scale,5f));
                matrix.setScale(scale,scale);
                imageView.setImageMatrix(matrix);
                return true;
            }
        });
    }

    public boolean onTouchEvent(MotionEvent event) {
        Detector.onTouchEvent(event);
        return true;
    }
}
