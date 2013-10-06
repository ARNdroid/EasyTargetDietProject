package br.com.arndroid.etdiet.provider;

import android.content.ContentValues;
import android.database.Cursor;

public class WeekdayParametersEntity extends AbstractEntity {

    private Long id;
    private Integer breakfastStartTime;
    private Integer breakfastEndTime;
    private Float breakfastGoal;
    private Integer brunchStartTime;
    private Integer brunchEndTime;
    private Float brunchGoal;
    private Integer lunchStartTime;
    private Integer lunchEndTime;
    private Float lunchGoal;
    private Integer snackStartTime;
    private Integer snackEndTime;
    private Float snackGoal;
    private Integer dinnerStartTime;
    private Integer dinnerEndTime;
    private Float dinnerGoal;
    private Integer supperStartTime;
    private Integer supperEndTime;
    private Float supperGoal;
    private Float exerciseGoal;
    private Integer liquidGoal;
    private Integer oilGoal;
    private Integer supplementGoal;

	/*
	 * Implementation
	 */

    public WeekdayParametersEntity(Long id, Integer breakfastStartTime, Integer breakfastEndTime,
                                   Float breakfastGoal, Integer brunchStartTime, Integer brunchEndTime,
                                   Float brunchGoal, Integer lunchStartTime, Integer lunchEndTime,
                                   Float lunchGoal, Integer snackStartTime, Integer snackEndTime,
                                   Float snackGoal, Integer dinnerStartTime, Integer dinnerEndTime,
                                   Float dinnerGoal, Integer supperStartTime, Integer supperEndTime,
                                   Float supperGoal, Float exerciseGoal, Integer liquidGoal, Integer oilGoal,
                                   Integer supplementGoal) {
        this.id = id;
        this.breakfastStartTime = breakfastStartTime;
        this.breakfastEndTime = breakfastEndTime;
        this.breakfastGoal = breakfastGoal;
        this.brunchStartTime = brunchStartTime;
        this.brunchEndTime = brunchEndTime;
        this.brunchGoal = brunchGoal;
        this.lunchStartTime = lunchStartTime;
        this.lunchEndTime = lunchEndTime;
        this.lunchGoal = lunchGoal;
        this.snackStartTime = snackStartTime;
        this.snackEndTime = snackEndTime;
        this.snackGoal = snackGoal;
        this.dinnerStartTime = dinnerStartTime;
        this.dinnerEndTime = dinnerEndTime;
        this.dinnerGoal = dinnerGoal;
        this.supperStartTime = supperStartTime;
        this.supperEndTime = supperEndTime;
        this.supperGoal = supperGoal;
        this.exerciseGoal = exerciseGoal;
        this.liquidGoal = liquidGoal;
        this.oilGoal = oilGoal;
        this.supplementGoal = supplementGoal;
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(Contract.WeekdayParameters._ID, id);
        cv.put(Contract.WeekdayParameters.BREAKFAST_START_TIME, breakfastStartTime);
        cv.put(Contract.WeekdayParameters.BREAKFAST_END_TIME, breakfastEndTime);
        cv.put(Contract.WeekdayParameters.BREAKFAST_GOAL, breakfastGoal);
        cv.put(Contract.WeekdayParameters.BRUNCH_START_TIME, brunchStartTime);
        cv.put(Contract.WeekdayParameters.BRUNCH_END_TIME, brunchEndTime);
        cv.put(Contract.WeekdayParameters.BRUNCH_GOAL, brunchGoal);
        cv.put(Contract.WeekdayParameters.LUNCH_START_TIME, lunchStartTime);
        cv.put(Contract.WeekdayParameters.LUNCH_END_TIME, lunchEndTime);
        cv.put(Contract.WeekdayParameters.LUNCH_GOAL, lunchGoal);
        cv.put(Contract.WeekdayParameters.SNACK_START_TIME, snackStartTime);
        cv.put(Contract.WeekdayParameters.SNACK_END_TIME, snackEndTime);
        cv.put(Contract.WeekdayParameters.SNACK_GOAL, snackGoal);
        cv.put(Contract.WeekdayParameters.DINNER_START_TIME, dinnerStartTime);
        cv.put(Contract.WeekdayParameters.DINNER_END_TIME, dinnerEndTime);
        cv.put(Contract.WeekdayParameters.DINNER_GOAL, dinnerGoal);
        cv.put(Contract.WeekdayParameters.SUPPER_START_TIME, supperStartTime);
        cv.put(Contract.WeekdayParameters.SUPPER_END_TIME, supperEndTime);
        cv.put(Contract.WeekdayParameters.SUPPER_GOAL, supperGoal);
        cv.put(Contract.WeekdayParameters.EXERCISE_GOAL, exerciseGoal);
        cv.put(Contract.WeekdayParameters.LIQUID_GOAL, liquidGoal);
        cv.put(Contract.WeekdayParameters.OIL_GOAL, oilGoal);
        cv.put(Contract.WeekdayParameters.SUPPLEMENT_GOAL, supplementGoal);
        return cv;
    }

