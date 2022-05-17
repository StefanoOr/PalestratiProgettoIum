package com.example.palestratiium.PersonalActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.palestratiium.Login;
import com.example.palestratiium.ModifyPassword;
import com.example.palestratiium.R;
import com.example.palestratiium.UserActivity.ProfiloUser;
import com.example.palestratiium.classi.PersonalTrainer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

public class ProfiloPersonalTrainer extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_PT = "package com.example.palestratiium";

    PersonalTrainer personal;
    Button logout;

    private TextView modifica, nome, eta, citta, username, team, instagram;
    private Spinner spinnerTitolo, spinnerCitta;

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int SELECT_IMAGE_CODE = 200;
    private static final int IMAGE_PICK_CAMERA_CODE = 200;

    private String[] cameraPermissions;

    private Uri imageUri;
    private String personalTitoloAttuale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo_personal_trainer);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_PT);

        if(obj instanceof PersonalTrainer){
            personal = (PersonalTrainer) obj;

        }else{
            personal = new PersonalTrainer();
        }

        logout=findViewById(R.id.logoutPT);
        modifica=findViewById(R.id.modify_password);

        spinnerTitolo = findViewById(R.id.titoli);

        nome=findViewById(R.id.nome_attuale);
        eta=findViewById(R.id.eta_pt);
        citta=findViewById(R.id.citta_attuale);
        username=findViewById(R.id.username_pt);
        team=findViewById(R.id.team_attuale);
        instagram=findViewById(R.id.insta_attuale);

        username.setText(personal.getUsername());
        nome.setText(personal.getNome());
        eta.setText(personal.getAge());
        citta.setText(personal.getCity());
        team.setText(personal.getTeam());
        instagram.setText(personal.getInstagram());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.titoli, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTitolo.setAdapter(adapter);
        spinnerTitolo.setOnItemSelectedListener(this);

        if(personal.getTitolo() != null){
            personalTitoloAttuale = personal.getTitolo();
        }
        else
            personalTitoloAttuale = "";

        String[] titoloAttualeArray = getResources().getStringArray(R.array.titoli);

        int titoliIndex = 0;

        for(int i = 0; i<titoloAttualeArray.length; i++){
            if(personalTitoloAttuale.equals(titoloAttualeArray[i]))
                titoliIndex = i;
        }

        spinnerTitolo.setSelection(titoliIndex);

        /*//permessi camera
        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        catch_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickDialog();
            }
        });*/

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.profilo);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent showResult;
                switch (item.getItemId()) {
                    case R.id.profilo:


                        return true;

                    case R.id.aggiungiVideo:

                        showResult = new Intent(ProfiloPersonalTrainer.this, PanelloDiUpload.class);
                        showResult.putExtra(EXTRA_PT, personal);
                        startActivity(showResult);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.menuHome:
                        showResult = new Intent(ProfiloPersonalTrainer.this, HomePersonalTrainer.class);
                        showResult.putExtra(EXTRA_PT, personal);
                        startActivity(showResult);
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personal = null;
                Intent login = new Intent(ProfiloPersonalTrainer.this, Login.class);
                login.putExtra(EXTRA_PT, personal);
                startActivity(login);
            }
        });


        modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showResult = new Intent(ProfiloPersonalTrainer.this, ModifyPassword.class);
                showResult.putExtra(EXTRA_PT, personal);
                startActivity(showResult);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        personal.setTitolo(spinnerTitolo.getSelectedItem().toString());
        Toast.makeText(this, "Titolo Aggiornato!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*private void imagePickDialog() {

        //opzioni per il dialog
        String[] options = {"Camera", "Galleria"};

        //dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleziona l'immagine da")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i==0){
                            //camera selezionata
                            if (!checkCameraPermission()) {
                                //permesso non dato, richiedilo
                                requestCameraPermission();
                            }else{
                                //permesso dato, fai il video
                                imagePickCamera();
                            }
                        }
                        else if (i==1){
                            //galleria selezionata
                            imagePickGallery();
                        }
                    }
                })
                .show();
    }

    private void imagePickGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleziona Imagine per l'icona"), SELECT_IMAGE_CODE);
    }

    private void imagePickCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, IMAGE_PICK_CAMERA_CODE);
    }

    private void requestCameraPermission(){
        //richiedi i permessi per la camera
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
    }

    private boolean checkCameraPermission(){
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        boolean result2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK) == PackageManager.PERMISSION_GRANTED;

        return result1 && result2;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        //chiamata dopo aver preso il video dalla camera/galleria
        if (resultCode == RESULT_OK) {
           if(requestCode==SELECT_IMAGE_CODE){
                Uri selectedImageUri = data.getData();
                if(null!=selectedImageUri){
                    image.setImageURI(selectedImageUri);
                    stringUriImage = selectedImageUri.toString();
                }

            }
        }
    }*/
}

