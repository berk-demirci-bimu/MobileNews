package com.iucproject.mobilenews.roomDB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.iucproject.mobilenews.interfaces.DatasDao;

@Database(entities = Datas.class,version = 1,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {


    private static RoomDB instance;
    public abstract DatasDao datasDao();

    public static synchronized RoomDB getInstance(Context context) {

        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class,"Values").fallbackToDestructiveMigration()
                    .addCallback(roomCallback).build();

        }
        return instance;
    }
    private static RoomDB.Callback roomCallback = new RoomDB.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsync(instance).execute();
        }
    };
    private static class PopulateDBAsync extends AsyncTask<Void,Void,Void> {
        private DatasDao datasDao;
        private PopulateDBAsync(RoomDB db) {
            datasDao = db.datasDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            datasDao.insert(new Datas("Example Title","Content for example ",null));
            datasDao.insert(new Datas("Example Title -2","Content for example -2 ",null));
            datasDao.insert(new Datas("Example Title -3 ","Content for example ",null));
            return null;
        }
    }
}