package br.com.arndroid.etdiet.provider;

import android.content.ContentValues;

public abstract class AbstractEntity {

    abstract public ContentValues toContentValues();

    abstract public ContentValues toContentValuesIgnoreNulls();

    abstract public void validateOrThrow();

    abstract public String getTableName();

    abstract public String getIdColumnName();
}