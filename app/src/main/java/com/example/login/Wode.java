package com.example.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;


public class Wode extends Fragment implements View.OnClickListener {
    private View view;

    private TextView exit_login, mine_user_name ;

    private ImageView my_head;
    private GridView listView;

    private static final int CHOSSE_PHOTO = 1;

    private MyDatabaseHelper helper;
    private Dao dao;
    private ImageAdapter imageAdapter;
    private List<ImageBean> list;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wode, container, false);
        initView();


        helper = new MyDatabaseHelper(getContext(), "UserDB.db", null, 1);
        dao = new Dao(getActivity());

        String username = (String) SharedPreUtil.getParam(getContext(), SharedPreUtil.LOGIN_DATA, "");
        mine_user_name.setText(username);
        exit_login.setOnClickListener(this);
        my_head.setOnClickListener(this);
        list = dao.getImgs(username);
        Log.i("ssss",String.valueOf(list.size()));
        imageAdapter = new ImageAdapter(getActivity(),list);
        listView.setAdapter(imageAdapter);


        try {
            String path = getContext().getCacheDir().getPath();
            String fileName = "user_head";
            File file = new File(path, fileName);
            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                Bitmap round = AlbumUtil.toRoundBitmap(bitmap);
                my_head.setImageBitmap(round);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(getContext(), "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void initView() {
        mine_user_name = view.findViewById(R.id.mine_user_name);

        exit_login = view.findViewById(R.id.exit_login);
        listView = view.findViewById(R.id.image_list);

        my_head = view.findViewById(R.id.my_head);

    }


    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOSSE_PHOTO);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOSSE_PHOTO:
                if (resultCode == -1) {
                    String imgPath = AlbumUtil.handleImageOnKitKat(getContext(), data);
                    setHead(imgPath);
                }
                break;
            default:
                break;
        }
    }


    private void setHead(String imgPath) {
        if (imgPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
            Bitmap round = AlbumUtil.toRoundBitmap(bitmap);
            try {
                String path = getContext().getCacheDir().getPath();
                File file = new File(path, "user_head");
                round.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            my_head.setImageBitmap(round);
        } else {
            Toast.makeText(getContext(), "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.exit_login: {
                SharedPreUtil.setParam(getContext(), SharedPreUtil.IS_LOGIN, false);
                SharedPreUtil.removeParam(getContext(), SharedPreUtil.LOGIN_DATA);
                //重新跳转到登录页面
                Intent intent = new Intent(getContext(), LoginOrRegisterActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
            break;


            case R.id.my_head: {
                if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
            }
            break;
            default:
                break;

        }
    }
}