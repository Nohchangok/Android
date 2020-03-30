package com.example.mycamera;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.File;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    ImageView imageView;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        AutoPermissions.Companion.loadAllPermissions(this,101);
    }

    private void takePicture(){
        if(file == null){
            file = createFile();
        }

        //파일객체로부터 파일uri객체로 만든다.
        Uri fileUri = FileProvider.getUriForFile(this,"com.example.mycamera.fileprovider", file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //카메라앱에게 파일의 주소를 받아와서 그주소를 써.
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        //결과가 빈값이 아니라면
        if(intent.resolveActivity(getPackageManager())!= null){
            startActivityForResult(intent,101);
        }
    }

    private File createFile() {
        String filename = "capture.jpg";
        //외부저장장소에 접근한다.
        File storageDir = Environment.getExternalStorageDirectory();
        Log.i("Test",storageDir.getAbsolutePath());

        //파일을 외부저장소에 접근해서 정해준 이름으로 만든다.
        File outFile = new File(storageDir, filename);
        return outFile;
    }

    //내가보낸 액티비티가 종료되어서 반환되는 값을 가져온다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Test","결과가 왔다.");
        Log.i("Test", "requestCode : "+requestCode +", resultCode : "+resultCode);
        if (requestCode == 101 && resultCode == RESULT_OK){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 0;
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onDenied(int i, String[] strings) {
        Toast.makeText(this, "퍼미션 거부", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onGranted(int i, String[] strings) {
        Toast.makeText(this, "퍼미션 승인", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this,requestCode,permissions,this);
    }
}
