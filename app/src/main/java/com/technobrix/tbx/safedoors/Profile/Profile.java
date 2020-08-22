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
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.GetImagePOJO.GetBean;
import com.technobrix.tbx.safedoors.GetProfilePOJO.GetProfileBean;
import com.technobrix.tbx.safedoors.R;

import com.technobrix.tbx.safedoors.UpdateProfilePOJO.UpdateBean;
import com.technobrix.tbx.safedoors.bean;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
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

public class Profile extends Fragment {

    TabLayout layout;
    ViewPager pager;
    ViewAdapter adapter;
    static TextView name1  , flat1 , edit;
    ImageView back;
    private final int PICK_IMAGE_REQUEST = 2;
    private final int PICK_IMAGE_REQUEST1 = 3;


    ProgressBar bar;

    CircleImageView round;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile , container , false);
        layout = (TabLayout)view.findViewById(R.id.sliding_tabs);
        pager = (ViewPager) view.findViewById(R.id.viewpager);

        edit = (TextView)view.findViewById(R.id.edit);

        flat1 = (TextView)view.findViewById(R.id.flat);

        name1 = (TextView)view.findViewById(R.id.name);

        back = (ImageView) view.findViewById(R.id.back);

        bar = (ProgressBar)view.findViewById(R.id.bar);

        round = (CircleImageView)view.findViewById(R.id.round);

        layout.addTab(layout.newTab().setText("ProfileInfo"));
        layout.addTab(layout.newTab().setText("FamilyInfo"));
        layout.addTab(layout.newTab().setText("DocInfo"));
        layout.addTab(layout.newTab().setText("OtherInfo"));
        layout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter = new ViewAdapter(getChildFragmentManager(), layout.getTabCount());

        pager.setAdapter(adapter);
        layout.setupWithViewPager(pager);

        layout.getTabAt(0).setText("ProfileInfo");
        layout.getTabAt(1).setText("FamilyInfo");
        layout.getTabAt(2).setText("DocInfo");
        layout.getTabAt(3).setText("OtherInfo");




        loadImage();




        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.profile_dialog);
                dialog.setCancelable(true);
                dialog.show();

                TextView profile = (TextView)dialog.findViewById(R.id.profile);
                TextView cover = (TextView)dialog.findViewById(R.id.cover);

                profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);
dialog.dismiss();

                    }
                });

                cover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST1);
                        dialog.dismiss();


                    }
                });





            }
        });

        return view;
    }


    public void loadImage()
    {
        bar.setVisibility(View.VISIBLE);

        bean b = (bean)getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<GetBean> call = cr.get(b.userId);

        call.enqueue(new Callback<GetBean>() {
            @Override
            public void onResponse(Call<GetBean> call, Response<GetBean> response) {

                DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                        .cacheOnDisc(true).resetViewBeforeLoading(false).build();
                ImageLoader loader = ImageLoader.getInstance();
                loader.displayImage(response.body().getBackgroundImage() , back , options);
                loader.displayImage(response.body().getProfileImage() , round , options);




                bar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<GetBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });
    }


    public class ViewAdapter extends FragmentStatePagerAdapter {

        int tabs;

        public ViewAdapter(FragmentManager fm, int List) {
            super(fm);
            this.tabs = List;
        }

        @Override
        public Fragment getItem(int position) {


            if (position == 0)
            {
                return new ProfileInfoFragment();
            }
            else if (position == 1)
            {
                return new FamilyInfoFragment();
            }
            else if (position == 2)
            {
                return new DoctInfoFragment();
            }
            else if (position == 3)
            {
                return new OtherInfoFragment();
            }
            return null;
        }

        @Override
        public int getCount () {
            return 4;
        }
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


            bar.setVisibility(View.VISIBLE);
            bean b = (bean)getContext().getApplicationContext();

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
                    loadImage();

                }

                @Override
                public void onFailure(Call<UpdateBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                }
            });

        }
else  if (requestCode == PICK_IMAGE_REQUEST1 && resultCode == RESULT_OK && data != null && data.getData() != null){

            Uri selectedImageUri = data.getData();


            MultipartBody.Part body = null;


            String mCurrentPhotoPath = getPath(getContext() , selectedImageUri);

            File file = new File(mCurrentPhotoPath);


            RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            body = MultipartBody.Part.createFormData("backimg", file.getName(), reqFile);


            bar.setVisibility(View.VISIBLE);
            bean b = (bean)getContext().getApplicationContext();

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
            });
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


    public static class ProfileInfoFragment extends Fragment {

        TextView email , dob , flat, pa, male, edit , society , age , phone , name;

        ProgressBar bar;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.profile_info_fragment , container , false);

            email = (TextView)view.findViewById(R.id.email);
            dob = (TextView)view.findViewById(R.id.dob);
            flat = (TextView)view.findViewById(R.id.flat);
            pa = (TextView)view.findViewById(R.id.address);
            male = (TextView)view.findViewById(R.id.male);
            edit = (TextView)view.findViewById(R.id.edit);
            society = (TextView)view.findViewById(R.id.society);
            age = (TextView)view.findViewById(R.id.age);
            phone = (TextView)view.findViewById(R.id.phone);
            // name = (TextView)view.findViewById(R.id.name);
            bar = (ProgressBar) view.findViewById(R.id.progress);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), ProfileEdit.class);
                    startActivity(i);
                }
            });

            bar.setVisibility(View.VISIBLE);

            bean b = (bean)getContext().getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);
            Call<GetProfileBean> call = cr.getprofile(b.userId);

            Log.d("nisha" , b.userId);

            call.enqueue(new Callback<GetProfileBean>() {
                @Override
                public void onResponse(Call<GetProfileBean> call, Response<GetProfileBean> response) {

                    email.setText(response.body().getEmail());

                    Log.d("asdasd" , response.body().getEmail());

                    dob.setText(response.body().getDob());
                    pa.setText(response.body().getPermanentAddress());
                    male.setText(response.body().getGender());
                    phone.setText(response.body().getPhone());
                    flat.setText(response.body().getFlatNo());
                    society.setText(response.body().getSocityName());
                    email.setText(response.body().getEmail());
                    age.setText(response.body().getAge());

                    name1.setText(response.body().getUsername());
                    flat1.setText(response.body().getFlatNo());



//                    Log.d("hmm" , response.body().getFlatNo());

                    bar.setVisibility(View.GONE);


                }

                @Override
                public void onFailure(Call<GetProfileBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                    Log.d("mmmm" , t.toString());

                }
            });
            return  view;
        }
    }


}


