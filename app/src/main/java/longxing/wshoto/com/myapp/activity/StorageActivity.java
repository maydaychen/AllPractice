package longxing.wshoto.com.myapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import longxing.wshoto.com.myapp.R;
import longxing.wshoto.com.myapp.StorageBean;
import longxing.wshoto.com.myapp.StorageUtils;
import longxing.wshoto.com.myapp.widget.CircleProgressViewEasy;

import static longxing.wshoto.com.myapp.MemorySpaceCheck.getSDTotalSize;
import static longxing.wshoto.com.myapp.MemorySpaceCheck.getSystemAvailableSize;

public class StorageActivity extends AppCompatActivity {
    @BindView(R.id.test)
    CircleProgressViewEasy mTest;
    @BindView(R.id.tv_storage_1)
    TextView mTvStorage1;
    @BindView(R.id.cb_starage_sd)
    CheckBox mCbStarageSd;
    @BindView(R.id.cb_starage_sd2)
    CheckBox mCbStarageSd2;
    @BindView(R.id.test2)
    CircleProgressViewEasy mTest2;
    @BindView(R.id.tv_storage_2)
    TextView mTvStorage2;
    @BindView(R.id.cv_sd2)
    CardView mCvSd2;

    private boolean IS_INNER = true;
    private String path;//默认存储路径
    private boolean HAS_SD = false;//是否拥有外置SD


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        ButterKnife.bind(this);
        mTest.setProgress((int) ((getSDTotalSize() - getSystemAvailableSize()) / (double) getSDTotalSize() * 100));
        DecimalFormat df = new DecimalFormat("######0.00");

        String result = String.format(getResources().getString(R.string.storage),
                df.format((float) getSystemAvailableSize() / 1024 / 1024 / 1024) + "",
                df.format((float) getSDTotalSize() / 1024 / 1024 / 1024) + "");//对应xml中定义的123顺序
        mTvStorage1.setText(result);

        ArrayList<StorageBean> storageData = StorageUtils.getStorageData(this);
        if (storageData.size() == 2) {
            mCvSd2.setVisibility(View.VISIBLE);
            mTest2.setProgress((int) ((storageData.get(1).getTotalSize() - storageData.get(1).getAvailableSize()) / (double) storageData.get(1).getTotalSize() * 100));

            String result2 = String.format(getResources().getString(R.string.storage),
                    df.format((float) storageData.get(1).getAvailableSize() / 1024 / 1024 / 1024) + "",
                    df.format((float) storageData.get(1).getTotalSize() / 1024 / 1024 / 1024) + "");//对应xml中定义的123顺序
            mTvStorage2.setText(result2);
        }
    }
}
