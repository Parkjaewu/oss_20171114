package com.materialnotes.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.materialnotes.R;
import com.materialnotes.data.Note;
import com.materialnotes.util.Strings;

import java.util.Date;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;


@ContentView(R.layout.activity_edit_note)
public class EditNoteActivity extends RoboActionBarActivity {

    private static final String EXTRA_NOTE = "EXTRA_NOTE";

    @InjectView(R.id.note_title)   private EditText noteTitleText;
    @InjectView(R.id.note_content) private EditText noteContentText;

    private Note note;


    public static Intent buildIntent(Context context, Note note) {
        Intent intent = new Intent(context, EditNoteActivity.class);
        intent.putExtra(EXTRA_NOTE, note);
        return intent;
    }


    public static Intent buildIntent(Context context) {
        return buildIntent(context, null);
    }


    public static Note getExtraNote(Intent intent) {
        return (Note) intent.getExtras().get(EXTRA_NOTE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        note = (Note) getIntent().getSerializableExtra(EXTRA_NOTE);
        if (note != null) { // Editar nota existente
            noteTitleText.setText(note.getTitle());
            noteContentText.setText(note.getContent());
        } else { // Nueva nota
            note = new Note();
            note.setCreatedAt(new Date());
        }
    }

 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_note, menu);
        return true;
    }

 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_save:
                if (isNoteFormOk()) {
                    setNoteResult();
                    finish();
                } else validateNoteForm();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }


    private boolean isNoteFormOk() {
        return !Strings.isNullOrBlank(noteTitleText.getText().toString()) && !Strings.isNullOrBlank(noteContentText.getText().toString());
    }


    private void setNoteResult() {
        note.setTitle(noteTitleText.getText().toString().trim());
        note.setContent(noteContentText.getText().toString().trim());
        note.setUpdatedAt(new Date());
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_NOTE, note);
        setResult(RESULT_OK, resultIntent);
    }

 
    private void validateNoteForm() {
        StringBuilder message = null;
        if (Strings.isNullOrBlank(noteTitleText.getText().toString())) {
            message = new StringBuilder().append(getString(R.string.title_required));
        }
        if (Strings.isNullOrBlank(noteContentText.getText().toString())) {
            if (message == null) message = new StringBuilder().append(getString(R.string.content_required));
            else message.append("\n").append(getString(R.string.content_required));
        }
        if (message != null) {
            Toast.makeText(getApplicationContext(),
                    message,
                    Toast.LENGTH_LONG)
                    .show();
        }
    }


    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED, new Intent());
        finish();
    }
}
