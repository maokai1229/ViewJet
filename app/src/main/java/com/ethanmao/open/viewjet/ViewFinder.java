package com.ethanmao.open.viewjet;

import android.app.Activity;
import android.view.View;

/**
 * @author Ethan.mao
 * @date 2018/11/6
 */
public class ViewFinder {

    private View mView;
    private Activity mActivity;

    public ViewFinder(View mView) {
        this.mView = mView;
    }

    public ViewFinder(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public View findViewById(int id){

        return mView==null?mActivity.findViewById(id):mView.findViewById(id);
    }

}
