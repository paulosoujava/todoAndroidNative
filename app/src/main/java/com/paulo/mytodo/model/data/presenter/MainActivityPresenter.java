package com.paulo.mytodo.model.data.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.paulo.mytodo.R;
import com.paulo.mytodo.model.data.service.Service;
import com.paulo.mytodo.model.entity.Title;
import com.paulo.mytodo.ui.ItemsTitleActivity;
import com.paulo.mytodo.utils.Utils;

import java.util.List;

public class MainActivityPresenter  implements  MVP.MainActivityPresenterIpl {

    private  MVP.MainActivityView view;
    private Service service;

    public MainActivityPresenter(MVP.MainActivityView view) {
        this.view = view;
        service = new Service(view.getContext());
    }

    public List<Title> getList() {
        return  service.getListTitle();
    }
    public Context getContext(){ return  view.getContext(); }

    public  int getCount(String uid){
        return service.getCount(uid);
    }

    @Override
    public void clickItemAdapter( Title title ) {

        Intent intent = new Intent(view.getContext(), ItemsTitleActivity.class );
        intent.putExtra(Title.TITLE_KEY, title);
        getContext().startActivity( intent );
    }

    @Override
    public void longClickItemAdapter(Title title) {
        warningDelete(title );
    }

    private void warningDelete(Title title) {
        AlertDialog.Builder builder = Utils.getBuilder(view.getContext());
        LayoutInflater inflater = Utils.getLayout(view.getContext());
        View customView = getView(inflater, R.layout.warning_delete);
        builder.setView(customView);
        AlertDialog alert = builder.create();
        Button save = customView.findViewById(R.id.save);
        Button close = customView.findViewById(R.id.close);

        close.setOnClickListener(v -> { alert.dismiss();});
        save.setOnClickListener(v -> {
            service.deleteTitle(title);
            Utils.toast("Deletado com sucesso", view.getContext());
            view.updateAdapter();
            alert.dismiss();
        });
        alert.show();
    }

    public void saveShowDialog(){
        AlertDialog.Builder builder = Utils.getBuilder(view.getContext());
        LayoutInflater inflater = Utils.getLayout(view.getContext());
        View customView = getView(inflater, R.layout.item_pop_up_title);
        builder.setView(customView);
        AlertDialog alert = builder.create();
        Button save = customView.findViewById(R.id.save);
        Button close = customView.findViewById(R.id.close);
        EditText title = customView.findViewById(R.id.title);
        close.setOnClickListener(v -> { alert.dismiss();});
        save.setOnClickListener(v -> {
            saveTitle(alert, title);});
        alert.show();
    }

    private View getView(LayoutInflater inflater, int resource ) {
        return inflater.inflate(resource, null);
    }


    private void saveTitle(AlertDialog alert, EditText title) {
        if(title.getText().toString().isEmpty()){
            Utils.toast("Preencha o título deste card.", view.getContext());
        }else{
            Title t = new Title(title.getText().toString());

                if(service.existTtile( t.getTitle()) != null){
                    Utils.toast("Ops este nome[ " + t.getTitle()+ " ] já está cadastrado.", view.getContext());
                }else{
                    t.setId(Utils.MyUID() );
                    Log.d("LOG" ,"->> "+ t.toString() );
                    service.createTitle(t);
                    Utils.toast("Salvo com sucesso.", view.getContext());
                    alert.dismiss();
                }

        }
    }
}
