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

    private int extra1, extra2, extra3;
    private String type;

    public SushiRoll(String type) {
        this.type = type;
    }

    public SushiRoll(int extra1, int extra2, int extra3, String type) {
        this.extra1 = extra1;
        this.extra2 = extra2;
        this.extra3 = extra3;
        this.type = type;
    }

    protected SushiRoll(Parcel in) {
        extra1 = in.readInt();
        extra2 = in.readInt();
        extra3 = in.readInt();
        type = in.readString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getExtra1() {
        return extra1;
    }

    public int getExtra2() {
        return extra2;
    }

    public int getExtra3() {
        return extra3;
    }

    public void setExtra1(int extra1) {
        this.extra1 = extra1;
    }

    public void setExtra2(int extra2) {
        this.extra2 = extra2;
    }

    public void setExtra3(int extra3) {
        this.extra3 = extra3;
    }


    public HashMap getExtras() {
        HashMap<Integer, Integer> extras = new HashMap();

        extras.put(extra1, 1);
        if (extras.get(extra2) != null)
            extras.put(extra2, 2);
        else
            extras.put(extra2, 1);

        Integer numOfextras3 = extras.get(extra3);
        if (numOfextras3 != null)
            extras.put(extra3, numOfextras3 + 1);
        else
            extras.put(extra3, 1);

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
        parcel.writeInt(extra1);
        parcel.writeInt(extra2);
        parcel.writeInt(extra3);
        parcel.writeString(type);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        SushiRoll sushi2 = ((SushiRoll) obj);
        if (!sushi2.getType().equals(type))
            return false;

        int objExtra1 = sushi2.getExtra1();
        int objExtra2 = sushi2.getExtra2();
        int objExtra3 = sushi2.getExtra3();

        List<Integer> obj1Extras = new ArrayList<Integer>(Arrays.asList(objExtra1, objExtra2, objExtra3));
        List<Integer> obj2Extras = new ArrayList<Integer>(Arrays.asList(extra1, extra2, extra3));

        Collections.sort(obj1Extras);
        Collections.sort(obj2Extras);

        for (int i = 0; i < 3; i++)
            if (!obj1Extras.get(i).equals(obj2Extras.get(i)))
                return false;
        return true;
    }
}
