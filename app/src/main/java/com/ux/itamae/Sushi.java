package com.ux.itamae;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

public class Sushi implements Parcelable {

    private int extra1;
    private int extra2;
    private int extra3;

    public Sushi(int extra1, int extra2, int extra3) {
        this.extra1 = extra1;
        this.extra2 = extra2;
        this.extra3 = extra3;
    }

    protected Sushi(Parcel in) {
        extra1 = in.readInt();
        extra2 = in.readInt();
        extra3 = in.readInt();
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

    public static final Creator<Sushi> CREATOR = new Creator<Sushi>() {
        @Override
        public Sushi createFromParcel(Parcel in) {
            return new Sushi(in);
        }

        @Override
        public Sushi[] newArray(int size) {
            return new Sushi[size];
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
    }
}
