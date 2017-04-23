package com.najah.edu.fileuser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pranay.rxloginsignup.R;
import com.najah.edu.DatABasE.DB_helper;

public class edit_info extends AppCompatActivity implements View.OnClickListener {
    private DB_helper nn;
    boolean flag_first_name;
    boolean flag_last_name;
    boolean flag_email_name;
    boolean flag_password_name;
    boolean flag_confirm_name;
    boolean flag_phone_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        nn = new DB_helper (this);
        firstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (firstname.getText().length() == 0)
                    flag_first_name = false;
                else
                    flag_first_name = true;
            }
        });
    }
    // EditText
    private EditText firstname = (EditText)findViewById(R.id.EditFirstName);
    private EditText lastname = (EditText)findViewById(R.id.EditLastName);
    private EditText email = (EditText)findViewById(R.id.Edit_email);
    private EditText password = (EditText)findViewById(R.id.Edit_password);
    private EditText password_confirm = (EditText)findViewById(R.id.Edit_password_confirm);
    private EditText phone = (EditText)findViewById(R.id.Edit_phone);
    private CheckBox checkboxsure = (CheckBox)findViewById(R.id.checkbox_sure);
    private Button btnupdate = (Button)findViewById(R.id.btnupdate);

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnupdate){
            if (1==1) {
                showToast(getString(R.string.str_please_accept));
            } else {

                String fname = firstname.getText().toString().trim();
                String lname = lastname.getText().toString().trim();
                String emal = email.getText().toString().trim();
                String passd = password.getText().toString().trim();
                String phon = phone.getText().toString().trim();
                if ( nn.insert_to_table(emal,passd,fname,lname,phon) )
                    showToast(getString(R.string.str_update_success));
                else
                    showToast(getString(R.string.str_update_notsuccess));
            }
        }


    }

    

    private void showToast(String string) {
        Toast.makeText(getBaseContext(),string,Toast.LENGTH_LONG);
    }
}
