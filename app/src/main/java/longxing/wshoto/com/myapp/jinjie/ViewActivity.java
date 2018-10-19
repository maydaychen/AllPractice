package longxing.wshoto.com.myapp.jinjie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import longxing.wshoto.com.myapp.R;

public class ViewActivity extends AppCompatActivity {

    @BindView(R.id.customView)
    CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view2);
        ButterKnife.bind(this);

        customView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.translate));
    }
}
