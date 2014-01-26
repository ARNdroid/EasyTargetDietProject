package br.com.arndroid.etdiet.foodsusage;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.com.arndroid.etdiet.R;
import br.com.arndroid.etdiet.action.FragmentActionReplier;
import br.com.arndroid.etdiet.action.MenuUtils;
import br.com.arndroid.etdiet.meals.Meals;
import br.com.arndroid.etdiet.meals.MealsAdapter;
import br.com.arndroid.etdiet.provider.Contract;

public class FoodsUsageActivity extends ActionBarActivity implements
        FoodsUsageListFragment.FoodsUsageListFragmentListener,
        ActionBar.OnNavigationListener {

    private String mDateId;
    private FoodsUsageHeaderFragment mHeaderFragment;
    private FoodsUsageListFragment mListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.foods_usage_activity);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        MealsAdapter adapter = new MealsAdapter(this);
        actionBar.setListNavigationCallbacks(adapter, this);

        if(savedInstanceState == null) {
            Bundle data = getIntent().getExtras().getBundle(FragmentActionReplier.ACTION_DATA_KEY);
            mDateId = data.getString(FoodsUsageListFragment.DATE_ID_ACTION_KEY);
            actionBar.setSelectedNavigationItem(data.getInt(FoodsUsageListFragment.MEAL_ACTION_KEY));
        } else {
            mDateId = savedInstanceState.getString(FoodsUsageListFragment.DATE_ID_ACTION_KEY);
            actionBar.setSelectedNavigationItem(savedInstanceState.getInt(
                    FoodsUsageListFragment.MEAL_ACTION_KEY));
        }

        bindScreen();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(FoodsUsageListFragment.DATE_ID_ACTION_KEY, mDateId);
        outState.putInt(FoodsUsageListFragment.MEAL_ACTION_KEY,
                Meals.getMealFromPosition(getSupportActionBar().getSelectedNavigationIndex()));

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mListFragment.registerListener(this);
        mListFragment.onDataChangedFromHolderActivity(mDateId,
                Meals.getMealFromPosition(getSupportActionBar().getSelectedNavigationIndex()));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mListFragment.unregisterListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        switch (itemId) {
            case R.id.quick_add:
                MenuUtils.callMenuInFragmentByMethod(getSupportFragmentManager(),
                        R.id.foods_usage_list_fragment, itemId);
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.foods_usage, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(int position, long itemId) {
        mListFragment.onDataChangedFromHolderActivity(mDateId, Meals.getMealFromPosition(position));
        return true;
    }

    private void bindScreen() {
        mHeaderFragment = (FoodsUsageHeaderFragment) getSupportFragmentManager()
                .findFragmentById(R.id.foods_usage_header_fragment);
        mListFragment = (FoodsUsageListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.foods_usage_list_fragment);
    }

    @Override
    public void onListValuesChanged(Cursor data) {
        mHeaderFragment.onDataChangedFromHolderActivity(mDateId,
                Meals.getMealFromPosition(getSupportActionBar().getSelectedNavigationIndex()), data);
    }

    @SuppressWarnings("UnusedDeclaration")
    private static final String TAG = "==>ETD/" + FoodsUsageActivity.class.getSimpleName();

    @SuppressWarnings("UnusedDeclaration")
    private static final boolean isLogEnabled = true;
}