package org.pacar_robotics.diagresults;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment representing a single Robot Test detail screen.
 * This fragment is either contained in a {@link RobotTestListActivity}
 * in two-pane mode (on tablets) or a {@link RobotTestDetailActivity}
 * on handsets.
 */
public class RobotTestDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private DiagResultsContent.DiagResultItem mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public RobotTestDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = DiagResultsContent.ITEMS.get(Integer.parseInt(getArguments().getString(ARG_ITEM_ID)));

			Activity activity = this.getActivity();
			CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
			if (appBarLayout != null) {
				appBarLayout.setTitle(mItem.name);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.robottest_detail, container, false);

		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.severity)).setText(mItem.resultSeverity);
			((TextView) rootView.findViewById(R.id.result_message)).setText(mItem.resultMessage);
			((TextView) rootView.findViewById(R.id.recommendation)).setText(mItem.recommendation);
			((TextView) rootView.findViewById(R.id.description)).setText(mItem.longDesc);
		}

		return rootView;
	}
}
