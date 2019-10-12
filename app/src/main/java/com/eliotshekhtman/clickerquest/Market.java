package com.eliotshekhtman.clickerquest;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

public class Market extends AppCompatActivity {

    GameView gameView;
    final Context context = this;
    float tapx;
    float tapy;
    final long[] center_enh = new long[] {(long)((18.0/100)*getScreenWidth()),(long)(0.5*getScreenHeight())};
    final long[] center_bla = new long[] {(long)((54.0/100)*getScreenWidth()), (long)(90.0/200*getScreenHeight())};
    final long[] center_res = new long[] {(long)(85.0/100*getScreenWidth()),(long)(0.5*getScreenHeight())};
    final long[] width_and_height = new long[] {getScreenWidth()/10,getScreenHeight()/10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        tapx = (-1) * getScreenWidth(); tapy = (-1) * getScreenHeight();
        // Initialize gameView and set it as the view
        gameView = new GameView(this);
        setContentView(gameView);
    }

    // GameView class will go here

    // Here is our implementation of GameView
    // It is an inner class.
    // Note how the final closing curly brace }
    // is inside SimpleGameEngine

    // Notice we implement runnable so we have
    // A thread and can override the run method.
    class GameView extends SurfaceView implements Runnable {

        Bitmap market_img = makePicture(R.drawable.market2, getScreenWidth(), getScreenHeight());
        // This is our thread
        Thread gameThread = null;
        // This is new. We need a SurfaceHolder
        // When we use Paint and Canvas in a thread
        // We will see it in action in the draw method soon.
        SurfaceHolder ourHolder;
        // A boolean which we will set and unset
        // when the game is running- or not.
        volatile boolean playing;
        // A Canvas and a Paint object
        Canvas canvas;
        Paint paint;
        // This variable tracks the game frame rate
        long fps;
        // This is used to help calculate the fps
        private long timeThisFrame;
        // When it is first opened, it knows that you aren't tapping
        boolean tap = false;
        // When the we initialize (call new()) on gameView
        // This special constructor method runs
        public GameView(Context context) {
            // The next line of code asks the
            // SurfaceView class to set up our object.
            // How kind.
            super(context);

            // Initialize ourHolder and paint objects
            ourHolder = getHolder();
            paint = new Paint();

            // Set our boolean to true - game on!
            playing = true;

        }

        @Override
        public void run() {
            while (playing) {

                // Capture the current time in milliseconds in startFrameTime
                long startFrameTime = System.currentTimeMillis();

                // Update the frame
                update();

                // Draw the frame
                draw();
                // Calculate the fps this frame
                // We can then use the result to
                // time animations and more.
                timeThisFrame = System.currentTimeMillis() - startFrameTime;
                if (timeThisFrame > 0) {
                    fps = 1000 / timeThisFrame;
                }

            }

        }

        // Everything that needs to be updated goes in here
        public void update() {
            if(tap && (tapx >= (center_enh[0]-width_and_height[0]) & tapx <= (width_and_height[0] + center_enh[0])) & (tapy >= (center_enh[1]-width_and_height[1]) & tapy <= (center_enh[1]+width_and_height[1]))) {
                tap = false;
                Intent intent = new Intent(getBaseContext(), Enhancement_tent.class);
                startActivity(intent);
            }
            if(tap && (tapx >= (center_bla[0]-width_and_height[0]) & tapx <= (width_and_height[0] + center_bla[0])) & (tapy >= (center_bla[1]-width_and_height[1]) & tapy <= (center_bla[1]+width_and_height[1]))) {
                tap = false;
                Intent intent = new Intent(getBaseContext(), Blacksmith_tent.class);
                startActivity(intent);
            }
            if(tap && (tapx >= (center_res[0]-width_and_height[0]) & tapx <= (width_and_height[0] + center_res[0])) & (tapy >= (center_res[1]-width_and_height[1]) & tapy <= (center_res[1]+width_and_height[1]))) {
                tap = false;
                //Intent intent = new Intent(getBaseContext(), Trade_tent.class);
                //startActivity(intent);
            }
            if(tap && (tapx >= (1.0/3) * getScreenWidth() & tapx <= (2.0/3) * getScreenWidth()) & (tapy > (5.0/7) * getScreenHeight())) {
                tap = false;
                MainActivity.player.setContext(App.getContext());
                MainActivity.player.goOnQuest();
                Intent intent = new Intent(getBaseContext(), Quest.class);
                startActivity(intent);
            }
        }

        // Draw the newly updated scene
        public void draw() {
            // Make sure our drawing surface is valid or we crash
            if (ourHolder.getSurface().isValid()) {
                // Lock the canvas ready to draw
                canvas = ourHolder.lockCanvas();
                canvas.drawBitmap(market_img, 0, 0, paint);
                ourHolder.unlockCanvasAndPost(canvas);
            }

        }

        public void startQuest() {
            MainActivity.player.setContext(App.getContext());
            MainActivity.player.goOnQuest();
            Intent intent = new Intent(getBaseContext(), Quest.class);
            startActivity(intent);
        }

        // If SimpleGameEngine Activity is paused/stopped
        // shutdown our thread.
        public void pause() {
            playing = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("Error:", "joining thread");
            }

        }

        // If SimpleGameEngine Activity is started then
        // start our thread.
        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        // The SurfaceView class implements onTouchListener
        // So we can override this method and detect screen touches.
        @Override
        public boolean onTouchEvent(MotionEvent motionEvent) {

            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

                // Player has touched the screen
                case MotionEvent.ACTION_DOWN:

                    // Set tap so tap is put down
                    tap = true;
                    tapx = (int)motionEvent.getX();
                    tapy = (int)motionEvent.getY();
                    break;

                // Player has removed finger from screen
                case MotionEvent.ACTION_UP:

                    // Set tap is raised
                    tap = false;

                    break;
            }
            return true;
        }
    }
    // This is the end of our GameView inner class

    // More SimpleGameEngine methods will go here

    // This method executes when the player starts the game
    @Override
    protected void onResume() {
        super.onResume();
        // Tell the gameView resume method to execute
        gameView.resume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();
        // Tell the gameView pause method to execute
        gameView.pause();
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public Bitmap makePicture(int id, int s) {
        return makePicture(id, s, s);
    }

    public Bitmap makePicture(int id, int s, int z) {
        Bitmap b = BitmapFactory.decodeResource(this.getResources(), id);
        b = Bitmap.createScaledBitmap(b, s, z, false);
        return b;
    }
}
