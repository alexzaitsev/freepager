package pro.alexzaitsev.freepager.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements OnItemClickListener {

	public static final int POSITION_FIXED = 0;
	public static final int POSITION_VERTICAL = 1;
	public static final int POSITION_HORIZONTAL = 2;
	public static final int POSITION_FREE = 3;
	private Fragment mCurrentFragment;
	private int mCurrentFragmentPosition = -1;
	private String[] mTitles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTitles = getResources().getStringArray(R.array.main);
		ListView listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.main)));
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int i, long arg3) {
		switch (i) {
		case POSITION_FIXED:
			mCurrentFragment = new FixedViewsFragment();
			break;

		case POSITION_VERTICAL:
			mCurrentFragment = new InfiniteVerticalFragment();
			break;

		case POSITION_HORIZONTAL:
			mCurrentFragment = new InfiniteHorizontalFragment();
			break;

		case POSITION_FREE:
			mCurrentFragment = new FreeFragment();
			break;

		default:
			break;
		}

		getSupportFragmentManager().beginTransaction()
				.replace(android.R.id.content, mCurrentFragment).commit();
		mCurrentFragmentPosition = i;
		setTitle(mTitles[mCurrentFragmentPosition]);
	}

	@Override
	public void onBackPressed() {
		if (mCurrentFragment == null) {
			super.onBackPressed();
		} else {
			getSupportFragmentManager().beginTransaction()
					.remove(mCurrentFragment).commit();
			mCurrentFragment = null;
			mCurrentFragmentPosition = -1;
			setTitle(R.string.app_name);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt("pos_key", mCurrentFragmentPosition);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mCurrentFragmentPosition = savedInstanceState.getInt("pos_key");
		if (mCurrentFragmentPosition >= 0) {
			onItemClick(null, null, mCurrentFragmentPosition, 0);
		}
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
