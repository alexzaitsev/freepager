package pro.alexzaitsev.freepager.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pro.alexzaitsev.freepager.library.view.infinite.Constants;
import pro.alexzaitsev.freepager.library.view.infinite.InfiniteHorizontalPager;
import pro.alexzaitsev.freepager.library.view.infinite.InfiniteVerticalPager;
import pro.alexzaitsev.freepager.library.view.infinite.ViewFactory;


public class FreeFragment extends Fragment implements OnPageChangeListener {

	private int mBgColor;
	private int mCurrentHorizontalIndex = 0;
	private ViewGroup mView;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mBgColor = getResources().getColor(R.color.sample_bg);
		InfiniteVerticalPager verticalPager = (InfiniteVerticalPager) inflater
				.inflate(R.layout.fragment_infinite_vertical, container, false);
		verticalPager.setFactory(mVerticalViewFactory);
		verticalPager.instantiate();
		return mView = verticalPager;
	}

	private ViewFactory mVerticalViewFactory = new ViewFactory() {

		static final int VIEWS_TO_INITIALIZE_COUNT = 3;

		private int mViewsToInitilizeLeft = VIEWS_TO_INITIALIZE_COUNT;

		@Override
		public View makeView(int vertical, int horizontal) {
			int normalizedHorizontal = 0;
			if (mViewsToInitilizeLeft > 0) {
				mViewsToInitilizeLeft--;
			} else {
				normalizedHorizontal = mCurrentHorizontalIndex;
			}
			InfiniteHorizontalPager pager = new InfiniteHorizontalPager(
					getActivity(), vertical, normalizedHorizontal,
					mHorizontalViewFactory);
			pager.getPager().setOnPageChangeListener(FreeFragment.this);
			return pager.getPager();
		}
	};

	private ViewFactory mHorizontalViewFactory = new ViewFactory() {

		@Override
		public View makeView(int vertical, int horizontal) {
			Button btn = new Button(getActivity());
			btn.setText("Horizontal " + horizontal + "\nVertical " + vertical);
			btn.setBackgroundColor(mBgColor);
			return btn;
		}
	};

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int i) {
		mCurrentHorizontalIndex = i - Constants.START_INDEX;

		ViewPager pagerTop = (ViewPager) mView
				.getChildAt(Constants.TOP_PAGE_INDEX);
		ViewPager pagerBottom = (ViewPager) mView
				.getChildAt(Constants.BOTTOM_PAGE_INDEX);
		synchronizePager(pagerTop, i);
		synchronizePager(pagerBottom, i);
	}

	private void synchronizePager(ViewPager pager, int i) {
		pager.setOnPageChangeListener(null);
		pager.setCurrentItem(i);
		pager.setOnPageChangeListener(FreeFragment.this);
	}
}
