package app.sample.nyt.recent_articles.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import app.sample.nyt.R;
import app.sample.nyt.article_details.activity.ArticleDetailsActivity;
import app.sample.nyt.article_details.fragment.ArticleDetailsFragment;
import app.sample.nyt.base.activity.MyActivity;
import app.sample.nyt.base.presenter.MyPresenter;
import app.sample.nyt.network.most_recent_articles.response.Result;
import app.sample.nyt.recent_articles.presenter.RecentArticlesPresenter;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ArticleDetailsActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ArticlesListActivity extends MyActivity<RecentArticlesPresenter> {

    private static List<Result> ITEMS = null;
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private View recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;

        getPresenter().loadRecentArticles(7);
    }

    public void setupRecyclerView(List<Result> items) {
        ITEMS = items;
        ((RecyclerView) recyclerView).setAdapter(new ArticleRecyclerViewAdapter(this, ITEMS, mTwoPane));
    }

    public static class ArticleRecyclerViewAdapter
            extends RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder> {

        private final ArticlesListActivity mParentActivity;
        private final List<Result> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Result item = (Result) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putSerializable(ArticleDetailsFragment.ARG_ARTICLE, item);
                    ArticleDetailsFragment fragment = new ArticleDetailsFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ArticleDetailsActivity.class);
                    intent.putExtra(ArticleDetailsFragment.ARG_ARTICLE, item);

                    context.startActivity(intent);
                }
            }
        };

        ArticleRecyclerViewAdapter(ArticlesListActivity parent,
                                   List<Result> items,
                                   boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.titleView.setText(mValues.get(position).getTitle());
            holder.byLineView.setText(mValues.get(position).getByline());
            String tDate = mValues.get(position).getPublishedDate();
            if (tDate == null) {
                tDate = Calendar.getInstance().getTime().toString();
            }
            holder.dateView.setText(tDate);
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
            try {
                Picasso.with(holder.img.getContext()).load(mValues.get(position).getMedia().get(0).getMediaMetadata().get(0).getUrl());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView titleView;
            final TextView byLineView;
            final TextView dateView;
            final AppCompatImageView img;
            final ImageView detailsImg;

            ViewHolder(View view) {
                super(view);
                titleView = (TextView) view.findViewById(R.id.title);
                byLineView = (TextView) view.findViewById(R.id.byline);
                dateView = (TextView) view.findViewById(R.id.date);
                img = (AppCompatImageView) view.findViewById(R.id.img);
                detailsImg = (ImageView) view.findViewById(R.id.details_img);
            }
        }
    }

    @Override
    public RecentArticlesPresenter getPresenter() {
        RecentArticlesPresenter myPresenter = new RecentArticlesPresenter();
        myPresenter.attachView(this);
        return myPresenter;
    }

    @Override
    protected void onDestroy() {
        getPresenter().detachView();
        super.onDestroy();
    }
}
