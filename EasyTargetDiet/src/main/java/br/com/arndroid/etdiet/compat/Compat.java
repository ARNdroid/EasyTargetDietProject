package br.com.arndroid.etdiet.compat;

import android.os.Build;
import android.widget.DatePicker;

public abstract class Compat {

    public abstract void setCalendarViewShownToDatePicker(boolean shown, DatePicker picker);

    public static Compat getInstance() {
        final int sdkVersion = Build.VERSION.SDK_INT;
        Compat compat;
        if (sdkVersion < Build.VERSION_CODES.HONEYCOMB) {
            compat = new CompatGINGERBREAD_MR1();
        } else {
            compat = new CompatHONEYCOMB();
        }

        return compat;
    }
}
