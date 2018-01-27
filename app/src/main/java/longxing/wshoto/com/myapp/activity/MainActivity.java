package longxing.wshoto.com.myapp.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;

import longxing.wshoto.com.myapp.R;

public class MainActivity extends Activity {
    static final String tag = "yan";
    ImageView imgView = null;
    ImageView imgView2 = null;
    FaceDetector faceDetector = null;
    FaceDetector.Face[] face;
    Button detectFaceBtn = null;
    final int N_MAX = 2;
    ProgressBar progressBar = null;
    ArrayList<HashMap<String, Integer>> list = new ArrayList<>();

    Bitmap srcImg = null;
    Bitmap srcFace = null;
    Thread checkFaceThread = new Thread() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            Bitmap faceBitmap = detectFace();
            mainHandler.sendEmptyMessage(2);
            Message m = new Message();
            m.what = 0;
            m.obj = faceBitmap;
            mainHandler.sendMessage(m);

        }

    };
    Handler mainHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            //super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Bitmap b = (Bitmap) msg.obj;
                    imgView.setImageBitmap(b);
                    Toast.makeText(MainActivity.this, "检测完毕", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    showProcessBar();
                    break;
                case 2:
                    progressBar.setVisibility(View.GONE);
                    detectFaceBtn.setClickable(false);
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initUI();
        initFaceDetect();
        detectFaceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mainHandler.sendEmptyMessage(1);
                checkFaceThread.start();

            }
        });


    }

    public void initUI() {

        detectFaceBtn = (Button) findViewById(R.id.btn_detect_face);
        imgView = (ImageView) findViewById(R.id.imgview);
        imgView2 = (ImageView) findViewById(R.id.iv2);
        LayoutParams params = imgView.getLayoutParams();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        //      int h = dm.heightPixels;

        srcImg = BitmapFactory.decodeResource(getResources(), R.drawable.kunlong);
        int h = srcImg.getHeight();
        int w = srcImg.getWidth();
        float r = (float) h / (float) w;
        params.width = w_screen;
        params.height = (int) (params.width * r);
        imgView.setLayoutParams(params);
        imgView.setImageBitmap(srcImg);
    }

    public void initFaceDetect() {
        this.srcFace = srcImg.copy(Config.RGB_565, true);
        int w = srcFace.getWidth();
        int h = srcFace.getHeight();
        Log.i(tag, "待检测图像: w = " + w + "h = " + h);
        faceDetector = new FaceDetector(w, h, N_MAX);
        face = new FaceDetector.Face[N_MAX];
    }

    public boolean checkFace(Rect rect) {
        int w = rect.width();
        int h = rect.height();
        int s = w * h;
        Log.i(tag, "人脸 宽w = " + w + "高h = " + h + "人脸面积 s = " + s);
        if (s < 10000) {
            Log.i(tag, "无效人脸，舍弃.");
            return false;
        } else {
            Log.i(tag, "有效人脸，保存.");
            return true;
        }
    }

    public Bitmap detectFace() {
        //      Drawable d = getResources().getDrawable(R.drawable.face_2);
        //      Log.i(tag, "Drawable尺寸 w = " + d.getIntrinsicWidth() + "h = " + d.getIntrinsicHeight());
        //      BitmapDrawable bd = (BitmapDrawable)d;
        //      Bitmap srcFace = bd.getBitmap();
        int nFace = faceDetector.findFaces(srcFace, face);
        Log.i(tag, "检测到人脸：n = " + nFace);
        for (int i = 0; i < nFace; i++) {
            Face f = face[i];
            PointF midPoint = new PointF();
            float dis = f.eyesDistance();

            f.getMidPoint(midPoint);
            int dd = (int) (dis);
            Point eyeLeft = new Point((int) (midPoint.x - dis / 2), (int) midPoint.y);
            Point eyeRight = new Point((int) (midPoint.x + dis / 2), (int) midPoint.y);
            Rect faceRect = new Rect((int) (midPoint.x - dd), (int) (midPoint.y - dd), (int) (midPoint.x + dd), (int) (midPoint.y + dd));
            Log.i(tag, "左眼坐标 x = " + eyeLeft.x + "y = " + eyeLeft.y);
            if (checkFace(faceRect)) {
                Canvas canvas = new Canvas(srcFace);
                Paint p = new Paint();
                p.setAntiAlias(true);
                p.setStrokeWidth(8);
                p.setStyle(Paint.Style.STROKE);
                p.setColor(Color.GREEN);
                canvas.drawCircle(eyeLeft.x, eyeLeft.y, 20, p);
                canvas.drawCircle(eyeRight.x, eyeRight.y, 20, p);
                canvas.drawRect(faceRect, p);

                Message msg = new Message();
                HashMap<String, Integer> map = new HashMap<>();
                map.put("left", (int) (midPoint.x - dd));
                map.put("top", (int) (midPoint.y - dd));
                map.put("width", dd * 2);
                msg.obj = map;
                EventBus.getDefault().post(msg);

            }

        }
        Log.i(tag, "保存完毕");

        //将绘制完成后的faceBitmap返回
        return srcFace;

    }

    public void showProcessBar() {
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.layout_main);
        progressBar = new ProgressBar(MainActivity.this, null, android.R.attr.progressBarStyleLargeInverse); //ViewGroup.LayoutParams.WRAP_CONTENT
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        progressBar.setVisibility(View.VISIBLE);
        //progressBar.setLayoutParams(params);
        mainLayout.addView(progressBar, params);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(Message message) {
        HashMap<String, Integer> map;
        map = (HashMap<String, Integer>) message.obj;
        list.add(map);
        Bitmap bitmap = Bitmap.createBitmap(srcImg, map.get("left"), map.get("top"), map.get("width"), map.get("width"));
        imgView2.setImageBitmap(bitmap);

        if (list.size() == 2) {
            Canvas canvas = new Canvas(srcFace);
            Paint p = new Paint();
            p.setAntiAlias(true);
            p.setStrokeWidth(8);
            p.setStyle(Paint.Style.STROKE);
            p.setColor(Color.GREEN);
            canvas.drawBitmap(bitmap, (float) list.get(0).get("left"), (float) list.get(0).get("top"), p);
        }

    }


}