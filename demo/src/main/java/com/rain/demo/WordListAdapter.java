package com.rain.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rain.demo.room.entity.Word;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author:rain
 * Date:2018/11/8 11:22
 * Description:
 */
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private Context mCtx;
    private List<Word> mWords;

    public WordListAdapter(Context context) {
        mCtx = context;
    }

    class WordViewHolder extends RecyclerView.ViewHolder{

        private final TextView content;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.tv_content);
        }


    }
    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.layout_recycler_item, parent,false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        if (mWords != null) {
            holder.content.setText(mWords.get(position).word);
        } else {
            holder.content.setText("no word");
        }
    }

    @Override
    public int getItemCount() {
        if (mWords != null) {
            return mWords.size();
        }
        return 0;
    }

    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }
}
