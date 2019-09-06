package com.eliotshekhtman.clickerquest;

import android.content.Context;

public abstract class tier3 extends mon {
    public tier3(Context c) {
        super(c); size = (int) (getScreenWidth() / 3);
    }
}
