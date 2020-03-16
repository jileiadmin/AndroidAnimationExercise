package home.smart.fly.animations.master.ui;

import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xw.repo.BubbleSeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import home.smart.fly.animations.R;
import home.smart.fly.animations.master.widget.MasterFilterView;

public class MasterFilterActivity extends AppCompatActivity {
    private static final String TAG = "MasterFilterActivity";
    @BindView(R.id.filterView)
    MasterFilterView filterView;
    @BindView(R.id.red)
    BubbleSeekBar red;
    @BindView(R.id.green)
    BubbleSeekBar green;
    @BindView(R.id.blue)
    BubbleSeekBar blue;
    @BindView(R.id.red1)
    BubbleSeekBar red1;
    @BindView(R.id.green1)
    BubbleSeekBar green1;
    @BindView(R.id.blue1)
    BubbleSeekBar blue1;
    @BindView(R.id.colorMultiply)
    TextView colorMultiply;
    @BindView(R.id.colorAdd)
    TextView colorAdd;
    @BindView(R.id.container)
    ScrollView container;

    private ColorFilter mColorFilter;

    int r = 255, g = 255, b = 255;
    int r1, g1, b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_master_filter);
        ButterKnife.bind(this);
        testFilter();
        setTitle("Paint ColorFilter 使用实例");
    }

    private void calculate() {
        int value = r << 16 | g << 8 | b;
        int value1 = r1 << 16 | g1 << 8 | b1;


        Log.e(TAG, "calculate: valueString=" + "0x" + Integer.toHexString(value));

        Log.e(TAG, "calculate: valueString1=" + "0x" + Integer.toHexString(value1));


        colorMultiply.setText("LightingColorFilter-colorMultiply: 0X" + Integer.toHexString(value).toUpperCase());
        colorAdd.setText("LightingColorFilter-colorAdd: 0X" + Integer.toHexString(value1).toUpperCase());
        mColorFilter = new LightingColorFilter(value, value1);
        filterView.setFilterAndUpdate(mColorFilter);

    }

    private void testFilter() {

        red.setOnProgressChangedListener(new SeekBarListener() {
            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat,boolean fromUser) {
                super.onProgressChanged(bubbleSeekBar, progress, progressFloat,fromUser);
                r = progress;
                calculate();
            }
        });
        green.setOnProgressChangedListener(new SeekBarListener() {
            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat,boolean fromUser) {
                super.onProgressChanged(bubbleSeekBar, progress, progressFloat,fromUser);
                g = progress;
                calculate();
            }
        });
        blue.setOnProgressChangedListener(new SeekBarListener() {
            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat,boolean fromUser) {
                super.onProgressChanged(bubbleSeekBar, progress, progressFloat,fromUser);
                b = progress;
                calculate();
            }
        });
        red1.setOnProgressChangedListener(new SeekBarListener() {
            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat,boolean fromUser) {
                super.onProgressChanged(bubbleSeekBar, progress, progressFloat,fromUser);
                r1 = progress;
                calculate();
            }
        });
        green1.setOnProgressChangedListener(new SeekBarListener() {


            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat,boolean fromUser) {
                super.onProgressChanged(bubbleSeekBar, progress, progressFloat,fromUser);
                g1 = progress;
                calculate();
            }
        });
        blue1.setOnProgressChangedListener(new SeekBarListener() {
            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat,boolean fromUser) {
                super.onProgressChanged(bubbleSeekBar, progress, progressFloat,fromUser);
                b1 = progress;
                calculate();
            }
        });


    }


    private class SeekBarListener implements BubbleSeekBar.OnProgressChangedListener {


        @Override
        public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

        }

        @Override
        public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

        }

        @Override
        public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

        }


    }
}
