package com.example.thermonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class List extends AppCompatActivity {

    ListView mListView;
    int[] images = {R.drawable.andr,
                    R.drawable.apple,
                    R.drawable.blackberry,
                    R.drawable.linux,
                    R.drawable.windows};
    String[] Operating_Systems = {"Android","Apple","Blackberry","Linux","windows"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mListView = findViewById(R.id.OS);

        CustomAdaptor customAdaptor = new CustomAdaptor();
        mListView.setAdapter(customAdaptor);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(List.this, DeviceDetail.class);
                startActivity(intent);
            }
        });

    }

    class CustomAdaptor extends BaseAdapter{

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.os_row, null);
            ImageView mImageView = view.findViewById(R.id.imageView);
            TextView mTextView =  view.findViewById(R.id.TextView);
            mImageView.setImageResource(images[position]);
            mTextView.setText(Operating_Systems[position]);

            return view;
        }
    }
}
