package longxing.wshoto.com.myapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import longxing.wshoto.com.myapp.R;
import longxing.wshoto.com.myapp.widget.CircleProgressViewEasy;

public class ViewActivity extends AppCompatActivity {
    @BindView(R.id.cpv_test)
    CircleProgressViewEasy mCpvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        mCpvTest.setProgress(80);

    }
}
