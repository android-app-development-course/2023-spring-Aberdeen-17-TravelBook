package com.example.login;

import static com.example.login.LoginActivity.Loginuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddImage extends AppCompatActivity {
    public static final int CHANGE_IMG_PROFILE = 3001;
    private ImageView imageView;
    private Button button;
    private String imagepath = "null", imagepath2 = "null";
    private Dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);
        imageView = findViewById(R.id.add_im);
        button = findViewById(R.id.add_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setBackground(null);//进入相册选择图片
                choosePhoto(CHANGE_IMG_PROFILE);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao = new Dao(getApplicationContext());
                if ( (dao.insertComments(imagepath,Loginuser))==0){
                    Toast.makeText(AddImage.this, "发表失败", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddImage.this,MainActivity.class);
                    AddImage.this.startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(AddImage.this, "发表成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddImage.this,MainActivity.class);
                    AddImage.this.startActivity(intent);
                    finish();
                }



            }
        });
    }
    private void choosePhoto(int type) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, type);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case CHANGE_IMG_PROFILE:
                Uri profileUri = data.getData();
                imagepath = new UritoString().getPath(getApplicationContext(), profileUri);//获取图片路径
                Bitmap bitmap = null;
                bitmap = BitmapFactory.decodeFile(imagepath);
                imageView.setImageBitmap(bitmap);
                break;
        }
    }
}