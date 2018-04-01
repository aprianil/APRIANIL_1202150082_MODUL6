package dikampus.com.aprianil_1202150082_modul6;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by MOTION-2 on 01/04/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    //declare variable
    TextInputLayout mEmail, mPassword;
    Button mSignIn, mSignUp;
    String email, password;
    //declare instance
    FirebaseAuth mAuth;
    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initialize instance
        mAuth = FirebaseAuth.getInstance();
        //bind data
        mEmail = findViewById(R.id.email_login);
        mPassword = findViewById(R.id.pass_login);
        mSignIn = findViewById(R.id.btn_login);
        mSignUp = findViewById(R.id.btn_signup);

        databaseUser = FirebaseDatabase.getInstance().getReference(MainActivity.table_3);
        //set on click button signin
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mengambil isi field form
                getTextField();
                if (validateForm()){
                    //panggil method login
                    login(email, password);
                }else {
                    //toast pesan
                    toastMessage("Please fill the form field");
                }
            }
        });
        //set on click button signup
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mengambil isi field form
                getTextField();
                if (validateForm()){
                    signUp(email, password);
                }else {
                    toastMessage("Please fill the form field");
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //mengambil current user aktif
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //cek current user ada atau tidak
        if (currentUser != null){
            moveToMain();
        }
    }
    //method signup
    private void signUp(final String email, String pass){
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //jika auth berhasil
                        if (task.isSuccessful()){
                            String id = mAuth.getCurrentUser().getUid();
                            String[] username = email.split("@");
                            User user = new User(id, username[0], email);
                            databaseUser.child(id).setValue(user);
                            moveToMain();
                        }else {
                            //toast pesan
                            toastMessage("Something went wrong");
                        }
                    }
                }
        );
    }
    //method login
    private void login(String email, String pass){
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            moveToMain();
                        }else {
                            //toast pesan
                            toastMessage("Account not yet registered");
                        }
                    }
                }
        );
    }

    private boolean validateForm(){
        //cek apakah field email atau password kosong
        if (email.isEmpty() || password.isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    //method get text
    private void getTextField(){
        email = mEmail.getEditText().getText().toString();
        password = mPassword.getEditText().getText().toString();
    }
    //method intent to main activity
    private void moveToMain(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    //method toast pesan
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    
}

