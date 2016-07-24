package com.example.administrator.southweek.ui.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ExpandableListView;


/**
 * Created by from -sky on 2016/7/19.
 */
public class BaseExpandablelistview extends ExpandableListView {
    private boolean isLocked;

    public BaseExpandablelistview(Context context) {
        super(context, null);
    }

    public BaseExpandablelistview(Context context, AttributeSet attrs) {
        super(context, attrs);
        isLocked = true;
    }

    public BaseExpandablelistview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_MOVE:
//                if (!isLocked) {
//                    try {
//                        return super.onInterceptTouchEvent(ev);
//                    } catch (IllegalArgumentException e) {
//                        e.printStackTrace();
//                        return false;
//                    }
//                }
//                break;
//        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_MOVE:
//                return !isLocked && super.onTouchEvent(event);
//
//        }
        return super.onTouchEvent(event);
    }
}
