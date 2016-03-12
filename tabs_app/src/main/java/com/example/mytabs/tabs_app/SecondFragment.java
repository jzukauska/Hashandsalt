package com.example.mytabs.tabs_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**


 */
public class SecondFragment extends Fragment {
    ArrayList<String> saltList;
    private Spinner spinz;
    private EditText passInput;
    private TextView out;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragView = inflater.inflate(R.layout.fragament_two, container, false);
        //**********************************************************************************************
        //gotta love taht good old instantiation
        passInput = (EditText) fragView.findViewById(R.id.editText2);
        out = (TextView) fragView.findViewById(R.id.textView11);
        out.setText("");//set the output to nothing

        saltList = new ArrayList<String>();

        populateList(saltList, fragView);//default list
        //**********************************************************************************************

        final ArrayAdapter<String> dynamicArrayHolder =
                new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_dropdown_item,
                        saltList);

        //honestly these next lines just make it work
        spinz = (Spinner) fragView.findViewById(R.id.spinner);//Associate "spinz" with xml
        spinz.setAdapter(dynamicArrayHolder);//I hate apapters

        //Handles enter key during input
        passInput.setOnKeyListener(new View.OnKeyListener() {
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
        //Fancy method to take an edittext and turn it into a string
        //then it cuts it off at the number selected in the spinner
        //and sets the output to the "cut" version of the string
        spinz.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String upToNCharacters = passInput.getText().toString().substring(0, Math.min(passInput.getText().toString().length(), (spinz.getSelectedItemPosition() + 1)));

                out.setText(upToNCharacters);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        //Genuinly have no idea how views work most of this is lucky key hammering
        return fragView;//returns the instantiation

    }


    //Needs the view to find the edittext box
    //simple for loop to fill the array

    //the array is then placed into an adapter that lets it play nice
    //with the spinner.
    public void populateList(ArrayList list, View v) {

        list.clear();

        for (int i = 0; i < getLength(v); i++)
            list.add(Integer.toString(i + 1));




    }

    private int getLength(View v) {
        EditText text = (EditText) v.findViewById(R.id.editText2);
        int number = text.length();
        return number;
    }

}