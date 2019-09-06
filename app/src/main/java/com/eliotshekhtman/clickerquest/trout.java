package com.eliotshekhtman.clickerquest;

import android.content.Context;

/*
          5(1)8
         4  2  7
        3  b e  6
          a   d
         9     c
*/

public class trout extends tier3 {
    trout(Context c) {
        super(c);
        name = "TROUT";
        attack = 3;
        health = 50;
        speed = 2+fuzz();
        resources[0] = 40;
        pic = makePicture(R.drawable.trout, size);
        attackDistr = new int[] {0,1,4,0,0,4,0,0,10,4,1,10,4,1};
    }
}
