package android.example.esm.researchmodule.questiontypes;

import android.content.Intent;
import android.example.esm.researchmodule.SurveyFormActivity;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.esm.R;
import android.widget.ImageView;
import android.widget.Toast;

//import com.kosalgeek.android.photoutil.CameraPhoto;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PhotoEntry extends Fragment {

    ImageView camera, photo;
    //CameraPhoto cameraPhoto;
    final int CAMERA_REQUEST = 12345;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String currentImagePath = null;
    private static final int IMAGE_REQUEST = 1;
    Uri imageUri;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview =  inflater.inflate(R.layout.fragment_photo_entry, container, false);

        //cameraPhoto = new CameraPhoto(getContext());
        camera = (ImageView)rootview.findViewById(R.id.imageview_camera);
        photo = (ImageView)rootview.findViewById(R.id.imageview_photo);

        int colorFilter = ContextCompat.getColor(getActivity(), R.color.colorPrimary);
        camera.setColorFilter(colorFilter, PorterDuff.Mode.SRC_IN);
        photo.setColorFilter(colorFilter, PorterDuff.Mode.SRC_IN);


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage(view);

               /* try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(),CAMERA_REQUEST);
                } catch (IOException e) {
                    Toast.makeText(getContext(), "Something wrong while taking photos", Toast.LENGTH_SHORT).show();
                    Log.d("error", e.getMessage());
                }*/

            }
        });


        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery(view);
            }
        });


        return rootview;


    }




    public void captureImage(View view){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null){
            File imageFile = null;

            try {
                imageFile = getImageFile();
            } catch (IOException e){
                e.printStackTrace();
            }
            if (imageFile != null){
                imageUri = FileProvider.getUriForFile(getActivity(), "com.example.android.fileprovider", imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(cameraIntent, IMAGE_REQUEST);
            }
        }
    }

    private File getImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "jpg_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File imageFile = File.createTempFile(imageName, ".jpg", storageDir);
        currentImagePath = imageFile.getAbsolutePath();
        return imageFile;
    }

    public void openGallery(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    public void getUserInput(){
        ((SurveyFormActivity) getActivity()).setUserInput("" + imageUri);
        Log.d("uri", ""+ imageUri);
    }






}
