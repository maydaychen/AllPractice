package longxing.wshoto.com.myapp.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import longxing.wshoto.com.myapp.R;
import longxing.wshoto.com.myapp.jinjie.ViewActivity;

public class JinjieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jinjie);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(this);
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com"));
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
                builder.setContentIntent(pendingIntent);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.kunlong));
                builder.setAutoCancel(true);
                builder.setContentTitle("普通通知");
                Notification notification = builder.build();
                nm.notify(0, notification);
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                startActivity(new Intent(JinjieActivity.this, ViewActivity.class));
                break;
            case R.id.button4:
                break;
        }
    }
}
