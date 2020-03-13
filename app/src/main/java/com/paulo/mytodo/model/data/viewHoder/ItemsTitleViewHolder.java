package com.paulo.mytodo.model.data.viewHoder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.paulo.mytodo.R;

public class ItemsTitleViewHolder extends RecyclerView.ViewHolder {

    public TextView mDate;
    public TextView mHour;
    public TextView mMoreInf;
    public LinearLayout mEdit;
    public LinearLayout mDelete;
    public LinearLayout mClickStatus;
    public ImageView mCheck;

    public ItemsTitleViewHolder(View v) {
        super(v);
        mDate = v.findViewById(R.id.item_data_event);
        mHour = v.findViewById(R.id.item_hour_event);
        mMoreInf = v.findViewById(R.id.item_more_inf);
        mEdit = v.findViewById(R.id.click_edit);
        mDelete = v.findViewById(R.id.click_delete);
        mClickStatus = v.findViewById(R.id.click_status);
        mCheck = v.findViewById(R.id.check);

    }
}
