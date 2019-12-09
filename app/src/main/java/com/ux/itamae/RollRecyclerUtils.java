package com.ux.itamae;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ux.itamae.Constants;
import com.ux.itamae.R;
import com.ux.itamae.SushiRoll;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class RollRecyclerUtils {

    static class RollCallBack extends DiffUtil.ItemCallback<SushiRoll> {

        @Override
        public boolean areItemsTheSame(@NonNull SushiRoll oldItem, @NonNull SushiRoll newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull SushiRoll oldItem, @NonNull SushiRoll newItem) {
            return areItemsTheSame(oldItem, newItem);
        }
    }

    interface RollClickCallBack {
        void onRollClick(SushiRoll sushiRoll);
    }

    static class RollAdapter extends ListAdapter<SushiRoll, SushiRollHolder> {

        Constants constants = Constants.getInstance();
        Context context;
        static HashMap<SushiRoll, Integer> sushiRollsAmounts;

        public RollAdapter() {

            super(new RollCallBack());
        }

        public void setUpAmounts(HashMap<SushiRoll, Integer> sushiRollsAmounts){
            this.sushiRollsAmounts = sushiRollsAmounts;
        }

        @NonNull
        @Override
        public SushiRollHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            this.context = parent.getContext();
            View itemView = LayoutInflater.from(context).inflate(R.layout.item_roll, parent, false);
            final SushiRollHolder holder = new SushiRollHolder(itemView);
            holder.minusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int numOfRolls = Integer.parseInt(holder.numOfRolls.getText().toString());
                    if (numOfRolls > 1) {
                        numOfRolls--;
                        holder.numOfRolls.setText(String.format("%d", numOfRolls));
                    }
                }
            });
            holder.plusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int numOfRolls = Integer.parseInt(holder.numOfRolls.getText().toString());
                    numOfRolls++;
                    holder.numOfRolls.setText(String.format("%d", numOfRolls));

                }
            });

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull SushiRollHolder holder, int position) {
            SushiRoll roll = getItem(position);
            if (sushiRollsAmounts != null && sushiRollsAmounts.containsKey(roll)) {
                holder.numOfRolls.setText(String.valueOf(sushiRollsAmounts.get(roll)));
            }

            holder.rollTitle.setText(roll.getType());

            Map<Integer, Integer> fillings = roll.getExtras();
            for (Map.Entry<Integer, Integer> filling : fillings.entrySet()) {
                Filling curFilling = holder.getCurrentFilling();
                int id = filling.getKey();
                int amountOfFilling = filling.getValue();
                curFilling.image.setImageDrawable(context.getDrawable(id));
                curFilling.content.setText(constants.getExtraString(id));
                curFilling.amount.setText(String.valueOf(amountOfFilling));
                curFilling.layout.setVisibility(View.VISIBLE);
            }

        }
    }

    static protected class Filling {
        public ConstraintLayout layout;
        public TextView content;
        public TextView amount;
        public ImageView image;
        private View view;

        protected Filling(View view) {
            this.view = view;
        }

        protected Filling(View view, int layoutId, int textId, int imageId, int amountId) {
            this.view = view;
            layout = view.findViewById(layoutId);
            content = view.findViewById(textId);
            image = view.findViewById(imageId);
            amount = view.findViewById(amountId);

        }
    }

    static class SushiRollHolder extends RecyclerView.ViewHolder {
        public final TextView rollTitle;

        private int counter = 0;

        public final TextView numOfRolls;
        public final ImageButton minusBtn;
        public final ImageButton plusBtn;

        ConstraintLayout fillingLayout1;
        ConstraintLayout fillingLayout2;
        ConstraintLayout fillingLayout3;

        ImageView fillingImage1;
        ImageView fillingImage2;
        ImageView fillingImage3;

        TextView fillingText1;
        TextView fillingText2;
        TextView fillingText3;

        TextView fillingAmount1;
        TextView fillingAmount2;
        TextView fillingAmount3;

        private View view;

        public SushiRollHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            rollTitle = itemView.findViewById(R.id.roll_title);
            numOfRolls = itemView.findViewById(R.id.num_of_rolls);
            minusBtn = itemView.findViewById(R.id.minus_roll_btn);
            plusBtn = itemView.findViewById(R.id.plus_roll_btn);

            fillingLayout1 = view.findViewById(R.id.roll_filling_1);
            fillingLayout2 = view.findViewById(R.id.roll_filling_2);
            fillingLayout3 = view.findViewById(R.id.roll_filling_3);

            fillingText1 = view.findViewById(R.id.roll_filling_1_text);
            fillingText2 = view.findViewById(R.id.roll_filling_2_text);
            fillingText3 = view.findViewById(R.id.roll_filling_3_text);

            fillingImage1 = view.findViewById(R.id.roll_filling_1_img);
            fillingImage2 = view.findViewById(R.id.roll_filling_2_img);
            fillingImage3 = view.findViewById(R.id.roll_filling_3_img);

            fillingAmount1 = view.findViewById(R.id.roll_filling_1_amount);
            fillingAmount2 = view.findViewById(R.id.roll_filling_2_amount);
            fillingAmount3 = view.findViewById(R.id.roll_filling_3_amount);
        }

        public Filling getCurrentFilling() {
            switch (counter) {
                case 0:
                    counter += 1;
                    return new Filling(view, R.id.roll_filling_1, R.id.roll_filling_1_text, R.id.roll_filling_1_img, R.id.roll_filling_1_amount);
                case 1:
                    counter += 1;
                    return new Filling(view, R.id.roll_filling_2, R.id.roll_filling_2_text, R.id.roll_filling_2_img, R.id.roll_filling_2_amount);
                case 2:
                    counter += 1;
                    return new Filling(view, R.id.roll_filling_3, R.id.roll_filling_3_text, R.id.roll_filling_3_img, R.id.roll_filling_3_amount);
                default:
                    // shouldn't get here
                    return null;

            }
        }
    }


}
