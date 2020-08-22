package com.technobrix.tbx.safedoors;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AllMemeberPOJO.MemberBean;
import com.technobrix.tbx.safedoors.ProfilePOJO.SetFamilyBean;
import com.technobrix.tbx.safedoors.SplashLogin.Login;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

public class GateKeeper extends AppCompatActivity {

    private static final int TAKE_PICTURE = 1;
    static final int RC_TAKE_PHOTO = 1;
    private Uri imageUri;

    EditText name , purpuse  ;

    Spinner member;

    List<String> memberName;
    List<String> memberId;
    List<String> houseId;

    String houId = "";

    ImageView image , log;

    String mCurrentPhotoPath = "";

    File file;
    Uri fileUri;

    Button submit;

    ProgressBar bar;

    String membId = "";

    TextView cap;

    SharedPreferences pref;
    SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate_keeper);
        pref = getSharedPreferences("pref" , MODE_PRIVATE);
        edit = pref.edit();


        memberName = new ArrayList<>();
        memberId = new ArrayList<>();
        houseId = new ArrayList<>();

        name = (EditText) findViewById(R.id.name);

        purpuse = (EditText) findViewById(R.id.purpuse);

        member = (Spinner) findViewById(R.id.member);

        image = (ImageView) findViewById(R.id.image);

        cap = (TextView) findViewById(R.id.capture);

        submit = (Button) findViewById(R.id.submit);

        bar = (ProgressBar) findViewById(R.id.progress);

        log = (ImageView) findViewById(R.id.log);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(GateKeeper.this , Login .class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                edit.remove("user");
                edit.remove("type");
                edit.remove("pass");
                edit.apply();

                bean b = (bean)getApplicationContext();

                b.name = "";
                b.userId = "";
                b.email = "";

                startActivity(i);
                finish();




            }
        });

        cap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file = new File(getExternalCacheDir(),
                        String.valueOf(System.currentTimeMillis()) + ".jpg");
                //fileUri = Uri.fromFile(file);
                fileUri = FileProvider.getUriForFile(
                        GateKeeper.this,
                        GateKeeper.this.getApplicationContext()
                                .getPackageName() + ".provider", file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, RC_TAKE_PHOTO);


            }
        });


        bar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bean b = (bean)getApplicationContext();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<MemberBean> call = cr.member(b.socity);

        call.enqueue(new Callback<MemberBean>() {
            @Override
            public void onResponse(Call<MemberBean> call, Response<MemberBean> response) {

                for (int i = 0 ; i < response.body().getMemberList().size() ; i++)
                {
                    memberName.add(response.body().getMemberList().get(i).getMemberName());
                    memberId.add(response.body().getMemberList().get(i).getMemberId());
                    houseId.add(response.body().getMemberList().get(i).getHouseNo());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(GateKeeper.this , android.R.layout.simple_spinner_item , memberName);

                bar.setVisibility(View.GONE);

                member.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<MemberBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });





        member.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                membId = memberId.get(i);
                houId = houseId.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n = name.getText().toString();
                String p = purpuse.getText().toString();


                Calendar cal = Calendar.getInstance();

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                Date todayDate = new Date();
                todayDate.getDay();
                todayDate.getHours();
                todayDate.getMinutes();
                todayDate.getMonth();
                todayDate.getTime();


                if (file != null)
                {
                    MultipartBody.Part body = null;

                    //File file = new File(mCurrentPhotoPath);


                    RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                    body = MultipartBody.Part.createFormData("profile", file.getName(), reqFile);





                    String date = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);

                    String time = ""+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);

                    bar.setVisibility(View.VISIBLE);


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://safedoors.in")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    bean b = (bean)getApplicationContext();

                    AllApiInterface cr = retrofit.create(AllApiInterface.class);
                    Call<SetFamilyBean> call = cr.setfamily(b.userId, b.socity , houId ,membId , n  , p ,date , time ,body );



                    call.enqueue(new Callback<SetFamilyBean>() {
                        @Override
                        public void onResponse(Call<SetFamilyBean> call, Response<SetFamilyBean> response) {

                            Toast.makeText(GateKeeper.this, "Notification Created Successfully", Toast.LENGTH_SHORT).show();

                            bar.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<SetFamilyBean> call, Throwable t) {

                            bar.setVisibility(View.GONE);

                        }
                    });
                }
                else
                {

                    Toast.makeText(GateKeeper.this , "Please capture a Pic" , Toast.LENGTH_SHORT).show();

                }

                
            }
        });
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_TAKE_PHOTO && resultCode == RESULT_OK) {



            //mCurrentPhotoPath = getPath(GateKeeper.this , fileUri);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),fileUri);

                image.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }

            //Log.d("asdasd" , String.valueOf(fileUri));


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
        final String column = "data";
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
