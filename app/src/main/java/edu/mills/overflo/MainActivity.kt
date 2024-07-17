package edu.mills.overflo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int sideSize = 4;
    private static int size;
    private static TextView[] textViews;
    private static Grid g;
    private static int next;

    private void drawGrid() {
        for (final Square s : g.getSquares()) {
            int i = s.getId();
            textViews[i].setText(Integer.toString(s.getValue()));
            if (s.getPlayer() == 1) {
                textViews[i].setTextColor(getResources().getColor(R.color.blue));
            }
            if (s.getPlayer() == 2) {
                textViews[i].setTextColor(getResources().getColor(R.color.gold));
            }
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
            textViews[i].setTextSize(80);
            textViews[i].setPadding(5, 5, 5, 5);
            textViews[i].setId(i);
            textViews[i].setTextColor(Color.GRAY);
            textViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    next = g.oneTurn(s);
                    View colorView = findViewById(R.id.turn_color);
                    TextView textView = findViewById(R.id.turn_text);
                    if (next == 1) {
                        colorView.setBackgroundColor(getResources().getColor(R.color.blue));
                        textView.setText(R.string.player_1_turn);
                    }
                    if (next == 2) {
                        colorView.setBackgroundColor(getResources().getColor(R.color.gold));
                        textView.setText(R.string.player_2_turn);
                    }
                    drawGrid();
                }
            });
            gridLayout.addView(textViews[i]);
        }
    }
}
