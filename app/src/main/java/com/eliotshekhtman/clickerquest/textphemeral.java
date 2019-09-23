package com.eliotshekhtman.clickerquest;

import android.content.Context;
import android.graphics.Color;

public abstract class textphemeral extends ephemeral {
    String text;
    colorName color;
    int alpha;

    public textphemeral(String s, colorName n, Context c, float x, float y, double yd, int sz, long sc) {
        super(c, x, y, yd, sz, sc);
        text = s; color = n;
        alpha  = 255;
    }

    public boolean update(long fps) {
        boolean res = super.update(fps);
        alpha = (int) (1 - (System.currentTimeMillis() - spawned) / scope) * 255;
        if(alpha < 0) alpha = 0;
        return res;
    }

    public String getText() { return text; }
    public int getAlpha() { return alpha; }

    enum colorName {
        RED, GREEN, BLUE, YELLOW
    }

    public int interpretColor() {
        switch (color) {
            case RED:
                return Color.argb(alpha,  255, 0, 0);
            case GREEN:
                return Color.argb( alpha, 0, 255, 0);
            case BLUE:
                return Color.argb( alpha, 0, 0, 255);
            case YELLOW:
                return Color.argb(alpha, 200, 255, 153);
        }
        return Color.argb(alpha, 0, 0, 0);
    }

}
