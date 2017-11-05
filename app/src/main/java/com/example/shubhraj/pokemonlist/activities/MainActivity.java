package com.example.shubhraj.pokemonlist.activities;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.shubhraj.pokemonlist.R;
import com.example.shubhraj.pokemonlist.adapters.PokemonCursorAdapter;
import com.example.shubhraj.pokemonlist.data.PokemonContract;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private ListView listView;
    private PokemonCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        cursorAdapter = new PokemonCursorAdapter(this, null);
        listView.setAdapter(cursorAdapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartLoader();
            }
        });
        getLoaderManager().initLoader(0, null, this);
    }

    private void restartLoader() {
        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projections = {PokemonContract.PokemonEntry._ID,
                PokemonContract.PokemonEntry.POKEMON_NAME,
                PokemonContract.PokemonEntry.POKEMON_TYPE};
        return new CursorLoader(this, PokemonContract.PokemonEntry.CONTENT_URI,projections,null,
                null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}
