package com.paulo.mytodo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.paulo.mytodo.R;
import com.paulo.mytodo.model.data.adapters.ItemsTitleAdapter;
import com.paulo.mytodo.model.data.presenter.ItemsTitleActivityPresenter;
import com.paulo.mytodo.model.data.presenter.MVP;
import com.paulo.mytodo.model.entity.Title;
import com.paulo.mytodo.utils.Utils;

import okhttp3.internal.Util;

public class ItemsTitleActivity extends AppCompatActivity implements MVP.ItemsTitleActivityView {

    private ItemsTitleActivityPresenter mPresenter;
    private RecyclerView mRecycler;
    private ItemsTitleAdapter mAdapter;
    private FloatingActionButton mFab;
    private LinearLayout mBack;
    private LinearLayout mClickFooter;
    private TextView mTextTitleFooter;

    public Title mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_title);

        Intent it = getIntent();
        if (it == null) {
            Utils.toast("Ops data missing", this);
            finish();
        }

        mTitle = it.getParcelableExtra(Title.TITLE_KEY);

        mPresenter = new ItemsTitleActivityPresenter(this);
        setup();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> mPresenter.showDialogCreateItem(null));

    }

    private void setup() {
        mRecycler = findViewById(R.id.recycler_title);
        mFab = findViewById(R.id.fab);
        mAdapter = new ItemsTitleAdapter(mPresenter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(layout);
        mRecycler.setAdapter(mAdapter);
        hideFloat(mRecycler);

        mTextTitleFooter = findViewById(R.id.title_footer);
        mTextTitleFooter.setText(mTitle.getTitle());
        mClickFooter = findViewById(R.id.click_footer);
        mClickFooter.setOnClickListener(v -> mPresenter.setFooter(mTitle));

        mBack = findViewById(R.id.click_back);
        mBack.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void updateUIFooter(Title sTitle) {
        runOnUiThread(() -> mTextTitleFooter.setText( sTitle.getTitle()));
    }

    @Override
    public String getIdTitle() {
        return mTitle.getId();
    }
    @Override
    public void updateAdapter() {
        runOnUiThread(() -> setup());
    }

    private void hideFloat(RecyclerView recyclerView) {
        Utils.hideFloat(recyclerView, mFab);
    }
}