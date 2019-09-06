package com.eliotshekhtman.clickerquest;

import android.content.Context;

/*
          5(1)8
         4  2  7
        3  b e  6
          a   d
         9     c
*/

public class crocodile extends tier5 {
    crocodile(Context c) {
        super(c);
        name = "CROCODILE";
        attack = 15;
        health = 200;
        speed = 2+fuzz();
        resources[0] = 200;
        pic = makePicture(R.drawable.croc, size);
        attackDistr = new int[] {3,7,4,7,2,4,7,2,2,10,6,2,10,6};
    }
}
