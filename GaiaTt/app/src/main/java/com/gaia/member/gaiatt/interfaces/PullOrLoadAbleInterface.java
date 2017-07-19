package com.gaia.member.gaiatt.interfaces;

/**
 * 此接口用于实现下拉刷新和加载更多,适用于大多数的View
 *
 * 使用方法: 让自己的View 实现该接口就可以了
 */
public interface PullOrLoadAbleInterface
{
	/**
	 * 判断是否可以下拉刷新，如果不需要下拉功能可以直接return false
	 *
	 * @return true如果可以下拉刷新,否则返回false
	 */
	boolean canRefresh();

	/**
	 * 判断是否可以上拉，如果不需要上拉功能可以直接return false
	 *
	 * @return true如果可以上拉否则返回false
	 */
	boolean canLoadMore();
}
