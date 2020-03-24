package com.example.roomwords.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomwords.data.model.Word;
import com.example.roomwords.data.repository.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mWordRepository;
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mWordRepository = new WordRepository(application);
        mAllWords = mWordRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word){
        mWordRepository.insert(word);
    }

    public void deleteAllWords(){
        mWordRepository.deleteAllWords();
    }

    public void deleteWord(Word word){
        mWordRepository.deleteWord(word);
    }
}
