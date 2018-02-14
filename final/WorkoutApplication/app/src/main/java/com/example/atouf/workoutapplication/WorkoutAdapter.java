package com.example.atouf.workoutapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by atouf on 11/15/2017.
 */

public class WorkoutAdapter extends ArrayAdapter<Workout>{
    private int wColorResourceId;
    public WorkoutAdapter(Context context, ArrayList<Workout> workouts, int colorResourceId){
        super(context, 0,workouts);
        wColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final Workout currentWorkout = getItem(position);

        TextView workoutTv = (TextView)listItemView.findViewById(R.id.workout_text_view);
        workoutTv.setText(currentWorkout.getType());

        TextView defaultTextView = (TextView)listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWorkout.getResult());

        TextView setsTv = (TextView)listItemView.findViewById(R.id.sets);
        setsTv.setText(currentWorkout.getSets());

        TextView repsTv = (TextView)listItemView.findViewById(R.id.reps);
        repsTv.setText(currentWorkout.getReps());



        ImageView imageView = (ImageView)listItemView.findViewById(R.id.image);

        if(currentWorkout.hasImage()){
            imageView.setImageResource(currentWorkout.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }else {
            imageView.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);

        int color = ContextCompat.getColor(getContext(), wColorResourceId);

        textContainer.setBackgroundColor(color);
        if(currentWorkout.hasLink()){
            textContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                            currentWorkout.getYoutube()
                    )));
                }
            });
        }

        return listItemView;

    }
}

