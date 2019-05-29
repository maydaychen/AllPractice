package longxing.wshoto.com.myapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import longxing.wshoto.com.myapp.R;

public class GlideActivity extends AppCompatActivity {

    @BindView(R.id.imageView2)
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);

        Glide.with(getApplicationContext()).load("http://pic37.nipic.com/20140113/8800276_184927469000_2.png").into(imageView2);

//          加载本地图片
//        int resource = R.drawable.kunlong;
//        Glide.with(getApplicationContext()).load(resource).into(imageView2);
    }
}
