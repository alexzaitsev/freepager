package pro.alexzaitsev.freepager.library.view.infinite;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Usage: call {@link setFactory(ViewFactory)} then call {@link instantiate()}
 * 
 * @author A.Zaitsev
 * 
 */
public class InfiniteVerticalPager extends AbstractInfiniteVerticalPager {

	private ViewFactory mFactory;

	public InfiniteVerticalPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setFactory(ViewFactory factory) {
		mFactory = factory;
	}

	@Override
	public void instantiate() {
		if (mFactory == null) {
			throw new IllegalStateException("ViewFactory can't be null!");
		}
		super.instantiate();
	}

	@Override
	public View getViewAt(int vertical) {
		return mFactory.makeView(vertical, -1);
	}
}
