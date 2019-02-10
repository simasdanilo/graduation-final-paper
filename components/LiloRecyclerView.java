package com.simas.danilo.lilo.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class LiloRecyclerView extends RecyclerView {
    //--------------------------------  Attributes -------------------------------------------------
    private Drawable emptyStateDrawable;
    private AdapterDataObserver adapterDataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            updateBackground();
        }
    };
    //-------------------------------- Constructor -------------------------------------------------
    public LiloRecyclerView(Context context) {
        super(context);
    }
    public LiloRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public LiloRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    //-------------------------------- @Override ---------------------------------------------
    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        Adapter currentAdapter = this.getAdapter();

        if (currentAdapter != null)
            currentAdapter.unregisterAdapterDataObserver(adapterDataObserver);

        if (adapter != null)
            adapter.registerAdapterDataObserver(adapterDataObserver);

        super.setAdapter(adapter);
        this.updateBackground();
    }
    //-------------------------------- EmptyState Methods ------------------------------------------

    /**
     * Sets the empty state drawable
     * @param emptyStateDrawable drawable to show when in empty state
     */
    public void setEmptyStateDrawable(Drawable emptyStateDrawable) {
        this.emptyStateDrawable = emptyStateDrawable;
        this.updateBackground();
    }

    /**
     * Updates the current background
     */
    private void updateBackground() {
        Adapter adapter = this.getAdapter();

        if (emptyStateDrawable == null || adapter == null)
            return;

        int itemCount = adapter.getItemCount();
        boolean showEmptyState = itemCount == 0;

        if (showEmptyState)
            this.setBackground(this.emptyStateDrawable);
        if (!showEmptyState)
            this.setBackground(null);
    }
}