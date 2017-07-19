package com.gaia.member.gaiatt.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.gaia.member.gaiatt.interfaces.PullOrLoadAbleInterface;

public class PullOrLoadListView extends ListView implements PullOrLoadAbleInterface
{

	public PullOrLoadListView(Context context)
	{
		super(context);
	}

	public PullOrLoadListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public PullOrLoadListView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override
	public boolean canRefresh()
	{
		if (getCount() == 0)
		{
			return true;
		} else if (getFirstVisiblePosition() == 0
				&& getChildAt(0).getTop() >= 0)
		{
			return true;
		} else
			return false;
	}

	@Override
	public boolean canLoadMore()
	{
		if (getCount() == 0)
		{
			return true;
		} else if (getLastVisiblePosition() == (getCount() - 1))
		{
			if (getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()) != null
					&& getChildAt(
							getLastVisiblePosition()
									- getFirstVisiblePosition()).getBottom() <= getMeasuredHeight())
				return true;
		}
		return false;
	}
}
