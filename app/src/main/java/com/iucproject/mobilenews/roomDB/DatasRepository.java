package com.iucproject.mobilenews.roomDB;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.iucproject.mobilenews.interfaces.DatasDao;

import java.util.List;

public class DatasRepository {

   private DatasDao datasDao;
   private LiveData<List<Datas>> allDatas;
   public DatasRepository(Application application) {
       RoomDB roomDB = RoomDB.getInstance(application);
       datasDao = roomDB.datasDao();
       allDatas = datasDao.getDatas();
   }

   public void insert(Datas data) {
    new InsertDataAsync(datasDao).execute(data);
   }

    public void update(Datas data) {
        new UpdateDataAsync(datasDao).execute(data);
    }

    public void delete(Datas data) {
        new DeleteDataAsync(datasDao).execute(data);
    }

    public void deleteAll() {
        new DeleteAllDataAsync(datasDao).execute();
    }
    public LiveData<List<Datas>> getAllDatas() {
       return allDatas;
    }
    private static class InsertDataAsync extends AsyncTask<Datas,Void,Void> {
       private DatasDao datadao;
       private InsertDataAsync(DatasDao datasDao) {
           this.datadao = datasDao;
       }
        @Override
        protected Void doInBackground(Datas... datas) {
           datadao.insert(datas[0]);
            return null;
        }
    }
    private static class UpdateDataAsync extends AsyncTask<Datas,Void,Void> {
        private DatasDao datadao;
        private UpdateDataAsync(DatasDao datasDao) {
            this.datadao = datasDao;
        }
        @Override
        protected Void doInBackground(Datas... datas) {
            datadao.update(datas[0]);
            return null;
        }
    }
    private static class DeleteDataAsync extends AsyncTask<Datas,Void,Void> {
        private DatasDao datadao;
        private DeleteDataAsync(DatasDao datasDao) {
            this.datadao = datasDao;
        }
        @Override
        protected Void doInBackground(Datas... datas) {
            datadao.delete(datas[0]);
            return null;
        }
    }
    private static class DeleteAllDataAsync extends AsyncTask<Void,Void,Void> {
        private DatasDao datadao;
        private DeleteAllDataAsync(DatasDao datasDao) {
            this.datadao = datasDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            datadao.deleteAll();
            return null;
        }
    }
}
