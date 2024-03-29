/*
    Corso IUM - AA 2019 - 2020
    65577 - Daniele Stochino
    Esercitazione Bonus
 */

package com.example.palestratiium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.palestratiium.UserActivity.Home;
import com.example.palestratiium.classi.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import com.example.palestratiium.classi.User;
import com.example.palestratiium.classi.UserFactory;

public class SignUp extends AppCompatActivity {

    User user = new User();
    TextInputLayout username, city, password, passwordConf;
    TextView date;
    Button signup_button;
    private ImageView back;
    private TextView toolbar_title;
    DatePickerFragment datePickerFragment = new DatePickerFragment();
    boolean isPasswordVisibleNEW, isPasswordVisibleCONFIRM;

    public static final String EXTRA_USER = "package com.example.palestratiium";
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.input_signup_username);
        password = findViewById(R.id.input_signup_password);
        passwordConf = findViewById(R.id.input_signup_passwordConfirm);
        city = findViewById(R.id.input_signup_city);
        date = findViewById(R.id.input_signup_date);
        date.setInputType(InputType.TYPE_NULL);
        signup_button = findViewById(R.id.input_button_signUp);
        back = findViewById(R.id.back_toolbar);
        toolbar_title = findViewById(R.id.toolbartag);
        isPasswordVisibleNEW = true;
        isPasswordVisibleCONFIRM = true;

        toolbar_title.setText("Registrazione");

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // here we want to show our datepickerFragment
                datePickerFragment.show(getSupportFragmentManager(), "date picker");
            }
        });

        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    datePickerFragment.show(getSupportFragmentManager(), "date picker");
                }
            }
        });

        datePickerFragment.setOnDatePickerFragmentChanged(new DatePickerFragment.DatePickerFragmentListener() {
            @Override
            public void onDatePickerFragmentOkButton(DialogFragment dialog, Calendar dateX) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                date.setText(format.format(dateX.getTime()));
            }

            @Override
            public void onDatePickerFragmentCancelButton(DialogFragment dialog) {

            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {

                    Context context = getApplicationContext();
                    CharSequence text = "Welcome " + Objects.requireNonNull(username.getEditText()).getText().toString();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    user.setUsername(username.getEditText().getText().toString());
                    user.setPassword(Objects.requireNonNull(password.getEditText()).getText().toString());
                    user.setCity(Objects.requireNonNull(city.getEditText()).getText().toString());
                    user.setDate(datePickerFragment.getDate());

                    UserFactory.getInstance().addUsers(user);
                    Intent home = new Intent(SignUp.this, Home.class);
                    home.putExtra(EXTRA_USER, user);
                    startActivity(home);
                }else{
                    //TODO raise expection for not matching passwords
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(SignUp.this, Login.class);
                home.putExtra(EXTRA_USER, user);
                startActivity(home);
            }
        });


    }

    private boolean checkInput() {

        //how many error occurred? We need to save the number
        int errors = 0;

        if(username.getEditText().getText() == null || username.getEditText().getText().length() == 0){
            username.setError("Insert UserName");
            errors++;
        }else{
            username.setError(null);
        }

        if(password.getEditText().getText() == null || password.getEditText().getText().length() == 0){
            password.setError("Insert Password");
            errors++;
        }else{
            password.setError(null);
        }

        if(passwordConf.getEditText().getText() == null || passwordConf.getEditText().getText().length() == 0 ){
            passwordConf.setError("Passowrd does not Match");
            errors++;
        }else{
            passwordConf.setError(null);
        }

        if( password.getEditText().getText().toString().equals(passwordConf.getEditText().getText().toString())) {
            passwordConf.setError(null);
        }else{
            passwordConf.setError("Password must Match");
            errors++;
        }

        if(city.getEditText().getText() == null || city.getEditText().getText().length() == 0 ){
            city.setError("Insert City");
            errors++;
        }else{
            city.setError(null);
        }

        if(date.getText() == null || date.getText().length() == 0){
            date.setError("Insert Birthday");
            errors++;
        }else{
            date.setError(null);
        }

        List<User> userList = UserFactory.getInstance().getUsers();
        for (User u: userList) {
            if ( u.getUsername().equals( username.getEditText().getText().toString() ) && u.getPassword().equals( password.getEditText().getText().toString() ) ) {
                errors++;
                username.setError("Username already Exist");
            }
        }

        return (errors == 0);
    }
}
