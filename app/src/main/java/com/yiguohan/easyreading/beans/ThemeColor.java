package com.yiguohan.easyreading.beans;

/**
 * Created by yiguohan on 2017/9/14.
 * GitHub: https://github.com/yiguohan
 * E-mail: yiguohan@gmail.com
 */

public class ThemeColor {
    int color;
    boolean isChosen = false;

    public ThemeColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }
}
