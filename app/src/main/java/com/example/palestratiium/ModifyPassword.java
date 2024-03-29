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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.palestratiium.PersonalActivity.HomePersonalTrainer;
import com.example.palestratiium.PersonalActivity.ProfiloPersonalTrainer;
import com.example.palestratiium.UserActivity.Home;
import com.example.palestratiium.UserActivity.ProfiloUser;
import com.example.palestratiium.classi.PersonalTrainer;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.util.Objects;

import com.example.palestratiium.classi.User;
import com.example.palestratiium.classi.UserFactory;

public class ModifyPassword extends AppCompatActivity {

    TextView username, oldPass;
    TextInputLayout newpass, newpassconf;
    Button modify;
    User user;
    PersonalTrainer personalTrainer;
    private ImageView back;
    boolean isPasswordVisibleNEW, isPasswordVisibleCONFIRM;
    boolean isPt,isUser;

    private TextView toolbar_title;

    public static final String EXTRA_USER = "package com.example.palestratiium";
    public static final String EXTRA_PT = "package com.example.palestratiium";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);

        username = findViewById(R.id.attributeUsername);
        oldPass = findViewById(R.id.attributeOldPassword);
        newpass = findViewById(R.id.input_passwordNew);
        newpassconf = findViewById(R.id.input_passwordNewConfirm);
        modify = findViewById(R.id.input_button_signUp);
        back = findViewById(R.id.back_toolbar);
        toolbar_title = findViewById(R.id.toolbartag);
        isPasswordVisibleNEW = true;
        isPasswordVisibleCONFIRM = true;

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);
        Serializable objT = intent.getSerializableExtra(Login.EXTRA_PT);

        if(obj instanceof User){
            user = (User) obj;
            isUser=true;
            username.setText(user.getUsername());
            oldPass.setText(user.getPassword());
        }else{
            user = new User();
        }


        if(objT instanceof PersonalTrainer){
            personalTrainer = (PersonalTrainer) objT;
            isPt=true;
            username.setText(personalTrainer.getUsername());
            oldPass.setText(personalTrainer.getPassword());
        }else{
            personalTrainer = new PersonalTrainer();
        }

        toolbar_title.setText("Modifica Password");

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkInput() && isUser) {

                    user.setPassword(Objects.requireNonNull(newpass.getEditText()).getText().toString());
                    UserFactory.getInstance().modifyPass(user);
                    Intent home = new Intent(ModifyPassword.this, ProfiloUser.class);
                    home.putExtra(EXTRA_USER, user);

                    Context context = getApplicationContext();
                    CharSequence text = "Password Updated";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    startActivity(home);
                }else if(checkInput() && isPt){

                    personalTrainer.setPassword(Objects.requireNonNull(newpass.getEditText()).getText().toString());
                    UserFactory.getInstance().modifyPassPersonal(personalTrainer);
                    Intent home = new Intent(ModifyPassword.this, ProfiloPersonalTrainer.class);
                    home.putExtra(EXTRA_PT, personalTrainer);

                    Context context = getApplicationContext();
                    CharSequence text = "Password Updated Personal";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    startActivity(home);
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUser){
                Intent home = new Intent(ModifyPassword.this, ProfiloUser.class);
                home.putExtra(EXTRA_USER, user);
                startActivity(home);
            }else if(isPt){
                    Intent homePt = new Intent(ModifyPassword.this, ProfiloPersonalTrainer.class);
                    homePt.putExtra(EXTRA_PT, personalTrainer);
                    startActivity(homePt);
                }
            }

        });
    }

    private boolean checkInput() {

        //how many error occurred? We need to save the number
        int errors = 0;

        if((Objects.requireNonNull(newpass.getEditText()).getText() == null) || (newpass.getEditText().getText().length() == 0)){
            newpass.setError("Insert Password");
            errors++;
        }else{
            newpass.setError(null);
        }

        if((Objects.requireNonNull(newpassconf.getEditText()).getText() == null) || (newpassconf.getEditText().getText().length() == 0)){
            newpassconf.setError("Insert matching Password");
            errors++;
        }else{
            newpassconf.setError(null);
        }

        if(newpass.getEditText().getText().toString().equals(newpassconf.getEditText().getText().toString())){
            newpassconf.setError(null);
        }else{
            newpassconf.setError("Passowords does not Match");
            errors++;
            //hello
        }

        if(newpass.getEditText().getText().toString().equals(oldPass.getText().toString())){
            newpass.setError("Cannot be equal to actual Password");
            errors++;
        }

        return (errors == 0);
    }
}
