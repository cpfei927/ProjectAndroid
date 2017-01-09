package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cpfei.project.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class RecyclerViewGridActivity extends AppCompatActivity {


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, RecyclerViewGridActivity.class);
        return intent;
    }


    private RecyclerView mRecyclerView;
    private DisplayImageOptions options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_grid);
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerGridView);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                if (position % 6 == 0) {
                    return gridLayoutManager.getSpanCount();
                }
                return 1;
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.setAdapter(new SampleAdapter());

    }



    class SampleAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (viewType == 1) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, null);
                return new HeaderViewHolder(view);
            }
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img, null);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            if (getItemViewType(position) == 1) {
                ((HeaderViewHolder)holder).mTextView.setText("标题" + position);
            } else {
                ImageLoader.getInstance().displayImage(
                        "http://brs.baicheng.com/up-freetrip-bucket/public/images/city/74a045db-9cc3-4b5b-b36f-3e1a8427177a.jpg",
                        ((ItemViewHolder)holder).mImageView, options);
            }
        }

        @Override
        public int getItemCount() {
            return 49;
        }

        @Override
        public int getItemViewType(int position) {
            if (position % 6 == 0) {
                return 1;
            }
            return 0;
        }

        class HeaderViewHolder extends RecyclerView.ViewHolder{
            public TextView mTextView;

            public HeaderViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.title);
            }
        }

        class ItemViewHolder extends RecyclerView.ViewHolder{
            ImageView mImageView;

            public ItemViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.img);
            }
        }


    }


}
