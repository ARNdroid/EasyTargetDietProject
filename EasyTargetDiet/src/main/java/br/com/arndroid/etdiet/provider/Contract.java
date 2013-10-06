package br.com.arndroid.etdiet.provider;

import android.net.Uri;
import android.provider.BaseColumns;

import java.io.Serializable;

public class Contract {

    // This utility class cannot be instantiated.
    private Contract() {}

	/*
	 * Global Definitions
	 */

    public static final String AUTHORITY = "br.com.arndroid.provider.etdiet";

    public static final int FIELD_TYPE_INTEGER = 1;
    public static final int FIELD_TYPE_LONG = 2;
    public static final int FIELD_TYPE_FLOAT = 3;
    public static final int FIELD_TYPE_STRING = 4;

	/*
	 * Exceptions
	 */

    /*
     * We use exception to error handling. We don't like but this is the providers way.
     * This class doesn't extend Exception because Providers API doesn't throw any checked exception.
     */

    public static class TargetException extends RuntimeException {

        // A descriptor for fields with problem:
        public static final class FieldDescriptor implements Serializable {

            private String mTableName;
            private String mColumnName;
            private Object mValue;

            protected FieldDescriptor(String tableName, String columnName, Object value) {
                mTableName = tableName;
                mColumnName = columnName;
                mValue = value;
            }

            public String getTableName() {
                return mTableName;
            }

            public String getColumnName() {
                return mColumnName;
            }

            public Object getValue() {
                return mValue;
            }

            private static final long serialVersionUID = 1L;
        }

        public static final int UNEXPECTED_ERROR = 0;
        public static final int NULL_VALUE = 1;
        public static final int INVALID_VALUE = 2;
        public static final int ID_UPDATED = 3;
        public static final int DUPLICATED_DATA = 4;

        private final int mErrorCode;
        private final FieldDescriptor[] mFieldDescriptorArray;
        private String mDetailMessage;

        protected TargetException(int errorCode, FieldDescriptor[] fieldDescriptorArray, Throwable cause) {
            super(cause);
            mErrorCode = errorCode;
            mFieldDescriptorArray = fieldDescriptorArray;
        }

        public int getErrorCode() {
            return mErrorCode;
        }

        public FieldDescriptor[] getFieldDescriptorArray() {
            // OK... we are returning the internal array and clients will have access
            // to our array and to fields descriptors. But we are not scared about
            // attacks here.
            return mFieldDescriptorArray;
        }

        @Override
        public String getMessage() {
            if (mDetailMessage == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error code: ").append(getErrorCode()).append('\n');
                for (int i = 0; i < mFieldDescriptorArray.length; i++) {
                    sb.append("Field descriptor at position ").append(i).append(":\n")
                            .append("Table = '").append(mFieldDescriptorArray[i].getTableName())
                            .append("', column = '").append(mFieldDescriptorArray[i].getColumnName())
                            .append("', value = '").append(mFieldDescriptorArray[i].getValue() == null ? null : mFieldDescriptorArray[i].getValue().toString()).append("'\n");
                }
                mDetailMessage = sb.toString();
            }
            return mDetailMessage;
        }

        private static final long serialVersionUID = 1L;
    }

	/*
	 * Foods Usage Definitions
	 */

    protected interface FoodsUsageColumns {

        // Table:
        public static final String TABLE_NAME = "foods_usage";

        // Columns:
        public static final String DAY_ID = "day_id";
        public static final String MEAL = "meal";
        public static final String TIME = "time";
        public static final String DESCRIPTION = "description";
        public static final String VALUE = "value";

    }

    public static final class FoodsUsage implements BaseColumns, FoodsUsageColumns {

        // This utility class cannot be instantiated:
        private FoodsUsage() {}

		/*
		 * URI's
		 */

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

		/*
		 * MIME types
		 */

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + TABLE_NAME;

		/*
		 * Projections
		 */

        public static final String[] SIMPLE_LIST_PROJECTION = {_ID, TIME, DESCRIPTION, VALUE};

		/*
		 * Selection
		 */

        public static final String DAY_AND_MEAL_SELECTION = DAY_ID + "=? AND " + MEAL + "=?";

		/*
		 * Sort order
		 */

