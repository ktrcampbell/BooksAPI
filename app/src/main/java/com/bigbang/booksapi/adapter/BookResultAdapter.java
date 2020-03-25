package com.bigbang.booksapi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bigbang.booksapi.R;
import com.bigbang.booksapi.model.Item;
import com.bigbang.booksapi.model.VolumeInfo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.internal.Util;

public class BookResultAdapter extends RecyclerView.Adapter<BookResultAdapter.BookResultViewHolder> {

    private List<Item> bookList;
    private BookClickListener bookClickListener;

    public BookResultAdapter(List<Item> bookList, BookClickListener bookClickListener){
        this.bookList = bookList;
        this.bookClickListener = bookClickListener;
    }

    public interface BookClickListener{
        void saveBook(Item bookItem);
    }
    @NonNull
    @Override
    public BookResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item_layout, parent, false);
        return new BookResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookResultViewHolder holder, int position) {
        Item item = bookList.get(position);
        holder.bookTitleTextView.setText("Title: " + item.getVolumeInfo().getTitle());
        holder.bookDateTextView.setText("Published Date: " + item.getVolumeInfo().getPublishedDate());

        if(item.getVolumeInfo().getImageLinks() != null){
            String imageUrl = item.getVolumeInfo().getImageLinks().getSmallThumbnail()
                    .replace("http://", "https://");
            Glide.with(holder.itemView)
                    .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                    .load(imageUrl)
                    .into(holder.bookImageView);
        }

        if(item.getVolumeInfo().getAuthors() != null){
            String authors = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                authors = String.join((CharSequence) item.getVolumeInfo().getAuthors(), ", ");
            }
            holder.bookAuthorTextView.setText(authors);
        }

        holder.itemView.setOnClickListener(view->
        {
            bookClickListener.saveBook(bookList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void setResults(List<Item>bookList){
        this.bookList = bookList;
        notifyDataSetChanged();

    }

    public class BookResultViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.book_imageview)
        ImageView bookImageView;

        @BindView(R.id.book_title_textview)
        TextView bookTitleTextView;

        @BindView(R.id.book_author_textview)
        TextView bookAuthorTextView;

        @BindView(R.id.published_date_textview)
        TextView bookDateTextView;

        public BookResultViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
