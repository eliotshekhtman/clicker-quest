package com.eliotshekhtman.clickerquest;

import android.content.Context;

/*
          5(1)8
         4  2  7
        3  b e  6
          a   d
         9     c
*/

public class wolf extends tier3 {
    public wolf(Context c) {
        super(c);
        name = "WOLF";
        attack = 5;
        health = 100;
        speed = 2+fuzz();
        resources[0] = 150;
        pic = makePicture(R.drawable.wolf, size);
        attackDistr = new int[] {2,10,6,3,1,6,3,1,1,6,8,1,6,8};
    }
}
