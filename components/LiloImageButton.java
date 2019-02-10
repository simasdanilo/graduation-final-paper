package com.simas.danilo.lilo.component;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;

public class LiloImageButton extends AppCompatImageButton {
    //--------------------------------  Attributes -------------------------------------------------
    // default min touch target size, in density-independent pixel (dp)
    public static final int MIN_TOUCH_TARGET_SIZE = 50;
    //-------------------------------- Constructors ------------------------------------------------
    public LiloImageButton(Context context){
        super(context);
    }
    public LiloImageButton(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    public LiloImageButton(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs, defStyleAttr);
    }
    //-------------------------------- Customizable Methods ----------------------------------------
    /**
     * Returns the min touch target width. The default implementation
     * of this method returns {@link #MIN_TOUCH_TARGET_SIZE}.
     * @return min touch target width, in density-independent pixel (dp)
     */
    public int getMinTouchTargetWidth(){
        return MIN_TOUCH_TARGET_SIZE;
    }
    /**
     * Returns the min touch target height. The default implementation
     * of this method returns {@link #MIN_TOUCH_TARGET_SIZE}.
     * @return min touch target height, in density-independent pixel (dp)
     */
    public int getMinTouchTargetHeight(){
        return MIN_TOUCH_TARGET_SIZE;
    }
    //-------------------------------- @Override ---------------------------------------------------
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable drawable = this.getDrawable();

        if (drawable != null) {
            int drawableHeight = this.getDrawable().getIntrinsicHeight();
            int drawableWidth = this.getDrawable().getIntrinsicWidth();

            int paddingHorizontal = 0;
            int paddingVertical = 0;

            int minTouchTargetWidthDp = this.getMinTouchTargetWidth();
            int minTouchTargetHeightDp = this.getMinTouchTargetHeight();

            int minTouchTargetWidthPx = this.convertDpToPx(minTouchTargetWidthDp);
            int minTouchTargetHeightPx = this.convertDpToPx(minTouchTargetHeightDp);

            if (drawableWidth < minTouchTargetWidthPx) {
                paddingHorizontal = Math.round((minTouchTargetWidthPx - drawableWidth) / 2f);
            }

            if (drawableHeight < minTouchTargetHeightPx) {
                paddingVertical = Math.round((minTouchTargetHeightPx - drawableHeight) / 2f);
            }

            if (paddingVertical != 0 || paddingHorizontal != 0)
                this.modifyPadding(paddingHorizontal, paddingVertical);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    //------------------------------- General Methods ----------------------------------------------

    private int convertDpToPx(int dp){
        // Get the screen's density scale
        final float dpi = this.getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        int px = (int) (dp * dpi + 0.5f);
        return px;
    }

    private void modifyPadding(int paddingHorizontal, int paddingVertical) {
        this.setPadding(
                paddingHorizontal,
                paddingVertical,
                paddingHorizontal,
                paddingVertical);
    }
}