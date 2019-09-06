package com.eliotshekhtman.clickerquest;

import android.content.Context;

/*
          5(1)8
         4  2  7
        3  b e  6
          a   d
         9     c
*/

public class bison extends tier2 {
    bison(Context c) {
        super(c);
        name = "BISON";
        attack = 5;
        health = 75;
        speed = 1.5+fuzz();
        resources[0] = 50;
        healthgain = 15;
        pic = makePicture(R.drawable.bison, size);
        attackDistr = new int[] {3,10,1,2,5,1,2,5,1,3,6,1,3,6};
    }
}
