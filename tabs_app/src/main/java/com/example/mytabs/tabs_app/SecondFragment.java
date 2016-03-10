package com.example.mytabs.tabs_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

/**


 */
public class SecondFragment extends Fragment {
    private Spinner spinz;
    private EditText text;

    ArrayList<String> saltList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragView = inflater.inflate(R.layout.fragament_two, container, false);

         text = (EditText) fragView.findViewById(R.id.editText2);

        saltList = new ArrayList<String>();

        populateList(saltList, fragView);


        final ArrayAdapter<String> dynamicArrayHolder =
                new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_dropdown_item,
                        saltList);

        spinz = (Spinner) fragView.findViewById(R.id.spinner);//Associate "spinz" with xml
        spinz.setAdapter(dynamicArrayHolder);//

        text.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {

                        case android.view.KeyEvent.KEYCODE_DPAD_CENTER:
                        case android.view.KeyEvent.KEYCODE_ENTER:
                            populateList(saltList, fragView);

                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });



        return fragView;


    }



    public int getLength(View v) {
        EditText text = (EditText) v.findViewById(R.id.editText2);
        int number = text.length();
        return number;
    }

    public void populateList(ArrayList list, View v) {

        list.clear();
        for (int i = 0; i < getLength(v); i++) {

            list.add(Integer.toString(i + 1));

        }


    }

}