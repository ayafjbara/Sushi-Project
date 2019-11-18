package com.ux.itamae;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FishFragment extends Fragment {
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.extras_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        Bundle args = getArguments();
//        ((TextView) view.findViewById(android.R.id.text1))
//                .setText(Integer.toString(args.getInt(ARG_OBJECT)));
        TableLayout tableLayout = view.findViewById(R.id.extrasTable);
        TableRow tableRow1 = new TableRow(getActivity());
        ImageView salmonImage =  new ImageView(getActivity());
        salmonImage.setImageResource(R.drawable.ic_ex_salmon);
        tableRow1.addView(salmonImage);
        ImageView redTunaImage =  new ImageView(getActivity());
        salmonImage.setImageResource(R.drawable.ic_ex_redtuna);
        tableRow1.addView(redTunaImage);
        tableLayout.addView(tableRow1);
    }
}
