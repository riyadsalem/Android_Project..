package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

@SuppressWarnings("ALL")
public class LogIn extends AppCompatActivity {

  public   EditText  userEmail = null;
  public   EditText  pass = null;
  public ProgressDialog progressDialog = null;
  private FirebaseAuth firAuth = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        userEmail = findViewById(R.id.edtEmailLogin);
        pass = findViewById(R.id.edtPassLogin);

        firAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait... ");
        progressDialog.setCancelable(true);

    }

    public void DoLogIN(View view) {

        String email = userEmail.getText().toString();
        String password = pass.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Log.d("Email" , "Error");
            userEmail.setError("Enter your Email  (\\__/) ");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            pass.setError("Enter your Password  (\\__/) ");
            return;
        }
        if (!(password.length() >= 4)) {
            Log.d("Password" , "Error");
            pass.setError("Password must be at least 4  __(><)__");

        }

        progressDialog.show();

        firAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.hide();
                    Toast.makeText(LogIn.this , "Successfully loggedIn", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Lists.class);
                    startActivity(intent);

                }else {
                    progressDialog.hide();
                    Toast.makeText(LogIn.this , "Error ..../ \n" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("LogIn"+task.getException().getMessage(),"Error");

                }

            }
        });


    }


    public void GoToSignUp(View view) {
        startActivity( new Intent(getApplicationContext(),SignUp.class));
    }

}







  /*  public void toBackToSignUp(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUp.class);
        startActivity(intent);
    }

   */
