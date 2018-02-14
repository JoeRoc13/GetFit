package com.example.atouf.workoutapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class FridayFragment extends Fragment {
    Cursor c = null;
    View rootView;
    SwipeRefreshLayout mySwipeRefreshLayout;
    int min = 1;
    int max = 100;
    String reps;
    String sets = null;
    String seconds = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.workout_list, container, false);
        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomize();
            }
        });

        mySwipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh);
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        randomize();
                    }
                }
        );

        randomize();
        return rootView;

    }

    private void getSetsAndReps() {
        int randomNum = min + (int)(Math.random() *((max - min) + 1));
        reps = Integer.toString(randomNum);
        if(randomNum <= 25){
            sets = "Sets: 5";
            reps = "Reps: 3-5";
            seconds = "Time: 30 seconds";
        } else if(randomNum > 25 && randomNum <= 50) {
            sets = "Sets: 4";
            reps = "Reps: 12-15";
            seconds = "Time: 45 seconds";
        } else if(randomNum > 50 && randomNum <= 75) {
            sets = "Sets: 3";
            reps = "Reps: 8-10";
            seconds = "Time: 60 seconds";
        } else if(randomNum > 75) {
            sets = "Sets: 3";
            reps = "Reps: 21-30";
            seconds = "Time: 60 seconds";
        }
    }

    private void randomize() {
        DBHelper myDBHelper = new DBHelper(getContext());
        ArrayList<Workout> workouts = new ArrayList<>();

        try {
            myDBHelper.createDatabase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        try {
            myDBHelper.openDatabase();
        } catch (SQLiteException sqle) {
            throw sqle;
        }

        try {
            c = myDBHelper.rawQuery("select 1 as SortOrder,* from ( select * from workouts where musclegroup=? order by random() limit 5 ) union select 2 as SortOrder, * from ( select * from workouts where musclegroup=? order by random() limit 2 ) order by SortOrder", new String[]{"Legs", "Abs"});
            if (c.moveToFirst()) {
                do {
                    getSetsAndReps();
                    String mDrawableName = c.getString(4).substring(0, c.getString(4).lastIndexOf('.'));
                    int resID = getResources().getIdentifier(mDrawableName, "drawable", getContext().getPackageName());
                    String workout_name = c.getString(1);
                    if(workout_name.contains("Plank")) {
                        workouts.add(new Workout(c.getString(2), c.getString(1), resID, c.getString(3), sets, seconds));
                    } else {
                        workouts.add(new Workout(c.getString(2), c.getString(1), resID, c.getString(3), sets, reps));
                    }
                } while (c.moveToNext());
            }
        } finally {
            c.close();
            myDBHelper.close();
        }

        WorkoutAdapter adapter = new WorkoutAdapter(getActivity(), workouts, R.color.colorWhite);
        ListView listview = rootView.findViewById(R.id.list);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mySwipeRefreshLayout.setRefreshing(false);
    }
}
