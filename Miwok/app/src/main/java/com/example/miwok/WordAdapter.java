package com.example.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter<Word> {

        private int mColorResourceId;
        /**
                * Create a new {@link WordAdapter} object.
     *
             * @param context is the current context (i.e. Activity) that the adapter is being created in.
                * @param words is the list of {@link Word}s to be displayed.
                * @param colorResourceId is the resource ID for the background color for this list of words
     */


        public WordAdapter(Activity context, ArrayList<Word> androidFlavors, int colorResourceId) {
                // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
                // the second argument is used when the ArrayAdapter is populating a single TextView.
                // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
                // going to use this second argument, so it can be any value. Here, we used 0.
                super(context, 0, androidFlavors);
                mColorResourceId = colorResourceId;
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
        listItemView = LayoutInflater.from(getContext()).inflate(
        R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());
        // Find the ImageView in the list_item.xml layout with the ID list_item_icon

        ImageView miwokImageView = (ImageView) listItemView.findViewById(R.id.miwok_image_view);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        if(currentWord.hasImage()) {
                miwokImageView.setImageResource(currentWord.getImageResourceId());
                miwokImageView.setVisibility(View.VISIBLE);
        }
        else
        {
                miwokImageView.setVisibility(View.GONE);
        }
        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        return listItemView;
        }

        }