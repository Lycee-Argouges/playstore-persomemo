package fr.argouges.persomemo.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import fr.argouges.persomemo.EditActivity;
import fr.argouges.persomemo.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public int ViewTab ;


    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
        ViewTab = index;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        EditText EditText = root.findViewById(R.id.EditText);
        FloatingActionButton fab = root.findViewById(R.id.save);
        final ScrollView Scrolling = root.findViewById(R.id.scroll);

        if (ViewTab==2) {
            EditText.setVisibility(View.VISIBLE);
            Scrolling.setVisibility(View.GONE);
            fab.setVisibility(View.VISIBLE);
        }
        if (ViewTab==1) {
            Scrolling.setVisibility(View.VISIBLE);
            EditText.setVisibility(View.GONE);
            fab.setVisibility(View.GONE);
            //Appel de la fonction ListView
            ListView(root, R.id.TexTV1, R.id.ButTV1, R.id.LL1, 1);
            ListView(root, R.id.TexTV2, R.id.ButTV2, R.id.LL2, 2);
            ListView(root, R.id.TexTV3, R.id.ButTV3, R.id.LL3, 3);
            ListView(root, R.id.TexTV4, R.id.ButTV4, R.id.LL4, 4);
            ListView(root, R.id.TexTV5, R.id.ButTV5, R.id.LL5, 5);
            ListView(root, R.id.TexTV6, R.id.ButTV6, R.id.LL6, 6);
            ListView(root, R.id.TexTV7, R.id.ButTV7, R.id.LL7, 7);
            ListView(root, R.id.TexTV8, R.id.ButTV8, R.id.LL8, 8);
            ListView(root, R.id.TexTV9, R.id.ButTV9, R.id.LL9, 9);
            ListView(root, R.id.TexTV10, R.id.ButTV10, R.id.LL10, 10);
            ListView(root, R.id.TexTV11, R.id.ButTV11, R.id.LL11, 11);
            ListView(root, R.id.TexTV12, R.id.ButTV12, R.id.LL12, 12);
            ListView(root, R.id.TexTV13, R.id.ButTV13, R.id.LL13, 13);
            ListView(root, R.id.TexTV14, R.id.ButTV14, R.id.LL14, 14);
            ListView(root, R.id.TexTV15, R.id.ButTV15, R.id.LL15, 15);
            ListView(root, R.id.TexTV16, R.id.ButTV16, R.id.LL16, 16);
            ListView(root, R.id.TexTV17, R.id.ButTV17, R.id.LL17, 17);
            ListView(root, R.id.TexTV18, R.id.ButTV18, R.id.LL18, 18);
            ListView(root, R.id.TexTV19, R.id.ButTV19, R.id.LL19, 19);
            ListView(root, R.id.TexTV20, R.id.ButTV20, R.id.LL20, 20);

            //Code debug afficher information
            /*if(MainActivity.PATH_DIR==true) {
                //String Path = Environment.getDataDirectory().toString();
                TextView OpenNote = root.findViewById(R.id.TexTV1);
                ImageView Bouton = root.findViewById(R.id.ButTV1);
                LinearLayout LLX = root.findViewById(R.id.LL1);
                OpenNote.setText(MainActivity.PATH_ROOT);
                LLX.setVisibility(View.VISIBLE);
            }*/
        }

        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = CreateReaderList.counter();
                if(count<=20) {
                    String NOTE = "PersoMemo" + count + ".txt";
                    String WriteNote = null;
                    String SaveNote = EditText.getText().toString();
                    if (!"".equals(SaveNote)) {
                        try {
                            WriteNote = NIOFileWriteExample.main(NOTE, SaveNote);
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
                    }
                } else {
                    Snackbar.make(view, "Trop de notes sont enregistrées !", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                EditText.setText("");
            }
        });
        return root;
    }

    public void ListView(View lview, int textId, int butId, int linId, int NbNote) {
        TextView OpenNote = lview.findViewById(textId);
        ImageView Bouton = lview.findViewById(butId);
        LinearLayout LLX = lview.findViewById(linId);
        final Intent newintent = new Intent().setClass(getContext(), EditActivity.class);
        String NOTE = "PersoMemo" + NbNote + ".txt";
        String NoteN = null;
        try {
            NoteN = NIOFileReadExample.main(NOTE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if("".equals(NoteN) || NoteN==null) {
            LLX.setVisibility(View.GONE);
        } else {
            OpenNote.setText(NoteN);
        }

        Bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String DeleteNote = null ;
                try {
                    DeleteNote = NIOFileWriteExample.main(NOTE, "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Snackbar.make(view, "Note supprimée. Actualisez !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        OpenNote.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                newintent.putExtra("fichier", NOTE);
                startActivity(newintent);
                return false;
            }
        });
    }
}

