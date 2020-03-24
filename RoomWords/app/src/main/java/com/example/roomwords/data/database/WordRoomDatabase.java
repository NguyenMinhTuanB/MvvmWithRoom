package com.example.roomwords.data.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomwords.data.model.Word;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao mWordDao();

    private static WordRoomDatabase INSTANCE;

    private static RoomDatabase.Callback sCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    public static WordRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    WordRoomDatabase.class,
                    "word_database"
            )
                    .fallbackToDestructiveMigration()
                    .addCallback(sCallback)
                    .build();
        }

        return INSTANCE;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private WordDao mWordDao;
        private String[] words = {"Toress", "Messi", "Ronaldo"};

        public PopulateDbAsync(WordRoomDatabase db) {
            mWordDao = db.mWordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //mWordDao.deleteAll();
            if (mWordDao.getAnyWord().length < 1) {
                for (int i = 0; i < words.length; i++) {
                    Word word = new Word(words[i]);
                    mWordDao.insert(word);
                }
            }
            return null;
        }
    }
}
