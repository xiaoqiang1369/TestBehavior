package com.xiaoqiang.testbehavior;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description: 控件与CoordinatorLayout滑动关联的Behavior
 * Created by crx on 2017/9/26.
 */

public class FootBehavior extends CoordinatorLayout.Behavior<View> {

    private boolean isVisible = true;
    private boolean isAnimating = false;
    private static final int MIN_SCROLL_DISTANCE = 15;
    public FootBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 当CoordinatorLayout开始滑动时会调用这个方法，任意与CoordinatorLayout通过behavior关联的控件都要响应此方法并返回true
     * 如果返回false，控件将不会响应CoordinatorLayout的滑动事件。
     * @param coordinatorLayout
     * @param child     与CoordinatorLayout通过behavior关联的控件
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes  CoordinatorLayout滑动方向，ViewCompat.SCROLL_AXIS_VERTICAL表示纵向滑动
     *                          ViewCompat.SCROLL_AXIS_HORIZONTAL表示横向滑动
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        //响应CoordinatorLayout的纵向滑动
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    /**
     *  CoordinatorLayout滑动状态更新时调用此方法
     * @param coordinatorLayout
     * @param child     与CoordinatorLayout通过behavior关联的控件
     * @param target
     * @param dx        手指水平方向滑动的距离，左滑dx>0 右滑dx<0
     * @param dy        手指竖直方法滑动的距离，上滑dy>0 下滑dy<0
     * @param consumed 实际已滑动的距离，consumed[0]表示水平距离，consumed[1]表示竖直距离；
     *                 实际已滑动的距离总是小于或等于手指滑动的距离
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        if(dy>MIN_SCROLL_DISTANCE && isVisible && !isAnimating){    //如果向上滑动，则隐藏底部控件
            hideBottomView(child);
        }else if(dy<-MIN_SCROLL_DISTANCE && !isVisible && !isAnimating){  //如果向下滑动，则显示底部控件
            showBottomView(child);
        }
    }

    private void showBottomView(final View view){
        view.animate().translationY(0).setDuration(200).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isAnimating = true;
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isAnimating = false;
                isVisible = true;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                isAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }).start();
    }

    private void hideBottomView(final View view){
        view.animate().translationY(view.getHeight()).setDuration(200).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isAnimating = false;
                view.setVisibility(View.INVISIBLE);
                isVisible = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                isAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }).start();
    }
}
