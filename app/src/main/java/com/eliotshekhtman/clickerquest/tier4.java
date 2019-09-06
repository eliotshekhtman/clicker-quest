package com.eliotshekhtman.clickerquest;

import android.content.Context;

public abstract class tier4 extends mon {
    public tier4(Context c) {
        super(c); size = (int) (getScreenWidth() / 3);
        attackDistr = new int[] {0,1,0,0,0,0,0,0,0,0,0,0,0,0};
    }
}
