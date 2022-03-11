package fr.argouges.persomemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) myToolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(myToolbar);
        mTitle.setText(myToolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Contact 1
        FloatingActionButton Contact1 = findViewById(R.id.ButContact1);
        Contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0476767675", null));
                startActivity(intent);
            }
        });

        // Contact 2
        FloatingActionButton Contact2 = findViewById(R.id.ButContact2);
        Contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0438701701", null));
                startActivity(intent);
            }
        });

        // Contact 3
        FloatingActionButton Contact3 = findViewById(R.id.ButContact3);
        Contact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0476865900", null));
                startActivity(intent);
            }
        });

        // Contact 4
        FloatingActionButton Contact4 = findViewById(R.id.ButContact4);
        Contact4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0476511638", null));
                startActivity(intent);
            }
        });

        // Contact 5
        FloatingActionButton Contact5 = findViewById(R.id.ButContact5);
        Contact5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0476879461", null));
                startActivity(intent);
            }
        });

        // Contact 6
        FloatingActionButton Contact6 = findViewById(R.id.ButContact6);
        Contact6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0456588270", null));
                startActivity(intent);
            }
        });

        // Contact 7
        FloatingActionButton Contact7 = findViewById(R.id.ButContact7);
        Contact7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0476121285", null));
                startActivity(intent);
            }
        });

        // Contact 8
        FloatingActionButton Contact8 = findViewById(R.id.ButContact8);
        Contact8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0456528830", null));
                startActivity(intent);
            }
        });

    }
}
