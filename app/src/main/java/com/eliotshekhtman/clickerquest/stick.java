package com.eliotshekhtman.clickerquest;

import android.content.Context;

public class stick extends weapons {

    stick(Context context) {
        super(R.drawable.stick2, context);
        upgrades = 0; baseAttack = 1;
        name = "STICK";
        desc = "A stick you found.  Can poke things with it, but it's hard to aim.";
    }

    @Override
    public int attack() {
        return (int) ((baseAttack + upgrades) * fuzzAdv(new int[] {20,1,2,5,4,4,10,3,2,1,0,0,0}));
    }
}
