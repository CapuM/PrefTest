package com.example.matteo.preftest;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView register;
    private TextView login;
    private EditText emailEdit;
    private EditText password;
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    private String mail;
    private String pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.register_button);
        login = findViewById(R.id.login_button);
        emailEdit = findViewById(R.id.email);
        password = findViewById(R.id.pass);

        mAuth = FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = emailEdit.getText().toString();
                pass = password.getText().toString();
                controllFields();
                createAccount(mail, pass);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = emailEdit.getText().toString();
                pass = password.getText().toString();
                controllFields();
                LogAccount(mail, pass);
            }
        });
    }

    private void updateUIOnRegistration(FirebaseUser user) {
        if (user != null) {
            WelcomeFragment fragment2 = new WelcomeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragger, fragment2);
            // fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
            //       android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.commit();
        } else {
            Toast.makeText(this, "errore",
                    Toast.LENGTH_SHORT).show();

        }
    }

    private void updateUIOnLogin(FirebaseUser user) {
        if (user != null) {
            SuccessFragment fragment2 = new SuccessFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragger, fragment2);
            // fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
            //       android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.commit();
        }

    }

    private void controllFields() {
        if (TextUtils.isEmpty(mail)) {
            Toast.makeText(this, "inserisci mail", Toast.LENGTH_LONG).show();
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "inserisci pass", Toast.LENGTH_LONG).show();
        }
    }

    private void saveData() {
        SharedPreferences.Editor editor = this.getSharedPreferences("shared", Context.MODE_PRIVATE).edit();
        editor.putString("email", mail);
        editor.apply();
    }

    private void createAccount(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).
                addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            saveData();

                            firebaseUser = mAuth.getCurrentUser();
                            updateUIOnRegistration(firebaseUser);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "firebase error",
                                    Toast.LENGTH_SHORT).show();
                            updateUIOnRegistration(null);
                        }
                    }
                });
    }

    private void LogAccount(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success");
                    saveData();
                    firebaseUser = mAuth.getCurrentUser();
                    updateUIOnLogin(firebaseUser);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(MainActivity.this, "firebase error",
                            Toast.LENGTH_SHORT).show();
                    updateUIOnLogin(null);
                }
            }


        });


    }
   /* @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUIOnLogin(currentUser);
    }*/
}