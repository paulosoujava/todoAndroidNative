package com.paulo.mytodo.model.data.presenter;

import android.content.Context;

import com.paulo.mytodo.model.entity.ItemTitle;
import com.paulo.mytodo.model.entity.Title;

public interface MVP {

    interface MainActivityView{
        Context getContext();
        void updateAdapter();
    }
    interface ItemsTitleActivityView {
        Context getContext();
        void updateUIFooter(Title sTitle);
        String getIdTitle();
        void updateAdapter();
    }
    interface ItemsTitleActivityIpl {
        void setFooter(Title title);
        void clickEditItemAdapter(ItemTitle item);
        void clickDeleteItemAdapter(ItemTitle item);
    }
    interface MainActivityPresenterIpl{
        void clickItemAdapter(Title title);
        void longClickItemAdapter(Title title);

    }
}
