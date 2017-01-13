package com.example.matinee.memaker;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText userText;
    private TextView memText;
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        memText = (TextView)findViewById(R.id.memText);
        userText = (EditText)findViewById(R.id.userText);
    }
    //Button which add image
    public void addImage (View view) {
        //Create intent to open Image Applications like Gallery
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.
                Images.Media.EXTERNAL_CONTENT_URI);
        //Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //If image is picked by user
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                //Get the image from data
                Uri selectedImage = data.getData();
                String [] filePathColumn = {MediaStore.Images.Media.DATA};

                //Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn,
                        null, null, null);
                //Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imgView);
                //Put the image in ImageView after decoding
                imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));

            }
        } catch (Exception e) {

        }
    }
    // Button which add text to your mem
    public void onClickAddText(View view) {
        if (userText.getText().length() == 0) {
            memText.setText("Не придумал мем");
        } else {
            memText.setText(userText.getText());
        }
    }
}
