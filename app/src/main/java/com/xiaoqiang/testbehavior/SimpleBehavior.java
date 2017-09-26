package com.xiaoqiang.testbehavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description: 控件与另一个控件关联的Behavior
 * Created by crx on 2017/9/25.
 */

public class SimpleBehavior extends CoordinatorLayout.Behavior<View> {

    public SimpleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 使一个控件与目标控件关联，如果要响应目标控件的变化，就返回true，否则返回false
     * @param parent
     * @param child     //关联控件
     * @param dependency    //被关联的目标控件，可以通过类型或者id来确定目标控件
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //return dependency instanceof Button;
        return dependency.getId() == R.id.button;
    }

    /**
     * 当目标控件状态发生状态变化时（比如位置和大小），会调用此方法
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //关联控件的位置随着目标控件位置变化
        child.setX(dependency.getX() + 30);
        child.setY(dependency.getY() + dependency.getHeight() + 30);
        return true;
    }
}
