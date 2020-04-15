package com.bigbang.booksapi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bigbang.booksapi.R;
import com.bigbang.booksapi.model.BookItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookResultAdapter extends RecyclerView.Adapter<BookResultAdapter.BookResultViewHolder> {

    private List<BookItem> bookList;
    private BookClickListener bookClickListener;

    public BookResultAdapter(List<BookItem> bookList, BookClickListener bookClickListener){
        this.bookList = bookList;
        this.bookClickListener = bookClickListener;
    }

    public interface BookClickListener{
        void saveBook(BookItem bookItem);
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
        BookItem item = bookList.get(position);
        holder.bookTitleTextView.setText("Title: " + item.getVolumeInfo().getTitle());
        holder.bookDateTextView.setText("Published Date: " + item.getVolumeInfo().getPublishedDate());

        if(item.getVolumeInfo().getImageLinks() != null) {
            String imageUrl = item.getVolumeInfo().getImageLinks().getSmallThumbnail()
                    .replace("http://", "https://");
            Glide.with(holder.itemView)
                    .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                    .load(imageUrl)
                    .into(holder.bookImageView);
        }

        if(item.getVolumeInfo().getAuthors() != null){
            holder.bookAuthorTextView.setText(item.getVolumeInfo().getAuthors().toString());
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

    public void setResults(List<BookItem>bookList){
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
