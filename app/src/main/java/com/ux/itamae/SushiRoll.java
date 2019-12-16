package com.ux.itamae;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SushiRoll implements Parcelable {

    private int roll_filling1, roll_filling2, roll_filling3;
    private String type;

    public SushiRoll(String type) {
        this.type = type;
    }

    protected SushiRoll(Parcel in) {
        roll_filling1 = in.readInt();
        roll_filling2 = in.readInt();
        roll_filling3 = in.readInt();
        type = in.readString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRoll_filling1() {
        return roll_filling1;
    }

    public int getRoll_filling2() {
        return roll_filling2;
    }

    public int getRoll_filling3() {
        return roll_filling3;
    }

    public void setRoll_filling1(int roll_filling1) {
        this.roll_filling1 = roll_filling1;
    }

    public void setRoll_filling2(int roll_filling2) {
        this.roll_filling2 = roll_filling2;
    }

    public void setRoll_filling3(int roll_filling3) {
        this.roll_filling3 = roll_filling3;
    }


    public HashMap getFillings() {
        HashMap<Integer, Integer> extras = new HashMap();

        extras.put(roll_filling1, 1);
        if (extras.get(roll_filling2) != null)
            extras.put(roll_filling2, 2);
        else
            extras.put(roll_filling2, 1);

        Integer numOfFillings3 = extras.get(roll_filling3);
        if (numOfFillings3 != null)
            extras.put(roll_filling3, numOfFillings3 + 1);
        else
            extras.put(roll_filling3, 1);

        return extras;
    }

    public static final Creator<SushiRoll> CREATOR = new Creator<SushiRoll>() {
        @Override
        public SushiRoll createFromParcel(Parcel in) {
            return new SushiRoll(in);
        }

        @Override
        public SushiRoll[] newArray(int size) {
            return new SushiRoll[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(roll_filling1);
        parcel.writeInt(roll_filling2);
        parcel.writeInt(roll_filling3);
        parcel.writeString(type);
    }

    /**
     * Two sushi rolls are equal if they have the same fillings
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        SushiRoll sushi2 = ((SushiRoll) obj);
        if (!sushi2.getType().equals(type))
            return false;

        int objFilling1 = sushi2.getRoll_filling1();
        int objFilling2 = sushi2.getRoll_filling2();
        int objFilling3 = sushi2.getRoll_filling3();

        List<Integer> obj1Fillings = new ArrayList<Integer>(Arrays.asList(objFilling1, objFilling2, objFilling3));
        List<Integer> obj2Fillings = new ArrayList<Integer>(Arrays.asList(roll_filling1, roll_filling2, roll_filling3));

        Collections.sort(obj1Fillings);
        Collections.sort(obj2Fillings);

        for (int i = 0; i < 3; i++)
            if (!obj1Fillings.get(i).equals(obj2Fillings.get(i)))
                return false;
        return true;
    }
}
