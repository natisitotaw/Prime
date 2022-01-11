package com.example.prime;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    GridView gridView;
    String[] names ={"phone","pc","car","cloth","toys","food"};
    int[] images={R.drawable.phone,R.drawable.pc,R.drawable.car,R.drawable.cloth,R.drawable.toys,R.drawable.food};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        gridView=findViewById(R.id.gridview);

        CustomAdapter customAdapter = new CustomAdapter(names,images, this);
        gridView.setAdapter(customAdapter);






    }
    public class CustomAdapter extends BaseAdapter {
        private String[] imageNames;
        private int[] imagePhoto;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(String[] imageNames, int[] imagePhoto, Context context) {
            this.imageNames = imageNames;
            this.imagePhoto = imagePhoto;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return imagePhoto.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null){
                view = layoutInflater.inflate(R.layout.row_items, viewGroup, false);
            }
            TextView tvName = view.findViewById(R.id.tvName);
            ImageView imageView = view.findViewById(R.id.imageview);

            tvName.setText(imageNames[i]);
            imageView.setImageResource(imagePhoto[i]);

            return view;
        }
    }

}
