package com.example.roomwords.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomwords.data.database.WordDao;
import com.example.roomwords.data.database.WordRoomDatabase;
import com.example.roomwords.data.model.Word;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.mWordDao();
        mAllWords = mWordDao.getAllWords();
    }
    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }
    public void insert(Word word){
        new AsyncTaskInsert(mWordDao).execute(word);
    }

    public void deleteAllWords(){
        new AsyncTaskDeleteAllWords(mWordDao).execute();
    }

    public void deleteWord(Word word){
        new AsyncTaskDeleteWord(mWordDao).execute(word);
    }

    private static class AsyncTaskInsert extends AsyncTask<Word, Void, Void>{
        private WordDao mAsyncWordDao;

        public AsyncTaskInsert(WordDao asyncWordDao) {
            mAsyncWordDao = asyncWordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncWordDao.insert(words[0]);
            return null;
        }
    }

    private static class AsyncTaskDeleteAllWords extends AsyncTask<Void, Void, Void>{
        private WordDao mAsyncWordDao;

        public AsyncTaskDeleteAllWords(WordDao asyncWordDao) {
            mAsyncWordDao = asyncWordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncWordDao.deleteAll();
            return null;
        }
    }

    private static class AsyncTaskDeleteWord extends AsyncTask<Word, Void, Void>{
        private WordDao mAsyncWordDao;

        public AsyncTaskDeleteWord(WordDao asyncWordDao) {
            mAsyncWordDao = asyncWordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncWordDao.deleteWord(words[0]);
            return null;
        }
    }
}
