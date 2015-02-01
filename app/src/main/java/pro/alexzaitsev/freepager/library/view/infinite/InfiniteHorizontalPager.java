package pro.alexzaitsev.freepager.library.view.infinite;

import android.content.Context;

import pro.alexzaitsev.freepager.library.view.core.SmartViewPager;

/**
 * <small>I don't use inheritance because in this case error in parent view is
 * occured.</small>
 * 
 * @author A.Zaitsev
 * 
 */
public class InfiniteHorizontalPager {

	private SmartViewPager mPager;

	/**
	 * 
	 * @param context
	 * @param vertical
	 *            index of this pager in vertical pager
	 * @param horizontal
	 *            current page of this pager (zero-based)
	 * @param factory
	 */
	public InfiniteHorizontalPager(Context context, int vertical,
			int horizontal, ViewFactory factory) {
		mPager = new SmartViewPager(context, null);
		mPager.setAdapter(new InfiniteHorizontalPagerAdapter(factory, vertical));
		mPager.setCurrentItem(horizontal + Constants.START_INDEX);
	}

	public SmartViewPager getPager() {
		return mPager;
	}

}
