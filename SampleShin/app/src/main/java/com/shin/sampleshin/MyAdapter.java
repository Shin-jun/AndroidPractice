package com.shin.sampleshin;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<NewsData> mDataset;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView TextView_title;
        public TextView TextView_content;
        public SimpleDraweeView ImageView_title;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            TextView_title = view.findViewById(R.id.TextView_title);
            TextView_content = view.findViewById(R.id.TextVIew_content);
            ImageView_title = view.findViewById(R.id.ImageView_title);
            ImageView_title = (SimpleDraweeView) view.findViewById(R.id.ImageView_title);

        }

        public TextView getTextView() {
            return TextView_title;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public MyAdapter(List<NewsData> myDataset, Context context) {
        mDataset = myDataset;
        Fresco.initialize(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_news, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        NewsData news = mDataset.get(position);
        viewHolder.TextView_title.setText(news.getTitle());
        viewHolder.TextView_content.setText(news.getContent());

        Uri uri = Uri.parse(news.getUrlToImage());

        viewHolder.ImageView_title.setImageURI(uri);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset != null ? 0 : mDataset.size();
    }
}
