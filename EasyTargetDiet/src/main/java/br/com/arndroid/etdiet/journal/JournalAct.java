package br.com.arndroid.etdiet.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import br.com.arndroid.etdiet.R;
import br.com.arndroid.etdiet.compat.Compat;
import br.com.arndroid.etdiet.foodsusage.FoodsUsageAct;
import br.com.arndroid.etdiet.foodsusage.FoodsUsageListFrag;
import br.com.arndroid.etdiet.util.DateUtil;

public class JournalAct extends ActionBarActivity {

    private Spinner mSpnMeal;
    private DatePicker mDteDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.journal_act);

        setFieldsReferenceFromForm();
        setupFields();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.journal, menu);
        return true;
    }

    private void setFieldsReferenceFromForm() {
        mSpnMeal = (Spinner) findViewById(R.id.spnMeal);
        mDteDate = (DatePicker) findViewById(R.id.dteDate);
    }

    private void setupFields() {
        SpinnerAdapter adapter = ArrayAdapter.createFromResource(this, R.array.meals_name_list,
                android.R.layout.simple_spinner_dropdown_item);
        mSpnMeal.setAdapter(adapter);
        Compat.getInstance().setCalendarViewShownToDatePicker(false, mDteDate);
    }

    public void cmdTestBreakfast(View view) {

        // TODO: for tests purposes only. Move and refactor.
        String dateId = DateUtil.datePickerToDateId(mDteDate);
        int meal = mSpnMeal.getSelectedItemPosition();

        FoodsUsageListFrag foodsUsageListFrag = (FoodsUsageListFrag)
                getSupportFragmentManager().findFragmentById(R.id.foods_usage_list_frag);

        if (foodsUsageListFrag != null) {
            foodsUsageListFrag.refresh(dateId, meal);
        } else {
            Intent intent = new Intent(this, FoodsUsageAct.class);
            intent.putExtra(FoodsUsageAct.DATE_ID_PARAMETER, dateId);
            intent.putExtra(FoodsUsageAct.MEAL_PARAMETER, meal);
            startActivity(intent);
        }

    }
}
