package br.com.arndroid.etdiet.provider.parametershistory;

import android.content.UriMatcher;
import android.net.Uri;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.arndroid.etdiet.provider.BaseProviderOperator;
import br.com.arndroid.etdiet.provider.Contract;
import br.com.arndroid.etdiet.provider.OperationParameters;
import br.com.arndroid.etdiet.provider.Provider;
import br.com.arndroid.etdiet.utils.UrisUtils;

public class ParametersHistoryOperator extends BaseProviderOperator {

    private static final Logger LOG = LoggerFactory.getLogger(ParametersHistoryOperator.class);

    // Safe change ParametersHistory.Uri: add line for a new uri.
    public static final int PARAMETERS_HISTORY_URI_MATCH = 1;
    public static final int PARAMETERS_HISTORY_ITEM_URI_MATCH = 2;

    public ParametersHistoryOperator() {
        UriMatcher matcher =  getUriMatcher();
        // Safe change ParametersHistory.Uri: add line for a new uri.
        matcher.addURI(Contract.ParametersHistory.CONTENT_URI.getAuthority(),
                UrisUtils.pathForUriMatcherFromUri(Contract.ParametersHistory.CONTENT_URI),
                PARAMETERS_HISTORY_URI_MATCH);
        matcher.addURI(Contract.ParametersHistory.CONTENT_URI.getAuthority(),
                UrisUtils.pathForUriMatcherFromUri(Contract.ParametersHistory.CONTENT_URI) + "/#",
                PARAMETERS_HISTORY_ITEM_URI_MATCH);
    }

    @Override
    public String getType(Uri uri) {
        switch (getUriMatcher().match(uri)) {
            // ParametersHistory.Uri: add line for a new uri.
            case PARAMETERS_HISTORY_URI_MATCH:
                return Contract.ParametersHistory.CONTENT_TYPE;
            case PARAMETERS_HISTORY_ITEM_URI_MATCH:
                return Contract.ParametersHistory.CONTENT_ITEM_TYPE;
            default:
                LOG.warn("Unknown uri in getType(Uri):{}", uri);
                return null;
        }
    }

    @Override
    public String tableName() {
        return Contract.ParametersHistory.TABLE_NAME;
    }

    @Override
    public boolean isOperationProhibitedForUri(int operation, Uri uri) {
        int match = getUriMatcher().match(uri);
        if(match == UriMatcher.NO_MATCH) throw new IllegalArgumentException("Unknown uri: " + uri);

        // Safe change ParametersHistory.Uri: evaluate line addition for new uri.
        switch (operation) {
            case QUERY_OPERATION:
                return false;
            case INSERT_OPERATION:
                return match != PARAMETERS_HISTORY_URI_MATCH;
            case UPDATE_OPERATION:
                return false;
            case DELETE_OPERATION:
                return match != PARAMETERS_HISTORY_ITEM_URI_MATCH;
            default:
                throw new IllegalArgumentException("Unknown operation: " + operation);
        }
    }

    @Override
    public void onValidateParameters(int operation, Uri uri, OperationParameters parameters,
                                     Provider provider) {

        // Safe change ParametersHistory.Uri: evaluate line(s) addition for new uri.
        if(getUriMatcher().match(uri) == PARAMETERS_HISTORY_ITEM_URI_MATCH) {
            parameters.setSelection(Contract.ParametersHistory.ID_SELECTION);
            parameters.setSelectionArgs(new String[] {uri.getLastPathSegment()});
        }
    }
}
