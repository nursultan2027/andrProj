package com.androidtutorialshub.loginregister.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.sql.DbCon;


public class ShowDetails extends Activity {

    EditText etNameDeatil, etSurnameDetail,etPhoneDe1tail;
    Button btnDelete, btnUpdate;
    DbCon dbCon = new DbCon(this);
    String id,name,surname, phone, owner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdetails);
        etNameDeatil = (EditText) findViewById(R.id.etNameDetail);
        etSurnameDetail = (EditText) findViewById(R.id.etSurnameDetail);
        etPhoneDe1tail = (EditText) findViewById(R.id.etPhoneDetail);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        Intent intent = getIntent();
        name = intent.getStringExtra("sendName");
        surname = intent.getStringExtra("sendSurname");
        id = intent.getStringExtra("sendId");
        phone = intent.getStringExtra("sendPhone");
        etNameDeatil.setText(name);
        etSurnameDetail.setText(surname);
        etPhoneDe1tail.setText(phone);
        owner = intent.getStringExtra("EMAIL");
        CheckOwner();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbCon.delete(Long.parseLong(id));
                returnHome();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbCon.update(Long.parseLong(id),etNameDeatil.getText().toString(),etSurnameDetail.getText().toString(), etPhoneDe1tail.getText().toString());
                returnHome();
            }
        });

    }

    public void returnHome(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void CheckOwner(){
        if (owner.length()!=phone.length()){
            btnUpdate.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
        }
    }

}
