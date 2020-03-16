package home.smart.fly.animations.property.basic;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import home.smart.fly.animations.R;


/**
 */
public class GoodsListAdapter1 extends RecyclerView.Adapter<GoodsListAdapter1.MyViewHolder> {
    private ArrayList<GoodItem> mData;
    private LayoutInflater mLayoutInflater;
    private CallBackListener mCallBackListener;

    public GoodsListAdapter1(Context context, ArrayList<GoodItem> mData) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mData = mData;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.adapter_shopping_cart_item_1, null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mShoppingCartItemIv.setImageBitmap(mData.get(position).getmGoodsBitmap());
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mShoppingCartItemIv;
        ImageView minus, plus;
        TextView num;


        public MyViewHolder(View view) {
            super(view);
            mShoppingCartItemIv = (ImageView) view.findViewById(R.id.iv_shopping_cart_item);
            num = (TextView) view.findViewById(R.id.num);
            minus = (ImageView) view.findViewById(R.id.minus);
            plus = (ImageView) view.findViewById(R.id.plus);


            plus.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mShoppingCartItemIv != null && mCallBackListener != null) {
                                int temp = Integer.parseInt(num.getText().toString());
                                num.setText(String.valueOf(++temp));
                                mCallBackListener.callBackPlus1(mShoppingCartItemIv);
                            }

                        }
                    });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mShoppingCartItemIv != null && mCallBackListener != null) {
                        int temp = Integer.parseInt(num.getText().toString());
                        if (temp > 0) {
                            num.setText(String.valueOf(--temp));
                            mCallBackListener.callBackMinus1(mShoppingCartItemIv);
                        }

                    }
                }
            });
        }


    }


    public void setCallBackListener(CallBackListener mCallBackListener) {
        this.mCallBackListener = mCallBackListener;
    }

    public interface CallBackListener {
        void callBackPlus1(ImageView goodsImg);

        void callBackMinus1(ImageView goodsImg);
    }
}
