package fr.argouges.persomemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import fr.argouges.persomemo.ui.main.NIOFileReadExample;
import fr.argouges.persomemo.ui.main.NIOFileWriteExample;

public class EditActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        TextView textbar = (TextView) findViewById(R.id.textbar) ;
        textbar.setText(R.string.app_name);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        //Lecture de la note
        Intent myIntent = getIntent();
        String fichier = myIntent.getStringExtra("fichier");
        EditText editbox = (EditText) findViewById(R.id.editbox) ;
        String NoteN = null;
        try {
            NoteN = NIOFileReadExample.main(fichier);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if("".equals(NoteN) || NoteN==null) {
            editbox.setText("");
        } else {
            editbox.setText(NoteN);
        }

        //Enregistrement de la note
        FloatingActionButton fabedit = (FloatingActionButton) findViewById(R.id.fabedit);
        fabedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Enregistrement de la note !", Toast.LENGTH_SHORT).show();
                String WriteNote = null;
                String SaveNote = editbox.getText().toString();
                if (!"".equals(SaveNote)) {
                    try {
                        WriteNote = NIOFileWriteExample.main(fichier, SaveNote);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (WriteNote!=null) {
                        Snackbar.make(view, WriteNote, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        Snackbar.make(view, "Un problème est apparu.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } else {
                    String DeleteNote = null ;
                    try {
                        DeleteNote = NIOFileWriteExample.main(fichier, "");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                finish();
            }
        });

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

}
