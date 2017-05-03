package com.example.tomas.mandatorycanteenapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText mAuthEmail;
    private EditText mAuthPassword;

    GestureDetector gesture;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gesture = new GestureDetector(this, this);

        SharedPreferences setting = getPreferences(MODE_PRIVATE);
        String prefferedEmail = setting.getString("Email", null);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuthEmail = (EditText) findViewById(R.id.emailView);
        mAuthEmail.setText(prefferedEmail);


        mAuthPassword = (EditText) findViewById(R.id.login_passwordView);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                {
                    if (user != null) {
                        //User is signed in
                        Log.d("TAG", "onAuthStateChanged:signed_in" + user.getUid());
                    } else {
                        //User is signed out
                        Log.d("TAG", "onAuthStateChanged:signed_out");
                    }
                }
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    public void createUser(String email, String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TAG", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "User Creation Failed!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "User Created!",
                                   Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
    public void signIn(String email, String password){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TAG", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, "User sign in failed!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        if  (task.isSuccessful()) {

                            Log.w("TAG", "signInWithEmail:succes", task.getException());
                            Toast.makeText(LoginActivity.this, "User have been sign in!",
                                    Toast.LENGTH_SHORT).show();


                        }



                        // ...
                    }
                });
    }
    public void LoginAct(View view){
        String email = mAuthEmail.getText().toString();
        String password = mAuthPassword.getText().toString();
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Email", mAuthEmail.getText().toString());
        editor.apply();

        signIn(email, password);
    }
    public void CreateUser(View view) {
        String email = mAuthEmail.getText().toString();
        String password = mAuthPassword.getText().toString();
        createUser(email,password);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gesture.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean leftSwipe = e1.getX() < e2.getX();
        Log.d("TAG", "onFling Left: " + leftSwipe);
        if (leftSwipe) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "leftFling",
                    Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
