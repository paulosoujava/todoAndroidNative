package com.paulo.mytodo.model.data.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paulo.mytodo.R;
import com.paulo.mytodo.model.data.presenter.MainActivityPresenter;
import com.paulo.mytodo.model.data.viewHoder.TitleViewHolder;
import com.paulo.mytodo.model.entity.Title;

import java.util.Collections;
import java.util.List;

public class TitleAdapter extends RecyclerView.Adapter<TitleViewHolder>{


    MainActivityPresenter mPresenter;

    public TitleAdapter(MainActivityPresenter presenter) {
        this.mPresenter = presenter;
    }

    @NonNull
    @Override
    public TitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mPresenter.getContext());
        return new TitleViewHolder(inflater.inflate(R.layout.item_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TitleViewHolder holder, int position) {
        Collections.reverse(mPresenter.getList());
        Title sTitle = mPresenter.getList().get(position);
        int x =  mPresenter.getCount(sTitle.getId());
        if( x > 0){
            holder.item_count.setVisibility(View.VISIBLE);
            holder.item_count.setText(""+x);
        }

        holder.title.setText( sTitle.getTitle().toUpperCase() );
        holder.click.setOnClickListener(v -> mPresenter.clickItemAdapter( sTitle ));
        holder.click.setOnLongClickListener(v -> {
            mPresenter.longClickItemAdapter(sTitle);
            return true;
        });


    }

    @Override
    public int getItemCount() {
        return mPresenter.getList() != null ? mPresenter.getList().size() : 0;
    }
}
