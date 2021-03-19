package com.example.eyephone_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnStart;
    private TextView Title;
    FirebaseDatabase database;
    DatabaseReference bbsRef;
    ListViewAdapter adapter;
    List<Bbs> datas = new ArrayList<>();
    String[] str=new String[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference("news");
        bbsRef.addListenerForSingleValueEvent(postListener);
        btnStart = (Button) findViewById(R.id.btnStart);
        Title = (TextView) findViewById(R.id.Title);
        System.out.println(datas.size());
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Intent intent = new Intent(MainActivity.this, ReadNewsActivity.class);
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                System.out.println(datas.size());
                for(int i=0;i<5;i++) {
                    Bbs b=datas.get(i);
                    str[i]=b.title;
                }
                intent.putExtra("title", str);
                startActivity(intent);
            }
        });

    }
    ValueEventListener postListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // 위에 선언한 저장소인 datas를 초기화하고

            datas.clear();
            // bbs 레퍼런스의 스냅샷을 가져와서 레퍼런스의 자식노드를 바복문을 통해 하나씩 꺼내서 처리.
            for( DataSnapshot snapshot : dataSnapshot.getChildren() ) {
                String key  = snapshot.getKey();
                System.out.println(key);

                Bbs bbs = snapshot.getValue(Bbs.class); // 컨버팅되서 Bbs로........
                bbs.key = key;
                datas.add(bbs);

            }
            System.out.println(datas.size());
            //adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException());
            // ...
        }
    };
}