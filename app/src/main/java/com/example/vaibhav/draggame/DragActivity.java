package com.example.vaibhav.draggame;

import android.content.ClipData;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class DragActivity extends AppCompatActivity {

    //constants
    private static final String TAG = "DragActivity";
    private static final int COLOR_RED   = 0;
    private static final int COLOR_Blue  = 1;
    private static final int COLOR_Black = 2;
    private static final int COLOR_Green = 3;

    //widgets
    private ImageView imgHome, imgRed, imgGreen, imgBlack, imgBlue;

    //variables
    private int colorOutside = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_drag);

        initialise();

    }

    private void initialise() {
        Log.d(TAG, "initialise: ");

        //initialising widgets
        imgRed   = (ImageView) findViewById(R.id.imgStripRed);
        imgBlue  = (ImageView) findViewById(R.id.imgStripBlue);
        imgGreen = (ImageView) findViewById(R.id.imgStripGreen);
        imgBlack = (ImageView) findViewById(R.id.imgStripBlack);
        imgHome  = (ImageView) findViewById(R.id.img_home);

        //setting on click listeners
        imgRed.setOnTouchListener(stripeClickListener);
        imgBlue.setOnTouchListener(stripeClickListener);
        imgBlack.setOnTouchListener(stripeClickListener);
        imgGreen.setOnTouchListener(stripeClickListener);

        imgRed.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) { return stripeRedDrag(view,dragEvent); }
        });
        imgGreen.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) { return stripeGreenDrag(view,dragEvent); }
        });
        imgBlack.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) { return stripeBlackDrag(view,dragEvent); }
        });
        imgBlue.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) { return stripeBlueDrag(view,dragEvent); }
        });

        imgHome.setOnDragListener(homeDragListener);
    }

    private boolean stripeGreenDrag(View view, DragEvent dragEvent) {
//        Log.d(TAG, "stripeGreenDrag: ");

        if (dragEvent.getAction() == DragEvent.ACTION_DRAG_EXITED){
            if (view.getId() == R.id.imgStripGreen){
                Log.d(TAG, "stripeGreenDrag: green exited");
                colorOutside = COLOR_Green;
            }
        }
        else if (dragEvent.getAction() == DragEvent.ACTION_DRAG_ENDED){
            Log.d(TAG, "stripeGreenDrag: ended "+ colorOutside);
            colorOutside = -1;
        }
        return true;
    }

    private boolean stripeBlackDrag(View view, DragEvent dragEvent) {
//        Log.d(TAG, "stripeBlackDrag: ");

        if (dragEvent.getAction() == DragEvent.ACTION_DRAG_EXITED){
            if (view.getId() == R.id.imgStripBlack){
                Log.d(TAG, "stripeBlackDrag: black exited");
                colorOutside = COLOR_Black;
            }
        }
        else if (dragEvent.getAction() == DragEvent.ACTION_DRAG_ENDED){
            Log.d(TAG, "stripeBlackDrag: ended "+ colorOutside);
            colorOutside = -1;
        }
        return true;
    }

    private boolean stripeRedDrag(View view, DragEvent dragEvent) {

        if (dragEvent.getAction() == DragEvent.ACTION_DRAG_EXITED){
            if(view.getId() == R.id.imgStripRed) {
                Log.d(TAG, "stripeRedDrag: exited red");
                colorOutside = COLOR_RED;
            }
        }
        else if (dragEvent.getAction() == DragEvent.ACTION_DRAG_ENDED){
            Log.d(TAG, "stripeRedDrag: drag ended "+ colorOutside);
            colorOutside = -1;
        }
        return true;
    }

    private boolean stripeBlueDrag(View view, DragEvent dragEvent) {
//        Log.d(TAG, "stripeBlueDrag: ");

        if (dragEvent.getAction() == DragEvent.ACTION_DRAG_EXITED){
            if(view.getId() == R.id.imgStripBlue){
                Log.d(TAG, "stripeBlueDrag: exited blue");
                colorOutside = COLOR_Blue;
            }
        }
        else if (dragEvent.getAction() == DragEvent.ACTION_DRAG_ENDED){
            Log.d(TAG, "stripeBlueDrag: drag ended");
            colorOutside = -1;
        }
        return true;
    }

    private View.OnTouchListener stripeClickListener = new View.OnTouchListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            Log.d(TAG, "onTouch: ");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

            imgBlack.setOnDragListener(null);
            imgGreen.setOnDragListener(null);
            imgBlue.setOnDragListener(null);
            imgRed.setOnDragListener(null);

            if (view == imgBlue) view.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent dragEvent) { return stripeBlueDrag(view,dragEvent); }
            });

            if (view == imgRed) view.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent dragEvent) { return stripeRedDrag(view,dragEvent); }
            });

            if (view == imgGreen) view.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent dragEvent) { return stripeGreenDrag(view,dragEvent); }
            });

            if (view == imgBlack) view.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent dragEvent) { return stripeBlackDrag(view,dragEvent); }
            });

            view.startDragAndDrop(null, shadowBuilder, null, 0);
            return true;
        }
    };

    private View.OnDragListener homeDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {

            if(dragEvent.getAction() == DragEvent.ACTION_DRAG_ENTERED) {
                    if (view == imgHome) {
                        Log.d(TAG, "onDrag: inside home");

                        if (colorOutside == COLOR_RED) {
                            Intent intent = new Intent(DragActivity.this, RedActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (colorOutside == COLOR_Blue) {
                            Intent intent = new Intent(DragActivity.this, BlueActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (colorOutside == COLOR_Black) {
                            Intent intent = new Intent(DragActivity.this, BlackActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (colorOutside == COLOR_Green) {
                            Intent intent = new Intent(DragActivity.this, GreenActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else Toast.makeText(DragActivity.this, "swipe could not be detected properly! Please swipe a little slower", Toast.LENGTH_SHORT).show();
                    }
//
//                case DragEvent.ACTION_DRAG_EXITED:
//
//                    if (view.getId() == R.id.img_home) {
//
//                        Log.d(TAG, "onDrag: outside home");
//                        isInside = false;
//                    }
//                    break;
//
//                case DragEvent.ACTION_DROP:
//
//                    if (view.getId() == R.id.img_home) {
//                        Log.d(TAG, "onDrag: dropped");
//
//                        if (isInside) {
//                            Log.d(TAG, "onDrag: dropped inside home!");
//
//
//                        }
//                    }
//                    break;
            }

            return true;
        }
    };

}
