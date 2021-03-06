package br.com.arndroid.etdiet.test.utils.sk.m217.tests.utils;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;

import java.util.Calendar;
import java.util.Date;

import br.com.arndroid.etdapi.data.Meal;
import br.com.arndroid.etdiet.compat.Compatibility;
import br.com.arndroid.etdiet.meals.Meals;
import br.com.arndroid.etdiet.provider.Contract;
import br.com.arndroid.etdiet.provider.Provider;
import br.com.arndroid.etdiet.provider.foodsusage.FoodsUsageEntity;
import br.com.arndroid.etdiet.provider.foodsusage.FoodsUsageManager;
import br.com.arndroid.etdiet.utils.DateUtils;
import br.com.arndroid.etdiet.utils.sk.m217.tests.utils.ProviderTestCase3;

public class ProviderTestCase3Test extends ProviderTestCase3<Provider> {

    public ProviderTestCase3Test() {
        super(Provider.class, Contract.AUTHORITY);
    }

    @Override
    protected void setUp() throws Exception {
        // Important: calling the base class implementation of this method
        // where the "magic" of isolation is set up:
        super.setUp();
    }

    public void testWithoutDatabaseChangeMustNotCallObserver() {
        // This test is NOT compatible with API > 20. For more information see #162.
        if (Compatibility.getInstance().compatibilityLevel() > Build.VERSION_CODES.KITKAT_WATCH) {
            // OK... JUnit 3 doesn't have a good way to warn the runner. We will assume a pass here.
            assertTrue(true);
            return;
        }

        final Context context = getMockContext();
        final TestContentObserver mObserver = new TestContentObserver();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        final String dateId = DateUtils.dateToDateId(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        final String otherDateId = DateUtils.dateToDateId(cal.getTime());
        registerContentObserver(Uri.withAppendedPath(
                Contract.FoodsUsage.DATE_ID_CONTENT_URI, dateId), true, mObserver);

        final FoodsUsageManager foodsManager = new FoodsUsageManager(context);
        FoodsUsageEntity foodsEntity = new FoodsUsageEntity(null, otherDateId,
                Meal.BREAKFAST, 1, "food for test", 7.7f);
        foodsManager.refresh(foodsEntity);

        assertTrue(!mObserver.mCalled);
        getMockContext().getContentResolver().unregisterContentObserver(mObserver);
    }

    public void testWithDatabaseChangeMustCallObserver() {
        // This test is NOT compatible with API > 20. For more information see #162.
        if (Compatibility.getInstance().compatibilityLevel() > Build.VERSION_CODES.KITKAT_WATCH) {
            // OK... JUnit 3 doesn't have a good way to warn the runner. We will assume a pass here.
            assertTrue(true);
            return;
        }

        final Context context = getMockContext();
        final TestContentObserver mObserver = new TestContentObserver();

        final String dateId = DateUtils.dateToDateId(new Date());

        registerContentObserver(Uri.withAppendedPath(
                Contract.FoodsUsage.DATE_ID_CONTENT_URI, dateId), true, mObserver);

        final FoodsUsageManager foodsManager = new FoodsUsageManager(context);
        FoodsUsageEntity foodsEntity = new FoodsUsageEntity(null, dateId,
                Meal.BREAKFAST, 1, "food for test", 7.7f);
        foodsManager.refresh(foodsEntity);

        assertTrue(mObserver.mCalled);
        getMockContext().getContentResolver().unregisterContentObserver(mObserver);
    }

    public static class TestContentObserver extends ContentObserver {

        public boolean mCalled = false;

        public TestContentObserver() {
            super(new Handler());
        }

        @Override
        public void onChange(boolean selfChange) {
            onChange(selfChange, null);
        }
        @Override
        public void onChange(boolean selfChange, Uri uri) {
            mCalled = true;
        }
    }
}
