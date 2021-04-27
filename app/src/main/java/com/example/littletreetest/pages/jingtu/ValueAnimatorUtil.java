package com.example.littletreetest.pages.jingtu;

import android.animation.ValueAnimator;

import androidx.annotation.NonNull;

import java.lang.reflect.Field;

import timber.log.Timber;

public class ValueAnimatorUtil {

    /**
     * 如果动画被禁用，则重置动画缩放时长
     */
    public static void resetDurationScaleIfDisable() {
        if (getDurationScale() == 0)
            resetDurationScale();
    }


    public static boolean check(){
        return ValueAnimator.areAnimatorsEnabled();
    }

    /**
     * 重置动画缩放时长
     */
    public static void resetDurationScale() {
        try {
            float res = getDurationScale();
            Timber.d(res +"");
            getField().setFloat(null, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static float getDurationScale() {
        try {
            return getField().getFloat(null);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @NonNull
    private static Field getField() throws NoSuchFieldException {
        Field field = ValueAnimator.class.getDeclaredField("sDurationScale");
        field.setAccessible(true);
        return field;
    }
}