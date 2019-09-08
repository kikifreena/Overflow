package edu.mills.overflo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int sideSize = 4;
    private static int size;
    private static TextView[] textViews;
    private static Grid g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout gridLayout = findViewById(R.id.grid);
        size = sideSize * sideSize;
        gridLayout.setColumnCount(sideSize);
        textViews = new TextView[size];
        g = new Grid(sideSize);
        for (final Square s : g.getSquares()) {
            int i = s.getId();
            textViews[i] = new TextView(this);
            textViews[i].setText(Integer.toString(s.getValue()));
            textViews[i].setTextSize(100);
            textViews[i].setPadding(10, 10, 10, 10);
            textViews[i].setId(i);
            textViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    g.increment(s, g.findNeighbors(s), new ArrayList<Square>(), new ArrayList<Square>());
                    drawGrid();
                };
            });
            gridLayout.addView(textViews[i]);
        }
    }

    private void drawGrid(){
        for (final Square s : g.getSquares()) {
            int i = s.getId();
            textViews[i].setText(Integer.toString(s.getValue()));
        }
    }

//    private void setAllValues(TextView textView) {
//        boolean status = getNextValue(textView); // Does the current one overflow?
//        if (status == true) {
//            //ArrayList<Integer> neighbors = findNeighbors(textView.getId());
////            for (int n : neighbors) { // Check if each of the neighbors overflow
////                setAllValues(textViews[n]); // Increase those and check their neighbors
////            }
////        }
////        setNextValue(textView); // Increase current one
//    }
//
//    private void setNextValue(TextView textView) {
//        String current = textView.getText().toString();
//        if (current.equals("2")) {
//            textView.setText("0");
//        } else {
//            int total = ((Integer.parseInt(current) + 1) % 3);
//            textView.setText(Integer.toString(total));
//        }
//    }
//
////    private ArrayList<Integer> hasDoubleOverflow(ArrayList<Integer> neighbors){
////
////        ArrayList<Integer> result = new ArrayList<>();
////        ArrayList<Integer> overflowNeighbors = new ArrayList<>();
////        for (int n : neighbors){
////            if (textViews[n].getText().toString().equals("2")){
////                overflowNeighbors.add(n);
////            }
////        }
////        for (int n : overflowNeighbors){
////            if (overflowNeighbors.contains(n + sideSize - 1) && n%sideSize != 1){
////                result.add(n-1);
////            }
////            if (overflowNeighbors.contains(n + sideSize + 1)){
////                result.add(n+1);
////            }
////
////        }
//
}
