package br.com.arndroid.etdiet.quickinsert;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;

import br.com.arndroid.etdiet.R;
import br.com.arndroid.etdiet.compat.Compat;
import br.com.arndroid.etdiet.provider.Contract;
import br.com.arndroid.etdiet.provider.FoodsUsageEntity;
import br.com.arndroid.etdiet.util.DateUtil;

/*
    TODO: Try a refactor.
    We need something like a DialogFragment with a layout that references a Fragment. This fragment
    will have its own layout with all UI bellow.
    The challenge is create the best communication between
      i - The host activity and the Dialog - Host will create the dialog and need to pass parameters
      ii - The dialog and the fragment - Dialog must pass every parameters forward
    We need to see Android Dialogs from Android Developers: there is an option to create a activity
    with the following custom attribute in AndroidManifest.xml:
    <activity android:theme="@android:style/Theme.Holo.DialogWhenLarge" >
 */
public class QuickInsertFrag extends DialogFragment {

    public static final String INSERT_TAG = QuickInsertFrag.class.getSimpleName() + ".INSERT_TAG";
    public static final String UPDATE_TAG = QuickInsertFrag.class.getSimpleName() + ".UPDATE_TAG";
    public static final String URI_PARAMETER = QuickInsertFrag.class.getSimpleName() + ".URI_PARAMETER";

    private Uri mUri;
    private Spinner mSpnMeal;
    private DatePicker mDteDate;
    private TimePicker mTimTime;
    private EditText mEdtDescription;
    private EditText mEdtValue;
    private String mDayIdSet = null;
    private Integer mTimeSet = null;
    private Integer mMealSet = null;
    private String mDescriptionSet = null;
    private Float mValueSet = null;

    public QuickInsertFrag() {
        // This constructor is required for DialogFragment
        super();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.quick_insert_frag, null);
        setFieldsReferenceFromForm(view);
        setupFields();

        if (savedInstanceState != null) {
            setUri((Uri) savedInstanceState.get(URI_PARAMETER));
        } else {
            if (UPDATE_TAG.equals(getTag())) {
                fillFormWithDataFromDB();
            }
            else {
                fillFormWithDataFromSetters();
            }
        }

        builder.setTitle(getResources().getText(R.string.quick_add));
        builder.setView(view);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // We will do nothing here because we are overriding onClick bellow...
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        final AlertDialog dlg = builder.create();

        dlg.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button b = dlg.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (insertOrUpdateFoodsUsage()) {
                            dlg.dismiss();
                        }
                    }
                });
            }
        });

        return dlg;
    }

    private void fillFormWithDataFromDB() {
        Cursor c = null;
        try {
            c = getActivity().getContentResolver().query(mUri, null, null, null, null);
            c.moveToFirst();
            FoodsUsageEntity entity = FoodsUsageEntity.fromCursor(c);
            mSpnMeal.setSelection(entity.getMeal());
            DateUtil.initDatePickerWithDateId(mDteDate, entity.getDayId());
            DateUtil.initTimePickerWithTimeAsInt(mTimTime, entity.getTime());
            mEdtDescription.setText(entity.getDescription());
            mEdtValue.setText(String.valueOf(entity.getValue()));
        } finally {
            if (c != null) c.close();
        }
    }

    private void setupFields() {
        SpinnerAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.meals_name_list,
                android.R.layout.simple_spinner_dropdown_item);
        mSpnMeal.setAdapter(adapter);
        Compat.getInstance().setCalendarViewShownToDatePicker(false, mDteDate);
        mTimTime.setIs24HourView(true);
    }

    private void setFieldsReferenceFromForm(View view) {
        mSpnMeal = (Spinner) view.findViewById(R.id.spnMeal);
        mDteDate = (DatePicker) view.findViewById(R.id.dteDate);
        mTimTime = (TimePicker) view.findViewById(R.id.timTime);
        mEdtDescription = (EditText) view.findViewById(R.id.edtDescription);
        mEdtValue = (EditText) view.findViewById(R.id.edtValue);
    }

    private boolean insertOrUpdateFoodsUsage() {
        final Long id = mUri == null ? null : Long.parseLong(mUri.getLastPathSegment());

        // TODO: this is intended to be a method, but I have a NullPointerException AVD Genymotion
        FoodsUsageEntity entity = new FoodsUsageEntity(
                id,
                DateUtil.datePickerToDateId(mDteDate),
                mSpnMeal.getSelectedItemPosition(),
                DateUtil.timePickerToTimeAsInt(mTimTime),
                TextUtils.isEmpty(mEdtDescription.getText().toString()) ? null : mEdtDescription.getText().toString(),
                TextUtils.isEmpty(mEdtValue.getText().toString()) ? null : Float.parseFloat(mEdtValue.getText().toString()));

        // Validations:
        try {
            entity.validateOrThrow();
        } catch (Contract.TargetException e) {
            constructDialogForError(e);
            return false;
        }

        // Operation:
        if (INSERT_TAG.equals(getTag())) {
            try {
                setUri(getActivity().getContentResolver().insert(Contract.FoodsUsage.CONTENT_URI,
                        entity.toContentValues()));
            } catch (Contract.TargetException e) {
                constructDialogForError(e);
                return false;
            }
        } else if (UPDATE_TAG.equals(getTag())) {
            try {
                getActivity().getContentResolver().update(mUri, entity.toContentValues(), null, null);
            } catch (Contract.TargetException e) {
                constructDialogForError(e);
                return false;
            }
        } else {
            throw new IllegalArgumentException("Invalid Tag '" + getTag());
        }
        return true;
    }

    private void constructDialogForError(Contract.TargetException e) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.quick_add);
        // TODO: construct a better message:
        builder.setMessage(e.getMessage());
        builder.setPositiveButton(android.R.string.ok, null);
        builder.create().show();
    }

    public void setUri(Uri uri) {
        mUri = uri;
    }

    private void fillFormWithDataFromSetters() {
        DateUtil.initDatePickerWithDateId(mDteDate, mDayIdSet);
        DateUtil.initTimePickerWithTimeAsInt(mTimTime, mTimeSet);
        mSpnMeal.setSelection(mMealSet);
        mEdtDescription.setText(mDescriptionSet);
        mEdtValue.setText(String.valueOf(mValueSet));
    }

    public void setDayId(String dayId) {
        mDayIdSet = dayId;
    }

    public void setTime(Integer time) {
        mTimeSet = time;
    }

    public void setMeal(Integer meal) {
        mMealSet = meal;
    }

    public void setDescription(String description) {
        mDescriptionSet = description;
    }

    public void setValue(Float value) {
        mValueSet = value;
    }
}
