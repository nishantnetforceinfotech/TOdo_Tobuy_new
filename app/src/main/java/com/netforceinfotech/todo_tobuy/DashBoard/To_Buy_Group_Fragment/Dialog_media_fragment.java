package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.netforceinfotech.todo_tobuy.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by abcd on 8/30/2016.
 */
public class Dialog_media_fragment extends DialogFragment {


    ImageView camera_call, by_gallery;
    static final int TAKE_PHOTO_GALLERY = 1;
    private final int REQUEST_CAMERA = 2;

    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_SMS, Manifest.permission.CAMERA};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if(!hasPermissions(getActivity(), PERMISSIONS)){
//            ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_ALL);
//        }

        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = RecyclerView.LayoutParams.WRAP_CONTENT;
        params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        View rootView = inflater.inflate(R.layout.dialog_media_fragment, container, false);


        camera_call = (ImageView) rootView.findViewById(R.id.imageView27);
        by_gallery = (ImageView) rootView.findViewById(R.id.imageView24);

        clickEvent();

        return rootView;
    }

    private void clickEvent(){

        camera_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cameraIntent();

            }
        });

        by_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                galleryIntent();
            }
        });
    }

 private void galleryIntent(){

     Intent in = new Intent();
     in.setType("image/*");
     in.setAction(Intent.ACTION_GET_CONTENT);
     startActivityForResult(Intent.createChooser(in, "Select File"), TAKE_PHOTO_GALLERY);
 }

    private void cameraIntent(){

//        Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(in,REQUEST_CAMERA);



        if(!hasPermissions(getActivity(), PERMISSIONS)){
            ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_ALL);
            Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(in, REQUEST_CAMERA);
        }
        else{
            Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(in, REQUEST_CAMERA);
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){

            if(requestCode == TAKE_PHOTO_GALLERY){

                onSelectFromGalleryResult(data);

            }else if(requestCode == REQUEST_CAMERA){

                onCaptureImageResult(data);
            }
        }
    }


    private void onCaptureImageResult(Intent data) {

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File mydir = new File(Environment.getExternalStorageDirectory().getPath());
        if(!mydir.exists())
        {  mydir.mkdirs();
            Log.e("working 1", "dir. already exists");
            setimage(mydir,thumbnail,bytes);
        }
        else {
            Log.e("working 2", "dir. already exists");
            Log.e("error", "dir. already exists");
            setimage(mydir,thumbnail,bytes);

//            File destination = new File(mydir,
//                    System.currentTimeMillis()+".jpg");
//            Log.e("destination_file", destination.getAbsolutePath());
//            FileOutputStream fo;
//            try {
//                destination.createNewFile();
//                fo = new FileOutputStream(destination);
//                fo.write(bytes.toByteArray());
//                fo.close();
//            } catch (FileNotFoundException e) {
//                Log.e("FileNot_dialog_media", e.toString());
//            } catch (IOException e) {
//                Log.e("IOExce_dialog_media", e.toString());
//            }
//
//            Group_Fragment_tobuy.item_image = thumbnail;
//            Group_Fragment_tobuy.item_file = destination;
//            getDialog().cancel();
        }
        //ivImage.setImageBitmap(thumbnail);
    }

    private void setimage(File mydir, Bitmap thumbnail, ByteArrayOutputStream bytes) {

        File destination = new File(mydir,
                System.currentTimeMillis()+".jpg");
        Log.e("destination_file", destination.getAbsolutePath());
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination.getAbsoluteFile());
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            Log.e("FileNot_dialog_media", e.toString());
        } catch (IOException e) {
            Log.e("IOExce_dialog_media", e.toString());
        }

        Group_Fragment_tobuy.item_image = thumbnail;
        Group_Fragment_tobuy.item_file = destination;
        getDialog().cancel();

    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());

            } catch (IOException e) {
               Log.e("IOException",e.toString());
            }
        }

//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//
//        File mydir = new File(Environment.getExternalStorageDirectory() + "Todo-ToBuy/To-Buy/");
//        if(!mydir.exists())
//            mydir.mkdirs();
//        else
//            Log.d("error", "dir. already exists");
//
//        File destination = new File(mydir.toString(),
//                System.currentTimeMillis() + ".jpg");
//
//        FileOutputStream fo;
//        try {
//            destination.createNewFile();
//            fo = new FileOutputStream(destination);
//            fo.write(bytes.toByteArray());
//            fo.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Group_Fragment_tobuy.item_image = bm;
        Uri tempUri = getImageUri(getActivity().getApplicationContext(), bm);

        // CALL THIS METHOD TO GET THE ACTUAL PATH
        File finalFile = new File(getRealPathFromURI(tempUri));
        Group_Fragment_tobuy.item_file = finalFile;
        Log.e(" Group_Fragm", Group_Fragment_tobuy.item_file.getAbsolutePath());
        getDialog().cancel();

        //ivImage.setImageBitmap(bm);
    }



    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}
