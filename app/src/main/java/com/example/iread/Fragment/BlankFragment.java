package com.example.iread.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iread.R;

public class BlankFragment extends Fragment {

    public static BlankFragment newInstance() {
        return (new BlankFragment());
    }

    public interface OnFriendClicklistener{
        void onAddButtonClick(View view);
        void onfriendViewClick(View view);
        void onInvitaionViewClick(View view);
        void onDejaluClick(View view);
        void onEntrainClick(View view);
        void onALireClick(View view);
        void onDefiReqView(View view);
    }
    private BlankFragment.OnFriendClicklistener addCalback;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_blank_page, container, false);

        result.findViewById(R.id.fragment_blank_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalback.onAddButtonClick(v);
            }
        });
        result.findViewById(R.id.fragment_blank_view_friends).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalback.onfriendViewClick(v);
            }
        });
        result.findViewById(R.id.fragment_blank_view_invitation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalback.onInvitaionViewClick(v);
            }
        });
        result.findViewById(R.id.fragment_blank_btn_deja).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalback.onDejaluClick(v);
            }
        });
        result.findViewById(R.id.fragment_blank_btn_entrain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalback.onEntrainClick(v);
            }
        });
        result.findViewById(R.id.fragment_blank_a_lire).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalback.onALireClick(v);
            }
        });
        result.findViewById(R.id.fragment_blank_view_defi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalback.onDefiReqView(v);
            }
        });
        return result;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.createCallbackToParentActivity();
    }

    private void createCallbackToParentActivity(){
        try{
            addCalback = (BlankFragment.OnFriendClicklistener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnButtonClickedListener");        }
    }
}