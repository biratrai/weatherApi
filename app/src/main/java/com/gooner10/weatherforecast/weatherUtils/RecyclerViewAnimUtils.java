package com.gooner10.weatherforecast.weatherUtils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.animation.DecelerateInterpolator;

public class RecyclerViewAnimUtils {


    public static void animateSunblind(RecyclerView.ViewHolder holder, boolean goesDown) {

        int holderHeight = holder.itemView.getHeight();

        holder.itemView.setPivotY(goesDown ? 0 : holderHeight);

        holder.itemView.setPivotX(holder.itemView.getHeight());

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goesDown ? 300 : -300, 0);

        ObjectAnimator animatorRotation = ObjectAnimator.ofFloat(holder.itemView, "rotationX", goesDown ? -90f : 90, 0f);

        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(holder.itemView, "scaleX", 0.5f, 1f);

        animatorSet.playTogether(animatorTranslateY, animatorRotation, animatorScaleX);

        animatorSet.setInterpolator(new DecelerateInterpolator(1.1f));

        animatorSet.setDuration(500);

        animatorSet.start();

    }


}
