package org.pacar_robotics.diagresults;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * An activity representing a single Robot Test detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link RobotTestListActivity}.
 */
public class RobotTestDetailActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_robottest_detail);
		Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

		try {
			// Set FAB color based on result of test
			DiagResultsContent.DiagResultItem resultItem =
					DiagResultsContent.ITEMS.get(Integer.parseInt(getIntent().getStringExtra(RobotTestDetailFragment.ARG_ITEM_ID)));

			if (resultItem.result.toLowerCase().contains("pass")) {
				fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorTestPassed)));
				fab.setImageResource(R.drawable.ic_check_white_24dp);
			} else {
				fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorTestFailed)));
				fab.setImageResource(R.drawable.ic_close_white_24dp);
			}
		} catch (IndexOutOfBoundsException e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT ).show();
		}

		// Show the Up button in the action bar.
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		// savedInstanceState is non-null when there is fragment state
		// saved from previous configurations of this activity
		// (e.g. when rotating the screen from portrait to landscape).
		// In this case, the fragment will automatically be re-added
		// to its container so we don't need to manually add it.
		// For more information, see the Fragments API guide at:
		//
		// http://developer.android.com/guide/components/fragments.html
		//
		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(RobotTestDetailFragment.ARG_ITEM_ID,
					getIntent().getStringExtra(RobotTestDetailFragment.ARG_ITEM_ID));
			RobotTestDetailFragment fragment = new RobotTestDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.robottest_detail_container, fragment)
					.commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			navigateUpTo(new Intent(this, RobotTestListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
