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

public class UpcomingGamesGridAdapter extends ArrayAdapter {
    private ArrayList<String> gamesTitle;
    private ArrayList<String> imageUrls;
    private Context context;

    public UpcomingGamesGridAdapter(@NonNull Context context, ArrayList<String> gamesTitle, ArrayList<String> imageUrls) {
        super(context, R.layout.upcoming_games_grid_template, gamesTitle);
        this.gamesTitle = gamesTitle;
        this.imageUrls = imageUrls;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View UpcomingGamesView = convertView;
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            UpcomingGamesView = inflater.inflate(R.layout.upcoming_games_grid_template, null, true);

        TextView animeText = (TextView) UpcomingGamesView.findViewById(R.id.GameName);
        ImageView animeImage = (ImageView) UpcomingGamesView.findViewById(R.id.GameImage);

        animeText.setText(gamesTitle.get(position));
        Picasso.get().load(imageUrls.get(position)).into(animeImage);

        return UpcomingGamesView;
    }
}
