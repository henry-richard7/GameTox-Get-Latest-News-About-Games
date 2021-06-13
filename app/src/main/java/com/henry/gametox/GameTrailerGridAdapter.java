package com.henry.gametox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GameTrailerGridAdapter extends ArrayAdapter {
    private final ArrayList<String> gamesTitle;
    private final ArrayList<String> imageUrls;
    private final Context context;

    public GameTrailerGridAdapter(@NonNull Context context, ArrayList<String> gamesTitle, ArrayList<String> imageUrls) {
        super(context, R.layout.free_games_grid_template, gamesTitle);
        this.gamesTitle = gamesTitle;
        this.imageUrls = imageUrls;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View GameTrailerView = convertView;
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            GameTrailerView = inflater.inflate(R.layout.free_games_grid_template, null, true);

        TextView animeText = (TextView) GameTrailerView.findViewById(R.id.FreeGameName);
        ImageView animeImage = (ImageView) GameTrailerView.findViewById(R.id.FreeGameImage);

        animeText.setText(gamesTitle.get(position));
        Picasso.get().load(imageUrls.get(position)).into(animeImage);

        return GameTrailerView;

    }
}
