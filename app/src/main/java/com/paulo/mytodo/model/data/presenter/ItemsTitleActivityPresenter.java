package com.paulo.mytodo.model.data.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.paulo.mytodo.R;
import com.paulo.mytodo.model.data.service.Service;
import com.paulo.mytodo.model.entity.ItemTitle;
import com.paulo.mytodo.model.entity.Title;
import com.paulo.mytodo.utils.Utils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ItemsTitleActivityPresenter implements MVP.ItemsTitleActivityIpl {

    private MVP.ItemsTitleActivityView view;
    private Service service;

    public ItemsTitleActivityPresenter(MVP.ItemsTitleActivityView view) {
        this.view = view;
        this.service = new Service(view.getContext());

    }

    public List<ItemTitle> getList() {
        return service.getItemsTitle(view.getIdTitle());
    }

    public Context getContext() {
        return view.getContext();
    }

    @Override
    public void clickEditItemAdapter(ItemTitle item) {
        showDialogCreateItem(item);
    }

    @Override
    public void clickDeleteItemAdapter(ItemTitle item) {
        warningDelete(item);
    }

    @Override
    public void setFooter(Title title) {
        showDialogUpdateFooter(title);
    }

    private void warningDelete(ItemTitle item) {
        AlertDialog.Builder builder = Utils.getBuilder(view.getContext());
        View customView = setUpLayout(R.layout.warning_delete);
        builder.setView(customView);
        AlertDialog alert = builder.create();
        Button save = customView.findViewById(R.id.save);
        Button close = customView.findViewById(R.id.close);

        close.setOnClickListener(v -> {
            alert.dismiss();
        });
        save.setOnClickListener(v -> {
            service.deleteItem(item);
            Utils.toast("Deletado com sucesso", view.getContext());
            view.updateAdapter();
            alert.dismiss();
        });
        alert.show();
    }

    private void showDialogUpdateFooter(Title sTitle) {
        AlertDialog.Builder builder = myBuilder();
        View customView = setUpLayout(R.layout.item_pop_up_title);
        builder.setView(customView);
        AlertDialog alert = builder.create();
        Button save = customView.findViewById(R.id.save);
        Button close = customView.findViewById(R.id.close);
        EditText title = customView.findViewById(R.id.title);
        title.setText(sTitle.getTitle());
        close.setOnClickListener(v -> {
            alert.dismiss();
        });
        save.setText("Atualizar");
        save.setOnClickListener(v -> {
            setUpUpdateFooter(alert, title);

        });
        alert.show();
    }

    private void setUpUpdateFooter(AlertDialog alert, EditText title) {
        if (!title.getText().toString().isEmpty()) {
            Title t = new Title(title.getText().toString());
            t.setId(view.getIdTitle());
            if (service.existTtile(t.getTitle()) != null) {
                Utils.toast("Ops este nome já está em uso", view.getContext());
            } else {
                service.updateTitle(t);
                Utils.toast("Atualizado com sucesso", view.getContext());
                view.updateUIFooter(t);
                alert.dismiss();
            }


        } else {
            Utils.toast("Campo em branco não permitido", view.getContext());
        }
    }

    public void showDialogCreateItem(final ItemTitle item) {
        AlertDialog.Builder builder = myBuilder();
        View customView = setUpLayout(R.layout.items_pop_up_title);
        builder.setView(customView);
        AlertDialog alert = builder.create();

        EditText hour = customView.findViewById(R.id.hour);
        EditText date = customView.findViewById(R.id.date);
        EditText more = customView.findViewById(R.id.more);

        if (item != null) {
            hour.setText(item.getHour());
            date.setText(item.getDate());
            more.setText(item.getOthers());
        }else{
            hour.setHint("Horas: "+Utils.hour());
            date.setHint("Data: "+Utils.date());

        }
        Button save = customView.findViewById(R.id.save);
        Button close = customView.findViewById(R.id.close);

        close.setOnClickListener(v -> {
            alert.dismiss();
        });
        save.setOnClickListener(v -> {
            setUpCreate(alert, item, hour, date, more);
        });
        alert.show();
    }

    private AlertDialog.Builder myBuilder() {
        return new AlertDialog.Builder(view.getContext());
    }

    private View setUpLayout(int resource) {
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(resource, null);
    }

    private void setUpCreate(AlertDialog alertDialog, ItemTitle itemTitle, EditText hour, EditText date, EditText more) {

        String h = hour.getText().toString();
        String d = date.getText().toString();
        String m = more.getText().toString();

        if (m.isEmpty()) {
            Utils.toast("Ops, o campo livre deve ser preenchido", view.getContext());
        } else {

            if (itemTitle != null) {

                itemTitle.setDate(d);
                itemTitle.setHour(h);
                itemTitle.setOthers(m);
                service.updateItemTitle(itemTitle);
            } else {
                ItemTitle it = new ItemTitle();
                if (d.isEmpty()) {
                    it.setDate("");
                } else
                    it.setDate(d);
                if (h.isEmpty()) {
                    it.setHour("");
                } else
                    it.setHour(h);
                it.setStatus("OFF");
                it.setOthers(m);
                it.setUid(Utils.MyUID());
                it.setUidTitle(view.getIdTitle());
                service.createItemTitle(it);
            }


            Utils.toast("Ação realizada com  sucesso", view.getContext());
            alertDialog.dismiss();
            view.updateAdapter();
        }
    }


    public void clickStatusItemAdapter(ItemTitle item) {
        item.setStatus((item.getStatus().equals("ON") ) ? "OFF" : "ON" );
        service.updateItemTitle(item);
        view.updateAdapter();
    }
}
