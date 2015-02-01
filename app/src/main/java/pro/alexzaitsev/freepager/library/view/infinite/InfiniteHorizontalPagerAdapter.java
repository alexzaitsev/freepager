package pro.alexzaitsev.freepager.library.view.infinite;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class InfiniteHorizontalPagerAdapter extends PagerAdapter {

	private ViewFactory mFactory;
	private int mVerticalPosition;

	public InfiniteHorizontalPagerAdapter(ViewFactory factory, int vertical) {
		this.mFactory = factory;
		this.mVerticalPosition = vertical;
	}

	@Override
	public int getCount() {
		return Constants.INF;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View subitem = mFactory.makeView(mVerticalPosition, position
				- Constants.START_INDEX);
		((ViewPager) container).addView(subitem, 0);
		return subitem;

	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
}
