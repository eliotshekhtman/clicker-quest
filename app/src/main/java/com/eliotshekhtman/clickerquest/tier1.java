package com.eliotshekhtman.clickerquest;

import android.content.Context;

public abstract class tier1 extends mon {
    public tier1(Context c) {
        super(c); size = (int) (getScreenWidth() / 6);
    }
}
