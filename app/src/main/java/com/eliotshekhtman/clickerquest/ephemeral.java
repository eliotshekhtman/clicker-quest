package com.eliotshekhtman.clickerquest;

import android.content.Context;
import android.content.res.Resources;

public abstract class ephemeral {
    public long spawned; // When the ephemeral was created
    protected int size;
    protected long scope; // How long the ephemeral lasts for
    protected double yDrift; // Speed to drift up at over time
    protected float xpos;
    protected float ypos;
    protected Context c;

    public ephemeral() {
        scope = 1000;
        yDrift = 0; size = 0;
        xpos = 0; ypos = 0;
        spawned = System.currentTimeMillis();
    }

    public ephemeral(Context c) {
        this();
        c = this.c;
    }

    public ephemeral(Context c, float x, float y, double yd, int sz, long sc) {
        this(c);
        xpos = x; ypos = y; yDrift = yd; scope = sc; size = sz;
    }

    public float getXpos() { return xpos; }
    public float getYpos() { return ypos; }
    public int getSize() { return size; }

    public boolean update(long fps) {
        ypos -= yDrift * 300 / fps;
        if(System.currentTimeMillis() - spawned > scope) return false;
        else return true;
    }

    protected static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    protected static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
