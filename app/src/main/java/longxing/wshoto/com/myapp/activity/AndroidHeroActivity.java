package longxing.wshoto.com.myapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import longxing.wshoto.com.myapp.R;

public class AndroidHeroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_android_hero);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button11, R.id.button12, R.id.button13})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button11:
                startActivity(new Intent(AndroidHeroActivity.this, HeroChapter3Activity.class));
                break;
            case R.id.button12:
                break;
            case R.id.button13:
                break;
        }
    }
}
