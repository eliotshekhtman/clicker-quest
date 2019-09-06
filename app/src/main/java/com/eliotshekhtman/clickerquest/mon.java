package com.eliotshekhtman.clickerquest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public abstract class mon {
    public String name;
    public int attack;
    public int health;
    public double speed;
    public int[] resources; // 6: GOLD, HEALTH, FUR, IRON, SCALE, Q
    public int size;
    public int healthgain;
    public int[] attackDistr;
    Context context;

    Bitmap pic;
    boolean swarm;
    int snumber;

    long lastAttack;

    public float xpos;
    public float zpos;
    double exs;
    double ezs;

    public mon() {}

    public mon(Context c) {
        resources = new int[] {0,0,0,0,0,0};
        context = c;
        healthgain = 0;
        lastAttack = System.currentTimeMillis();
        ezs = 8; exs = 0; zpos = 0; xpos = (int) (Math.random() * getScreenWidth());
    }

    protected Bitmap makePicture(int id, int s) {
        Bitmap b = BitmapFactory.decodeResource(context.getResources(), id);
        b = Bitmap.createScaledBitmap(b, s, s, false);
        return b;
    }

    protected static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    protected static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    protected double fuzz() {
        double a = Math.random(); double b = Math.random(); double c = Math.random(); double d = Math.random();
        return (a+b+c+d)/2;
    }

    protected int chooseBodyPart() {
        int tot = attackDistr[0]+attackDistr[1]+attackDistr[2]+attackDistr[3]+attackDistr[4]+attackDistr[5]+attackDistr[6]+
                attackDistr[7]+attackDistr[8]+attackDistr[9]+attackDistr[10]+attackDistr[11]+attackDistr[12]+attackDistr[13];
        int num = (int) (Math.random() * tot) + 1;
        for(int i = 0; i < 14; i++) {
            if(num <= attackDistr[i])
                return i;
            else
                num -= attackDistr[i];
        }
        return 13;
    }

    public int[] getAttack() {
        if(System.currentTimeMillis()-lastAttack < 1000/speed) return new int[] {0,0};
        int[] ret = new int[2];
        ret[1] = chooseBodyPart();
        ret[0] = (int) (fuzz() * attack);
        lastAttack = System.currentTimeMillis();
        return ret;
    }

    protected void move(long fps) {
        // At the start, the speed in the x direction will be zero - rectify this
        if(exs == 0) exs = speed;
        // If in the air, gravity accelerates downwards
        if(zpos < (float) (getScreenHeight() * 3.0/5) - size) {
            ezs += 3.0/fps;
        }
        int prob = (int) (Math.random() * 350) + 1; // change 100 to m.variability
        // If on the ground, there's a chance to change directions or jump
        if (prob == 1 & zpos >= (float) (getScreenHeight() * 3.0/5) - size)
            exs = (-1) * exs;
        else if (prob <= 4 & zpos >= (float) (getScreenHeight() * 3.0/5) - size) {
            ezs = -4;
        }
        // Don't fall off any of the edges
        if(xpos < 0) {
            exs = -exs; xpos = 0;
        }
        if(xpos + size > getScreenWidth()) {
            exs = -exs; xpos = getScreenWidth() - size;
        }
        if(zpos > (float) (getScreenHeight() * 3.0/5) - size) {
            ezs = 0; zpos = (float) (getScreenHeight() * 3.0/5) - size;
        }
        // Move according to speed, shouldn't be affected by framerate in pixels/sec
        xpos = (float) (xpos + (exs * 300 / fps));
        zpos = (float) (zpos + (ezs * 300 / fps));
        // If funky shit is happening, stop
        if(Float.isNaN(xpos)) xpos = getScreenWidth() / 2;
        if(Float.isNaN(zpos)) zpos = 0;
    }

    public boolean getHit(int damage) {
        health -= damage;
        if(health <= 0) return false;
        else return true;
    }

    public int getHealth() { return health; }
    public double getSpeed() { return speed; }
    public int getGolddrop() { return resources[0]; }
    public int[] getResources() { return resources; }
    public String getName() { return name; }
    public float getXpos() { return xpos; }
    public float getZpos() { return zpos; }
    public Bitmap getPic() { return pic; }
    public int getSize() { return size; }
}
