package com.technobrix.tbx.safedoors.NewGatekeeper;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.AllMemeberPOJO.MemberBean;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;
import com.technobrix.tbx.safedoors.entryBean;
import com.technobrix.tbx.safedoors.visitorListPOJO.VisitorList;
import com.technobrix.tbx.safedoors.visitorListPOJO.visitorListBean;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NewVisitor extends AppCompatActivity {

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

    ProgressBar progress;

    String membId = "";

    TextView cap;

    SharedPreferences pref;

    SharedPreferences.Editor edit;

    Toolbar toolbar;

    RecyclerView grid;

    GridLayoutManager manager;

    RegularAdapter adapter;

    List<VisitorList> list;
    EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visitor);
        pref = getSharedPreferences("pref" , MODE_PRIVATE);
        edit = pref.edit();

        comment = (EditText) findViewById(R.id.comment);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }

        });

        toolbar.setTitle("New Visitor");

        grid = (RecyclerView) findViewById(R.id.grid);

        manager = new GridLayoutManager(getApplicationContext() , 1);

        list = new ArrayList<>();

        adapter = new RegularAdapter(this , list);

        grid.setLayoutManager(manager);

        grid.setAdapter(adapter);
        grid.setNestedScrollingEnabled(false);



        memberName = new ArrayList<>();
        memberId = new ArrayList<>();
        houseId = new ArrayList<>();

        name = (EditText) findViewById(R.id.name);

        purpuse = (EditText) findViewById(R.id.purpuse);

        member = (Spinner) findViewById(R.id.spinner);

        image = (ImageView) findViewById(R.id.image);

        cap = (TextView) findViewById(R.id.capture);

        submit = (Button) findViewById(R.id.submit);

        progress = (ProgressBar) findViewById(R.id.progress);

        log = (ImageView) findViewById(R.id.log);

       /* log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(NewVisitor.this , Login.class);
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
        });*/

        cap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file = new File(getExternalCacheDir(),
                        String.valueOf(System.currentTimeMillis()) + ".jpg");
                //fileUri = Uri.fromFile(file);
                fileUri = FileProvider.getUriForFile(
                        NewVisitor.this,
                        NewVisitor.this.getApplicationContext()
                                .getPackageName() + ".provider", file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, RC_TAKE_PHOTO);


            }
        });




        progress.setVisibility(View.VISIBLE);

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
                    memberName.add(response.body().getMemberList().get(i).getHouseName());
                    memberId.add(response.body().getMemberList().get(i).getMemberId());
                    houseId.add(response.body().getMemberList().get(i).getHouseNo());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewVisitor.this , android.R.layout.simple_spinner_item , memberName);

                progress.setVisibility(View.GONE);

                member.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<MemberBean> call, Throwable t) {

                progress.setVisibility(View.GONE);

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

                    progress.setVisibility(View.VISIBLE);


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://safedoors.in")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    bean b = (bean)getApplicationContext();

                    AllApiInterface cr = retrofit.create(AllApiInterface.class);
                    Call<entryBean> call = cr.regularNew(b.socity , comment.getText().toString() , b.userId , "New" , n  , houId ,body );


                    call.enqueue(new Callback<entryBean>() {
                        @Override
                        public void onResponse(Call<entryBean> call, Response<entryBean> response) {

                            Toast.makeText(NewVisitor.this, "Notification Created Successfully", Toast.LENGTH_SHORT).show();

                            loadData();

                            progress.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<entryBean> call, Throwable t) {

                            progress.setVisibility(View.GONE);

                        }
                    });
                }
                else
                {

                    Toast.makeText(NewVisitor.this , "Please capture a Pic" , Toast.LENGTH_SHORT).show();

                }




            }
        });





    }



    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData() {

        list.clear();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        progress.setVisibility(View.VISIBLE);

        bean b = (bean) getApplicationContext();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);


        Call<visitorListBean> call = cr.getVisitors(b.socity);

        call.enqueue(new Callback<visitorListBean>() {
            @Override
            public void onResponse(Call<visitorListBean> call, Response<visitorListBean> response) {

                Log.d("asdasdSize" , String.valueOf(response.body().getVisitorList().size()));

                for (int i = 0; i < response.body().getVisitorList().size(); i++)
                {
                    if (Objects.equals(response.body().getVisitorList().get(i).getVisitorType(), "New")) {
                        list.add(response.body().getVisitorList().get(i));
                    }
                }

                adapter.setGridData(list);


                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<visitorListBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });
    }

    public  class RegularAdapter extends RecyclerView.Adapter<RegularAdapter.MyViewHolder> {

        List<VisitorList> list = new ArrayList<>();
        Context context;



        public RegularAdapter(Context context, List<VisitorList> list) {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<VisitorList> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.regular_list_model, parent, false);
            return new MyViewHolder(view);
        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            final VisitorList item = list.get(position);

            holder.time.setText(item.getIntime());
            holder.visitor.setText(item.getVisitorName());

            holder.house.setText(item.getHouseNo());

            if (Objects.equals(item.getStatus(), "0")) {
                holder.out.setBackgroundResource(R.drawable.green_circle);
            } else if (Objects.equals(item.getStatus(), "1")) {
                holder.out.setBackgroundResource(R.drawable.red_circle);
            }

            holder.comment.setText(item.getComment());

            holder.out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    progress.setVisibility(View.VISIBLE);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://safedoors.in")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    bean b = (bean) context.getApplicationContext();

                    AllApiInterface cr = retrofit.create(AllApiInterface.class);

                    Call<String> visitorOut = cr.out(b.socity, item.getVisitorId());

                    visitorOut.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            loadData();

                            progress.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            progress.setVisibility(View.GONE);
                        }
                    });

                }
            });



        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView time, visitor, house , comment;
            ImageButton out;

            public MyViewHolder(View itemView) {
                super(itemView);


                time = (TextView) itemView.findViewById(R.id.time);
                visitor = (TextView) itemView.findViewById(R.id.visitor);
                house = (TextView) itemView.findViewById(R.id.house);
                comment = (TextView) itemView.findViewById(R.id.comment);
                out = (ImageButton) itemView.findViewById(R.id.out);
            }
        }
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
