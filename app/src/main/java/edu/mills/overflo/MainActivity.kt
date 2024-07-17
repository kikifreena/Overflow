package edu.mills.overflo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var textViews: Array<TextView?>
    private var size = 0
    private var g: Grid? = null
    private var next = 0

    private fun drawGrid() {
        for (s in g!!.squares) {
            val i = s.id
            textViews[i]!!.text = s.value.toString()
            if (s.player == 1) {
                textViews[i]!!.setTextColor(resources.getColor(R.color.blue, theme))
            }
            if (s.player == 2) {
                textViews[i]!!.setTextColor(resources.getColor(R.color.gold, theme))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gridLayout = findViewById<GridLayout>(R.id.grid)
        size = sideSize * sideSize
        gridLayout.columnCount = sideSize
        textViews = arrayOfNulls(size)
        g = Grid(sideSize)
        for (s in g!!.squares) {
            val i = s.id
            textViews[i] = TextView(this)
            textViews[i]!!.text = s.value.toString()
            textViews[i]!!.textSize = 80f
            textViews[i]!!.setPadding(5, 5, 5, 5)
            textViews[i]!!.id = i
            textViews[i]!!.setTextColor(Color.GRAY)
            textViews[i]!!.setOnClickListener {
                next = g!!.oneTurn(s)
                val colorView = findViewById<View>(R.id.turn_color)
                val textView = findViewById<TextView>(R.id.turn_text)
                if (next == 1) {
                    colorView.setBackgroundColor(resources.getColor(R.color.blue))
                    textView.setText(R.string.player_1_turn)
                }
                if (next == 2) {
                    colorView.setBackgroundColor(resources.getColor(R.color.gold))
                    textView.setText(R.string.player_2_turn)
                }
                drawGrid()
            }
            gridLayout.addView(textViews[i])
        }
    }

    companion object {
        private const val sideSize = 4
    }
}
