package br.com.arndroid.etdiet.weights;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;

import br.com.arndroid.etdiet.R;
import br.com.arndroid.etdiet.action.FragmentActionReplier;
import br.com.arndroid.etdiet.action.FragmentMenuReplier;
import br.com.arndroid.etdiet.dialog.WeightAutoDialog;
import br.com.arndroid.etdiet.foodsusage.FoodsUsageListAdapter;
import br.com.arndroid.etdiet.meals.Meals;
import br.com.arndroid.etdiet.provider.Contract;
import br.com.arndroid.etdiet.provider.foodsusage.FoodsUsageEntity;
import br.com.arndroid.etdiet.provider.foodsusage.FoodsUsageManager;
import br.com.arndroid.etdiet.provider.weights.WeightsEntity;
import br.com.arndroid.etdiet.provider.weights.WeightsManager;
import br.com.arndroid.etdiet.quickinsert.QuickInsertFrag;
import br.com.arndroid.etdiet.utils.DateUtils;
import br.com.arndroid.etdiet.utils.ExposedObservable;

public class WeightsListFragment extends ListFragment implements FragmentMenuReplier,
        LoaderManager.LoaderCallbacks<Cursor>,
        FragmentActionReplier {

    private static final int WEIGHTS_LOADER_ID = 1;

    private TextView mTxtEmpty;
    private ListView mLstList;
    private WeightsListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.weights_list_fragment, container, false);
        bindScreen(rootView);
        setupScreen(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new WeightsListAdapter(getActivity());
        setListAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        WeightAutoDialog dialog = new WeightAutoDialog();
        dialog.setTitle(getString(R.string.weight));
        dialog.setMinIntegerValue(0);
        dialog.setMaxIntegerValue(300);
        final WeightsManager manager = new WeightsManager(getActivity().getApplicationContext());
        dialog.setWeightEntity(manager.weightFromId(id));
        dialog.show(getActivity().getSupportFragmentManager(), null);
    }

    @Override
    public void onReplyMenuFromHolderActivity(int menuItemId) {
        switch (menuItemId) {
            case R.id.add:
                WeightAutoDialog dialog = new WeightAutoDialog();
                dialog.setTitle(getString(R.string.weight));
                dialog.setMinIntegerValue(0);
                dialog.setMaxIntegerValue(300);
                final WeightsManager manager = new WeightsManager(getActivity().getApplicationContext());
                final WeightsEntity entity = manager.getSuggestedNewWeight();
                dialog.setWeightEntity(entity);
                FragmentManager fragmentManager = getFragmentManager();
                dialog.show(fragmentManager, null);
                break;
            default:
                throw new IllegalArgumentException("Invalid menuItemId=" + menuItemId);
        }
    }

    private void bindScreen(View rootView) {
        mTxtEmpty = (TextView) rootView.findViewById(android.R.id.empty);
        mLstList = (ListView) rootView.findViewById(android.R.id.list);
    }

    private void setupScreen(View rootView) {
        final ListView list = (ListView) rootView.findViewById(android.R.id.list);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // We need a final variable to use inside alert onClick event:
                final long weightId = id;
                final WeightsManager manager = new WeightsManager(
                        getActivity().getApplicationContext());
                final WeightsEntity entity = manager.weightFromId(weightId);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        manager.remove(weightId);
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, null);
                builder.setMessage(String.format(getResources().getString(R.string.delete_weight_msg),
                        entity.getWeight(), DateUtils.dateIdToFormattedString(entity.getDateId()),
                        DateUtils.timeToFormattedString(entity.getTime())));
                builder.create().show();
                return true;
            }
        });
        mTxtEmpty.setText(getResources().getText(R.string.list_empty_weights));
    }

    private void refreshScreen() {
        if (mLstList.getAdapter().getCount() == 0) {
            mTxtEmpty.setVisibility(View.VISIBLE);
            mLstList.setVisibility(View.GONE);
        } else {
            mTxtEmpty.setVisibility(View.GONE);
            mLstList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onReplyActionFromOtherFragment(String actionTag, Bundle actionData) {
        // If not loaded, load the first instance,
        // otherwise closes current loader e start a new one:
        getLoaderManager().restartLoader(WEIGHTS_LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case WEIGHTS_LOADER_ID:
                return new CursorLoader(getActivity(), Contract.Weights.CONTENT_URI,
                        null , null, null, Contract.Weights.DATE_AND_TIME_DESC);
            default:
                throw new IllegalArgumentException("Invalid loader id=" + id);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor data) {
        mAdapter.swapCursor(data);
        refreshScreen();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mAdapter.swapCursor(null);
    }
}