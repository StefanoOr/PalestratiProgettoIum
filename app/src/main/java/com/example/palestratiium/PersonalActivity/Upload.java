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
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.palestratiium.Login;
import com.example.palestratiium.R;
import com.example.palestratiium.UserActivity.Profilo;
import com.example.palestratiium.classi.Esercizio;
import com.example.palestratiium.classi.PersonalTrainer;
import com.example.palestratiium.classi.UserFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;

public class Upload extends AppCompatActivity {

    Esercizio esercizio = new Esercizio();
   PersonalTrainer  personal;

    private EditText titleEt;
    private VideoView videoView;
    private Button uploadVideoBtn;
    private FloatingActionButton selectVideoBtn;

    private static final int VIDEO_PICK_GALLERY_CODE = 100;
    private static final int VIDEO_PICK_CAMERA_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 100;

    private String[] cameraPermissions;

    private Uri videoUri; //uri del video selezionato

    public static final String EXTRA_PT = "package com.example.palestratiium";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_PT);

        if(obj instanceof PersonalTrainer){
            personal = (PersonalTrainer) obj;

        }else{
            personal = new PersonalTrainer();
        }

        titleEt = findViewById(R.id.title_edit_text);
        videoView = findViewById(R.id.videoView);
        uploadVideoBtn = findViewById(R.id.uploadVideoButton);
        selectVideoBtn = findViewById(R.id.select_video_button);

        //permessi camera
        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        uploadVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                esercizio.setNome(titleEt.getText().toString());
                esercizio.setVideo(videoUri);
                personal.addEsercizi(esercizio);
                UserFactory.getInstance().addEsercizio(personal, esercizio);
                Intent ex = new Intent(Upload.this, HomePersonalTrainer.class);
                ex.putExtra(EXTRA_PT, personal);
                startActivity(ex);
            }
        });

        selectVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPickDialog();
            }
        });



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
        //chiamata dopo aver preso il video dalla camera/galleria
        if(resultCode == RESULT_OK){
            if(requestCode == VIDEO_PICK_GALLERY_CODE){
                videoUri = data.getData();
                //mostra il video selezionato nella VideoView
                setVideoToVideoView();
            }
            else if(requestCode == VIDEO_PICK_CAMERA_CODE){
                videoUri = data.getData();
                //mostra il video selezionato nella VideoView
                setVideoToVideoView();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}