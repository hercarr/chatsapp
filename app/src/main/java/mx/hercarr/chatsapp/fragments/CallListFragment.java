package mx.hercarr.chatsapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.hercarr.chatsapp.R;

public class CallListFragment extends Fragment {


    public CallListFragment() {

    }

    public static CallListFragment newInstance() {
        CallListFragment fragment = new CallListFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calls, container, false);
    }

}
