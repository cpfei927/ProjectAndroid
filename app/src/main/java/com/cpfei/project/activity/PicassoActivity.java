package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cpfei.project.R;
import com.cpfei.utils.DensityUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PicassoActivity extends AppCompatActivity {

    private Context mCxt;

    protected ListView picassoListView;

    List<String> urls = new ArrayList<>();


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, PicassoActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        mCxt = this;
        picassoListView = ((ListView) findViewById(R.id.picassoListView));

        urls.add("http://img05.tooopen.com/images/20141216/sy_77381495134.jpg");
        urls.add("http://img05.tooopen.com/images/20141222/sy_77687647775.jpg");
        urls.add("http://img05.tooopen.com/images/20150202/sy_80219211654.jpg");
        urls.add("http://img02.tooopen.com/images/20150202/sy_80217549496.jpg");
        urls.add("http://img05.tooopen.com/images/20150128/sy_79895038572.jpg");
        urls.add("http://img05.tooopen.com/images/20150123/sy_79742338578.jpg");
        urls.add("http://img05.tooopen.com/images/20141231/sy_78299865841.jpg");
        urls.add("http://img05.tooopen.com/images/20141223/sy_77782822613.jpg");
        urls.add("http://img05.tooopen.com/images/20141209/sy_76823533954.jpg");

        urls.add("http://img05.tooopen.com/images/20141127/sy_75918161446.jpg");
        urls.add("http://img05.tooopen.com/images/20141119/sy_75371057629.jpg");
        urls.add("http://img05.tooopen.com/images/20141117/sy_75012078499.jpg");
        urls.add("http://img05.tooopen.com/images/20141104/sy_73996594737.jpg");
        urls.add("http://img05.tooopen.com/images/20141101/sy_73834829289.jpg");

        MyAdapter myAdapter = new MyAdapter();

        picassoListView.setAdapter(myAdapter);
    }



    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return urls == null ? 0 : urls.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView imageView = new ImageView(mCxt);

            LinearLayout.LayoutParams layoutParams
                    = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dip2px(mCxt, 150));
            layoutParams.setMargins(0, DensityUtils.dip2px(mCxt, 10), 0, DensityUtils.dip2px(mCxt, 10));
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(mCxt).load(urls.get(position)).placeholder(R.mipmap.ic_loading).error(R.mipmap.ic_loading).into(imageView);
            return imageView;
        }
    }






}
