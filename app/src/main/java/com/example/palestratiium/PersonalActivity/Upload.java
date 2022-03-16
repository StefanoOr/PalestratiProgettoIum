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
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.palestratiium.Login;
import com.example.palestratiium.R;
import com.example.palestratiium.UserActivity.Profilo;
import com.example.palestratiium.classi.Esercizio;
import com.example.palestratiium.classi.MyEnum;
import com.example.palestratiium.classi.PersonalTrainer;
import com.example.palestratiium.classi.User;
import com.example.palestratiium.classi.UserFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.List;

public class Upload extends AppCompatActivity implements Serializable, AdapterView.OnItemSelectedListener, View.OnClickListener {

    Esercizio esercizio = new Esercizio();
    PersonalTrainer  personal;

    private EditText videoErr, checkBoxErr;
    private EditText titleEt, descrizioneEt;
    private VideoView videoView;
    private ImageView imageView;
    private Button uploadVideoBtn, uploadImageBtn;
    private FloatingActionButton selectVideoBtn;
    private Spinner seleziona_difficolta;
    private CheckBox petto, gambe, bicipiti, dorso, tricipiti, spalle;
    private MyEnum gruppoSelezionato = null;


    private static final int VIDEO_PICK_GALLERY_CODE = 100;
    private static final int VIDEO_PICK_CAMERA_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int SELECT_IMAGE_CODE = 200;
    private static final int IMAGE_PICK_CAMERA_CODE = 200;

    private String[] cameraPermissions;

    private Uri imageUri;
    private Uri videoUri; //uri del video selezionato
    private String stringUriVideo, stringUriImage;

