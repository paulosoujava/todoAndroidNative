package com.paulo.mytodo.model.data.adapters;

import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paulo.mytodo.R;
import com.paulo.mytodo.model.data.presenter.ItemsTitleActivityPresenter;
import com.paulo.mytodo.model.data.presenter.MainActivityPresenter;
import com.paulo.mytodo.model.data.viewHoder.ItemsTitleViewHolder;
import com.paulo.mytodo.model.data.viewHoder.TitleViewHolder;
import com.paulo.mytodo.model.entity.ItemTitle;
import com.paulo.mytodo.model.entity.Title;

import java.util.Collections;

public class ItemsTitleAdapter extends RecyclerView.Adapter<ItemsTitleViewHolder> {


    ItemsTitleActivityPresenter mPresenter;

    public ItemsTitleAdapter(ItemsTitleActivityPresenter presenter) {
        this.mPresenter = presenter;
    }

    @NonNull
    @Override
    public ItemsTitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mPresenter.getContext());
        return new ItemsTitleViewHolder(inflater.inflate(R.layout.itens_title_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsTitleViewHolder holder, int position) {
        Collections.reverse(mPresenter.getList());
        ItemTitle item = mPresenter.getList().get(position);

        checkBox(holder, item);

        if (item.getDate().isEmpty()) holder.mDate.setVisibility(View.GONE);
        else holder.mDate.setText("Data: " + item.getDate());
        if (item.getHour().isEmpty()) holder.mHour.setVisibility(View.GONE);
        else holder.mHour.setText("Hora: " + item.getHour());

        holder.mMoreInf.setText(item.getOthers().toUpperCase());

        holder.mEdit.setOnClickListener(v -> mPresenter.clickEditItemAdapter(item));
        holder.mDelete.setOnClickListener(v -> mPresenter.clickDeleteItemAdapter(item));
        holder.mClickStatus.setOnClickListener(v -> mPresenter.clickStatusItemAdapter(item));


    }

    private void checkBox(@NonNull ItemsTitleViewHolder holder, ItemTitle item) {
        if (item.getStatus().equals("ON")) {
            holder.mCheck.setImageResource(R.drawable.ic_check_box_on);
            holder.mMoreInf.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        } else {
            holder.mCheck.setImageResource(R.drawable.ic_check_box_off);
        }
    }


    @Override
    public int getItemCount() {
        return mPresenter.getList() != null ? mPresenter.getList().size() : 0;
    }
}
