package com.example.administrator.southweek.ui.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

public class BaseGridView extends GridView {
	public BaseGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BaseGridView(Context context) {
		super(context);
	}

	public BaseGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_MOVE) {
			return true; // 禁止滑动
		}
		return super.dispatchTouchEvent(ev);
	}
}
