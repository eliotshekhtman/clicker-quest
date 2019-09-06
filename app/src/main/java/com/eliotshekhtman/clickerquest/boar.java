package com.eliotshekhtman.clickerquest;

import android.content.Context;

/*
          5(1)8
         4  2  7
        3  b e  6
          a   d
         9     c
*/

public class boar extends tier2 {
    boar(Context c) {
        super(c);
        name = "BOAR";
        attack = 3;
        health = 50;
        speed = 2+fuzz();
        resources[0] = 20;
        pic = makePicture(R.drawable.boar2, size);
        attackDistr = new int[] {2,7,4,2,1,4,2,1,3,7,10,3,7,10};
    }
}
