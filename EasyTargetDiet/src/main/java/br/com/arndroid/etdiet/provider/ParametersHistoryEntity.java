package br.com.arndroid.etdiet.provider;

import android.content.ContentValues;
import android.database.Cursor;

public class ParametersHistoryEntity extends AbstractEntity {
    private Long id;
    private Integer type;
    private String date;
    private Integer integralNewValue;
    private Float floatingPointNewValue;
    private String textNewValue;

	/*
	 * Implementation
	 */

    public ParametersHistoryEntity(Long id, Integer type, String date, Integer integralNewValue,
                                   Float floatingPointNewValue, String textNewValue) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.integralNewValue = integralNewValue;
        this.floatingPointNewValue = floatingPointNewValue;
        this.textNewValue = textNewValue;
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(Contract.ParametersHistory._ID, id);
        cv.put(Contract.ParametersHistory.TYPE, type);
        cv.put(Contract.ParametersHistory.DATE, date);
        cv.put(Contract.ParametersHistory.INTEGRAL_NEW_VALUE, integralNewValue);
        cv.put(Contract.ParametersHistory.FLOATING_POINT_NEW_VALUE, floatingPointNewValue);
        cv.put(Contract.ParametersHistory.TEXT_NEW_VALUE, textNewValue);
        return cv;
    }

    @Override
    public ContentValues toContentValuesIgnoreNulls() {
        ContentValues cv = new ContentValues();
        if (id != null) {
            cv.put(Contract.ParametersHistory._ID, id);
        }
        if (type != null) {
            cv.put(Contract.ParametersHistory.TYPE, type);
        }
        if (date != null) {
            cv.put(Contract.ParametersHistory.DATE, date);
        }
        if (integralNewValue != null) {
            cv.put(Contract.ParametersHistory.INTEGRAL_NEW_VALUE, integralNewValue);
        }
        if (floatingPointNewValue != null) {
            cv.put(Contract.ParametersHistory.FLOATING_POINT_NEW_VALUE, floatingPointNewValue);
        }
        if (textNewValue != null) {
            cv.put(Contract.ParametersHistory.TEXT_NEW_VALUE, textNewValue);
        }
        return cv;
    }

    @Override
    public void validateOrThrow() {

        if (type == null) {
            throwNullValueException(Contract.ParametersHistory.TYPE);
        }

        if (date == null) {
            throwNullValueException(Contract.ParametersHistory.DATE);
        }

        if (integralNewValue == null && floatingPointNewValue == null && textNewValue == null) {
            // We have chosen one...
            throwNullValueException(Contract.ParametersHistory.INTEGRAL_NEW_VALUE);
        }
    }

    private void throwNullValueException(String columnName) {
        throw new Contract.TargetException(Contract.TargetException.NULL_VALUE,
                new Contract.TargetException.FieldDescriptor[] {new Contract.TargetException.FieldDescriptor(Contract.ParametersHistory.TABLE_NAME,
                        columnName, null)}, null);
    }

    @Override
    public String getTableName() {
        return Contract.ParametersHistory.TABLE_NAME;
    }

    @Override
    public String getIdColumnName() {
        return Contract.ParametersHistory._ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;

        } else if (obj instanceof ParametersHistoryEntity) {
            ParametersHistoryEntity temp = (ParametersHistoryEntity) obj;
            // id:
            if (this.id != null) {
                if (!this.id.equals(temp.id))
                    return false;
            } else {
                if (temp.id != null)
                    return false;
            }

            // type:
            if (this.type != null) {
                if(!this.type.equals(temp.type))
                    return false;
            } else {
                if (temp.type != null)
                    return false;
            }

            // date:
            if (this.date != null) {
                if (!this.date.equals(temp.date))
                    return false;
            } else {
                if (temp.date != null)
                    return false;
            }

            // integral new value:
            if (this.integralNewValue != null) {
                if (!this.integralNewValue.equals(temp.integralNewValue))
                    return false;
            } else {
                if (temp.integralNewValue != null)
                    return false;
            }

            // floating point new value:
            if (this.floatingPointNewValue != null) {
                if (!this.floatingPointNewValue.equals(temp.floatingPointNewValue))
                    return false;
            } else {
                if (temp.floatingPointNewValue != null)
                    return false;
            }

            // text new value:
            if (this.textNewValue != null) {
                if (!this.textNewValue.equals(temp.textNewValue))
                    return false;
            } else {
                if (temp.textNewValue != null)
                    return false;
            }

            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result;
        result = id == null? 0 : id.hashCode();
        result += type == null? 0: type.hashCode();
        result += date == null? 0: date.hashCode();
        result += integralNewValue == null? 0: integralNewValue.hashCode();
        result += floatingPointNewValue == null? 0: floatingPointNewValue.hashCode();
        result += textNewValue == null? 0: textNewValue.hashCode();
        return result;
    }

    @Override
    public String toString() {

        return "["
                + Contract.ParametersHistory._ID + "=" + id + ", "
                + Contract.ParametersHistory.TYPE + "=" + type + ", "
                + Contract.ParametersHistory.DATE + "=" + date + ", "
                + Contract.ParametersHistory.INTEGRAL_NEW_VALUE + "=" + integralNewValue + ", "
                + Contract.ParametersHistory.FLOATING_POINT_NEW_VALUE + "=" + floatingPointNewValue + ", "
                + Contract.ParametersHistory.TEXT_NEW_VALUE + "=" + textNewValue +
                "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getIntegralNewValue() {
        return integralNewValue;
    }

    public void setIntegralNewValue(Integer integralNewValue) {
        this.integralNewValue = integralNewValue;
    }

    public Float getFloatingPointNewValue() {
        return floatingPointNewValue;
    }

    public void setFloatingPointNewValue(Float floatingPointNewValue) {
        this.floatingPointNewValue = floatingPointNewValue;
    }

    public String getTextNewValue() {
        return textNewValue;
    }

    public void setTextNewValue(String textNewValue) {
        this.textNewValue = textNewValue;
    }

    /*
     * Factories
     */
    public static ParametersHistoryEntity fromCursor(Cursor cursor) {
        if(cursor == null) {
            throw new IllegalArgumentException("Cursor must be not null.");
        }

        return new ParametersHistoryEntity(
                cursor.getLong(cursor.getColumnIndex(Contract.ParametersHistory._ID)),
                cursor.getInt(cursor.getColumnIndex(Contract.ParametersHistory.TYPE)),
                cursor.getString(cursor.getColumnIndex(Contract.ParametersHistory.DATE)),
                cursor.getInt(cursor.getColumnIndex(Contract.ParametersHistory.INTEGRAL_NEW_VALUE)),
                cursor.getFloat(cursor.getColumnIndex(Contract.ParametersHistory.FLOATING_POINT_NEW_VALUE)),
                cursor.getString(cursor.getColumnIndex(Contract.ParametersHistory.TEXT_NEW_VALUE)));
    }

    public static ParametersHistoryEntity fromJoinInContentValues(ContentValues principal, ContentValues complement) {
        if (principal == null || complement == null) {
            throw new IllegalArgumentException("Principal and complement must be not null.");
        }

        ParametersHistoryEntity result = new ParametersHistoryEntity(null, null, null, null, null, null);
        // id:
        if (principal.getAsLong(Contract.ParametersHistory._ID) != null) {
            result.setId(principal.getAsLong(Contract.ParametersHistory._ID));
        } else {
            result.setId(complement.getAsLong(Contract.ParametersHistory._ID));
        }
        // type:
        if (principal.getAsInteger(Contract.ParametersHistory.TYPE) != null) {
            result.setType(principal.getAsInteger(Contract.ParametersHistory.TYPE));
        } else {
            result.setType(complement.getAsInteger(Contract.ParametersHistory.TYPE));
        }
        // date:
        if (principal.getAsString(Contract.ParametersHistory.DATE) != null) {
            result.setDate(principal.getAsString(Contract.ParametersHistory.DATE));
        } else {
            result.setDate(complement.getAsString(Contract.ParametersHistory.DATE));
        }
        // integral new value:
        if (principal.getAsInteger(Contract.ParametersHistory.INTEGRAL_NEW_VALUE) != null) {
            result.setIntegralNewValue(principal.getAsInteger(Contract.ParametersHistory.INTEGRAL_NEW_VALUE));
        } else {
            result.setIntegralNewValue(complement.getAsInteger(Contract.ParametersHistory.INTEGRAL_NEW_VALUE));
        }
        // floating point new value:
        if (principal.getAsFloat(Contract.ParametersHistory.FLOATING_POINT_NEW_VALUE) != null) {
            result.setFloatingPointNewValue(principal.getAsFloat(Contract.ParametersHistory.FLOATING_POINT_NEW_VALUE));
        } else {
            result.setFloatingPointNewValue(complement.getAsFloat(Contract.ParametersHistory.FLOATING_POINT_NEW_VALUE));
        }
        // text new value:
        if (principal.getAsString(Contract.ParametersHistory.TEXT_NEW_VALUE) != null) {
            result.setTextNewValue(principal.getAsString(Contract.ParametersHistory.TEXT_NEW_VALUE));
        } else {
            result.setTextNewValue(complement.getAsString(Contract.ParametersHistory.TEXT_NEW_VALUE));
        }

        return result;
    }

    public static ParametersHistoryEntity fromContentValues(ContentValues values) {
        if (values == null) {
            throw new IllegalArgumentException("Values must be not null.");
        }

        return new ParametersHistoryEntity(
                values.getAsLong(Contract.ParametersHistory._ID),
                values.getAsInteger(Contract.ParametersHistory.TYPE),
                values.getAsString(Contract.ParametersHistory.DATE),
                values.getAsInteger(Contract.ParametersHistory.INTEGRAL_NEW_VALUE),
                values.getAsFloat(Contract.ParametersHistory.FLOATING_POINT_NEW_VALUE),
                values.getAsString(Contract.ParametersHistory.TEXT_NEW_VALUE));
    }
}
