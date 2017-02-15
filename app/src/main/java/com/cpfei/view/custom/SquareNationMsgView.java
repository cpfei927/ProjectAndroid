package com.cpfei.view.custom;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.cpfei.bean.BroadcastNewBaseInfo;
import com.cpfei.project.R;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Administrator on 2017/1/17
 * 首页全国消息
 */
public class SquareNationMsgView extends FrameLayout {
    private static final String TAG = "SquareWithDrawView";
    private final static int PLAY_ANIM=100100; //
    private final static int PLAY_TIME=1500; //动画播放时间间隔
    private Context mContext;
    private float mDensity;
    private LinkedList<BroadcastNewBaseInfo> dataQueue = new LinkedList<BroadcastNewBaseInfo>();


    public SquareNationMsgView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public SquareNationMsgView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initView();
    }

    public SquareNationMsgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private int playIndex;
    private Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what) {
                case PLAY_ANIM:
                    if(dataQueue.size() > playIndex)
                    {
                        playAnim(dataQueue.get(playIndex));
                        mHandler.sendEmptyMessageDelayed(PLAY_ANIM, PLAY_TIME);
                        playIndex++;
                    }else
                    {


                    }
                    break;
                default:
                    break;
            }
        }
    };
    private SquareNationMsgChildView lastChildView;
    private SquareNationMsgChildView topChildView;
    private ImageView giftImageView;
    private void initView() {
        mDensity = mContext.getResources().getDisplayMetrics().density;
        lastChildView= new SquareNationMsgChildView(mContext);
        addView(lastChildView);

        topChildView= new SquareNationMsgChildView(mContext);
        addView(topChildView);

        View gifView =  LayoutInflater.from(getContext()).inflate(R.layout.square_nation_msg_gif_ainm_item, null);
        giftImageView = (ImageView) gifView.findViewById(R.id.gif_anim_icon);
        addView(gifView);
        initAnim();
    }


    private Animation animChildView1;
    private Animation animChildView2;
    private void initAnim()
    {
        AnimationSet anim1 = new AnimationSet(false);
        Animation animChild1 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, -1f);
        animChild1.setDuration(500);
        animChild1.setFillAfter(true);

        Animation animHideView = new AlphaAnimation(1f, 0);
        animHideView.setDuration(500);
        animHideView.setFillAfter(true);

        anim1.addAnimation(animChild1);
        anim1.addAnimation(animHideView);
        anim1.setFillAfter(true);
        animChildView1 = anim1;


        animChildView2 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,1f, Animation.RELATIVE_TO_PARENT, 0f);
        animChildView2.setDuration(500);
        animChildView2.setFillAfter(true);

    }

    private boolean isFrist = true;
    private BroadcastNewBaseInfo lastMsgInfo;
    public void setData(LinkedList<BroadcastNewBaseInfo> playList)
    {
        if(playList == null) {
            return ;
        }
        if(dataQueue == null){
            dataQueue = new LinkedList<BroadcastNewBaseInfo>();
        }else
        {
            dataQueue.clear();
        }
        playIndex = 0;
        dataQueue.addAll(playList);
        if(!dataQueue.isEmpty())
        {
            isFrist = false;
        }
    }
    public void startAnim()
    {
        if(isFrist)
        {
            setVisibility(View.GONE);
        }else
        {
            if(!mHandler.hasMessages(PLAY_ANIM))
            {
                mHandler.sendEmptyMessage(PLAY_ANIM);
            }
            setVisibility(View.VISIBLE);
        }
    }
    private void playAnim(BroadcastNewBaseInfo info)
    {
        this.clearAnimation();
        if(lastMsgInfo !=null)
        {
            lastChildView.startAnimation(animChildView1);
            setItemData(lastChildView, lastMsgInfo);
            if(lastChildView.getVisibility() != View.VISIBLE)
            {
                lastChildView.setVisibility(View.VISIBLE);
            }
        }else
        {
            lastChildView.setVisibility(View.INVISIBLE);
        }


        topChildView.startAnimation(animChildView2);
        setItemData(topChildView, info);
        if(topChildView.getVisibility() != View.VISIBLE)
        {
            topChildView.setVisibility(View.VISIBLE);
        }
        lastMsgInfo = info;

//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                playGif(giftImageView);
//            }
//        },500);
    }


    private void setItemData(SquareNationMsgChildView msgView,final BroadcastNewBaseInfo info)
    {
        if(info != null)
        {
            msgView.processMsg(info);

        }else
        {
            msgView.setVisibility(View.INVISIBLE);
        }

    }


    public void clear()
    {

        if( mHandler != null)
        {
            mHandler.removeCallbacksAndMessages(null);
        }
//        if(palyGif != null)
//        {
//            palyGif.stopImageSeq();
//        }
        lastChildView.setVisibility(View.VISIBLE);
    }


   // private PlayImageSeqUtil  palyGif=null;
    private void playGif(final ImageView imageView)
    {
//        if(palyGif==null)
//        {
//            palyGif=new PlayImageSeqUtil(imageView, mContext,false,60);
//        }
//        palyGif.setOnPlayCompleteListener(new PlayImageSeqUtil.OnPlayCompleteListener() {
//            @Override
//            public void onComplete() {
//                imageView.setImageBitmap(null);
//            }
//        });
//        String lstr= "square_nation_sun_gif.txt";
//        palyGif.playImageSeq(lstr, 0,mDensity);

        Animation animChild1 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -0.1f, Animation.RELATIVE_TO_PARENT, 0.7f, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0f);
        animChild1.setDuration(600);
        animChild1.setInterpolator(new LinearInterpolator());
       // animChild1.setFillAfter(true);
        animChild1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imageView.setVisibility(View.VISIBLE);
        imageView.startAnimation(animChild1);
    }
}
