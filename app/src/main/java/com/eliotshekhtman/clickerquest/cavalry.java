package com.eliotshekhtman.clickerquest;

import android.content.Context;

/*
          5(1)8
         4  2  7
        3  b e  6
          a   d
         9     c
*/

public class cavalry extends tier5 {
    cavalry(Context c) {
        super(c);
        name = "CAVALRY";
        attack = 25;
        health = 250;
        speed = 4+fuzz();
        resources[0] = 600;
        pic = makePicture(R.drawable.cavalry, size);
        attackDistr = new int[] {5,10,4,2,1,4,2,1,0,1,1,0,1,1};
    }
}
