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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MVP.MainActivityView {

    private MainActivityPresenter mPresenter;
    private RecyclerView mRecycler;
    private TitleAdapter mAdapter;
    private FloatingActionButton mFab;
    private TextView mFooter_date;
    private TextView mFooter_hour;

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
        mFooter_date = findViewById(R.id.footer_date);
        mFooter_date.setText( "Paulo & Renata");
        getHour();
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

    private void getHour() {
        mFooter_hour = findViewById(R.id.footer_hour);
        mFooter_hour.setText( Utils.date() );
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
