package home.smart.fly.animations.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.viewpager.widget.PagerAdapter;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import home.smart.fly.animations.R;
import home.smart.fly.animations.utils.ColorAnimator;
import home.smart.fly.animations.utils.StatusBarUtil;
import home.smart.fly.animations.utils.Tools;

public class IModeActivity extends AppCompatActivity {
    private static final String URL = "http://upload.jianshu.io/admin_banners/web_images/3107/a9416a7506d328428321ffb84712ee5eb551463e.jpg";
    private static final String TAG = "IModeActivity";
    private RelativeLayout head;
    private NestedScrollView mNestedScrollView;

    private Context mContext;

    //
    private ViewPager mViewPager;
    private int lastColor = Color.TRANSPARENT;
    private ColorAnimator mColorAnimator;
    private TextView title;
    private ImageView search;

    private MyHandler mMyHandler = new MyHandler();

    private List<String> pics = new ArrayList<>();

    private ScheduledExecutorService mScheduledExecutorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_imode);
        //
        StatusBarUtil.setColor(this, R.color.transparent, 0);
        //定义动画初始颜色和最终颜色值
        mColorAnimator = new ColorAnimator(Color.TRANSPARENT, getResources().getColor(R.color.colorPrimaryDark));
        initDatas();
        initView();
    }

    private void initDatas() {
        String data = Tools.readStrFromAssets("pics.data", mContext);
        pics = new Gson().fromJson(data, new TypeToken<List<String>>() {
        }.getType());

        pics = pics.subList(0, 4);
    }

    private void initView() {
        search = (ImageView) findViewById(R.id.search);
        title = (TextView) findViewById(R.id.title);
        head = (RelativeLayout) findViewById(R.id.head);
        head.setBackgroundColor(Color.TRANSPARENT);
        mNestedScrollView = (NestedScrollView) findViewById(R.id.scrollview);
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, final int scrollY, int oldScrollX, int oldScrollY) {
                float fraction = (float) scrollY / (mViewPager.getHeight());
                int color = mColorAnimator.getFractionColor(fraction);
                if (color != lastColor) {
                    lastColor = color;
                    head.setBackgroundColor(color);
                    StatusBarUtil.setColor(IModeActivity.this, color, 0);
                }

                if (fraction < 1) {
                    title.setVisibility(View.INVISIBLE);
                    search.setVisibility(View.INVISIBLE);
                } else {
                    title.setVisibility(View.VISIBLE);
                    search.setVisibility(View.VISIBLE);
                }
            }
        });


        mViewPager = (ViewPager) findViewById(R.id.banner);
        mViewPager.setAdapter(new MyPagerAdapter());
        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        mScheduledExecutorService.scheduleAtFixedRate(new MyTask(), 3, 3, TimeUnit.SECONDS);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    int position = mViewPager.getCurrentItem();
                    final int lastPosition = mViewPager.getAdapter().getCount() - 1;
                    Log.e(TAG, "onPageScrollStateChanged: lastPosition " + lastPosition);
                    if (position == 0) {
                        mViewPager.setCurrentItem(lastPosition == 0 ? 0 : lastPosition - 1, false);
                    } else if (position == lastPosition) {
                        mViewPager.setCurrentItem(1, false);
                    }
                }
            }
        });
