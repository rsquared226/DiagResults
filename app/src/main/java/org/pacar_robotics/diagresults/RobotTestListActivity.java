package org.pacar_robotics.diagresults;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * An activity representing a list of Robot Tests. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RobotTestDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RobotTestListActivity extends AppCompatActivity {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_robottest_list);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		toolbar.setTitle(getTitle());

		View recyclerView = findViewById(R.id.robottest_list);
		assert recyclerView != null;
		setupRecyclerView((RecyclerView) recyclerView);

		if (findViewById(R.id.robottest_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-w900dp).
			// If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;
		}
	}

	private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
		recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DiagResultsContent.ITEMS));
	}

	public class SimpleItemRecyclerViewAdapter
			extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

		private final List<DiagResultsContent.DiagResultItem> mValues;

		public SimpleItemRecyclerViewAdapter(List<DiagResultsContent.DiagResultItem> items) {
			mValues = items;
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.robottest_list_content, parent, false);
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(final ViewHolder holder, int position) {
			holder.mItem = mValues.get(position);
			holder.mIdView.setText(mValues.get(position).id);
			holder.mContentView.setText(mValues.get(position).name);

			holder.mView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mTwoPane) {
						Bundle arguments = new Bundle();
						arguments.putString(RobotTestDetailFragment.ARG_ITEM_ID, holder.mItem.id);
						RobotTestDetailFragment fragment = new RobotTestDetailFragment();
						fragment.setArguments(arguments);
						getSupportFragmentManager().beginTransaction()
								.replace(R.id.robottest_detail_container, fragment)
								.commit();
					} else {
						Context context = v.getContext();
						Intent intent = new Intent(context, RobotTestDetailActivity.class);
						intent.putExtra(RobotTestDetailFragment.ARG_ITEM_ID, holder.mItem.id);

						context.startActivity(intent);
					}
				}
			});
		}

		@Override
		public int getItemCount() {
			return mValues.size();
		}

		public class ViewHolder extends RecyclerView.ViewHolder {
			public final View mView;
			public final TextView mIdView;
			public final TextView mContentView;
			public DiagResultsContent.DiagResultItem mItem;

			public ViewHolder(View view) {
				super(view);
				mView = view;
				mIdView = (TextView) view.findViewById(R.id.id);
				mContentView = (TextView) view.findViewById(R.id.content);
			}

			@Override
			public String toString() {
				return super.toString() + " '" + mContentView.getText() + "'";
			}
		}
	}
}
