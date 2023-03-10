package com.example.selectordialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SelectorDialog extends Dialog {

    TextView txtTitle;
    Button btnSet;
    String [] options;
    LinearLayout mainContainer;
    Context context;
    String title;

    ArrayList<CheckBox> chkOptions = new ArrayList<CheckBox>();

    public interface OnOptionsClickListener{
        void OnOptionsSet(ArrayList<String> selectedOptions);
    }

    private OnOptionsClickListener onOptionsClickListener;

    public void setOnOptionsClickListener(OnOptionsClickListener onOptionsClickListener){
        this.onOptionsClickListener = onOptionsClickListener;
    }

    public SelectorDialog(@NonNull Context context, String [] options) {
        super(context);
        this.context = context;
        this.options = options;
        setContentView(R.layout.selector_dialog);

        initViews();
        initListeners();
    }

    private void initViews(){
        txtTitle = findViewById(R.id.txtTitle);
        btnSet = findViewById(R.id.btnSet);
        mainContainer = findViewById(R.id.mainContainer);

        for(int i = 0; i < options.length;i++){
            CheckBox chkOption = new CheckBox(context);

            chkOption.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));

            chkOption.setText(options[i]);
            chkOptions.add(chkOption);
            mainContainer.addView(chkOption);
        }
    }

    private void initListeners(){
        btnSet.setOnClickListener(new BtnSetClickListener());

    }

    class BtnSetClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(onOptionsClickListener == null){
                return;
            }

            ArrayList<String> selectedOptions = new ArrayList<>();
            for(CheckBox chkOption : chkOptions){
                if(chkOption.isChecked()){
                    selectedOptions.add(chkOption.getText().toString());
                }
            }
            onOptionsClickListener.OnOptionsSet(selectedOptions);
            dismiss();
        }
    }

    public void setTitle(String title){
        this.title = title;
        txtTitle.setText(title);
    }
}
