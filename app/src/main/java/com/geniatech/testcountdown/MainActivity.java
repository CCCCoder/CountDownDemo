package com.geniatech.testcountdown;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Animator.AnimatorListener {


    private ImageView count1, count2, count3, count4;
    private AnimatorSet set;

    private RelativeLayout relativeLayout;

    int count = 0;
    private ArrayList<ImageView> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count1 = (ImageView) findViewById(R.id.count_1_iv);
        count2 = (ImageView) findViewById(R.id.count_2_iv);
        count3 = (ImageView) findViewById(R.id.count_3_iv);
        count4 = (ImageView) findViewById(R.id.count_4_iv);

        relativeLayout = (RelativeLayout) findViewById(R.id.count_relative);

        arrayList.add(count1);
        arrayList.add(count2);
        arrayList.add(count3);
        arrayList.add(count4);
        set = new AnimatorSet();
        set.addListener(this);
        startAnimator(count, set);
    }

    @Override
    public void onAnimationStart(Animator animation) {
        Log.d("xyz","onAnimationStart");
    }

    @Override
    public void onAnimationEnd(Animator animation) {

        Log.d("xyz","onAnimationEnd");

        arrayList.get(count).setVisibility(View.GONE);
        ++count;
        if (count < 4) {
            startAnimator(count, set);
        } else {
            Toast.makeText(getApplicationContext(), "finish", Toast.LENGTH_SHORT).show();
            relativeLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public void onAnimationCancel(Animator animation) {
        Log.d("xyz","onAnimationCancel");
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
        Log.d("xyz","onAnimationRepeat");
    }


    public void startAnimator(int count, AnimatorSet set) {
        ImageView view = arrayList.get(count);
        view.setVisibility(View.VISIBLE);
        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1.1f, 1f);
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(view, "scaleY", 0, 1.1f, 1f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        set.playTogether(objectAnimatorX, objectAnimatorY, alpha);
        set.setDuration(700);
        set.start();
    }

}