    // TODO: start from here
    @Override
    public ContentValues toContentValuesIgnoreNulls() {
        ContentValues cv = new ContentValues();
        if (id != null) {
            cv.put(Contract.WeekdayParameters._ID, id);
        }
        if (breakfastStartTime != null) {
            cv.put(Contract.WeekdayParameters.BREAKFAST_START_TIME, breakfastStartTime);
        }
        if (breakfastEndTime != null) {
            cv.put(Contract.WeekdayParameters.BREAKFAST_END_TIME, breakfastEndTime);
        }
        if (breakfastGoal != null) {
            cv.put(Contract.WeekdayParameters.BREAKFAST_GOAL, breakfastGoal);
        }
        if (brunchStartTime != null) {
            cv.put(Contract.WeekdayParameters.BRUNCH_START_TIME, brunchStartTime);
        }
        if (brunchEndTime != null) {
            cv.put(Contract.WeekdayParameters.BRUNCH_END_TIME, brunchEndTime);
        }
        if (brunchGoal != null) {
            cv.put(Contract.WeekdayParameters.BRUNCH_GOAL, brunchGoal);
        }
        if (lunchStartTime != null) {
            cv.put(Contract.WeekdayParameters.LUNCH_START_TIME, lunchStartTime);
        }
        if (lunchEndTime != null) {
            cv.put(Contract.WeekdayParameters.LUNCH_END_TIME, lunchEndTime);
        }
        if (lunchGoal != null) {
            cv.put(Contract.WeekdayParameters.LUNCH_GOAL, lunchGoal);
        }
        if (snackStartTime != null) {
            cv.put(Contract.WeekdayParameters.SNACK_START_TIME, snackStartTime);
        }
        if (snackEndTime != null) {
            cv.put(Contract.WeekdayParameters.SNACK_END_TIME, snackEndTime);
        }
        if (snackGoal != null) {
            cv.put(Contract.WeekdayParameters.SNACK_GOAL, snackGoal);
        }
        if (dinnerStartTime != null) {
            cv.put(Contract.WeekdayParameters.DINNER_START_TIME, dinnerStartTime);
        }
        if (dinnerEndTime != null) {
            cv.put(Contract.WeekdayParameters.DINNER_END_TIME, dinnerEndTime);
        }
        if (dinnerGoal != null) {
            cv.put(Contract.WeekdayParameters.DINNER_GOAL, dinnerGoal);
        }
        if (supperStartTime != null) {
            cv.put(Contract.WeekdayParameters.SUPPER_START_TIME, supperStartTime);
        }
        if (supperEndTime != null) {
            cv.put(Contract.WeekdayParameters.SUPPER_END_TIME, supperEndTime);
        }
        if (supperGoal != null) {
            cv.put(Contract.WeekdayParameters.SUPPER_GOAL, supperGoal);
        }
        if (exerciseGoal != null) {
            cv.put(Contract.WeekdayParameters.EXERCISE_GOAL, exerciseGoal);
        }
        if (liquidGoal != null) {
            cv.put(Contract.WeekdayParameters.LIQUID_GOAL, liquidGoal);
        }
        if (oilGoal != null) {
            cv.put(Contract.WeekdayParameters.OIL_GOAL, oilGoal);
        }
        if (supplementGoal != null) {
            cv.put(Contract.WeekdayParameters.SUPPLEMENT_GOAL, supplementGoal);
        }
        return cv;
    }

    @Override
    public void validateOrThrow() {

        if (breakfastStartTime == null) {
            throwNullValueException(Contract.WeekdayParameters.BREAKFAST_START_TIME);
        }
        if (breakfastEndTime == null) {
            throwNullValueException(Contract.WeekdayParameters.BREAKFAST_END_TIME);
        }
        if (breakfastGoal == null) {
            throwNullValueException(Contract.WeekdayParameters.BREAKFAST_GOAL);
        }

        if (brunchStartTime == null) {
            throwNullValueException(Contract.WeekdayParameters.BRUNCH_START_TIME);
        }
        if (brunchEndTime == null) {
            throwNullValueException(Contract.WeekdayParameters.BRUNCH_END_TIME);
        }
        if (brunchGoal == null) {
            throwNullValueException(Contract.WeekdayParameters.BRUNCH_GOAL);
        }

        if (lunchStartTime == null) {
            throwNullValueException(Contract.WeekdayParameters.LUNCH_START_TIME);
        }
        if (lunchEndTime == null) {
            throwNullValueException(Contract.WeekdayParameters.LUNCH_END_TIME);
        }
        if (lunchGoal == null) {
            throwNullValueException(Contract.WeekdayParameters.LUNCH_GOAL);
        }

        if (snackStartTime == null) {
            throwNullValueException(Contract.WeekdayParameters.SNACK_START_TIME);
        }
        if (snackEndTime == null) {
            throwNullValueException(Contract.WeekdayParameters.SNACK_END_TIME);
        }
        if (snackGoal == null) {
            throwNullValueException(Contract.WeekdayParameters.SNACK_GOAL);
        }

        if (dinnerStartTime == null) {
            throwNullValueException(Contract.WeekdayParameters.DINNER_START_TIME);
        }
        if (dinnerEndTime == null) {
            throwNullValueException(Contract.WeekdayParameters.DINNER_END_TIME);
        }
        if (dinnerGoal == null) {
            throwNullValueException(Contract.WeekdayParameters.DINNER_GOAL);
        }

        if (supperStartTime == null) {
            throwNullValueException(Contract.WeekdayParameters.SUPPER_START_TIME);
        }
        if (supperEndTime == null) {
            throwNullValueException(Contract.WeekdayParameters.SUPPER_END_TIME);
        }
        if (supperGoal == null) {
            throwNullValueException(Contract.WeekdayParameters.SUPPER_GOAL);
        }

        if (exerciseGoal == null) {
            throwNullValueException(Contract.WeekdayParameters.EXERCISE_GOAL);
        }

        if (liquidGoal == null) {
            throwNullValueException(Contract.WeekdayParameters.LIQUID_GOAL);
        }

        if (oilGoal == null) {
            throwNullValueException(Contract.WeekdayParameters.OIL_GOAL);
        }

        if (supplementGoal == null) {
            throwNullValueException(Contract.WeekdayParameters.SUPPLEMENT_GOAL);
        }
    }

