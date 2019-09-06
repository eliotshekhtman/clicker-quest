package com.eliotshekhtman.clickerquest;

import android.content.Context;

/*
          5(1)8
         4  2  7
        3  b e  6
          a   d
         9     c
*/

public class will_o_the_wisp extends tier3 {
    will_o_the_wisp(Context c) {
        super(c);
        name = "WILL-O-THE-WISP";
        attack = 15;
        health = 320;
        speed = 4+fuzz();
        resources[0] = 100;
        size = (int) (getScreenWidth() / 4);
        pic = makePicture(R.drawable.wotw, size);
        attackDistr = new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    }
}
