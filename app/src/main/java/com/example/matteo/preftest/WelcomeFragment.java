package com.example.matteo.preftest;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {

    private String[] preference={"informatica","sport","medicina","pastorizia","magnare","seno"};
    private TextView username;
    private String name;
    private ListView checkList;
    private Button submit;
    private String prefchoice;
    private ArrayList<String> preferencechoice;

    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        container.removeAllViews();




        checkList=view.findViewById(R.id.checkable_list);
        checkList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter<String> adapter=new ArrayAdapter<>(view.getContext(),R.layout.row_preferences,preference);
        checkList.setAdapter(adapter);


        checkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(((CheckedTextView)view).isChecked()){
                    ((CheckedTextView) view).setCheckMarkDrawable(R.drawable.chex);
                    ((CheckedTextView) view).toggle();

                    prefchoice = (((CheckedTextView) view).getText().toString());
                    if(!preferencechoice.contains(prefchoice)){
                        preferencechoice.add(prefchoice);
                    }
                }else{((CheckedTextView) view).setCheckMarkDrawable(0);
                    ((CheckedTextView) view).toggle();
                    preferencechoice.remove(prefchoice);
                }
            }
        });






        return view;
    }

}
