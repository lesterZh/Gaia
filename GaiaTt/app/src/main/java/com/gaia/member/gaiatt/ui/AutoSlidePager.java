package com.gaia.member.gaiatt.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.gaia.member.gaiatt.gaiaclinic.adapter.AutoPagerAdapter;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AutoSlidePager extends ViewPager {
	// Timer计时每隔5秒切换一个Pager页面
	private Timer mTimer;
	//TimerTask用来定时执行任务
	private TimerTask mTask;
	// 回调接口实现类对象
	private OnSlidingChangedListener mOnSlidingChangedListener;

	public void setOnSlidingChangedListener(OnSlidingChangedListener  l){
		this.mOnSlidingChangedListener = l;
	}

	/**
	 * 回调接口，用于关联指示器����ڹ���ָʾ��
	 * */
	public interface OnSlidingChangedListener{
		void onScrollChanged(int pos);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if(mOnSlidingChangedListener != null){
			mOnSlidingChangedListener.onScrollChanged(l);
		}
	}

	// Handler更新Pager
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			setCurrentItem(getCurrentItem() + 1);
		}
	};

	public AutoSlidePager(Context context, AttributeSet attrs) {
		super(context, attrs);
		initTimer();
	}

	public AutoSlidePager(Context context) {
		super(context);
		initTimer();
	}

	private void initTimer() {
		mTimer = new Timer();
	}

	/**
	 * 开始滑动���
	 * */
	public void startSliding(){
		if(mTask == null){
			mTask = new TimerTask() {
				@Override
				public void run() {
					mHandler.sendEmptyMessage(0);
				}
			};

			mTimer.schedule(mTask, 5000, 5000);
		}
	}

	/**
	 * 停止滑动���
	 * */
	public void stopSliding(){
		mHandler.removeMessages(0);
		if(mTask != null){
			mTask.cancel();
			mTask = null;
		}
	}

	public void setAutoPagerAdapter(AutoPagerAdapter adapter){
		setAdapter(adapter);
		//初始位置
		int curPage = Integer.MAX_VALUE/2 - (Integer.MAX_VALUE/2)%adapter.list.size();
		setCurrentItem(curPage,false);
		startSliding();
	}



}
