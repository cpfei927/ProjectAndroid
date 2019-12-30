package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cpfei.project.R;
import com.cpfei.utils.LogUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class RecyclerViewGridActivity extends AppCompatActivity {


    private Handler handler;

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

//        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(linearLayoutManager);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                if (position == 0) {
                    return 2;
                }
                return 1;
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.setAdapter(new SampleAdapter());


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                LogUtil.d("Tag", "onScrollStateChanged = " + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItemPosition == 0) {
                    LogUtil.d("Tag", "onScrolled , " + firstVisibleItemPosition);
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        handler = new Handler();

        handler.postDelayed(runnable, 1000);


//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                linearLayoutManager.scrollToPositionWithOffset(10, 0);
//            }
//        });

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Rect rect = new Rect();
            boolean globalVisibleRect = viewHeader.getGlobalVisibleRect(rect);
            Toast.makeText(RecyclerViewGridActivity.this, "" + globalVisibleRect, Toast.LENGTH_SHORT).show();
            LogUtil.d("Tag", "globalVisibleRect , " + globalVisibleRect);
            LogUtil.d("Tag", "Rect ,left " + rect.left + " top, " + rect.top + " right ," + rect.right + " bottom, " +  rect.bottom);
//            handler.postDelayed(runnable, 3000);
        }
    };


    View viewHeader;

    class SampleAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (viewType == 1) {
                viewHeader = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, null);
                return new HeaderViewHolder(viewHeader);
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
            if (position == 0) {
                return 1;
            }
            return 0;
        }

        class HeaderViewHolder extends RecyclerView.ViewHolder{
            public TextView mTextView;

            public HeaderViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.title);
//                mTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Rect rect = new Rect();
//                        boolean globalVisibleRect = v.getGlobalVisibleRect(rect);
//                        Toast.makeText(RecyclerViewGridActivity.this, "" + globalVisibleRect, Toast.LENGTH_LONG).show();
//                    }
//                });



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
