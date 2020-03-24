package com.example.roomwords.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwords.R;
import com.example.roomwords.data.model.Word;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Word> mWords;

    public WordAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_word, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if(mWords != null){
            Word word = mWords.get(position);
            holder.mTextWord.setText(word.getWord());
        }else {
            holder.mTextWord.setText("No Word");
        }
    }


    @Override
    public int getItemCount() {
        return mWords != null ? mWords.size() : 0;
    }

    static class WordViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextWord;

        private WordViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextWord = itemView.findViewById(R.id.text_word);
        }
    }

    public Word getWordAtPosition(int position){
        return mWords.get(position);
    }

    public void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }
}