    private void throwNullValueException(String columnName) {
        throw new Contract.TargetException(Contract.TargetException.NULL_VALUE,
                new Contract.TargetException.FieldDescriptor[] {new Contract.TargetException.FieldDescriptor(Contract.WeekdayParameters.TABLE_NAME,
                        columnName, null)}, null);
    }

    @Override
    public String getTableName() {
        return Contract.WeekdayParameters.TABLE_NAME;
    }

    @Override
    public String getIdColumnName() {
        return Contract.WeekdayParameters._ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;

        } else if (obj instanceof WeekdayParametersEntity) {
            WeekdayParametersEntity temp = (WeekdayParametersEntity) obj;
            // id:
            if (this.id != null) {
                if (!this.id.equals(temp.id))
                    return false;
            } else {
                if (temp.id != null)
                    return false;
            }

            // breakfastStartTime:
            if (this.breakfastStartTime != null) {
                if(!this.breakfastStartTime.equals(temp.breakfastStartTime))
                    return false;
            } else {
                if (temp.breakfastStartTime != null)
                    return false;
            }

            // breakfastEndTime:
            if (this.breakfastEndTime != null) {
                if(!this.breakfastEndTime.equals(temp.breakfastEndTime))
                    return false;
            } else {
                if (temp.breakfastEndTime != null)
                    return false;
            }

            // breakfastGoal:
            if (this.breakfastGoal != null) {
                if(!this.breakfastGoal.equals(temp.breakfastGoal))
                    return false;
            } else {
                if (temp.breakfastGoal != null)
                    return false;
            }

            // brunchStartTime:
            if (this.brunchStartTime != null) {
                if(!this.brunchStartTime.equals(temp.brunchStartTime))
                    return false;
            } else {
                if (temp.brunchStartTime != null)
                    return false;
            }

            // brunchEndTime:
            if (this.brunchEndTime != null) {
                if(!this.brunchEndTime.equals(temp.brunchEndTime))
                    return false;
            } else {
                if (temp.brunchEndTime != null)
                    return false;
            }

            // brunchGoal:
            if (this.brunchGoal != null) {
                if(!this.brunchGoal.equals(temp.brunchGoal))
                    return false;
            } else {
                if (temp.brunchGoal != null)
                    return false;
            }

            // lunchStartTime:
            if (this.lunchStartTime != null) {
                if(!this.lunchStartTime.equals(temp.lunchStartTime))
                    return false;
            } else {
                if (temp.lunchStartTime != null)
                    return false;
            }

            // lunchEndTime:
            if (this.lunchEndTime != null) {
                if(!this.lunchEndTime.equals(temp.lunchEndTime))
                    return false;
            } else {
                if (temp.lunchEndTime != null)
                    return false;
            }

            // lunchGoal:
            if (this.lunchGoal != null) {
                if(!this.lunchGoal.equals(temp.lunchGoal))
                    return false;
            } else {
                if (temp.lunchGoal != null)
                    return false;
            }

            // snackStartTime:
            if (this.snackStartTime != null) {
                if(!this.snackStartTime.equals(temp.snackStartTime))
                    return false;
            } else {
                if (temp.snackStartTime != null)
                    return false;
            }

            // snackEndTime:
            if (this.snackEndTime != null) {
                if(!this.snackEndTime.equals(temp.snackEndTime))
                    return false;
            } else {
                if (temp.snackEndTime != null)
                    return false;
            }

            // snackGoal:
            if (this.snackGoal != null) {
                if(!this.snackGoal.equals(temp.snackGoal))
                    return false;
            } else {
                if (temp.snackGoal != null)
                    return false;
            }

            // dinnerStartTime:
            if (this.dinnerStartTime != null) {
                if(!this.dinnerStartTime.equals(temp.dinnerStartTime))
                    return false;
            } else {
                if (temp.dinnerStartTime != null)
                    return false;
            }

            // dinnerEndTime:
            if (this.dinnerEndTime != null) {
                if(!this.dinnerEndTime.equals(temp.dinnerEndTime))
                    return false;
            } else {
                if (temp.dinnerEndTime != null)
                    return false;
            }

            // dinnerGoal:
            if (this.dinnerGoal != null) {
                if(!this.dinnerGoal.equals(temp.dinnerGoal))
                    return false;
            } else {
                if (temp.dinnerGoal != null)
                    return false;
            }

            // supperStartTime:
            if (this.supperStartTime != null) {
                if(!this.supperStartTime.equals(temp.supperStartTime))
                    return false;
            } else {
                if (temp.supperStartTime != null)
                    return false;
            }

            // supperEndTime:
            if (this.supperEndTime != null) {
                if(!this.supperEndTime.equals(temp.supperEndTime))
                    return false;
            } else {
                if (temp.supperEndTime != null)
                    return false;
            }

            // supperGoal:
            if (this.supperGoal != null) {
                if(!this.supperGoal.equals(temp.supperGoal))
                    return false;
            } else {
                if (temp.supperGoal != null)
                    return false;
            }

            // exerciseGoal:
            if (this.exerciseGoal != null) {
                if(!this.exerciseGoal.equals(temp.exerciseGoal))
                    return false;
            } else {
                if (temp.exerciseGoal != null)
                    return false;
            }

            // liquidGoal:
            if (this.liquidGoal != null) {
                if(!this.liquidGoal.equals(temp.liquidGoal))
                    return false;
            } else {
                if (temp.liquidGoal != null)
                    return false;
            }

            // oilGoal:
            if (this.oilGoal != null) {
                if(!this.oilGoal.equals(temp.oilGoal))
                    return false;
            } else {
                if (temp.oilGoal != null)
                    return false;
            }

            // supplementGoal:
            if (this.supplementGoal != null) {
                if(!this.supplementGoal.equals(temp.supplementGoal))
                    return false;
            } else {
                if (temp.supplementGoal != null)
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
        result += breakfastStartTime == null? 0: breakfastStartTime.hashCode();
        result += breakfastEndTime == null? 0: breakfastEndTime.hashCode();
        result += breakfastGoal == null? 0: breakfastGoal.hashCode();
        result += brunchStartTime == null? 0: brunchStartTime.hashCode();
        result += brunchEndTime == null? 0: brunchEndTime.hashCode();
        result += brunchGoal == null? 0: brunchGoal.hashCode();
        result += lunchStartTime == null? 0: lunchStartTime.hashCode();
        result += lunchEndTime == null? 0: lunchEndTime.hashCode();
        result += lunchGoal == null? 0: lunchGoal.hashCode();
        result += snackStartTime == null? 0: snackStartTime.hashCode();
        result += snackEndTime == null? 0: snackEndTime.hashCode();
        result += snackGoal == null? 0: snackGoal.hashCode();
        result += dinnerStartTime == null? 0: dinnerStartTime.hashCode();
        result += dinnerEndTime == null? 0: dinnerEndTime.hashCode();
        result += dinnerGoal == null? 0: dinnerGoal.hashCode();
        result += supperStartTime == null? 0: supperStartTime.hashCode();
        result += supperEndTime == null? 0: supperEndTime.hashCode();
        result += supperGoal == null? 0: supperGoal.hashCode();
        result += exerciseGoal == null? 0: exerciseGoal.hashCode();
        result += liquidGoal == null? 0: liquidGoal.hashCode();
        result += oilGoal == null? 0: oilGoal.hashCode();
        result += supplementGoal == null? 0: supplementGoal.hashCode();
        return result;
    }

    @Override
    public String toString() {

        return "["
                + Contract.WeekdayParameters._ID + "=" + id + ", "
                + Contract.WeekdayParameters.BREAKFAST_START_TIME + "=" + breakfastStartTime + ", "
                + Contract.WeekdayParameters.BREAKFAST_END_TIME + "=" + breakfastEndTime + ", "
                + Contract.WeekdayParameters.BREAKFAST_GOAL + "=" + breakfastGoal + ", "
                + Contract.WeekdayParameters.BRUNCH_START_TIME + "=" + brunchStartTime + ", "
                + Contract.WeekdayParameters.BRUNCH_END_TIME + "=" + brunchEndTime + ", "
                + Contract.WeekdayParameters.BRUNCH_GOAL + "=" + brunchGoal + ", "
                + Contract.WeekdayParameters.LUNCH_START_TIME + "=" + lunchStartTime + ", "
                + Contract.WeekdayParameters.LUNCH_END_TIME + "=" + lunchEndTime + ", "
                + Contract.WeekdayParameters.LUNCH_GOAL + "=" + lunchGoal + ", "
                + Contract.WeekdayParameters.SNACK_START_TIME + "=" + snackStartTime + ", "
                + Contract.WeekdayParameters.SNACK_END_TIME + "=" + snackEndTime + ", "
                + Contract.WeekdayParameters.SNACK_GOAL + "=" + snackGoal + ", "
                + Contract.WeekdayParameters.DINNER_START_TIME + "=" + dinnerStartTime + ", "
                + Contract.WeekdayParameters.DINNER_END_TIME + "=" + dinnerEndTime + ", "
                + Contract.WeekdayParameters.DINNER_GOAL + "=" + dinnerGoal + ", "
                + Contract.WeekdayParameters.SUPPER_START_TIME + "=" + supperStartTime + ", "
                + Contract.WeekdayParameters.SUPPER_END_TIME + "=" + supperEndTime + ", "
                + Contract.WeekdayParameters.SUPPER_GOAL + "=" + supperGoal + ", "
                + Contract.WeekdayParameters.EXERCISE_GOAL + "=" + exerciseGoal + ", "
                + Contract.WeekdayParameters.LIQUID_GOAL + "=" + liquidGoal + ", "
                + Contract.WeekdayParameters.OIL_GOAL + "=" + oilGoal + ", "
                + Contract.WeekdayParameters.SUPPLEMENT_GOAL + "=" + supplementGoal + ", "
                + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBreakfastStartTime() {
        return breakfastStartTime;
    }

    public void setBreakfastStartTime(Integer breakfastStartTime) {
        this.breakfastStartTime = breakfastStartTime;
    }

    public Integer getBreakfastEndTime() {
        return breakfastEndTime;
    }

    public void setBreakfastEndTime(Integer breakfastEndTime) {
        this.breakfastEndTime = breakfastEndTime;
    }

    public Float getBreakfastGoal() {
        return breakfastGoal;
    }

    public void setBreakfastGoal(Float breakfastGoal) {
        this.breakfastGoal = breakfastGoal;
    }

    public Integer getBrunchStartTime() {
        return brunchStartTime;
    }

    public void setBrunchStartTime(Integer brunchStartTime) {
        this.brunchStartTime = brunchStartTime;
    }

    public Integer getBrunchEndTime() {
        return brunchEndTime;
    }

    public void setBrunchEndTime(Integer brunchEndTime) {
        this.brunchEndTime = brunchEndTime;
    }

    public Float getBrunchGoal() {
        return brunchGoal;
    }

    public void setBrunchGoal(Float brunchGoal) {
        this.brunchGoal = brunchGoal;
    }

    public Integer getLunchStartTime() {
        return lunchStartTime;
    }

    public void setLunchStartTime(Integer lunchStartTime) {
        this.lunchStartTime = lunchStartTime;
    }

    public Integer getLunchEndTime() {
        return lunchEndTime;
    }

    public void setLunchEndTime(Integer lunchEndTime) {
        this.lunchEndTime = lunchEndTime;
    }

    public Float getLunchGoal() {
        return lunchGoal;
    }

    public void setLunchGoal(Float lunchGoal) {
        this.lunchGoal = lunchGoal;
    }

    public Integer getSnackStartTime() {
        return snackStartTime;
    }

    public void setSnackStartTime(Integer snackStartTime) {
        this.snackStartTime = snackStartTime;
    }

    public Integer getSnackEndTime() {
        return snackEndTime;
    }

    public void setSnackEndTime(Integer snackEndTime) {
        this.snackEndTime = snackEndTime;
    }

    public Float getSnackGoal() {
        return snackGoal;
    }

    public void setSnackGoal(Float snackGoal) {
        this.snackGoal = snackGoal;
    }

    public Integer getDinnerStartTime() {
        return dinnerStartTime;
    }

    public void setDinnerStartTime(Integer dinnerStartTime) {
        this.dinnerStartTime = dinnerStartTime;
    }

    public Integer getDinnerEndTime() {
        return dinnerEndTime;
    }

    public void setDinnerEndTime(Integer dinnerEndTime) {
        this.dinnerEndTime = dinnerEndTime;
    }

    public Float getDinnerGoal() {
        return dinnerGoal;
    }

    public void setDinnerGoal(Float dinnerGoal) {
        this.dinnerGoal = dinnerGoal;
    }

    public Integer getSupperStartTime() {
        return supperStartTime;
    }

    public void setSupperStartTime(Integer supperStartTime) {
        this.supperStartTime = supperStartTime;
    }

    public Integer getSupperEndTime() {
        return supperEndTime;
    }

    public void setSupperEndTime(Integer supperEndTime) {
        this.supperEndTime = supperEndTime;
    }

    public Float getSupperGoal() {
        return supperGoal;
    }

    public void setSupperGoal(Float supperGoal) {
        this.supperGoal = supperGoal;
    }

    public Float getExerciseGoal() {
        return exerciseGoal;
    }

    public void setExerciseGoal(Float exerciseGoal) {
        this.exerciseGoal = exerciseGoal;
    }

    public Integer getLiquidGoal() {
        return liquidGoal;
    }

    public void setLiquidGoal(Integer liquidGoal) {
        this.liquidGoal = liquidGoal;
    }

    public Integer getOilGoal() {
        return oilGoal;
    }

    public void setOilGoal(Integer oilGoal) {
        this.oilGoal = oilGoal;
    }

    public Integer getSupplementGoal() {
        return supplementGoal;
    }

    public void setSupplementGoal(Integer supplementGoal) {
        this.supplementGoal = supplementGoal;
    }

    /*
     * Factories
     */
    public static WeekdayParametersEntity fromCursor(Cursor cursor) {
        if(cursor == null) {
            throw new IllegalArgumentException("Cursor must be not null.");
        }

        return new WeekdayParametersEntity(
                cursor.getLong(cursor.getColumnIndex(Contract.WeekdayParameters._ID)),
                cursor.getInt(cursor.getColumnIndex(Contract.WeekdayParameters.BREAKFAST_START_TIME)),
                cursor.getInt(cursor.getColumnIndex(Contract.WeekdayParameters.BREAKFAST_END_TIME)),
                cursor.getFloat(cursor.getColumnIndex(Contract.WeekdayParameters.BREAKFAST_GOAL)),
                cursor.getInt(cursor.getColumnIndex(Contract.WeekdayParameters.BRUNCH_START_TIME)),
                cursor.getInt(cursor.getColumnIndex(Contract.WeekdayParameters.BRUNCH_END_TIME)),
                cursor.getFloat(cursor.getColumnIndex(Contract.WeekdayParameters.BRUNCH_GOAL)),
                cursor.getInt(cursor.getColumnIndex(Contract.WeekdayParameters.LUNCH_START_TIME)),
                cursor.getInt(cursor.getColumnIndex(Contract.WeekdayParameters.LUNCH_END_TIME)),
                cursor.getFloat(cursor.getColumnIndex(Contract.WeekdayParameters.LUNCH_GOAL)),
                cursor.getInt(cursor.getColumnIndex(Contract.WeekdayParameters.SNACK_START_TIME)),
                cursor.getInt(cursor.getColumnIndex(Contract.WeekdayParameters.SNACK_END_TIME)),
                cursor.getFloat(cursor.getColumnIndex(Contract.WeekdayParameters.SNACK_GOAL)),
                cursor.getInt(cursor.getColumnIndex(Contract.WeekdayParameters.DINNER_START_TIME)),
                cursor.getInt(cursor.getColumnIndex(Contract.WeekdayParameters.DINNER_END_TIME)),
                cursor.getFloat(cursor.getColumnIndex(Contract.WeekdayParameters.DINNER_GOAL)),
                cursor.getInt(cursor.getColumnIndex(Contract.WeekdayParameters.SUPPER_START_TIME)),
                cursor.getInt(cursor.getColumnIndex(Contract.WeekdayParameters.SUPPER_END_TIME)),
                cursor.getFloat(cursor.getColumnIndex(Contract.WeekdayParameters.SUPPER_GOAL)),
                cursor.getFloat(cursor.getColumnIndex(Contract.WeekdayParameters.EXERCISE_GOAL)),
                cursor.getInt(cursor.getColumnIndex(Contract.WeekdayParameters.LIQUID_GOAL)),
                cursor.getInt(cursor.getColumnIndex(Contract.WeekdayParameters.OIL_GOAL)),
                cursor.getInt(cursor.getColumnIndex(Contract.WeekdayParameters.SUPPLEMENT_GOAL)));
    }

    public static WeekdayParametersEntity fromJoinInContentValues(ContentValues principal, ContentValues complement) {
        if (principal == null || complement == null) {
            throw new IllegalArgumentException("Principal and complement must be not null.");
        }

        WeekdayParametersEntity result = new WeekdayParametersEntity(null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null);
        
        // Id:
        if (principal.getAsLong(Contract.WeekdayParameters._ID) != null) {
            result.setId(principal.getAsLong(Contract.WeekdayParameters._ID));
        } else {
            result.setId(complement.getAsLong(Contract.WeekdayParameters._ID));
        }

        // Breakfast:
        if (principal.getAsInteger(Contract.WeekdayParameters.BREAKFAST_START_TIME) != null) {
            result.setBreakfastStartTime(principal.getAsInteger(Contract.WeekdayParameters.BREAKFAST_START_TIME));
        } else {
            result.setBreakfastStartTime(complement.getAsInteger(Contract.WeekdayParameters.BREAKFAST_START_TIME));
        }
        if (principal.getAsInteger(Contract.WeekdayParameters.BREAKFAST_END_TIME) != null) {
            result.setBreakfastEndTime(principal.getAsInteger(Contract.WeekdayParameters.BREAKFAST_END_TIME));
        } else {
            result.setBreakfastEndTime(complement.getAsInteger(Contract.WeekdayParameters.BREAKFAST_END_TIME));
        }
        if (principal.getAsFloat(Contract.WeekdayParameters.BREAKFAST_GOAL) != null) {
            result.setBreakfastGoal(principal.getAsFloat(Contract.WeekdayParameters.BREAKFAST_GOAL));
        } else {
            result.setBreakfastGoal(complement.getAsFloat(Contract.WeekdayParameters.BREAKFAST_GOAL));
        }

        // Brunch:
        if (principal.getAsInteger(Contract.WeekdayParameters.BRUNCH_START_TIME) != null) {
            result.setBrunchStartTime(principal.getAsInteger(Contract.WeekdayParameters.BRUNCH_START_TIME));
        } else {
            result.setBrunchStartTime(complement.getAsInteger(Contract.WeekdayParameters.BRUNCH_START_TIME));
        }
        if (principal.getAsInteger(Contract.WeekdayParameters.BRUNCH_END_TIME) != null) {
            result.setBrunchEndTime(principal.getAsInteger(Contract.WeekdayParameters.BRUNCH_END_TIME));
        } else {
            result.setBrunchEndTime(complement.getAsInteger(Contract.WeekdayParameters.BRUNCH_END_TIME));
        }
        if (principal.getAsFloat(Contract.WeekdayParameters.BRUNCH_GOAL) != null) {
            result.setBrunchGoal(principal.getAsFloat(Contract.WeekdayParameters.BRUNCH_GOAL));
        } else {
            result.setBrunchGoal(complement.getAsFloat(Contract.WeekdayParameters.BRUNCH_GOAL));
        }

        // Lunch:
        if (principal.getAsInteger(Contract.WeekdayParameters.LUNCH_START_TIME) != null) {
            result.setLunchStartTime(principal.getAsInteger(Contract.WeekdayParameters.LUNCH_START_TIME));
        } else {
            result.setLunchStartTime(complement.getAsInteger(Contract.WeekdayParameters.LUNCH_START_TIME));
        }
        if (principal.getAsInteger(Contract.WeekdayParameters.LUNCH_END_TIME) != null) {
            result.setLunchEndTime(principal.getAsInteger(Contract.WeekdayParameters.LUNCH_END_TIME));
        } else {
            result.setLunchEndTime(complement.getAsInteger(Contract.WeekdayParameters.LUNCH_END_TIME));
        }
        if (principal.getAsFloat(Contract.WeekdayParameters.LUNCH_GOAL) != null) {
            result.setLunchGoal(principal.getAsFloat(Contract.WeekdayParameters.LUNCH_GOAL));
        } else {
            result.setLunchGoal(complement.getAsFloat(Contract.WeekdayParameters.LUNCH_GOAL));
        }

        // Snack:
        if (principal.getAsInteger(Contract.WeekdayParameters.SNACK_START_TIME) != null) {
            result.setSnackStartTime(principal.getAsInteger(Contract.WeekdayParameters.SNACK_START_TIME));
        } else {
            result.setSnackStartTime(complement.getAsInteger(Contract.WeekdayParameters.SNACK_START_TIME));
        }
        if (principal.getAsInteger(Contract.WeekdayParameters.SNACK_END_TIME) != null) {
            result.setSnackEndTime(principal.getAsInteger(Contract.WeekdayParameters.SNACK_END_TIME));
        } else {
            result.setSnackEndTime(complement.getAsInteger(Contract.WeekdayParameters.SNACK_END_TIME));
        }
        if (principal.getAsFloat(Contract.WeekdayParameters.SNACK_GOAL) != null) {
            result.setSnackGoal(principal.getAsFloat(Contract.WeekdayParameters.SNACK_GOAL));
        } else {
            result.setSnackGoal(complement.getAsFloat(Contract.WeekdayParameters.SNACK_GOAL));
        }

        // Dinner:
        if (principal.getAsInteger(Contract.WeekdayParameters.DINNER_START_TIME) != null) {
            result.setDinnerStartTime(principal.getAsInteger(Contract.WeekdayParameters.DINNER_START_TIME));
        } else {
            result.setDinnerStartTime(complement.getAsInteger(Contract.WeekdayParameters.DINNER_START_TIME));
        }
        if (principal.getAsInteger(Contract.WeekdayParameters.DINNER_END_TIME) != null) {
            result.setDinnerEndTime(principal.getAsInteger(Contract.WeekdayParameters.DINNER_END_TIME));
        } else {
            result.setDinnerEndTime(complement.getAsInteger(Contract.WeekdayParameters.DINNER_END_TIME));
        }
        if (principal.getAsFloat(Contract.WeekdayParameters.DINNER_GOAL) != null) {
            result.setDinnerGoal(principal.getAsFloat(Contract.WeekdayParameters.DINNER_GOAL));
        } else {
            result.setDinnerGoal(complement.getAsFloat(Contract.WeekdayParameters.DINNER_GOAL));
        }

        // Supper:
        if (principal.getAsInteger(Contract.WeekdayParameters.SUPPER_START_TIME) != null) {
            result.setSupperStartTime(principal.getAsInteger(Contract.WeekdayParameters.SUPPER_START_TIME));
        } else {
            result.setSupperStartTime(complement.getAsInteger(Contract.WeekdayParameters.SUPPER_START_TIME));
        }
        if (principal.getAsInteger(Contract.WeekdayParameters.SUPPER_END_TIME) != null) {
            result.setSupperEndTime(principal.getAsInteger(Contract.WeekdayParameters.SUPPER_END_TIME));
        } else {
            result.setSupperEndTime(complement.getAsInteger(Contract.WeekdayParameters.SUPPER_END_TIME));
        }
        if (principal.getAsFloat(Contract.WeekdayParameters.SUPPER_GOAL) != null) {
            result.setSupperGoal(principal.getAsFloat(Contract.WeekdayParameters.SUPPER_GOAL));
        } else {
            result.setSupperGoal(complement.getAsFloat(Contract.WeekdayParameters.SUPPER_GOAL));
        }

        // Exercise:
        if (principal.getAsFloat(Contract.WeekdayParameters.EXERCISE_GOAL) != null) {
            result.setExerciseGoal(principal.getAsFloat(Contract.WeekdayParameters.EXERCISE_GOAL));
        } else {
            result.setExerciseGoal(complement.getAsFloat(Contract.WeekdayParameters.EXERCISE_GOAL));
        }

        // Liquid:
        if (principal.getAsInteger(Contract.WeekdayParameters.LIQUID_GOAL) != null) {
            result.setLiquidGoal(principal.getAsInteger(Contract.WeekdayParameters.LIQUID_GOAL));
        } else {
            result.setLiquidGoal(complement.getAsInteger(Contract.WeekdayParameters.LIQUID_GOAL));
        }

        // Oil:
        if (principal.getAsInteger(Contract.WeekdayParameters.OIL_GOAL) != null) {
            result.setOilGoal(principal.getAsInteger(Contract.WeekdayParameters.OIL_GOAL));
        } else {
            result.setOilGoal(complement.getAsInteger(Contract.WeekdayParameters.OIL_GOAL));
        }

        // Supplement:
        if (principal.getAsInteger(Contract.WeekdayParameters.SUPPLEMENT_GOAL) != null) {
            result.setSupplementGoal(principal.getAsInteger(Contract.WeekdayParameters.SUPPLEMENT_GOAL));
        } else {
            result.setSupplementGoal(complement.getAsInteger(Contract.WeekdayParameters.SUPPLEMENT_GOAL));
        }

        return result;
    }

    public static WeekdayParametersEntity fromContentValues(ContentValues values) {
        if (values == null) {
            throw new IllegalArgumentException("Values must be not null.");
        }

        return new WeekdayParametersEntity(
                values.getAsLong(Contract.WeekdayParameters._ID),
                values.getAsInteger(Contract.WeekdayParameters.BREAKFAST_START_TIME),
                values.getAsInteger(Contract.WeekdayParameters.BREAKFAST_END_TIME),
                values.getAsFloat(Contract.WeekdayParameters.BREAKFAST_GOAL),
                values.getAsInteger(Contract.WeekdayParameters.BRUNCH_START_TIME),
                values.getAsInteger(Contract.WeekdayParameters.BRUNCH_END_TIME),
                values.getAsFloat(Contract.WeekdayParameters.BRUNCH_GOAL),
                values.getAsInteger(Contract.WeekdayParameters.LUNCH_START_TIME),
                values.getAsInteger(Contract.WeekdayParameters.LUNCH_END_TIME),
                values.getAsFloat(Contract.WeekdayParameters.LUNCH_GOAL),
                values.getAsInteger(Contract.WeekdayParameters.SNACK_START_TIME),
                values.getAsInteger(Contract.WeekdayParameters.SNACK_END_TIME),
                values.getAsFloat(Contract.WeekdayParameters.SNACK_GOAL),
                values.getAsInteger(Contract.WeekdayParameters.DINNER_START_TIME),
                values.getAsInteger(Contract.WeekdayParameters.DINNER_END_TIME),
                values.getAsFloat(Contract.WeekdayParameters.DINNER_GOAL),
                values.getAsInteger(Contract.WeekdayParameters.SUPPER_START_TIME),
                values.getAsInteger(Contract.WeekdayParameters.SUPPER_END_TIME),
                values.getAsFloat(Contract.WeekdayParameters.SUPPER_GOAL),
                values.getAsFloat(Contract.WeekdayParameters.EXERCISE_GOAL),
                values.getAsInteger(Contract.WeekdayParameters.LIQUID_GOAL),
                values.getAsInteger(Contract.WeekdayParameters.OIL_GOAL),
                values.getAsInteger(Contract.WeekdayParameters.SUPPLEMENT_GOAL));
    }
}
