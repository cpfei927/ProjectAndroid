package com.cpfei.project.activity.material;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cpfei.project.R;
import com.cpfei.utils.DensityUtils;

/**
 * CoordinatorLayout
 * CoordinatorLayout是一个增强型的FrameLayout。它的作用有两个
 * 1.作为一个布局的根布局
 * 2.作为各个子类协调手势操作的一个协调布局（从它的名字就可以看出来）
 * <p>
 * 1.通过app:layout_scrollFlags=”scroll|enterAlways” 属性来确定哪个组件是可滑动的并且如何滑动
 * layout_scrollFlags有如下几种选项：
 * •scroll: 所有想滚动出屏幕的view都需要设置这个flag
 * •enterAlways: 这个flag让任意向下的滚动都会导致该view变为可见，启用快速“返回模式”。
 * •enterAlwaysCollapsed: 当你的视图已经设置minHeight属性又使用此标志时，你的视图只能已最小高度进入，
 * 只有当滚动视图到达顶部时才扩大到完整高度。(还没研究明白)
 * •exitUntilCollapsed: 只有滑动到最顶端在向下滚动会导致该view变为可见
 * 我们上面的布局中 给ImageView设置了app:layout_scrollFlags属性，因此，ImageView是可以滚动出屏幕，且向下滚动就可以出现。
 * <p>
 * 2.我们必须还得有个条件，就是CoordinatorLayout布局下包裹一个可以滑动的布局，
 * 比如 RecyclerView，NestedScrollView(经过测试，ListView，ScrollView不支持)具有滑动效果的组件。
 * 并且给这些组件设置如下属性来告诉CoordinatorLayout，
 * 该组件是带有滑动行为的组件，然后CoordinatorLayout在接受到滑动时会通知AppBarLayout 中可滑动的Imageview可以滑出屏幕了。
 * <p>
 * 总结一下：
 * 1.要使用CoordinatorLayout作为父布局
 * 2.相应滑动事件的控件需要添加app:layout_scrollFlags=”scroll|enterAlways”属性。
 * 3.包裹的滑动控件需要添加app:layout_behavior=”@string/appbar_scrolling_view_behavior”属性
 * <p>
 * CollapsingToolbarLayout 提供以下属性和方法是用：
 * 1.Collapsing title：ToolBar的标题，当CollapsingToolbarLayout全屏没有折叠时，
 * title显示的是大字体，在折叠的过程中，title不断变小到一定大小的效果。你可以调用setTitle(CharSequence)方法设置title。
 * 通常，我们我们都是设置Toolbar的title，而现在，我们需要把title设置在CollapsingToolBarLayout上，而不是Toolbar。
 * 2.Content scrim：ToolBar被折叠到顶部固定时候的背景，你可以调用setContentScrim(Drawable)方法改变背景
 * 或者 在属性中使用 app:contentScrim=”?attr/colorPrimary”来改变背景,这里需要注意toolbar不能有背景不然toolbar背景会始终显示在顶部.
 * 3.Parallax scrolling children：CollapsingToolbarLayout滑动时，子视图的视觉差，
 * 可以通过属性app:layout_collapseParallaxMultiplier=”0.6”改变。(感觉没啥用)
 * 4.CollapseMode ：子视图的折叠模式，有两种“pin”：固定模式，在折叠的时候最后固定在顶端；
 * “parallax”：视差模式，在折叠的时候会有个视差折叠的效果。我们可以在布局中使用属性app:layout_collapseMode=”parallax”来改变。
 * 经过我个人的测试ImageView只能放在ToolBar的上面才会有效果,还不明白为啥
 * CollapsingToolbarLayout主要是提供一个可折叠的Toolbar容器，对容器中的不同View设置layout_collapseMode折叠模式，
 * 来达到不同的折叠效果。
 */

public class CoordinatorLayoutActivity extends AppCompatActivity {

    private Context mCxt;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, CoordinatorLayoutActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCxt = this;
        setContentView(R.layout.activity_coordinator_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mCxt);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(new MyRecyclerViewAdapter());
    }


    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{


        @Override
        public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(mCxt).inflate(R.layout.item_recycler_view, parent, false));
        }

        @Override
        public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {

            switch (position % 4){
                case 0:
                    holder.linear.setBackgroundColor(getResources().getColor(R.color.color_2bc284));
                    break;
                case 1:
                    holder.linear.setBackgroundColor(getResources().getColor(R.color.color_fc6044));
                    break;
                case 2:
                    holder.linear.setBackgroundColor(getResources().getColor(R.color.color_f6a90e));
                    break;
                default:
                    holder.linear.setBackgroundColor(getResources().getColor(R.color.color_763ecc));
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return 30;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public LinearLayout linear;
            public ViewHolder(View itemView) {
                super(itemView);
                linear = ((LinearLayout) itemView.findViewById(R.id.itemLinear));
            }
        }

    }

}
