package home.smart.fly.animations.ui.activity;

import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import home.smart.fly.animations.R;
import home.smart.fly.animations.customview.EasySeekBar;
import home.smart.fly.animations.customview.TransformView;


public class ViewTransformActivity extends AppCompatActivity {

    @BindView(R.id.transformView)
    TransformView transformView;

    @BindView(R.id.rotateX)
    EasySeekBar rotateX;
    @BindView(R.id.rotateY)
    EasySeekBar rotateY;
    @BindView(R.id.scaleX)
    EasySeekBar scaleX;
    @BindView(R.id.scaleY)
    EasySeekBar scaleY;
    @BindView(R.id.fixed)
    CheckBox fixed;
    @BindView(R.id.FlipViewSel)
    RadioGroup FlipViewSel;
    @BindView(R.id.rotateZ)
    EasySeekBar rotateZ;
    @BindView(R.id.negative)
    CheckBox negative;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transform);
        ButterKnife.bind(this);
        addListener();
    }

    private void addListener() {
        fixed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                transformView.setFixed(isChecked);
            }
        });

        negative.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rotateX.setNegate(isChecked);
                rotateY.setNegate(isChecked);
                rotateZ.setNegate(isChecked);
            }
        });

        FlipViewSel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.LRFlipView) {
                    transformView.setLeftRightFlipView(true);
                    transformView.setUpDownFlipView(false);
                } else if (checkedId == R.id.UDFlipView) {
                    transformView.setUpDownFlipView(true);
                    transformView.setLeftRightFlipView(false);
                } else {
                    transformView.setUpDownFlipView(false);
                    transformView.setLeftRightFlipView(false);
                }
            }
        });


        rotateX.setOnProgressChangedListener(new EasySeekBar.onProgressChangeListener() {
            @Override
            public void onChangedValue(int value) {
                transformView.setDegreeX(value);
            }
        });
        rotateY.setOnProgressChangedListener(new EasySeekBar.onProgressChangeListener() {
            @Override
            public void onChangedValue(int value) {
                transformView.setDegreeY(value);
            }
        });
        rotateZ.setOnProgressChangedListener(new EasySeekBar.onProgressChangeListener() {
            @Override
            public void onChangedValue(int value) {
                transformView.setDegreeZ(value);
            }
        });
        scaleX.setOnProgressChangedListener(new EasySeekBar.onProgressChangeListener() {
            @Override
            public void onChangedValue(int value) {
                transformView.setScaleX(1 + value / 100.0f);
            }
        });
        scaleY.setOnProgressChangedListener(new EasySeekBar.onProgressChangeListener() {
            @Override
            public void onChangedValue(int value) {
                transformView.setScaleY(1 + value / 100.f);
            }
        });


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        rotateX.setMax(180);
        rotateY.setMax(180);
        rotateZ.setMax(360);
        scaleX.setMax(100);
        scaleY.setMax(100);
        scaleX.setProgresss(0);
        scaleY.setProgresss(0);
    }
}
