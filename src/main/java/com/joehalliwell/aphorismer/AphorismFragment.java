package com.joehalliwell.aphorismer;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AphorismFragment extends Fragment {

    String aphorism;

    static AphorismFragment newInstance(String aphorism) {
        AphorismFragment fragment = new AphorismFragment();
        Bundle args = new Bundle();
        args.putString("aphorism", aphorism);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aphorism = getArguments() != null ? getArguments().getString("aphorism") : "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aphorism_fragment, container, false);
        TextView text = (TextView) view.findViewById(R.id.aphorism);
        text.setText(aphorism);
        return view;
    }

}