    public static final String EXTRA_PT = "package com.example.palestratiium";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        final Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_PT);

        if(obj instanceof PersonalTrainer){
            personal = (PersonalTrainer) obj;

        }else{
            personal = new PersonalTrainer();
        }

        checkBoxErr = findViewById(R.id.error_checkBox);
        videoErr = findViewById(R.id.error_video);
        titleEt = findViewById(R.id.title_edit_text);
        descrizioneEt = findViewById(R.id.descrizione_edit_text);
        imageView = findViewById(R.id.image_view);
        videoView = findViewById(R.id.videoView);
        uploadImageBtn = findViewById(R.id.image_catch);
        uploadVideoBtn = findViewById(R.id.uploadVideoButton);
        selectVideoBtn = findViewById(R.id.select_video_button);
        seleziona_difficolta = findViewById(R.id.difficolta_spinner);
        petto = findViewById(R.id.checkBox);
        dorso = findViewById(R.id.checkBox5);
        gambe = findViewById(R.id.checkBox4);
        tricipiti = findViewById(R.id.checkBox3);
        bicipiti = findViewById(R.id.checkBox6);
        spalle = findViewById(R.id.checkBox2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.difficolta, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seleziona_difficolta.setAdapter(adapter);
        seleziona_difficolta.setOnItemSelectedListener(this);

        petto.setOnClickListener(this);
        spalle.setOnClickListener(this);
        gambe.setOnClickListener(this);
        bicipiti.setOnClickListener(this);
        tricipiti.setOnClickListener(this);
        dorso.setOnClickListener(this);

        //permessi camera
        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        uploadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickDialog();
            }
        });

        uploadVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkInput()) {
                    esercizio.setNome(titleEt.getText().toString());
                    esercizio.setVideo(stringUriVideo);
                    esercizio.setImage(stringUriImage);
                    esercizio.setDescrizioene(descrizioneEt.getText().toString());
                    esercizio.setDifficolta(seleziona_difficolta.getSelectedItem().toString());
                    esercizio.setGruppoMuscolare(gruppoSelezionato);
                    personal.addEsercizi(esercizio);
                    UserFactory.getInstance().addEsercizio(personal, esercizio);
                    Intent ex = new Intent(Upload.this, HomePersonalTrainer.class);
                    ex.putExtra(EXTRA_PT, personal);
                    startActivity(ex);
                }
            }
        });

        selectVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPickDialog();
            }
        });

    }

    private boolean checkInput() {

        //how many error occurred? We need to save the number
        int errors = 0;

        if(titleEt.getText().toString() == null || titleEt.getText().length() == 0){
            titleEt.setError("Perfavore inserisci un titolo");
            errors++;
        }else{
            titleEt.setError(null);
        }

        if(descrizioneEt.getText().toString() == null || descrizioneEt.getText().length() == 0){
            descrizioneEt.setError("Perfavore inserisci una descrizione");
            errors++;
        }else{
            descrizioneEt.setError(null);
        }

        if(videoUri == null){
            videoErr.setVisibility(View.VISIBLE);
            videoErr.setError("Perfavore inserisci un video");
            errors++;
        }else{
            videoErr.setError(null);
        }

        //TODO
        if(!petto.isChecked() && !gambe.isChecked() && !dorso.isChecked() && !bicipiti.isChecked() && !tricipiti.isChecked() && !spalle.isChecked()){
            checkBoxErr.setVisibility(View.VISIBLE);
            checkBoxErr.setError("Perfavore inserisci un gruppo muscolare");
            errors++;
        }else{
            checkBoxErr.setError(null);
        }
        //TODO

        return (errors == 0);
    }

    private void imagePickDialog() {
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

    private void videoPickDialog() {
        //opzioni per il dialog
        String[] options = {"Camera", "Galleria"};

        //dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleziona il video da")
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
                                videoPickCamera();
                            }
                        }
                        else if (i==1){
                            //galleria selezionata
                            videoPickGallery();
                        }
                    }
                })
                .show();
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

    private void videoPickGallery(){
        //pick video from gallery - intent

        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleziona Video"), VIDEO_PICK_GALLERY_CODE);
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

    private void videoPickCamera(){
        //pick video from camera - intent
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, VIDEO_PICK_CAMERA_CODE);
    }

    private void setVideoToVideoView(){
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(videoUri);
        videoView.requestFocus();
        videoErr.setVisibility(View.INVISIBLE);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.pause();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CAMERA_REQUEST_CODE:
                if(grantResults.length > 0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted && storageAccepted){
                        //entrambe concesse
                        videoPickCamera();
                    }
                    else{
                        //entrambe o una non sono concesse
                        Toast.makeText(this, "Sono richiesti i permessi per la camera e la galleria", Toast.LENGTH_SHORT).show();
                    }
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        //chiamata dopo aver preso il video dalla camera/galleria
        if (resultCode == RESULT_OK) {
            if (requestCode == VIDEO_PICK_GALLERY_CODE) {
                videoUri = data.getData();
                //mostra il video selezionato nella VideoView
                setVideoToVideoView();
                stringUriVideo = videoUri.toString();
            } else if (requestCode == VIDEO_PICK_CAMERA_CODE) {
                videoUri = data.getData();
                //mostra il video selezionato nella VideoView
                setVideoToVideoView();
                videoErr.setVisibility(View.INVISIBLE);
                stringUriVideo = videoUri.toString();
            } else if(requestCode==SELECT_IMAGE_CODE){
                Uri selectedImageUri = data.getData();
                if(null!=selectedImageUri){
                    imageView.setImageURI(selectedImageUri);
                    stringUriImage = selectedImageUri.toString();
                }

            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.checkBox:
                if (petto.isChecked())
                    Toast.makeText(getApplicationContext(), "Petto", Toast.LENGTH_LONG).show();
                gruppoSelezionato = MyEnum.PETTO;
                checkBoxErr.setVisibility(View.INVISIBLE);
                break;
            case R.id.checkBox2:
                if (spalle.isChecked())
                    Toast.makeText(getApplicationContext(), "Spalle", Toast.LENGTH_LONG).show();
                gruppoSelezionato = MyEnum.SPALLE;
                checkBoxErr.setVisibility(View.INVISIBLE);

                break;
            case R.id.checkBox3:
                if (tricipiti.isChecked())
                    Toast.makeText(getApplicationContext(), "Tricipiti", Toast.LENGTH_LONG).show();
                gruppoSelezionato = MyEnum.TRICIPITI;
                checkBoxErr.setVisibility(View.INVISIBLE);
                break;
            case R.id.checkBox4:
                if (gambe.isChecked())
                    Toast.makeText(getApplicationContext(), "Gambe", Toast.LENGTH_LONG).show();
                gruppoSelezionato = MyEnum.GAMBE;
                checkBoxErr.setVisibility(View.INVISIBLE);
                break;
            case R.id.checkBox5:
                if (dorso.isChecked())
                    Toast.makeText(getApplicationContext(), "Dorso", Toast.LENGTH_LONG).show();
                gruppoSelezionato = MyEnum.DORSO;
                checkBoxErr.setVisibility(View.INVISIBLE);
                break;
            case R.id.checkBox6:
                if (bicipiti.isChecked())
                    Toast.makeText(getApplicationContext(), "Bicipiti", Toast.LENGTH_LONG).show();
                gruppoSelezionato = MyEnum.BICIPITI;
                checkBoxErr.setVisibility(View.INVISIBLE);
                break;
        }

    }

}