package com.saidrobley.citydetail;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    private ColorWheel mColorWheel = new ColorWheel();
    static int i = 0;

    static String[] population = { "600,158", "416,427", "325,078", "143,986", "142,980",
            "118,772", "106,595", "106,433", "106,114", "100,377" };

    static String[] mayor = {"Michael Hancock", "Steve Bach", "Steve Hogan", "Karen Weitkunat",
    "Bob Murphy", "Erik Hansen", "Barbara Vidmar", "Bob Frie", "Nancy McNally", "Cathy Noon"};

    public static DetailsFragment newInstance(int index) {
        DetailsFragment f = new DetailsFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);

        i = index;
        f.setArguments(args);

        return f;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        int colorTv1 = mColorWheel.getColor();
        int colorTv2 = mColorWheel.getColor();
        int colorTv3 = mColorWheel.getColor();


        View v = getActivity().getLayoutInflater().inflate(
                R.layout.layout_details_content, null);

       // v.setBackgroundColor(color);
        TextView tv1 = (TextView)v.findViewById(R.id.tv1);
        TextView tv2 = (TextView)v.findViewById(R.id.tv2);
        TextView tv3 = (TextView) v.findViewById(R.id.tv3);
        //tv.setText(TitlesFragment.city[getShownIndex()].toUpperCase());

        tv1.setText(TitlesFragment.city[getShownIndex()].toUpperCase());
       // tv1.setTextColor(Color.WHITE);
        tv1.setTextColor(colorTv1);
        tv1.setTextSize(20);

        tv2.setText("POPULATION: " + population[getShownIndex()]);
        tv2.setTextColor(colorTv2);
        tv2.setTextSize(20);

        tv3.setText("MAYOR: " + mayor[getShownIndex()].toUpperCase());
        tv3.setTextColor(colorTv3);
        tv3.setTextSize(20);



        return v;
    }
}
