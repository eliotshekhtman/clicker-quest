package com.eliotshekhtman.clickerquest;

import android.content.Context;

/*
          5(1)8
         4  2  7
        3  b e  6
          a   d
         9     c
*/

public class soldier extends tier6 {
    soldier(Context c) {
        super(c);
        name = "SOLDIER";
        attack = 10;
        health = 110;
        speed = 1+fuzz();
        resources[0] = 150;
        healthgain = 30;
        pic = makePicture(R.drawable.basesymbol, size);
        attackDistr = new int[] {5,10,2,4,1,2,4,1,0,1,1,0,1,1};
    }
}
