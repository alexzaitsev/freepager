package pro.alexzaitsev.freepager.library.view.infinite;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import pro.alexzaitsev.freepager.library.view.core.VerticalPager;

/**
 * 
 * Adding functionality of program page spapping and infinite paging. Call
 * {@link instaniate()} to initialize.
 * 
 * @author A.Zaitsev
 * 
 */
public abstract class AbstractInfiniteVerticalPager extends VerticalPager
		implements VerticalPager.OnVerticalPageChangeListener {

	private static final int CHILDREN_CHANGE_DELAY_MS = 300;
	private static final int PAGE_SNAP_DURATION = 0;

	private int mAbsoluteIndex = 0;

	public AbstractInfiniteVerticalPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void instantiate() {
		addView(getViewAt(Constants.TOP_PAGE_INDEX - 1));
		addView(getViewAt(Constants.CENTRAL_PAGE_INDEX - 1));
		addView(getViewAt(Constants.BOTTOM_PAGE_INDEX - 1));
		snapPageWhenLayoutIsReady(Constants.CENTRAL_PAGE_INDEX);
		addOnPageChangedListener(this);
	}

	public void snapPageWhenLayoutIsReady(final int page) {
		/*
		 * VerticalPager is not fully initialized at the moment, so we want to
		 * snap to the central page only when it layout and measure all its
		 * pages.
		 */
		getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					@SuppressWarnings("deprecation")
					@Override
					public void onGlobalLayout() {
						getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);

						snapToPage(page, PAGE_SNAP_DURATION);
					}
				});
	}

	@Override
	public void onVerticalPageChanged(final int newPageIndex) {
		// wait on scrolling
		postDelayed(new Runnable() {

			@Override
			public void run() {
				// then modify children
				onChanged(newPageIndex);
			}
		}, CHILDREN_CHANGE_DELAY_MS);
	}

	private void onChanged(int newPageIndex) {
		if (newPageIndex == Constants.TOP_PAGE_INDEX) {
			mAbsoluteIndex--;
			// remove bottom view
			if (getChildAt(Constants.BOTTOM_PAGE_INDEX) != null) {
				removeViewAt(Constants.BOTTOM_PAGE_INDEX);
			}
			// add top view
			View newTopView = getViewAt(mAbsoluteIndex - 1);
			addView(newTopView, Constants.TOP_PAGE_INDEX);
			// make this view central
			snapPageWhenLayoutIsReady(Constants.CENTRAL_PAGE_INDEX);
		} else if (newPageIndex == Constants.BOTTOM_PAGE_INDEX) {
			mAbsoluteIndex++;
			// remove top view
			removeViewAt(Constants.TOP_PAGE_INDEX);
			// add bottom view
			View newBottomView = getViewAt(mAbsoluteIndex + 1);
			addView(newBottomView, Constants.BOTTOM_PAGE_INDEX);
			// make this view central
			snapPageWhenLayoutIsReady(Constants.CENTRAL_PAGE_INDEX);
		}
	}

	abstract public View getViewAt(int vertical);
}
