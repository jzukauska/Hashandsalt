package com.example.mytabs.tabs_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class FirstFragment extends Fragment {

    EditText input;
    TextView output;
    Button hashButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //******************************************************************************************
        //This section instantiates the editable fields and button
        View view = inflater.inflate(R.layout.fragament_one, container, false);
        input = (EditText) view.findViewById(R.id.editText);
        output = (TextView) view.findViewById(R.id.textView4);
        hashButton = (Button) view.findViewById(R.id.button);
        //******************************************************************************************

        // Output hash during view creation so we have something to look at immediatly
        changeOutput(input);

        //This listener handles when the enter key is hit after editing the "password"
        //It just changes the output really
        input.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {

                        case android.view.KeyEvent.KEYCODE_DPAD_CENTER:
                        case android.view.KeyEvent.KEYCODE_ENTER:

                            changeOutput(input);

                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        //Listens to the big hash it button
        hashButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                changeOutput(input);
            }
        });

        return view;
    }

    public void changeOutput(EditText s) {
        String str;
        str = s.getText().toString();
        output.setText(md5(str));
    }//end changeoutput

    //private class for the change output method
    private String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }//end md5


}//end first fragment
