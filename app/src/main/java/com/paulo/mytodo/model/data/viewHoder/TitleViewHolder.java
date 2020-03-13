package com.paulo.mytodo.model.data.viewHoder;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.paulo.mytodo.R;

public class TitleViewHolder extends RecyclerView.ViewHolder {
    public  TextView title;
    public  TextView item_count;
    public CardView click;

    public TitleViewHolder(View v) {
        super(v);
        title = v.findViewById(R.id.item_title);
        item_count = v.findViewById(R.id.item_count);
        click = v.findViewById(R.id.click);
    }
}
