package com.iucproject.mobilenews.roomDB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DatasViewModel extends AndroidViewModel {

    private DatasRepository repository;
    private LiveData<List<Datas>> allDatas;
    public DatasViewModel(@NonNull Application application) {
        super(application);
        repository = new DatasRepository(application);
        allDatas = repository.getAllDatas();
    }
    public void insert(Datas datas) {
        repository.insert(datas);
    }
    public void update(Datas datas) {
        repository.update(datas);
    }
    public void delete(Datas datas) {
        repository.delete(datas);
    }
    public void deleteAll() {
        repository.deleteAll();
    }
    public LiveData<List<Datas>> getAllDatas() {
    return allDatas;
    }
}
