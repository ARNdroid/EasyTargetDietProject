package br.com.arndroid.etdiet.compat;

import android.annotation.TargetApi;
import android.os.Build;
import android.widget.DatePicker;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CompatHONEYCOMB extends CompatGINGERBREAD_MR1 {

    // HONEYCOMB == V11

    @Override
    public void setCalendarViewShownToDatePicker(boolean shown, DatePicker picker) {
        picker.setCalendarViewShown(shown);
    }
}
