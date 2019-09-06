package com.eliotshekhtman.clickerquest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public abstract class weapons {
    int upgrades;
    int baseAttack;
    Bitmap pic;
    Context context;
    String name;
    String desc;

    public weapons(Context c) { context = c; desc = ""; }
    public weapons(int id, Context c) {
        this(c);
        pic = makePicture(id, (int) (getScreenWidth() * 2/7.5));
    }

    public abstract int attack();
    public boolean upgrade() { upgrades++; return true; }
    public int getUpgradeNumber() {return upgrades; }
    public Bitmap getPic() {
        return pic;
    }
    public String getDesc() { return desc; }
    public String getName() {
        return name;
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

    protected double fuzzAcc() {
        double a = Math.random(); double b = Math.random(); double c = Math.random(); double d = Math.random();
        double e = Math.random(); double f = Math.random(); double g = Math.random(); double h = Math.random();
        return (a+b+c+d+e+f+g+h)/4;
    }

    protected double fuzzAdv(int[] distr) {
        // distr: 0:miss::1:17%::2:34%::3:50%::4:67%::5:84%::6:100::7:117%::8:134%::9:150%::10:167%::11:184%:12:200%
        int tot = distr[0]+distr[1]+distr[2]+distr[3]+distr[4]+distr[5]+distr[6]+
                distr[7]+distr[8]+distr[9]+distr[10]+distr[11]+distr[12];
        int num = (int) (Math.random() * tot) + 1;
        for(int i = 0; i < 13; i++) {
            if(num <= distr[i])
                return i*0.17;
            else
                num -= distr[i];
        }
        return 0;
    }
}
