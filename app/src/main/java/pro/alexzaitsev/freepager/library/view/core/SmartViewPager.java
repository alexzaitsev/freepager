package pro.alexzaitsev.freepager.library.view.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Custom {@link ViewPager} implementation to resolve scroll gesture directions
 * more accurate than a regular {@link ViewPager} component. This will make it
 * perfectly usable into a scroll container such as {@link ScrollView},
 * {@link ListView}, etc.
 * <p>
 * Default ViewPager becomes hardly usable when it's nested into a scroll
 * container. Such container will intercept any touch event with minimal
 * vertical shift from the child ViewPager. So switch the page by scroll gesture
 * with a regular {@link ViewPager} nested into a scroll container, user will
 * need to move his finger horizontally without vertical shift. Which is
 * obviously quite irritating. {@link SmartViewPager} has a much much better
 * behavior at resolving scrolling directions.
 */
public class SmartViewPager extends ViewPager {

	// -----------------------------------------------------------------------
	//
	// Constructor
	//
	// -----------------------------------------------------------------------
	public SmartViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector = new GestureDetector(context, new XScrollDetector());
	}

	// -----------------------------------------------------------------------
	//
	// Fields
	//
	// -----------------------------------------------------------------------
	private GestureDetector mGestureDetector;
	private boolean mIsLockOnHorizontalAxis = false;

	// -----------------------------------------------------------------------
	//
	// Methods
	//
	// -----------------------------------------------------------------------
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// decide if horizontal axis is locked already or we need to check the
		// scrolling direction
		if (!mIsLockOnHorizontalAxis)
			mIsLockOnHorizontalAxis = mGestureDetector.onTouchEvent(event);

		// release the lock when finger is up
		if (event.getAction() == MotionEvent.ACTION_UP)
			mIsLockOnHorizontalAxis = false;

		getParent().requestDisallowInterceptTouchEvent(mIsLockOnHorizontalAxis);
		return super.onTouchEvent(event);
	}

	// -----------------------------------------------------------------------
	//
	// Inner Classes
	//
	// -----------------------------------------------------------------------
	private class XScrollDetector extends SimpleOnGestureListener {

		// -----------------------------------------------------------------------
		//
		// Methods
		//
		// -----------------------------------------------------------------------
		/**
		 * @return true - if we're scrolling in X direction, false - in Y
		 *         direction.
		 */
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			return Math.abs(distanceX) > Math.abs(distanceY);
		}

	}
}
