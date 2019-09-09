package edu.mills.overflo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int sideSize = 3;
    private static int size;
    private static TextView[] textViews;
    private static Grid g;

    public static void drawGrid() {
        for (final Square s : g.getSquares()) {
            int i = s.getId();
            textViews[i].setText(Integer.toString(s.getValue()));
        }
    }

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
                    g.increment(s);
                    drawGrid();
                }

                ;
            });
            gridLayout.addView(textViews[i]);
        }
    }
}
