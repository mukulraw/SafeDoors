package com.technobrix.tbx.safedoors.Profile;

import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.DocumentListPOJO.DoctorBean;
import com.technobrix.tbx.safedoors.DocumentListPOJO.DocumentList;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.app.Activity.RESULT_OK;


public class DoctInfoFragment extends Fragment {

    RecyclerView recyclerView;

    GridLayoutManager manager;

    DoctAdapter adapter;

    List<DocumentList> list;

    ProgressBar bar;

    private final int PICK_IMAGE_REQUEST = 2;
    private final int PICK_IMAGE_REQUEST1 = 3;

    TextView add;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.docinfo , container , false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);

        add = (TextView) view.findViewById(R.id.add);

        manager = new GridLayoutManager(getContext() , 1);

        list = new ArrayList();

        adapter = new DoctAdapter(getContext(), list);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(manager);

        bar = (ProgressBar) view.findViewById(R.id.progress);

        bar.setVisibility(View.VISIBLE);

        Log.d("fjgsd" , "response");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.adddocument_dialog);
                dialog.setCancelable(false);
                dialog.show();





                Spinner title = (Spinner)dialog.findViewById(R.id.spinner);

                List<String> list = new ArrayList<String>();

                list.add("Aadhar Card ");
                list.add("PAN Card");
                list.add("Voter Card Id");


                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, list);

                title.setAdapter(dataAdapter);


                EditText number = (EditText)dialog.findViewById(R.id.number);
                TextView attach = (TextView)dialog.findViewById(R.id.attach);
                Button submit = (Button)dialog.findViewById(R.id.submit);


                attach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.setType("*/*");
                        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);
                        dialog.dismiss();


                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();


                    }
                });


            }
        });


        bean b = (bean)getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<DoctorBean> call = cr.doc(b.userId);
        call.enqueue(new Callback<DoctorBean>() {
            @Override
            public void onResponse(Call<DoctorBean> call, Response<DoctorBean> response) {

                adapter.set(response.body().getDocumentList());
                bar.setVisibility(View.GONE);

                Log.d("diya" ,  String.valueOf(response.body().getDocumentList().size()));


            }

            @Override
            public void onFailure(Call<DoctorBean> call, Throwable t) {


                bar.setVisibility(View.GONE);

                Log.d("jiya" , t.toString());

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();


            MultipartBody.Part body = null;


            String mCurrentPhotoPath = getPath(getContext() , selectedImageUri);

            File file = new File(mCurrentPhotoPath);


            RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            body = MultipartBody.Part.createFormData("profile", file.getName(), reqFile);

            //bar.setVisibility(View.VISIBLE);
           /* bean b = (bean)getContext().getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);
            Call<UpdateBean> call = cr.update(b.userId , file.getName() , body);



            call.enqueue(new Callback<UpdateBean>() {
                @Override
                public void onResponse(Call<UpdateBean> call, Response<UpdateBean> response) {

                    bar.setVisibility(View.GONE);
                    //loadImage();

                }

                @Override
                public void onFailure(Call<UpdateBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                }
            });
*/
        }
        else  if (requestCode == PICK_IMAGE_REQUEST1 && resultCode == RESULT_OK && data != null && data.getData() != null){

            Uri selectedImageUri = data.getData();


            MultipartBody.Part body = null;


            String mCurrentPhotoPath = getPath(getContext() , selectedImageUri);

            File file = new File(mCurrentPhotoPath);


            RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            body = MultipartBody.Part.createFormData("backimg", file.getName(), reqFile);


            //bar.setVisibility(View.VISIBLE);
           /* bean b = (bean)getContext().getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);
            Call<UpdateBean> call = cr.ground(b.userId , file.getName() , body);



            call.enqueue(new Callback<UpdateBean>() {
                @Override
                public void onResponse(Call<UpdateBean> call, Response<UpdateBean> response) {

                    bar.setVisibility(View.GONE);
                    loadImage();

                }

                @Override
                public void onFailure(Call<UpdateBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                }
            });*/
        }


        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }



    private static String getPath(final Context context, final Uri uri)
    {
        final boolean isKitKatOrAbove = Build.VERSION.SDK_INT >=  Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isKitKatOrAbove && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }

                    // TODO handle non-primary volumes
                }
                // DownloadsProvider
                else if (isDownloadsDocument(uri)) {

                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                    return getDataColumn(context, contentUri, null, null);
                }
                // MediaProvider
                else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[] {
                            split[1]
                    };

                    return getDataColumn(context, contentUri, selection, selectionArgs);
                }
            }
            // MediaStore (and general)
            else if ("content".equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(context, uri, null, null);
            }
            // File
            else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }

        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }



}



