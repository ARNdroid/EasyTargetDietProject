package br.com.arndroid.etdiet.provider;

import android.content.ContentValues;
import android.database.Cursor;
import static br.com.arndroid.etdiet.provider.Contract.TargetException.FieldDescriptor;

public class FoodsUsageEntity extends AbstractEntity {

    private Long id;
    private String dayId;
    private Integer meal;
    private Integer time;
    private String description;
    private Float value;

	/*
	 * Implementation
	 */

    public FoodsUsageEntity(Long id, String dayId, Integer meal, Integer time, String description,
                            Float value) {
        this.id = id;
        this.dayId = dayId;
        this.meal = meal;
        this.time = time;
        this.description = description;
        this.value = value;
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(Contract.FoodsUsage._ID, id);
        cv.put(Contract.FoodsUsage.DAY_ID, dayId);
        cv.put(Contract.FoodsUsage.MEAL, meal);
        cv.put(Contract.FoodsUsage.TIME, time);
        cv.put(Contract.FoodsUsage.DESCRIPTION, description);
        cv.put(Contract.FoodsUsage.VALUE, value);
        return cv;
    }

    @Override
    public ContentValues toContentValuesIgnoreNulls() {
        ContentValues cv = new ContentValues();
        if (id != null) {
            cv.put(Contract.FoodsUsage._ID, id);
        }
        if (dayId != null) {
            cv.put(Contract.FoodsUsage.DAY_ID, dayId);
        }
        if (meal != null) {
            cv.put(Contract.FoodsUsage.MEAL, meal);
        }
        if (time != null) {
            cv.put(Contract.FoodsUsage.TIME, time);
        }
        if (description != null) {
            cv.put(Contract.FoodsUsage.DESCRIPTION, description);
        }
        if (value != null) {
            cv.put(Contract.FoodsUsage.VALUE, value);
        }
        return cv;
    }

    @Override
    public void validateOrThrow() {

        // Day (id) must be not null:
        if (dayId == null) {
            throwNullValueException(Contract.FoodsUsage.DAY_ID);
        }

        // Meal must be not null:
        if (meal == null) {
            throwNullValueException(Contract.FoodsUsage.MEAL);
        }

        // Time must be not null:
        if (time == null) {
            throwNullValueException(Contract.FoodsUsage.TIME);
        }

        // Description must be not null:
        if (description == null) {
            throwNullValueException(Contract.FoodsUsage.DESCRIPTION);
        }

        // Value must be not null:
        if (value == null) {
            throwNullValueException(Contract.FoodsUsage.VALUE);
        }
    }

    private void throwNullValueException(String columnName) {
        throw new Contract.TargetException(Contract.TargetException.NULL_VALUE,
                new FieldDescriptor[] {new FieldDescriptor(Contract.FoodsUsage.TABLE_NAME,
                        columnName, null)}, null);
    }

    @Override
    public String getTableName() {
        return Contract.FoodsUsage.TABLE_NAME;
    }

