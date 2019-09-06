package com.example.ca1b11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class signup extends AppCompatActivity {
    EditText e3,e4,e5,e6;
    Button b4;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        b4=findViewById(R.id.button3);
        e3=findViewById(R.id.editText3);
        e4=findViewById(R.id.editText4);
        e5=findViewById(R.id.editText4);
        firebaseAuth=FirebaseAuth.getInstance();
        //if(isEmailValid(email==true))
        //{
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=e4.getText().toString();
                String pass=e5.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Intent i2=new Intent(signup.this,MainActivity.class);
                            startActivity(i2);
                            Toast.makeText(getApplicationContext(), "acc created", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException)
                            {
                                Toast.makeText(signup.this, "user already exists", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(signup.this, "check connectivity", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }
   /*public boolean isEmailValid(String email)
   {
       String pattern="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
       String Matcher="";
       Matcher.matches(pattern)
       return true;
   }*/
}
