package longxing.wshoto.com.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class AnimationActivity extends AppCompatActivity {

    private Button btn_scale, btn_rotate, btn_translate, btn_alpha, btn_set;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        bindViews();

    }

    private void bindViews() {
        btn_alpha = (Button) findViewById(R.id.btn_alpha);
        btn_rotate = (Button) findViewById(R.id.btn_rotate);
        btn_scale = (Button) findViewById(R.id.btn_scale);
        btn_translate = (Button) findViewById(R.id.btn_translate);
        btn_set = (Button) findViewById(R.id.btn_set);
        image = (ImageView) findViewById(R.id.image);
    }

    public void click(View view) {

        switch (view.getId()) {

            case R.id.btn_alpha:
                /** 资源文件中实现方式 **/
            /*
             * Animation alpha = AnimationUtils.loadAnimation(this,
             * R.anim.anim_alpha); image.startAnimation(alpha);
             */

                /** java代码中实现方式 **/
                Animation alpha = new AlphaAnimation(0.1f, 1);
                alpha.setDuration(2000);
                image.startAnimation(alpha);

                break;
            case R.id.btn_rotate:
                /** 资源文件中实现方式 **/
            /*
             * Animation rotate = AnimationUtils.loadAnimation(this,
             * R.anim.anim_rotate); image.startAnimation(rotate);
             */

                /** java代码中实现方式 **/
                Animation rotate = new RotateAnimation(0, 360);
                rotate.setDuration(2000);
                image.startAnimation(rotate);
                break;
            case R.id.btn_scale:
                /** 资源文件中实现方式 **/
            /*
             * Animation scale = AnimationUtils.loadAnimation(this,
             * R.anim.anim_scale); image.startAnimation(scale);
             */

                /** java代码中实现方式 **/
                Animation scale = new ScaleAnimation(0, 1, 0, 1);
                scale.setDuration(2000);
                image.startAnimation(scale);

                break;
            case R.id.btn_translate:
                /** 资源文件中实现方式 **/
            /*
             * Animation translate = AnimationUtils.loadAnimation(this,
             * R.anim.anim_translate); image.startAnimation(translate);
             */

                /** java代码中实现方式 **/
                Animation translate = new TranslateAnimation(0, 100, 0, 0);
                translate.setDuration(2000);
                image.startAnimation(translate);

                break;
            case R.id.btn_set:
                Animation set = AnimationUtils.loadAnimation(this, R.anim.anim_set);
                image.startAnimation(set);

                break;

        }

    }

}