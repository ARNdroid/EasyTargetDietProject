package br.com.arndroid.etdiet.foodsusage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import br.com.arndroid.etdiet.R;
import br.com.arndroid.etdiet.provider.Contract;
import br.com.arndroid.etdiet.provider.FoodsUsageEntity;
import br.com.arndroid.etdiet.quickinsert.QuickInsertFrag;
import br.com.arndroid.etdiet.util.DateUtil;

public class FoodsUsageAct extends ActionBarActivity implements FoodsUsageListFrag.OnFoodUsageListFragListener,
        ActionBar.OnNavigationListener {

    public static final String DATE_ID_PARAMETER = FoodsUsageAct.class.getSimpleName() + ".DATE_ID_PARAMETER";
    public static final String MEAL_PARAMETER = FoodsUsageAct.class.getSimpleName() + ".MEAL_PARAMETER";
    private static final String TAG = "==>ETD/" + FoodsUsageAct.class.getSimpleName();
    private static final boolean isLogEnabled = true;
    private FoodsUsageListFrag mFragment;
    private String mDateId;
    private String[] mealsNameList;
    private TextView mTxtDate;

    @Override
    public void onFoodUsageSelected(long foodUsageId) {
        Uri uri = Uri.withAppendedPath(Contract.FoodsUsage.CONTENT_URI, String.valueOf(foodUsageId));
        FragmentManager manager = getSupportFragmentManager();
        QuickInsertFrag dialog = new QuickInsertFrag();
        dialog.setUri(uri);
        dialog.show(manager, QuickInsertFrag.UPDATE_TAG);
    }

    @Override
    public void onFoodUsageLongSelected(long foodUsageId) {
        final Uri uri = Uri.withAppendedPath(Contract.FoodsUsage.CONTENT_URI, String.valueOf(foodUsageId));
        Cursor c = this.getContentResolver().query(uri, null, null, null, null);
        FoodsUsageEntity entity = null;
        try {
            c.moveToFirst();
            entity = FoodsUsageEntity.fromCursor(c);
        } finally {
            c.close();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                getContentResolver().delete(uri, null, null);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setMessage(String.format(getResources().getString(R.string.delete_food_usage_msg),
                entity.getDescription(), mealsNameList[entity.getMeal()]));
        builder.create().show();
    }

    @Override
    public void onQuickAddMenuSelected(String dayId, int time, int meal, String description, float value) {
        FragmentManager manager = getSupportFragmentManager();
        QuickInsertFrag dialog = new QuickInsertFrag();
        dialog.setDayId(dayId);
        dialog.setTime(time);
        dialog.setMeal(meal);
        dialog.setDescription(description);
        dialog.setValue(value);
        dialog.show(manager, QuickInsertFrag.INSERT_TAG);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.foods_usage_act);

        mealsNameList = getResources().getStringArray(R.array.meals_name_list);

        SpinnerAdapter adapter = ArrayAdapter.createFromResource(this, R.array.meals_name_list,
                android.R.layout.simple_spinner_dropdown_item);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setListNavigationCallbacks(adapter, this);

        // Tells fragment to show foods usage filtered by date and meal inside the intent/bundle
        // that started this activity:
        if(savedInstanceState == null) {
            Intent intent = getIntent();
            mDateId = intent.getExtras().getString(DATE_ID_PARAMETER);
            actionBar.setSelectedNavigationItem(intent.getExtras().getInt(MEAL_PARAMETER));
        } else {
            mDateId = savedInstanceState.getString(DATE_ID_PARAMETER);
            actionBar.setSelectedNavigationItem(savedInstanceState.getInt(MEAL_PARAMETER));
        }
        mFragment = (FoodsUsageListFrag) getSupportFragmentManager().findFragmentById(R.id.foods_usage_list_frag);
        mFragment.refresh(mDateId, actionBar.getSelectedNavigationIndex());

        setFieldsReferenceFromForm();
        setupFields();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(DATE_ID_PARAMETER, mDateId);
        outState.putInt(MEAL_PARAMETER, getSupportActionBar().getSelectedNavigationIndex());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(int position, long itemId) {
        // TODO: the following logic needs change to use itemId
        if(isLogEnabled) {
            Log.d(TAG,
                  " ->onNavigationItemSelected()" +
                  " ->position = " + position +
                  " ->itemId = " + itemId +
                  " ->mDateId = " + mDateId
            );
        }
        mFragment.refresh(mDateId, position);
        return true;
    }

    private void setFieldsReferenceFromForm() {
        mTxtDate = (TextView) findViewById(R.id.txtDate);
    }

    private void setupFields() {
        mTxtDate.setText(DateUtil.dateIdToFormattedString(mDateId));
    }
}
