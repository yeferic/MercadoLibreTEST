package com.yeferic.mercadolibreapp.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yeferic.mercadolibreapp.model.QueryResult;

public class MainViewModel extends ViewModel {
    private MutableLiveData<String> queryToSearch;
    private MutableLiveData<QueryResult> queryResult;
    private int currentOffset;

    public MainViewModel(){
        queryToSearch = new MutableLiveData<>();
        queryResult = new MutableLiveData<>();
        currentOffset = 0;
    }

    public LiveData<String> getQueryToSearch() {
        return queryToSearch;
    }

    public void setQueryToSearch(String query) {
        this.queryToSearch.setValue(query);
    }

    public LiveData<QueryResult> getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(QueryResult result) {
        this.queryResult.setValue(result);
    }

    public int getCurrentOffset() {
        return currentOffset;
    }

    public void setCurrentOffset(int currentOffset) {
        this.currentOffset = currentOffset;
    }
}