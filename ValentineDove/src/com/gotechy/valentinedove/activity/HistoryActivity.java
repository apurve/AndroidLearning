package com.gotechy.valentinedove.activity;

import java.util.List;

import com.gotechy.valentinedove.dao.DaysDAO;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

public class HistoryActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments representing
     * each object in a collection. We use a {@link android.support.v4.app.FragmentStatePagerAdapter}
     * derivative, which will destroy and re-create fragments as needed, saving and restoring their
     * state in the process. This is important to conserve memory and is a best practice when
     * allowing navigation between objects in a potentially large collection.
     */
    TextCollectionPagerAdapter mTextCollectionPagerAdapter;

    /**
     * The {@link android.support.v4.view.ViewPager} that will display the object collection.
     */
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        mTextCollectionPagerAdapter = new TextCollectionPagerAdapter(getSupportFragmentManager());

        final ActionBar actionBar = getActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mTextCollectionPagerAdapter);
    }

    @SuppressWarnings("deprecation")
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent upIntent = new Intent(this, HomeActivity.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder.from(this)
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                      NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a fragment
     * representing an object in the collection.
     */
    public static class TextCollectionPagerAdapter extends FragmentStatePagerAdapter {

        public TextCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new TextObjectFragment();
            Bundle args = new Bundle();
            args.putInt(TextObjectFragment.ARG_OBJECT, i + 1); // Our object is just an integer :-P
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public CharSequence getPageTitle(int position) {
        	switch(position){
        	case 0:return "Rose Day";
    		case 1:return "Propose Day";
    		case 2:return "Chocolate Day";
    		case 3:return "Teddy Day";
    		case 4:return "Promise Day";
    		case 5:return "Hug Day";
    		case 6:return "Kiss Day";
    		case 7:return "Valentine Day";
    		default:return "Happy Day";
    		}
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class TextObjectFragment extends Fragment {

        public static final String ARG_OBJECT = "postion";

        @Override
        public TextView onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            TextView view = (TextView) inflater.inflate(R.layout.simple_msg, container, false);
            view.setMovementMethod(new ScrollingMovementMethod());
            Bundle args = getArguments();
            int position = args.getInt(ARG_OBJECT);
            List<String> history = DaysDAO.readDayHistory(getActivity().getApplicationContext());
            switch(position-1){
            case 0:{view.setText(history.get(0));break;}
			case 1:{view.setText(history.get(1));break;}
			case 2:{view.setText(history.get(2));break;}
			case 3:{view.setText(history.get(3));break;}
			case 4:{view.setText(history.get(4));break;}
			case 5:{view.setText(history.get(5));break;}
			case 6:{view.setText(history.get(6));break;}
			case 7:{view.setText(history.get(7));break;}
			default:{view.setText(history.get(0));break;}
			}
            return view;
        }
    }
}