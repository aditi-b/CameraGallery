package com.cameragallery;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MultipleImages extends AppCompatActivity implements View.OnClickListener {

    int PICK_IMAGE_MULTIPLE = 1;                            // pick multiple images code
    Button btnMultiple;
    ArrayList<Uri> image;
    RecyclerView recyclerView;
    private RecyclerView.Adapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_images);

        btnMultiple = findViewById(R.id.button2);

        recyclerView = findViewById(R.id.recyclerview);
        image = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        imageAdapter = new ImageAdapter(image, this);
        recyclerView.setAdapter(imageAdapter);

        btnMultiple.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        if(requestCode==PICK_IMAGE_MULTIPLE){


            if(resultCode==RESULT_OK){

                //If Single image selected then it will fetch from Gallery
                if(data.getData()!=null){

                   Uri mImageUri=data.getData();
                   image.add(mImageUri);
                   imageAdapter.notifyDataSetChanged();
                }
                else{
                    if(data.getClipData()!=null){
                        ClipData mClipData=data.getClipData();

                        for(int i=0;i<mClipData.getItemCount();i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();

                            image.add(uri);
                            imageAdapter.notifyDataSetChanged();
                        }
                    }

                }

            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            // select multiple images
            case R.id.button2:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
                image.clear();
                break;

        }
    }
}
