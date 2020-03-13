package com.paulo.mytodo.ui;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.paulo.mytodo.R;
import com.paulo.mytodo.model.data.adapters.TitleAdapter;
import com.paulo.mytodo.model.data.presenter.MVP;
import com.paulo.mytodo.model.data.presenter.MainActivityPresenter;
import com.paulo.mytodo.utils.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class MainActivity extends AppCompatActivity implements MVP.MainActivityView {

    private MainActivityPresenter mPresenter;
    private RecyclerView mRecycler;
    private TitleAdapter mAdapter;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainActivityPresenter(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.saveShowDialog( );
            }
        });
    }

    private void setup() {
        mRecycler = findViewById(R.id.recycler_title);
        mFab = findViewById(R.id.fab);
        mAdapter = new TitleAdapter(mPresenter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(layout);
        mRecycler.setAdapter(mAdapter);
        hideFloat(mRecycler);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setup();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void updateAdapter() {
        runOnUiThread(() -> setup());
    }

    private void hideFloat(RecyclerView recyclerView) {
        Utils.hideFloat(recyclerView, mFab);

    }
}
