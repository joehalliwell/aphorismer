package com.joehalliwell.aphorismer;


import android.content.Context;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;

public class PlainShareActionProvider extends ShareActionProvider {

    public PlainShareActionProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        return null;
    }
}
