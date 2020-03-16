package home.smart.fly.animations.adapter;

import android.graphics.Bitmap;

/**
 * Created by Rookie on 2017/7/20.
 */

public class Player {

    Bitmap mBitmap;
    String name;
    //气泡（球员）被设置了真实的头像
    boolean isSetReal;
    boolean isSelected;

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }


    /**
     * 气泡（球员）被设置了真实的头像
     */
    public boolean isSetReal() {
        return isSetReal;
    }


    public void setSetReal(boolean setReal) {
        isSetReal = setReal;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