        public static final String DEFAULT_SORT_ORDER = TIME + " ASC";

		/*
		 * Utility methods
		 */

        public static int fieldTypeForColumn(String columnName) {
            return fieldTypeForTableAndColumn(TABLE_NAME, columnName);
        }
    }

	/*
	 * Weekday Parameters Definitions
	 */

    protected interface WeekdaysParametersColumns {

        // Table:
        public static final String TABLE_NAME = "weekday_parameters";

        // Columns:
        public static final String BREAKFAST_START_TIME = "breakfast_start_time";
        public static final String BREAKFAST_END_TIME = "breakfast_end_time";
        public static final String BREAKFAST_GOAL = "breakfast_goal";
        public static final String BRUNCH_START_TIME = "brunch_start_time";
        public static final String BRUNCH_END_TIME = "brunch_end_time";
        public static final String BRUNCH_GOAL = "brunch_goal";
        public static final String LUNCH_START_TIME = "lunch_start_time";
        public static final String LUNCH_END_TIME = "lunch_end_time";
        public static final String LUNCH_GOAL = "lunch_goal";
        public static final String SNACK_START_TIME = "snack_start_time";
        public static final String SNACK_END_TIME = "snack_end_time";
        public static final String SNACK_GOAL = "snack_goal";
        public static final String DINNER_START_TIME = "dinner_start_time";
        public static final String DINNER_END_TIME = "dinner_end_time";
        public static final String DINNER_GOAL = "dinner_goal";
        public static final String SUPPER_START_TIME = "supper_start_time";
        public static final String SUPPER_END_TIME = "supper_end_time";
        public static final String SUPPER_GOAL = "supper_goal";
        public static final String EXERCISE_GOAL = "exercise_goal";
        public static final String LIQUID_GOAL = "liquid_goal";
        public static final String OIL_GOAL = "oil_goal";
        public static final String SUPPLEMENT_GOAL = "supplement_goal";
    }

    public static final class WeekdayParameters implements BaseColumns, WeekdaysParametersColumns {

        // This utility class cannot be instantiated:
        private WeekdayParameters() {}

		/*
		 * URI's
		 */

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

		/*
		 * MIME types
		 */

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + TABLE_NAME;

		/*
		 * Utility methods
		 */

        public static int fieldTypeForColumn(String columnName) {
            return fieldTypeForTableAndColumn(TABLE_NAME, columnName);
        }
    }

    /*
	 * Parameters History Definitions
	 */

    protected interface ParametersHistoryColumns {

        // Table:
        public static final String TABLE_NAME = "parameters_history";

        // Columns:
        public static final String TYPE = "type";
        public static final String DATE = "date";
        public static final String INTEGRAL_NEW_VALUE = "integral_new_value";
        public static final String FLOATING_POINT_NEW_VALUE = "floating_point_new_value";
        public static final String TEXT_NEW_VALUE = "text_new_value";

    }

    public static final class ParametersHistory implements BaseColumns, ParametersHistoryColumns {

        // This utility class cannot be instantiated:
        private ParametersHistory() {}

		/*
		 * URI's
		 */

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

		/*
		 * MIME types
		 */

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + TABLE_NAME;

		/*
		    Type constants
		 */

        /*
            Warning!
            The value of follow constants can NOT be changed or
            we will break legacy data in DB.
         */
        public static final int DAILY_ALLOWANCE_PARAMETER_TYPE = 0;
        public static final int WEEKLY_ALLOWANCE_PARAMETER_TYPE = 1;
        public static final int TRACKING_WEEKDAY_PARAMETER_TYPE = 2;
        public static final int EXERCISE_USE_MODE_PARAMETER_TYPE = 3;
        public static final int EXERCISE_USE_ORDER_PARAMETER_TYPE = 4;

        public static final int EXERCISES_FIRST_EXERCISE_USE_ORDER = 0;
        public static final int WEEKLY_FIRST_EXERCISE_USE_ORDER = 1;

        public static final int DONT_USE_EXERCISE_USE_MODE = 0;
        public static final int USE_DONT_ACCUMULATE_EXERCISE_USE_MODE = 1;
        public static final int USE_AND_ACCUMULATE_EXERCISE_USE_MODE = 2;
		/*
		 * Sort order
		 */

