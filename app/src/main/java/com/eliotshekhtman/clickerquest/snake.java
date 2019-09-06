package com.eliotshekhtman.clickerquest;

import android.content.Context;

/*
          5(1)8
         4  2  7
        3  b e  6
          a   d
         9     c
*/

public class snake extends tier1 {
    snake(Context c) {
        super(c);
        name = "SNAKE";
        attack = 3;
        health = 90;
        speed = 4+fuzz();
        resources[0] = 50;
        size = (int) (getScreenWidth() / 5);
        pic = makePicture(R.drawable.snake2, size);
        attackDistr = new int[] {2,5,5,4,2,5,4,2,10,8,6,10,8,6};
    }
}
