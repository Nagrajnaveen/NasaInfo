package com.nags.nasainfo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.nags.nasainfo.R;
import com.squareup.picasso.Picasso;

public class ImageDescriptionActivity extends AppCompatActivity {

    ImageView mImageView;
    TextView mDescTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);

        mImageView=findViewById(R.id.image_view);
        mDescTv=findViewById(R.id.imageDescTV);


        Intent intent= getIntent();
        String imageUrl=intent.getStringExtra("imageurl");
        String imageDesc=intent.getStringExtra("imageExp");

        Picasso.get().load(imageUrl).into(mImageView);
        mDescTv.setText(imageDesc);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
