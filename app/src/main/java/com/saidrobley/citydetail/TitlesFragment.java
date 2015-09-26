package com.saidrobley.citydetail;

/**
 * Created by saidrobley on 9/6/15.
 */
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TitlesFragment extends ListFragment {



    boolean mDualPane;
    int mCurCheckPosition = 0;

    static String city[] = { "Denver", "Colorado Springs", "Aurora","Fort Collins",
            "Lakewood","Thornton", "Pueblo", "Arvada", "Westminster", "Centenial" };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Populate list with our static array of city.
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, city));

        View detailsFrame = getActivity().findViewById(R.id.details);
        mDualPane = detailsFrame !=null && detailsFrame.getVisibility() == View.VISIBLE;

        if(savedInstanceState != null){
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if(mDualPane) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            showDetails(mCurCheckPosition);
        }
        else{
            System.out.println("NOT DUAL");
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

                showDetails(position);
    }


    public void showDetails(int index) {

        mCurCheckPosition = index;
        if(mDualPane) {
            getListView().setItemChecked(index, true);

            DetailsFragment details = (DetailsFragment) getFragmentManager()
                    .findFragmentById(R.id.details);
            if(details == null || details.getShownIndex() != index) {
                details = DetailsFragment.newInstance(index);
                FragmentTransaction fragmentTransaction = getFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.details, details);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();

            }
        }
        else {
            // Otherwise we need to launch a new activity to display
            Intent intent = new Intent();
            intent.setClass(getActivity(), DetailsActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }

    }
}
