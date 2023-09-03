package com.joehalliwell.aphorismer;


import android.content.Context;
import androidx.appcompat.widget.ShareActionProvider;
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
