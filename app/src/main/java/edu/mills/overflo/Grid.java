package edu.mills.overflo;

import java.util.ArrayList;

public class Grid {
    private int size;
    private int itemCount;
    private ArrayList<Square> squares;

    public Grid(int gridSize) {
        size = gridSize;
        itemCount = gridSize * gridSize;
        createGrid();
    }

    private void createGrid() {
        squares = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            squares.add(new Square(i));
        }
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }

    public void increment(Square s){
        increment(s, new ArrayList<Square>());
    }

    private void increment(Square s, ArrayList<Square> neighbors) {
        s.increment();
        System.out.println(s.toString() + s.getOverflowStatus());
        if (s.getOverflowStatus()) {
            for (Square square : neighbors) {
                MainActivity.drawGrid();
                increment(square, findNeighbors(square));
            }
        }
    }

    private ArrayList<Square> findNeighbors(Square square) {
        ArrayList<Square> neighbors = new ArrayList<>();
        int current = square.getId();
        int top = current - size;
        if (top >= 0) {
            neighbors.add(squares.get(top));
        }
        int left = current - 1;
        if (left % size != size - 1 && left >= 0) {
            neighbors.add(squares.get(left));
        }
        int right = current + 1;
        if (right % size != 0 && right >= 0) {
            neighbors.add(squares.get(right));
        }
        int bottom = current + size;
        if (bottom < itemCount) {
            neighbors.add(squares.get(bottom));
        }
        return neighbors;
    }
}
