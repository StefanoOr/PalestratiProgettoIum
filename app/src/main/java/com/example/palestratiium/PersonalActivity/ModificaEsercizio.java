package com.example.palestratiium.PersonalActivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
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
import com.example.palestratiium.classi.Esercizio;
import com.example.palestratiium.classi.MyEnum;
import com.example.palestratiium.classi.PersonalTrainer;
import com.example.palestratiium.classi.UserFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;

public class ModificaEsercizio extends AppCompatActivity implements Serializable, AdapterView.OnItemSelectedListener, View.OnClickListener{

    Esercizio esercizio = new Esercizio();
    PersonalTrainer personalTrainer;

    private EditText nomeEsercizio, descrizioneEsercizio;
    private EditText checkBoxErr;
    private VideoView videoView;
    private ImageView imageView, back_home;
    private Button confirmEdit, editImg;
    private FloatingActionButton selectVideoBtn;
    private Spinner seleziona_difficolta;
    private TextView difficoltaAttuale, muscoloAttuale;
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
    String nomeOriginale;
    boolean isPt;

    public static final String EXTRA_PT = "package com.example.palestratiium";

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_esercizio);

        isPt = false;

        final Intent intent = getIntent();
        Serializable objT = intent.getSerializableExtra(Login.EXTRA_PT);

        if(objT instanceof PersonalTrainer){
            personalTrainer = (PersonalTrainer) objT;
            isPt = true;
        }else{
            personalTrainer = new PersonalTrainer();
        }

       String name = getIntent().getStringExtra("NAME");

        final String descrizione = getIntent().getStringExtra("DESCRIPTION");
        final MyEnum gruppo = (MyEnum) intent.getSerializableExtra("GRUPPOMUSCOLARE");
        final String difficolta = getIntent().getStringExtra("DIFFICOLTA");
        String image = getIntent().getStringExtra("IMAGE");


        final String video = getIntent().getStringExtra("VIDEO");//video che abbiamo pubblicato noi a mano  TODO integer

        int videoDefault=intent.getExtras().getInt("VIDEODEFAULT");//video che è gia impostato TODO uri


        int imageDefault = intent.getExtras().getInt("IMAGEDAFAULT");





        nomeEsercizio = findViewById(R.id.title_edit_text_edit);
        descrizioneEsercizio = findViewById(R.id.descrizione_edit_text_edit);
        difficoltaAttuale = findViewById(R.id.difficolta_attuale);
        back_home = findViewById(R.id.back_home_modifica_esercizio);
        confirmEdit = findViewById(R.id.editVideoButton);
        selectVideoBtn = findViewById(R.id.select_video_button);
        checkBoxErr = findViewById(R.id.error_checkBox);
        imageView = findViewById(R.id.image_view);
        videoView = findViewById(R.id.videoView);
        editImg = findViewById(R.id.image_catch_edit);
        petto = findViewById(R.id.checkBox);
        dorso = findViewById(R.id.checkBox5);
        gambe = findViewById(R.id.checkBox4);
        tricipiti = findViewById(R.id.checkBox3);
        bicipiti = findViewById(R.id.checkBox6);
        spalle = findViewById(R.id.checkBox2);
        seleziona_difficolta = findViewById(R.id.difficolta_spinner);
        muscoloAttuale = findViewById(R.id.muscoli_attuali);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.difficolta, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seleziona_difficolta.setAdapter(adapter);
        seleziona_difficolta.setOnItemSelectedListener(this);




        nomeEsercizio.setText(name);
        descrizioneEsercizio.setText(descrizione);
        descrizioneEsercizio.setMovementMethod(new ScrollingMovementMethod());
        difficoltaAttuale.setText(difficolta);
        muscoloAttuale.setText(gruppo.name());

        nomeOriginale=nomeEsercizio.getText().toString();



        switch (gruppo){

            case PETTO:
                petto.setChecked(true);
                gruppoSelezionato=MyEnum.PETTO;

                break;
            case BICIPITI:
                bicipiti.setChecked(true);
                gruppoSelezionato=MyEnum.BICIPITI;
                break;
            case GAMBE:
                gambe.setChecked(true);
                gruppoSelezionato=MyEnum.GAMBE;
                break;
            case DORSO:
                dorso.setChecked(true);
                gruppoSelezionato=MyEnum.DORSO;
                break;
            case SPALLE:
                spalle.setChecked(true);
                gruppoSelezionato=MyEnum.SPALLE;
                break;
            case TRICIPITI:
                tricipiti.setChecked(true);
                gruppoSelezionato=MyEnum.TRICIPITI;
                break;



        }


        if(video != null) {
            setVideoToVideoView(video);
        }

        if(image!=null) {
            setImageToImageView(image);
        }

        if(imageDefault>0){
           // imageView.setImageResource(imageDefault);


            String uriPath="android.resource://"+ getPackageName()+"/"+  imageDefault ;
            Uri uri=Uri.parse(uriPath);
            imageView.setImageURI(uri);
            imageView.setTag(uri);

        }

        if(videoDefault>0) {
           setVideoToVideoViewDefault(videoDefault);
        }







        //permessi camera
        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        editImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickDialog();
            }
        });

        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showResult;
                showResult = new Intent(ModificaEsercizio.this, HomePersonalTrainer.class);
                showResult.putExtra(EXTRA_PT, personalTrainer);
                startActivity(showResult);
                overridePendingTransition(0, 0);
            }
        });

        selectVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPickDialog();
            }
        });

        confirmEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                esercizio.setNome(nomeEsercizio.getText().toString());
                esercizio.setDescrizioene(descrizioneEsercizio.getText().toString());
                esercizio.setDifficolta(seleziona_difficolta.getSelectedItem().toString());



                esercizio.setVideo(videoView.getTag().toString());
                esercizio.setImage(imageView.getTag().toString());
                //personalTrainer.addEsercizi(esercizio);
                esercizio.setGruppoMuscolare(gruppo);
                //TODO cambiare il modifica da quui
                UserFactory.getInstance().modifyEsercizio( nomeOriginale,personalTrainer,esercizio);
                Intent home = new Intent(ModificaEsercizio.this, HomePersonalTrainer.class);
                home.putExtra(EXTRA_PT, personalTrainer);
                startActivity(home);

                Context context = getApplicationContext();
                CharSequence text =  esercizio.getNome()+ " Esercizio modificato con successo";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void setVideoToVideoViewDefault(int videoDefault) {
        MediaController mediaController = new MediaController(this);


        String uriPath="android.resource://"+ getPackageName()+"/"+  videoDefault ;
        Uri uri=Uri.parse(uriPath);


        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.setTag(uri.toString());
        mediaController.setAnchorView(videoView);
        videoView.requestFocus();
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.pause();
            }
        });
    }

    private void setImageToImageView(String image) {
        Uri iUri = Uri.parse(image);
        imageView.setImageURI(iUri);
        imageView.setTag(iUri);
    }


    //funzione che prende la stringa e lo trasforma in uri e lo passa a  videovieww
    private void setVideoToVideoView(String v){
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        Uri vUri = Uri.parse(v);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(vUri);
        videoView.setTag(vUri.toString());
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.pause();
            }
        });
    }
}