//        mViewPager.setPageTransformer(false, new MyFlipTransformer());
//        mViewPager.setPageTransformer(false, new MyCubeTransformer());
        mViewPager.setPageTransformer(false, new MyZoomCenterTransformer());
    }


    private class MyTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            if (position < -1) {
                ViewCompat.setScaleY(page, 0.9f);
            } else if (position <= 1) {
                float scale = Math.max(0.9f, 1 - Math.abs(position));
                ViewCompat.setScaleY(page, scale);
            } else {
                ViewCompat.setScaleY(page, 0.9f);
            }
        }
    }

    private class MyCubeTransformer implements ViewPager.PageTransformer {
        private float baseRotate = 90.0f;

        @Override
        public void transformPage(View view, float position) {
            if (position < -1) {
                ViewCompat.setPivotX(view, view.getMeasuredWidth());
                ViewCompat.setPivotY(view, view.getMeasuredHeight() * 0.5f);
                ViewCompat.setRotationY(view, 0);
            } else if (position <= 0) {
                ViewCompat.setPivotX(view, view.getMeasuredWidth());
                ViewCompat.setPivotY(view, view.getMeasuredHeight() * 0.5f);
                ViewCompat.setRotationY(view, baseRotate * position);
            } else if (position <= 1) {
                ViewCompat.setPivotX(view, 0);
                ViewCompat.setPivotY(view, view.getMeasuredHeight() * 0.5f);
                ViewCompat.setRotationY(view, baseRotate * position);
            } else {
                ViewCompat.setPivotX(view, view.getMeasuredWidth());
                ViewCompat.setPivotY(view, view.getMeasuredHeight() * 0.5f);
                ViewCompat.setRotationY(view, 0);
            }
        }
    }

    private class MyFlipTransformer implements ViewPager.PageTransformer {
        private static final float BASE_ROTATION = 180.0f;

        @Override
        public void transformPage(View page, float position) {
            if (position < -1) {

            } else if (position <= 0) {
                ViewCompat.setTranslationX(page, -page.getWidth() * position);
                float rotation = (BASE_ROTATION * position);
                ViewCompat.setRotationY(page, rotation);

                if (position > -0.5) {
                    page.setVisibility(View.VISIBLE);
                } else {
                    page.setVisibility(View.INVISIBLE);
                }
            } else if (position <= 1) {
                ViewCompat.setTranslationX(page, -page.getWidth() * position);
                float rotation = (BASE_ROTATION * position);
                ViewCompat.setRotationY(page, rotation);

                if (position < 0.5) {
                    page.setVisibility(View.VISIBLE);
                } else {
                    page.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    private class MyZoomCenterTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View view, float position) {
            if (position < -1) {

            } else if (position <= 0) {
                ViewCompat.setTranslationX(view, -view.getWidth() * position);

                ViewCompat.setPivotX(view, view.getWidth() * 0.5f);
                ViewCompat.setPivotY(view, view.getHeight() * 0.5f);
                ViewCompat.setScaleX(view, 1 + position);
                ViewCompat.setScaleY(view, 1 + position);

                if (position < -0.95f) {
                    ViewCompat.setAlpha(view, 0);
                } else {
                    ViewCompat.setAlpha(view, 1);
                }
            } else if (position <= 1) {
                ViewCompat.setTranslationX(view, -view.getWidth() * position);

                ViewCompat.setPivotX(view, view.getWidth() * 0.5f);
                ViewCompat.setPivotY(view, view.getHeight() * 0.5f);
                ViewCompat.setScaleX(view, 1 - position);
                ViewCompat.setScaleY(view, 1 - position);

                if (position > 0.95f) {
                    ViewCompat.setAlpha(view, 0);
                } else {
                    ViewCompat.setAlpha(view, 1);
                }
            }
        }
    }


    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int current = mViewPager.getCurrentItem();
            Log.e(TAG, "handleMessage: current " + current);
            mViewPager.setCurrentItem((current + 1));
        }
    }


    private class MyTask implements Runnable {

        @Override
        public void run() {
            mMyHandler.sendEmptyMessage(0);
        }
    }


    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.e(TAG, "instantiateItem: " + position);
            LayoutInflater inflater = LayoutInflater.from(container.getContext());
            View itemView = inflater.inflate(R.layout.recycler_view_header, container, false);
            ImageView mImageView = (ImageView) itemView.findViewById(R.id.image);
            TextView mTextView = (TextView) itemView.findViewById(R.id.index);

            if (position == 0) {
                position = pics.size() - 1;
            } else if (position == pics.size() + 1) {
                position = 0;
            } else {
                position = position - 1;
            }

            Glide.with(mContext).load(pics.get(position)).into(mImageView);
            mTextView.setText("Index-" + position);
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return pics.size() + 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mScheduledExecutorService.isShutdown()) {
            mScheduledExecutorService.shutdownNow();
        }
    }
}
