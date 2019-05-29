package longxing.wshoto.com.myapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;
import longxing.wshoto.com.myapp.AnimationActivity;
import longxing.wshoto.com.myapp.R;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SumActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private static final String[] permissionManifest = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private final int PERMISSION_REQUEST_CODE = 0x001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        permissionCheck();
    }

    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                startActivity(new Intent(SumActivity.this, MainActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(SumActivity.this, AnimationActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(SumActivity.this, ViewActivity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(SumActivity.this, TestActivity.class));
                break;
            case R.id.button5:
                startActivity(new Intent(SumActivity.this, BackgroundActivity.class));
                break;
            case R.id.button6:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(
                            CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{CAMERA, WRITE_EXTERNAL_STORAGE, RECORD_AUDIO},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    } else {
                        record();
                    }
                } else {
                    record();
                }
                break;
            case R.id.button7:
                startActivity(new Intent(SumActivity.this, StorageActivity.class));
                break;
            case R.id.button8:
                startActivity(new Intent(SumActivity.this, AndroidHeroActivity.class));
                break;
            case R.id.button9:
                startActivity(new Intent(SumActivity.this, JinjieActivity.class));
                break;
            case R.id.button10:
                startActivity(new Intent(SumActivity.this, GlideActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, "调用照相机完毕", Toast.LENGTH_SHORT).show();
        super.onActivityResult(requestCode, resultCode, data);

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
//            if (permissions[0].equals(WRITE_EXTERNAL_STORAGE)
//                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                //用户同意使用write
//                record();
//            } else {
//                //用户不同意，自行处理即可
//                finish();
//            }
//        }
//    }

    private void record() {
        Intent intent = new Intent();
        intent.setAction("android.media.action.VIDEO_CAPTURE");
        intent.addCategory("android.intent.category.DEFAULT");

        // 保存录像到指定的路径
        File file = new File("/sdcard/video.mp4");
        Uri uri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
        intent.putExtra(MediaStore.EXTRA_FULL_SCREEN, false);

        startActivityForResult(intent, 0);
    }

    private void permissionCheck() {
        if (Build.VERSION.SDK_INT >= 23) {
            boolean permissionState = true;
            for (String permission : permissionManifest) {
                if (ContextCompat.checkSelfPermission(this, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    permissionState = false;
                }
            }
            if (!permissionState) {
                ActivityCompat.requestPermissions(this, permissionManifest, PERMISSION_REQUEST_CODE);
            } else {
//                setSupportCameraSize();
            }
        } else {
//            setSupportCameraSize();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    if (Manifest.permission.CAMERA.equals(permissions[i])) {
//                        setSupportCameraSize();
                    } else if (Manifest.permission.RECORD_AUDIO.equals(permissions[i])) {

                    }
                }
            }
        }
    }
}