    @Override
    public String getIdColumnName() {
        return Contract.FoodsUsage._ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;

        } else if (obj instanceof FoodsUsageEntity) {
            FoodsUsageEntity temp = (FoodsUsageEntity) obj;
            // id:
            if (this.id != null) {
                if (!this.id.equals(temp.id))
                    return false;
            } else {
                if (temp.id != null)
                    return false;
            }

            // dayId:
            if (this.dayId != null) {
                if(!this.dayId.equals(temp.dayId))
                    return false;
            } else {
                if (temp.dayId != null)
                    return false;
            }

            // meal:
            if (this.meal != null) {
                if (!this.meal.equals(temp.meal))
                    return false;
            } else {
                if (temp.meal != null)
                    return false;
            }

            // time:
            if (this.time != null) {
                if (!this.time.equals(temp.time))
                    return false;
            } else {
                if (temp.time != null)
                    return false;
            }

            // description:
            if (this.description != null) {
                if (!this.description.equals(temp.description))
                    return false;
            } else {
                if (temp.description != null)
                    return false;
            }

            // value:
            if (this.value != null) {
                if (!this.value.equals(temp.value))
                    return false;
            } else {
                if (temp.value != null)
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
        result += dayId == null? 0: dayId.hashCode();
        result += meal == null? 0: meal.hashCode();
        result += time == null? 0: time.hashCode();
        result += description == null? 0: description.hashCode();
        result += value == null? 0: value.hashCode();
        return result;
    }

    @Override
    public String toString() {

        return "[" + Contract.FoodsUsage._ID + "=" + id + ", " + Contract.FoodsUsage.DAY_ID + "=" + dayId + ", " + Contract.FoodsUsage.MEAL + "=" + meal + ", " + Contract.FoodsUsage.TIME + "=" + time + ", " + Contract.FoodsUsage.DESCRIPTION + "=" + description + ", " + Contract.FoodsUsage.VALUE + "=" + value + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public Integer getMeal() {
        return meal;
    }

    public void setMeal(Integer meal) {
        this.meal = meal;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    /*
     * Factories
     */
    public static FoodsUsageEntity fromCursor(Cursor cursor) {
        if(cursor == null) {
            throw new IllegalArgumentException("Cursor must be not null.");
        }

        return new FoodsUsageEntity(
                cursor.getLong(cursor.getColumnIndex(Contract.FoodsUsage._ID)),
                cursor.getString(cursor.getColumnIndex(Contract.FoodsUsage.DAY_ID)),
                cursor.getInt(cursor.getColumnIndex(Contract.FoodsUsage.MEAL)),
                cursor.getInt(cursor.getColumnIndex(Contract.FoodsUsage.TIME)),
                cursor.getString(cursor.getColumnIndex(Contract.FoodsUsage.DESCRIPTION)),
                cursor.getFloat(cursor.getColumnIndex(Contract.FoodsUsage.VALUE)));
    }

    public static FoodsUsageEntity fromJoinInContentValues(ContentValues principal, ContentValues complement) {
        if (principal == null || complement == null) {
            throw new IllegalArgumentException("Principal and complement must be not null.");
        }

        FoodsUsageEntity result = new FoodsUsageEntity(null, null, null, null, null, null);
        // id:
        if (principal.getAsLong(Contract.FoodsUsage._ID) != null) {
            result.setId(principal.getAsLong(Contract.FoodsUsage._ID));
        } else {
            result.setId(complement.getAsLong(Contract.FoodsUsage._ID));
        }
        // day_id:
        if (principal.getAsString(Contract.FoodsUsage.DAY_ID) != null) {
            result.setDayId(principal.getAsString(Contract.FoodsUsage.DAY_ID));
        } else {
            result.setDayId(complement.getAsString(Contract.FoodsUsage.DAY_ID));
        }
        // meal:
        if (principal.getAsInteger(Contract.FoodsUsage.MEAL) != null) {
            result.setMeal(principal.getAsInteger(Contract.FoodsUsage.MEAL));
        } else {
            result.setMeal(complement.getAsInteger(Contract.FoodsUsage.MEAL));
        }
        // time:
        if (principal.getAsInteger(Contract.FoodsUsage.TIME) != null) {
            result.setTime(principal.getAsInteger(Contract.FoodsUsage.TIME));
        } else {
            result.setTime(complement.getAsInteger(Contract.FoodsUsage.TIME));
        }
        // description:
        if (principal.getAsString(Contract.FoodsUsage.DESCRIPTION) != null) {
            result.setDescription(principal.getAsString(Contract.FoodsUsage.DESCRIPTION));
        } else {
            result.setDescription(complement.getAsString(Contract.FoodsUsage.DESCRIPTION));
        }
        // value:
        if (principal.getAsFloat(Contract.FoodsUsage.VALUE) != null) {
            result.setValue(principal.getAsFloat(Contract.FoodsUsage.VALUE));
        } else {
            result.setValue(complement.getAsFloat(Contract.FoodsUsage.VALUE));
        }

        return result;
    }

    public static FoodsUsageEntity fromContentValues(ContentValues values) {
        if (values == null) {
            throw new IllegalArgumentException("Values must be not null.");
        }

        return new FoodsUsageEntity(
                values.getAsLong(Contract.FoodsUsage._ID),
                values.getAsString(Contract.FoodsUsage.DAY_ID),
                values.getAsInteger(Contract.FoodsUsage.MEAL),
                values.getAsInteger(Contract.FoodsUsage.TIME),
                values.getAsString(Contract.FoodsUsage.DESCRIPTION),
                values.getAsFloat(Contract.FoodsUsage.VALUE));
    }
}