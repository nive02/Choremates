package com.example.choremates;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Chore> implements View.OnClickListener {

    ArrayList<Chore> choreSet;
    Context context;
    public static ImageView delete;
    ImageView image;
    String email;
    TextView blurb;
    public static int position1;
    String past;


    // View lookup cache
    private static class ViewHolder {
        TextView name;
        ImageView icon;
    }

    public CustomAdapter(ArrayList<Chore> chore, Context context, String e, TextView t, String s) {
        super(context, R.layout.custom_row, chore);
        this.choreSet = chore;
        this.context = context;
        blurb = t;
        email = e;
        past = s;
    }

    @Override
    public void onClick(View v) {

    }

    private int lastPosition = -1;
    private static boolean edit;



    private ImageView getImage(ImageView i){
        return i;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Chore ch = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_row, null);
        image = convertView.findViewById(R.id.icon);
        TextView title = convertView.findViewById(R.id.name);
        delete = convertView.findViewById(R.id.delete);

        if (past.equals("ChoresActivity")) {
            if (edit) {
                delete.setVisibility(View.VISIBLE);
                delete.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
                        final View view = LayoutInflater.from(context).inflate(R.layout.layout_alert_dialog_2, null);
                        builder.setView(view);
                        ((TextView)view.findViewById(R.id.message_text2)).setText("Are you sure you want\nto delete this chore?\n This action cannot be\nundone.");
                        ((TextView)view.findViewById(R.id.title_text_alert2)).setText("Delete Chore?");
                        final AlertDialog alertDialog = builder.create();
                        final DatabaseHelper db = new DatabaseHelper(context);
                        view.findViewById(R.id.pos_button2).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                db.deleteChore(email, ch.getName());
                                choreSet.remove(position);
                                if (choreSet.size() == 0) {
                                    blurb.setVisibility(View.VISIBLE);
                                }
                                notifyDataSetChanged();
                                alertDialog.dismiss();
                            }
                        });
                        view.findViewById(R.id.neg_button2).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                        if (alertDialog.getWindow() != null){
                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                        }
                        alertDialog.show();

                    }
                });
            }
            image.setImageResource(ch.getImage());
            title.setText(ch.getName());
        } else if (past.equals("RoommatesActivity")){
            image.setVisibility(View.GONE);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) title.getLayoutParams();
            params.leftMargin = 35;
            if (edit) {
                delete.setVisibility(View.VISIBLE);
                delete.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        final DatabaseHelper db = new DatabaseHelper(context);
                        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
                        final View view = LayoutInflater.from(context).inflate(R.layout.layout_alert_dialog_2, null);
                        builder.setView(view);
                        ((TextView)view.findViewById(R.id.message_text2)).setText("Are you sure you want\nto delete this roommate?\nAll their chores will also\n" +
                                            "be deleted. This action\n cannot be undone.");
                        ((TextView)view.findViewById(R.id.title_text_alert2)).setText("Delete Roommate?");
                        final AlertDialog alertDialog = builder.create();
                        view.findViewById(R.id.pos_button2).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                db.deleteRoommate(email, "roommate"+(position+1));
                                choreSet.remove(position);
                                if (choreSet.size() == 0) {
                                    blurb.setVisibility(View.VISIBLE);
                                }
                                notifyDataSetChanged();
                                alertDialog.dismiss();
                            }
                        });
                        view.findViewById(R.id.neg_button2).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                        if (alertDialog.getWindow() != null){
                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                        }
                        alertDialog.show();
                    }
                });
            }
            title.setText(ch.getName());
        }

        return convertView;
    }
    public void edit(boolean s){ edit = s; }

    public static int getSize(){
        return position1;
    }
}
