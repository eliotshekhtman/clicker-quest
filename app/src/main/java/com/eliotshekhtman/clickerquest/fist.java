package com.eliotshekhtman.clickerquest;

import android.content.Context;

public class fist extends weapons {
    fist(Context context) {
        super(R.drawable.fist2, context);
        baseAttack = 1;
        upgrades = 0;
        name = "STICK";
        desc = "Homegrown.  You can punch things with it, but you aren't particularly good at it.";
    }

    @Override
    public boolean upgrade() {
        System.out.println("Can't upgrade a fist!"); return false;
    }

    @Override
    public int attack() {
        return (int) ((baseAttack) * fuzzAdv(new int[] {18,1,2,5,4,4,10,3,2,1,0,0,2}));
    }
}
