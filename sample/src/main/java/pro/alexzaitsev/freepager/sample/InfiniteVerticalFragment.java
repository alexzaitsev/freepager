package pro.alexzaitsev.freepager.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pro.alexzaitsev.freepager.library.view.infinite.InfiniteVerticalPager;
import pro.alexzaitsev.freepager.library.view.infinite.ViewFactory;


public class InfiniteVerticalFragment extends Fragment implements ViewFactory {

	private int mBgColor;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mBgColor = getResources().getColor(R.color.sample_bg);
		InfiniteVerticalPager verticalPager = (InfiniteVerticalPager) inflater
				.inflate(R.layout.fragment_infinite_vertical, container, false);
		verticalPager.setFactory(this);
		verticalPager.instantiate();
		return verticalPager;
	}

	@Override
	public View makeView(int vertical, int horizontal) {
		Button btn = new Button(getActivity());
		btn.setText("Vertical " + vertical);
		btn.setBackgroundColor(mBgColor);
		return btn;
	}

}
