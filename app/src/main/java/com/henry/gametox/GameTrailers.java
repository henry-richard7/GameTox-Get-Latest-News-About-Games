package com.henry.gametox;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.github.kevinsawicki.http.HttpRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameTrailers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameTrailers extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameTrailers() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameTrailers.
     */
    // TODO: Rename and change types and number of parameters
    public static GameTrailers newInstance(String param1, String param2) {
        GameTrailers fragment = new GameTrailers();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private GridView gridView;
    private final ArrayList<String> GamesTitles = new ArrayList<>();
    private final ArrayList<String> imagesUrls = new ArrayList<>();
    private final ArrayList<String> gameUrls = new ArrayList<>();

    public GameTrailerGridAdapter trailerGridAdapter;


    public class GetGameTrailers extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String request = HttpRequest.get("https://gx-proxy.operacdn.com/trailers").body();
            JSONParser parser = new JSONParser();

            try {
                JSONArray gamesArray = (JSONArray) parser.parse(request);

                for (Object game : gamesArray) {
                    JSONObject object = (JSONObject) game;
                    gameUrls.add(object.get("url").toString());
                    GamesTitles.add(object.get("title").toString());
                    imagesUrls.add(object.get("thumbnail").toString());
                    gameUrls.add(object.get("url").toString());

                }


            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            trailerGridAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_trailers, container, false);


        gridView = (GridView) view.findViewById(R.id.GameTrailers_GridView);

        trailerGridAdapter = new GameTrailerGridAdapter(inflater.getContext(), GamesTitles, imagesUrls);
        gridView.setAdapter(trailerGridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(gameUrls.get(position)));
                startActivity(intent);
            }
        });

        GetGameTrailers gameTrailers = new GetGameTrailers();
        gameTrailers.execute("");

        return view;


    }
}