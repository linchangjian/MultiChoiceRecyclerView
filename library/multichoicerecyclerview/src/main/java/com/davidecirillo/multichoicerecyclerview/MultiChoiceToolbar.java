package com.davidecirillo.multichoicerecyclerview;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

public class MultiChoiceToolbar {

    private AppCompatActivity mAppCompatActivity;
    private Toolbar mToolbar;
    private String mDefaultToolbarTitle;
    private String mSelectedToolbarTitle;
    private int mDefaultPrimaryColor = 0;
    private int mDefaultPrimaryColorDark = 0;
    private int mMultiPrimaryColor = 0;
    private int mMultiPrimaryColorDark = 0;
    private int mIcon;
    private View.OnClickListener mIconAction;
    private Listener mListener;

    private MultiChoiceToolbar(Builder builder) {
        this.mAppCompatActivity = builder.mAppCompatActivity;
        this.mToolbar = builder.mToolbar;
        this.mDefaultToolbarTitle = builder.mDefaultToolbarTitle.trim();
        this.mSelectedToolbarTitle = builder.mSelectedToolbarTitle.trim();
        this.mDefaultPrimaryColor = builder.mDefaultPrimaryColor;
        this.mDefaultPrimaryColorDark = builder.mDefaultPrimaryColorDark;
        this.mMultiPrimaryColor = builder.mMultiPrimaryColor;
        this.mMultiPrimaryColorDark = builder.mMultiPrimaryColorDark;
        this.mIcon = builder.mIcon;
        this.mIconAction = builder.mIconAction;
    }

    void setToolbarListener(Listener listener) {
        mListener = listener;
    }

    /**
     * Builder class for MultiChoiceToolbar
     */
    public static class Builder {
        private AppCompatActivity mAppCompatActivity;
        private Toolbar mToolbar;
        private String mDefaultToolbarTitle;
        private String mSelectedToolbarTitle;

        // Colours
        private int mDefaultPrimaryColor = 0;
        private int mDefaultPrimaryColorDark = 0;
        private int mMultiPrimaryColor = 0;
        private int mMultiPrimaryColorDark = 0;
        private int mIcon;
        private View.OnClickListener mIconAction;

        public Builder(AppCompatActivity appCompatActivity,
                       Toolbar toolbar) {
            this.mAppCompatActivity = appCompatActivity;
            this.mToolbar = toolbar;
        }

        /**
         * Set the colours when the toolbar is the default one
         *
         * @param defaultPrimaryColor     The color used for the background of the toolbar when is in default mode
         * @param defaultPrimaryColorDark The color used for the status bar background when is in default mode
         * @return Builder so you can chain together setters and build
         */
        public Builder setDefaultColours(int defaultPrimaryColor, int defaultPrimaryColorDark) {
            this.mDefaultPrimaryColor = defaultPrimaryColor;
            this.mDefaultPrimaryColorDark = defaultPrimaryColorDark;
            return this;
        }

        /**
         * Set the colours when the toolbar is the multi choice one
         *
         * @param multiPrimaryColor     The color used for the background of the toolbar when is in multi choice mode
         * @param multiPrimaryColorDark The color used for the status bar background when is in multi choice mode
         * @return Builder so you can chain together setters and build
         */
        public Builder setMultiChoiceColours(int multiPrimaryColor, int multiPrimaryColorDark) {
            this.mMultiPrimaryColor = multiPrimaryColor;
            this.mMultiPrimaryColorDark = multiPrimaryColorDark;
            return this;
        }

        /**
         * Set the titles for the differente state of the toolbar default/multiChoice
         *
         * @param defaultTitle  Title when the toolbar shown is the default one
         * @param selectedTitle Title shown when some item are selected. Will be of the format "itemCount title" where "itemCount" is the number of
         *                      selected item and "title" is this param
         * @return Builder so you can chain together setters and build
         */
        public Builder setTitles(String defaultTitle, String selectedTitle) {
            this.mDefaultToolbarTitle = defaultTitle;
            this.mSelectedToolbarTitle = selectedTitle;
            return this;
        }

        public Builder setDefaultIcon(int icon, View.OnClickListener action) {
            mIcon = icon;
            mIconAction = action;
            return this;
        }

        public MultiChoiceToolbar build() {
            if (mDefaultPrimaryColor == 0 || mDefaultPrimaryColorDark == 0) {
                mDefaultPrimaryColor = getDefaultColorFromContext(new int[]{R.attr.colorPrimary});
                mDefaultPrimaryColorDark = getDefaultColorFromContext(new int[]{R.attr.colorPrimaryDark});
            }
            return new MultiChoiceToolbar(this);
        }

        private int getDefaultColorFromContext(int[] colorRes) {
            TypedValue typedValue = new TypedValue();
            TypedArray a = mAppCompatActivity.obtainStyledAttributes(typedValue.data, colorRes);
            int color = a.getColor(0, 0);
            a.recycle();
            return color;
        }
    }

    /* Getters */

    AppCompatActivity getAppCompatActivity() {
        return mAppCompatActivity;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    String getDefaultToolbarTitle() {
        return mDefaultToolbarTitle;
    }

    String getSelectedToolbarTitle() {
        return mSelectedToolbarTitle;
    }

    int getDefaultPrimaryColor() {
        return mDefaultPrimaryColor;
    }

    int getDefaultPrimaryColorDark() {
        return mDefaultPrimaryColorDark;
    }

    int getMultiPrimaryColor() {
        return mMultiPrimaryColor;
    }

    int getMultiPrimaryColorDark() {
        return mMultiPrimaryColorDark;
    }

    public int getIcon() {
        return mIcon;
    }

    View.OnClickListener getIconAction() {
        return mIconAction;
    }

    Listener getToolbarListener() {
        return mListener;
    }

    /*
            * Listener
            * */
    interface Listener {
        void onClearButtonPressed();
    }
}
