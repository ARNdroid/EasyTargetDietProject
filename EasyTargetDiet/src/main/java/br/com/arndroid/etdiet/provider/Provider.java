package br.com.arndroid.etdiet.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import br.com.arndroid.etdiet.sqlite.DBOpenHelper;

import static br.com.arndroid.etdiet.provider.Contract.TargetException.FieldDescriptor;

public class Provider extends ContentProvider {

	/*
	 * Static Initialization
	 */

    private static final UriMatcher sUriMatcher;
    private static final int FOODS_USAGE_URI_MATCH = 1;
    private static final int FOODS_USAGE_ITEM_URI_MATCH = 2;
    private static final int WEEKDAY_PARAMETERS_URI_MATCH = 3;
    private static final int WEEKDAY_PARAMETERS_ITEM_URI_MATCH = 4;
    private static final int PARAMETERS_HISTORY_URI_MATCH = 5;
    private static final int PARAMETERS_HISTORY_ITEM_URI_MATCH = 6;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.FoodsUsage.TABLE_NAME, FOODS_USAGE_URI_MATCH);
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.FoodsUsage.TABLE_NAME + "/#", FOODS_USAGE_ITEM_URI_MATCH);
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.WeekdayParameters.TABLE_NAME, WEEKDAY_PARAMETERS_URI_MATCH);
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.WeekdayParameters.TABLE_NAME + "/#", WEEKDAY_PARAMETERS_ITEM_URI_MATCH);
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.ParametersHistory.TABLE_NAME, PARAMETERS_HISTORY_URI_MATCH);
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.ParametersHistory.TABLE_NAME + "/#", PARAMETERS_HISTORY_ITEM_URI_MATCH);
    }

	/*
	 * Implementation
	 */
    private DBOpenHelper mDBHelper;

    @Override
    public boolean onCreate() {
        mDBHelper = new DBOpenHelper(getContext());
        return true;
    }

    @Override
    public void shutdown() {
        // Do nothing. We are disabling warnings
        // suggesting a implementation.
    }

    @Override
    public String getType(Uri uri) {

        switch (sUriMatcher.match(uri)) {
            case FOODS_USAGE_URI_MATCH:
                return Contract.FoodsUsage.CONTENT_TYPE;

            case FOODS_USAGE_ITEM_URI_MATCH:
                return Contract.FoodsUsage.CONTENT_ITEM_TYPE;

            case WEEKDAY_PARAMETERS_URI_MATCH:
                return Contract.WeekdayParameters.CONTENT_TYPE;

            case WEEKDAY_PARAMETERS_ITEM_URI_MATCH:
                return Contract.WeekdayParameters.CONTENT_ITEM_TYPE;

            case PARAMETERS_HISTORY_URI_MATCH:
                return Contract.ParametersHistory.CONTENT_TYPE;

            case PARAMETERS_HISTORY_ITEM_URI_MATCH:
                return Contract.ParametersHistory.CONTENT_ITEM_TYPE;

            default:
                Log.w(TAG, "Unknown URI in getType(Uri): " + uri);
                return null;
        }
    }

    public SQLiteOpenHelper hookOpenHelperForTests() {
        return mDBHelper;
    }

	/*
	 * CRUD
	 */

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String orderBy = null;

        switch (sUriMatcher.match(uri)) {
            case FOODS_USAGE_URI_MATCH:
                qb.setTables(Contract.FoodsUsage.TABLE_NAME);
                if(TextUtils.isEmpty(sortOrder)) {
                    orderBy = Contract.FoodsUsage.DEFAULT_SORT_ORDER;
                } else {
                    orderBy = sortOrder;
                }
                break;

            case FOODS_USAGE_ITEM_URI_MATCH:
                qb.setTables(Contract.FoodsUsage.TABLE_NAME);
                qb.appendWhere(Contract.FoodsUsage._ID + "=" + uri.getLastPathSegment());
                break;

            case WEEKDAY_PARAMETERS_URI_MATCH:
                qb.setTables(Contract.WeekdayParameters.TABLE_NAME);
                break;

            case WEEKDAY_PARAMETERS_ITEM_URI_MATCH:
                qb.setTables(Contract.WeekdayParameters.TABLE_NAME);
                qb.appendWhere(Contract.WeekdayParameters._ID + "=" + uri.getLastPathSegment());
                break;

            case PARAMETERS_HISTORY_URI_MATCH:
                qb.setTables(Contract.ParametersHistory.TABLE_NAME);
                if(TextUtils.isEmpty(sortOrder)) {
                    orderBy = Contract.ParametersHistory.DEFAULT_SORT_ORDER;
                } else {
                    orderBy = sortOrder;
                }
                break;

            case PARAMETERS_HISTORY_ITEM_URI_MATCH:
                qb.setTables(Contract.ParametersHistory.TABLE_NAME);
                qb.appendWhere(Contract.ParametersHistory._ID + "=" + uri.getLastPathSegment());
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        if (values == null) {
            throw new IllegalArgumentException("values == null");
        }

        final AbstractEntity entity;
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case FOODS_USAGE_URI_MATCH:
                // Get entity (we don't have default values to join here):
                entity = FoodsUsageEntity.fromContentValues(values);
                break;

            case WEEKDAY_PARAMETERS_URI_MATCH:
                // Insert not allowed here:
                return null;

            case PARAMETERS_HISTORY_URI_MATCH:
                // Get entity (we don't have default values to join here):
                entity = ParametersHistoryEntity.fromContentValues(values);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        entity.validateOrThrow();
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        long idInserted = -1;
        try {
            idInserted = db.insertOrThrow(entity.getTableName(), null, entity.toContentValues());
        } catch (SQLiteConstraintException e) {
            // Is this constraint violation "expected"? If is, we will throw a "better" exception.
            // An example is an attribute used like a "primary key" but not a DB PK. Since this
            // attribute will have an UNIQUE INDEX, an attempt to insert an equal value can throw
            // this constraint exception (and we know that just in the insert moment).
            switch (match) {
                case FOODS_USAGE_URI_MATCH:
                    // We're doing nothing here because all constraint are safely validated inside
                    // validateOrThrow().
                    break;
                case PARAMETERS_HISTORY_URI_MATCH:
                    // We're doing nothing here because despite we have a UNIQUE INDEX, breaking it
                    // is not allowed to users. The index will be break only by a bug.
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);
            }
        }

        Uri result = Uri.withAppendedPath(uri, String.valueOf(idInserted));
        getContext().getContentResolver().notifyChange(result, null);

        if(isLogEnabled) {
            Log.d(TAG,
                  " ->insert()" +
                  " ->entity = " + entity.toString() +
                  " ->idInserted = " + idInserted
            );
        }
        return result;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values == null) {
            throw new IllegalArgumentException("values must be not null");
        }

        final int fail = 0;
        final ContentValues currentValues;
        final AbstractEntity entity;
        final Cursor c;
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case FOODS_USAGE_URI_MATCH:
                return fail;

            case FOODS_USAGE_ITEM_URI_MATCH:
                // Verify current values:
                c = query(uri, null, null, null, null);
                try {
                    if (c.getCount() != 1) {
                        return fail;
                    }
                    c.moveToFirst();
                    currentValues = FoodsUsageEntity.fromCursor(c).toContentValues();
                } finally {
                    c.close();
                }
                // Get entity AND join current values:
                entity = FoodsUsageEntity.fromJoinInContentValues(values, currentValues);
                break;

            case WEEKDAY_PARAMETERS_URI_MATCH:
                return fail;

            case WEEKDAY_PARAMETERS_ITEM_URI_MATCH:
                // Verify current values:
                c = query(uri, null, null, null, null);
                try {
                    if (c.getCount() != 1) {
                        return fail;
                    }
                    c.moveToFirst();
                    currentValues = WeekdayParametersEntity.fromCursor(c).toContentValues();
                } finally {
                    c.close();
                }
                // Get entity AND join current values:
                entity = WeekdayParametersEntity.fromJoinInContentValues(values, currentValues);
                break;

            case PARAMETERS_HISTORY_URI_MATCH:
                return fail;

            case PARAMETERS_HISTORY_ITEM_URI_MATCH:
                // Verify current values:
                c = query(uri, null, null, null, null);
                try {
                    if (c.getCount() != 1) {
                        return fail;
                    }
                    c.moveToFirst();
                    currentValues = ParametersHistoryEntity.fromCursor(c).toContentValues();
                } finally {
                    c.close();
                }
                // Get entity AND join current values:
                entity = ParametersHistoryEntity.fromJoinInContentValues(values, currentValues);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        // Validations

        // TODO: we need to prepare following op to days table that has a TEXT _id (yyyyMMdd)
        // Id change is not allowed.
        // A try will be notified with the exception.
        // If client is sending the same id, we'll remove it.
        if (values.containsKey(entity.getIdColumnName())) {
            if (!values.getAsLong(entity.getIdColumnName()).equals(currentValues.getAsLong(entity.getIdColumnName()))) {
                FieldDescriptor[] fieldsDescriptorArray = new FieldDescriptor[2];
                fieldsDescriptorArray[0] = new FieldDescriptor(entity.getTableName(), entity.getIdColumnName(), currentValues.getAsLong(entity.getIdColumnName()));
                fieldsDescriptorArray[1] = new FieldDescriptor(entity.getTableName(), entity.getIdColumnName(), values.getAsLong(entity.getIdColumnName()));
                throw new Contract.TargetException(Contract.TargetException.ID_UPDATED, fieldsDescriptorArray, null);
            }
            values.remove(entity.getIdColumnName());
        }

        // Fields validations (entity must be values U currentValues here!):
        entity.validateOrThrow();

        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowsUpdated = -1;
        try {
            rowsUpdated = db.update(entity.getTableName(), values, entity.getIdColumnName() + "=?", new String[] {uri.getLastPathSegment()});
        } catch (SQLiteConstraintException e) {
            // Is this constraint violation "expected"? If is, we will throw a "better" exception.
            // An example is an attribute used like a "primary key" but not a DB PK. Since this
            // attribute will have an UNIQUE INDEX, an attempt to insert an equal value can throw
            // this constraint exception (and we know that just in the insert moment).
            switch (match) {
                case FOODS_USAGE_URI_MATCH:
                case WEEKDAY_PARAMETERS_URI_MATCH:
                case PARAMETERS_HISTORY_URI_MATCH:
                    // We're doing nothing here because all constraint are safely validated inside
                    // entity.validateOrThrow().
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);
            }
        }

        if (rowsUpdated > fail) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        final int fail = 0;
        final String tableName;
        final String whereClause;
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case WEEKDAY_PARAMETERS_URI_MATCH:
            case WEEKDAY_PARAMETERS_ITEM_URI_MATCH:
            case PARAMETERS_HISTORY_URI_MATCH:
            case PARAMETERS_HISTORY_ITEM_URI_MATCH:
                // Delete not allowed here:
                return fail;
            case FOODS_USAGE_URI_MATCH:
                tableName = Contract.FoodsUsage.TABLE_NAME;
                whereClause = selection;
                break;
            case FOODS_USAGE_ITEM_URI_MATCH:
                tableName = Contract.FoodsUsage.TABLE_NAME;
                whereClause = Contract.FoodsUsage._ID + "=" + uri.getLastPathSegment();
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        int rowsDeleted = db.delete(tableName, whereClause, selectionArgs);
        if (rowsDeleted > fail) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    // Log

    private static final String TAG = "==>ETD/" + Provider.class.getSimpleName();
    private static final boolean isLogEnabled = true;
}