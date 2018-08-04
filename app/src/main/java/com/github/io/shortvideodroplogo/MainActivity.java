package com.github.io.shortvideodroplogo;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.io.shortvideodroplogo.douyin.DouyinHttpRequest;
import com.github.io.shortvideodroplogo.douyin.DownloadCallback;
import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.VideoPickActivity;

import java.io.File;

import static com.vincent.filepicker.activity.BaseActivity.IS_NEED_FOLDER_LIST;
import static com.vincent.filepicker.activity.VideoPickActivity.IS_NEED_CAMERA;

public class MainActivity extends AppCompatActivity {

    EditText mUrlEdit;
    Button mButtonVideoSelect;
    Button mStartDownload;
    DownloadCallback mDownloadCallback = new DownloadCallback() {
        @Override
        public void fail(final String errStr) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, errStr, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void success(final String path) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, path, Toast.LENGTH_SHORT).show();
                    //由文件得到uri
                    Uri imageUri = Uri.fromFile(new File(path));

                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    shareIntent.setType("video/*");
                    startActivity(Intent.createChooser(shareIntent, "分享到"));

                }
            });
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUrlEdit = findViewById(R.id.url);
        mButtonVideoSelect = findViewById(R.id.video_select);
        mStartDownload = findViewById(R.id.btn_start_download);
        mStartDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mUrlEdit.getText())) {
                    DouyinHttpRequest.get(mUrlEdit.getText().toString(),mDownloadCallback);
                } else {
                    mUrlEdit.setError("请输入链接");
                }
            }
        });
        mButtonVideoSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, VideoPickActivity.class);
                intent2.putExtra(IS_NEED_CAMERA, true);
                intent2.putExtra(Constant.MAX_NUMBER, 9);
                intent2.putExtra(IS_NEED_FOLDER_LIST, true);
                startActivityForResult(intent2, Constant.REQUEST_CODE_PICK_VIDEO);
            }
        });
//        DouyinHttpRequest.get("可不可以亲亲\n" +
//                "http://v.douyin.com/J1Kww4/");
//        Log.d("aa",getBaseContext().getdir().toString());
//        getFileDir(Environment.getExternalStorageDirectory().toString() + "/tuandai/download");
    }

    private File getFileDir(String dirName) {
        File file = new File(dirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
