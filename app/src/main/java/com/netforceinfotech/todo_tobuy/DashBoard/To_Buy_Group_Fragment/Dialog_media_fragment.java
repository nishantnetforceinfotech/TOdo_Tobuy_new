package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment;

import android.Manifest;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
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
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.netforceinfotech.todo_tobuy.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by abcd on 8/30/2016.
 */
public class Dialog_media_fragment extends DialogFragment implements View.OnClickListener {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    ImageView camera_call, by_gallery;
    static String IMAGE_DIRECTORY_NAME = "tobuy";
    static final int MEDIA_TYPE_IMAGE = 1;

    static final int PICK_IMAGE = 1;
    static final int TAKE_PHOTO_CODE = 2;
    private final int REQUEST_CAMERA = 1;
    Uri fileuri;
    Context c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = RecyclerView.LayoutParams.WRAP_CONTENT;
        params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        View rootView = inflater.inflate(R.layout.dialog_media_fragment, container, false);
        c = getActivity();
        camera_call = (ImageView) rootView.findViewById(R.id.imageView27);
        by_gallery = (ImageView) rootView.findViewById(R.id.imageView24);
        camera_call.setOnClickListener(this);
        by_gallery.setOnClickListener(this);


        // Do something else
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.imageView27:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    getPermission();
                    Picture_click_Intent();
//                    int permission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//                    if (permission != PackageManager.PERMISSION_GRANTED) {
//                        // We don't have permission so prompt the user
//                        ActivityCompat.requestPermissions(
//                                getActivity(),
//                                PERMISSIONS_STORAGE,
//                                REQUEST_EXTERNAL_STORAGE
//                        );
//                        Picture_click_Intent();
//                    } else {
//                        Picture_click_Intent();
//                    }
                }
                //verifyStoragePermissions(getActivity());


                //camera event

                break;
            case R.id.imageView24:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    getPermission();
                    getbygallery();}
//                int permission2 = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//                if (permission2 != PackageManager.PERMISSION_GRANTED) {
//                    // We don't have permission so prompt the user
//                    ActivityCompat.requestPermissions(
//                            getActivity(),
//                            PERMISSIONS_STORAGE,
//                            REQUEST_EXTERNAL_STORAGE
//                    );
//                    getbygallery();
//                } else {
//                    getbygallery();
//                }
//                //image get by gallery
//                getbygallery();
                //  pickPictureIntent();
                break;


        }
    }

    private void getbygallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(intent, 2);
    }

    private void Picture_click_Intent() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");

        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        //pic = f;

        startActivityForResult(intent, 1);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {

            if (requestCode == 1) {


                //h=0;
                File f = new File(Environment.getExternalStorageDirectory().toString());

                for (File temp : f.listFiles()) {

                    if (temp.getName().equals("temp.jpg")) {

                        f = temp;
                        File photo = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                        //pic = photo;
                        break;

                    }

                }

                try {

                    Bitmap bitmap;

                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();


                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),

                            bitmapOptions);

                    Group_Fragment_tobuy.get_cameraorgalley_image.setImageBitmap(bitmap);
                    getDialog().dismiss();
                    //  a.setImageBitmap(bitmap);


                    String path = android.os.Environment

                            .getExternalStorageDirectory()

                            + File.separator

                            + "Phoenix" + File.separator + "default";
                    //p = path;

                    f.delete();

                    OutputStream outFile = null;

                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");

                    try {

                        outFile = new FileOutputStream(file);

                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        //pic=file;
                        outFile.flush();

                        outFile.close();


                    } catch (FileNotFoundException e) {

                        e.printStackTrace();

                    } catch (IOException e) {

                        e.printStackTrace();

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }

            } else if (requestCode == 2) {


                Uri selectedImage = data.getData();
                // h=1;
                //imgui = selectedImage;
                String[] filePath = {MediaStore.Images.Media.DATA};

                Cursor c = getActivity().getContentResolver().query(selectedImage, filePath, null, null, null);

                c.moveToFirst();

                int columnIndex = c.getColumnIndex(filePath[0]);

                String picturePath = c.getString(columnIndex);

                c.close();

                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));


                Log.w("path o.", picturePath + "");
                Group_Fragment_tobuy.get_cameraorgalley_image.setImageBitmap(thumbnail);
                getDialog().dismiss();
                // a.setImageBitmap(thumbnail);

            }
        }


    }
    private void getPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            String[] permission = {
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,


            };

            ActivityCompat.requestPermissions(getActivity(),
                    permission, 1);


        }
    }
}
