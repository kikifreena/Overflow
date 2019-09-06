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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout gridLayout = findViewById(R.id.grid);
        size = sideSize * sideSize;
        gridLayout.setColumnCount(sideSize);
        textViews = new TextView[size];
        for (int i = 0; i < size; i++) {
            textViews[i] = new TextView(this);
            textViews[i].setText(R.string.x);
            textViews[i].setTextSize(100);
            textViews[i].setPadding(10, 10, 10, 10);
            textViews[i].setId(i);
            gridLayout.addView(textViews[i]);
        }
        for (final TextView textView : textViews) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAllValues(textView);
                }
            });
        }
    }

    private void setAllValues(TextView textView) {
        setNextValue(textView, textView.getText().toString());
    }

    private void setNextValue(TextView textView, String current) {
        if (current.equals("1")) {
            textView.setText("2");
        } else if (current.equals("2")) {
            textView.setText("3");
        } else if (current.equals("3")) {
            textView.setText("1");
            ArrayList<Integer> neighbors = findNeighbors(textView.getId());
            System.out.println(neighbors);
            for (int n : neighbors) {
                System.out.println(textViews[n].getText().toString());
                setNextValue(textViews[n], textViews[n].getText().toString());
            }
        }
    }

    private ArrayList<Integer> findNeighbors(int current) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        int top = current - sideSize;
        if (top >= 0) {
            neighbors.add(top);
        }
        int left = current - 1;
        if (left % sideSize != sideSize - 1 && left >=0) {
            neighbors.add(left);
        }
        int right = current + 1;
        if (right % sideSize != 0 && right >=0) {
            neighbors.add(right);
        }
        int bottom = current + sideSize;
        if (bottom < size) {
            neighbors.add(bottom);
        }
        return neighbors;
    }
}
