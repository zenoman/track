package com.t.podomorotrack.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.t.podomorotrack.Config.Spf;
import com.t.podomorotrack.MainActivity;
import com.t.podomorotrack.R;

public class Beranda extends AppCompatActivity {
    private CardView crdmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        if(!Spf.getmInstance(Beranda.this).isLogIn()){
            startActivity(new Intent(Beranda.this,MainActivity.class));
        }
        crdmap=(CardView) findViewById(R.id.mapl);
        crdmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Beranda.this,Map_Place.class));
            }
        });
    }
}