        public static final String DEFAULT_SORT_ORDER = DATE + " DESC";

		/*
		 * Utility methods
		 */

        public static int fieldTypeForColumn(String columnName) {
            return fieldTypeForTableAndColumn(TABLE_NAME, columnName);
        }
    }

    private static int fieldTypeForTableAndColumn(String tableName, String columnName) {
        if(FoodsUsage.TABLE_NAME.equals(tableName)) {
            if (FoodsUsage._ID.equals(columnName)) {
                return FIELD_TYPE_LONG;
            } else if (FoodsUsage.DAY_ID.equals(columnName)) {
                return FIELD_TYPE_STRING;
            } else if (FoodsUsage.MEAL.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (FoodsUsage.TIME.equals(columnName)) {
                return FIELD_TYPE_LONG;
            } else if (FoodsUsage.DESCRIPTION.equals(columnName)) {
                return FIELD_TYPE_STRING;
            } else if (FoodsUsage.VALUE.equals(columnName)) {
                return FIELD_TYPE_FLOAT;
            } else {
                throw new IllegalArgumentException("Unknown column " + columnName + " for table " + tableName);
            }

        } else if(WeekdayParameters.TABLE_NAME.equals(tableName)) {
            if (WeekdayParameters._ID.equals(columnName)) {
                return FIELD_TYPE_LONG;
            } else if (WeekdayParameters.BREAKFAST_START_TIME.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (WeekdayParameters.BREAKFAST_END_TIME.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (WeekdayParameters.BREAKFAST_GOAL.equals(columnName)) {
                return FIELD_TYPE_FLOAT;
            } else if (WeekdayParameters.BRUNCH_START_TIME.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (WeekdayParameters.BRUNCH_END_TIME.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (WeekdayParameters.BRUNCH_GOAL.equals(columnName)) {
                return FIELD_TYPE_FLOAT;
            } else if (WeekdayParameters.LUNCH_START_TIME.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (WeekdayParameters.LUNCH_END_TIME.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (WeekdayParameters.LUNCH_GOAL.equals(columnName)) {
                return FIELD_TYPE_FLOAT;
            } else if (WeekdayParameters.SNACK_START_TIME.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (WeekdayParameters.SNACK_END_TIME.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (WeekdayParameters.SNACK_GOAL.equals(columnName)) {
                return FIELD_TYPE_FLOAT;
            } else if (WeekdayParameters.DINNER_START_TIME.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (WeekdayParameters.DINNER_END_TIME.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (WeekdayParameters.DINNER_GOAL.equals(columnName)) {
                return FIELD_TYPE_FLOAT;
            } else if (WeekdayParameters.SUPPER_START_TIME.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (WeekdayParameters.SUPPER_END_TIME.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (WeekdayParameters.SUPPER_GOAL.equals(columnName)) {
                return FIELD_TYPE_FLOAT;
            } else if (WeekdayParameters.EXERCISE_GOAL.equals(columnName)) {
                return FIELD_TYPE_FLOAT;
            } else if (WeekdayParameters.LIQUID_GOAL.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (WeekdayParameters.OIL_GOAL.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (WeekdayParameters.SUPPLEMENT_GOAL.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else {
                throw new IllegalArgumentException("Unknown column " + columnName + " for table " + tableName);
            }

        } else if (ParametersHistory.TABLE_NAME.equals(tableName)) {
            if (ParametersHistory._ID.equals(columnName)) {
                return FIELD_TYPE_LONG;
            } else if (ParametersHistory.TYPE.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (ParametersHistory.DATE.equals(columnName)) {
                return FIELD_TYPE_STRING;
            } else if (ParametersHistory.INTEGRAL_NEW_VALUE.equals(columnName)) {
                return FIELD_TYPE_INTEGER;
            } else if (ParametersHistory.FLOATING_POINT_NEW_VALUE.equals(columnName)) {
                return FIELD_TYPE_FLOAT;
            } else if (ParametersHistory.TEXT_NEW_VALUE.equals(columnName)) {
                return FIELD_TYPE_STRING;
            } else {
                throw new IllegalArgumentException("Unknown column " + columnName + " for table " + tableName);
            }

        } else {
            throw new IllegalArgumentException("Unknown table " + tableName);
        }
    }
}
