package app.sample.nyt.article_details.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;

import app.sample.nyt.R;
import app.sample.nyt.article_details.activity.ArticleDetailsActivity;
import app.sample.nyt.article_details.presenter.ArticleDetailsPresenter;
import app.sample.nyt.base.fragment.MyFragment;
import app.sample.nyt.base.presenter.MyPresenter;
import app.sample.nyt.network.most_recent_articles.response.Result;
import app.sample.nyt.recent_articles.activity.ArticlesListActivity;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ArticlesListActivity}
 * in two-pane mode (on tablets) or a {@link ArticleDetailsActivity}
 * on handsets.
 */
public class ArticleDetailsFragment extends MyFragment<ArticleDetailsPresenter> {
    /**
     * The fragment argument representing the article that this fragment
     * represents.
     */
    public static final String ARG_ARTICLE = "article";

    /**
     * The dummy content this fragment is presenting.
     */
    private Result mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ArticleDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ARTICLE)) {
            mItem = (Result) getArguments().getSerializable(ARG_ARTICLE);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getTitle());
            }
        }
    }

    @Override
    protected ArticleDetailsPresenter createPresenter() {
        return new ArticleDetailsPresenter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.item_detail;
    }

    @Override
    public ArticleDetailsPresenter getPresenter() {
        return presenter;
    }
}
