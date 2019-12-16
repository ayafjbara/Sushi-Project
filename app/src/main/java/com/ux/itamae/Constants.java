package com.ux.itamae;

import android.util.ArrayMap;
import java.util.Map;

public final class Constants {

    private Map<Integer, String> extraIdToString = new ArrayMap<>();
    private static Constants instance;
    static String MAKI_INTENT_KEY = "Maki";
    static String IOMAKI_INTENT_KEY = "Maki I/O";

    private Constants(){
        setExtraIdToString();
    }

    public static Constants getInstance() {
        if (instance == null){
            instance = new Constants();
        }
        return instance;
    }

    private void setExtraIdToString(){
        extraIdToString.put(R.drawable.ic_ex_avocado, "Avocado");
        extraIdToString.put(R.drawable.ic_ex_carrot, "Carrot");
        extraIdToString.put(R.drawable.ic_ex_cucumber, "Cucumber");
        extraIdToString.put(R.drawable.ic_ex_redtuna, "Red Tuna");
        extraIdToString.put(R.drawable.ic_ex_salmon, "Salmon");
    }

    public String getExtraString(int extraId){
        return extraIdToString.get(extraId);
    }
}
