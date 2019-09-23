package com.eliotshekhtman.clickerquest;

import android.content.Context;

import static com.eliotshekhtman.clickerquest.textphemeral.colorName.RED;

public class damageText extends textphemeral {
    public damageText(int damage, float x, float y, Context c) {
        super(damage+"", RED, c, x, y, 1, (int) ((double) getScreenWidth() / 12), 1000);
        //spawned = System.currentTimeMillis();
    }
}
