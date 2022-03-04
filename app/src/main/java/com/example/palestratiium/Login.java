/*
    Corso IUM - AA 2019 - 2020
    65577 - Daniele Stochino
    Esercitazione Bonus
 */

package com.example.palestratiium;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.palestratiium.PersonalActivity.HomePersonalTrainer;
import com.example.palestratiium.UserActivity.Home;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import com.example.palestratiium.classi.PersonalTrainer;
import com.example.palestratiium.classi.User;
import com.example.palestratiium.classi.UserFactory;

public class Login extends AppCompatActivity {

    List<User> userList;
    List<PersonalTrainer> ptList;
    User user;
    PersonalTrainer personal;
    TextInputLayout username, password;
    Button signIn_button;
    TextView signup_text;
    boolean isPt,isUser;
    boolean isPasswordVisible = true;

    public static final String EXTRA_USER = "package com.example.palestratiium";
    public static final String EXTRA_PT = "package com.example.palestratiium";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = new User();
        personal =  new PersonalTrainer();

        isUser=false;
        isPt=false;

        username = findViewById(R.id.input_login_username);
        password = findViewById(R.id.input_login_password);
        signIn_button = findViewById(R.id.signin_button);
        signup_text = findViewById(R.id.signup_text);

        signIn_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (checkInput() && isUser) {
                Context context = getApplicationContext();
                CharSequence text = "Welcome Back " + username.getEditText().getText().toString();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Intent home = new Intent(Login.this, Home.class);
                home.putExtra(EXTRA_USER, user);
                startActivity(home);
            }else{
                if (checkInput() && isPt) {
                    Context context = getApplicationContext();
                    CharSequence text = "Welcome Back Personal Trainer " + username.getEditText().getText().toString();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Intent homept = new Intent(Login.this, HomePersonalTrainer.class);
                    homept.putExtra(EXTRA_PT, personal);
                    startActivity(homept);
                }
            }
         }
        });



        signup_text.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent showResult = new Intent(Login.this, SignUp.class);
            showResult.putExtra(EXTRA_USER, user);
            startActivity(showResult);
            }
        });

    }

    private boolean checkInput() {

        userList = UserFactory.getInstance().getUsers();
        ptList =  UserFactory.getInstance().getPersonal();

        int errors = 0;

        if(username.getEditText().getText().toString().length() == 0){  //the second condition is for the case where the user wrote and then erased all
            username.setError("Insert Username");
            errors++;
        }else{
            username.setError(null);
        }
        if(password.getEditText().getText().toString().length() == 0){  //the second condition is for the case where the user wrote and then erased all
            password.setError("Insert Password");
            errors++;
        }else{
            for (User u : userList) {
                if (u.getUsername().equals(username.getEditText().getText().toString()) && u.getPassword().equals(password.getEditText().getText().toString())){
                    user = u;
                    isUser=true;
                    return true;
                }
            }

            for (PersonalTrainer i : ptList) {
                if (i.getUsername().equals(username.getEditText().getText().toString()) && i.getPassword().equals(password.getEditText().getText().toString())){
                    personal = i;
                    isPt=true;
                    return true;
                }
            }

            errors++;
            username.setError("Insert Valid Username");
            password.setError("Insert Valid Password");
        }


        return (errors == 0);
    }

}
