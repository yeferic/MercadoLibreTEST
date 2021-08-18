package com.yeferic.mercadolibreapp.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<String> queryToSearch;

    public MainViewModel(){
        queryToSearch = new MutableLiveData<>();
    }

    public LiveData<String> getQueryToSearch() {
        return queryToSearch;
    }

    public void setQueryToSearch(String query) {
        this.queryToSearch.setValue(query);
    }
}