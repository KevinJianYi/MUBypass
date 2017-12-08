package com.personage.kevin.mubypass.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/** load more
 *  加载更多的监听
 * Created by pc1 on 2016/2/23.
 */
public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener{
    public static String TAG = EndlessRecyclerOnScrollListener.class.getSimpleName();
    private int previousTotal = 0;
    private boolean loading = true;
    int lastCompletelyVisiableItemPosition , visibleItemCount ,totalItemCount;
    private int currentPage = 1;
    private LinearLayoutManager mLayoutManager;

    public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager){
        this.mLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLayoutManager.getItemCount();
        lastCompletelyVisiableItemPosition = mLayoutManager.findLastCompletelyVisibleItemPosition();
        if (loading){
            if (totalItemCount > previousTotal){
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (visibleItemCount > 0)
                && (lastCompletelyVisiableItemPosition >= totalItemCount -1)){
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);
}
