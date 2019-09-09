package edu.mills.overflo;

import java.util.ArrayList;

public class Grid {
    private int size;
    private int itemCount;
    private ArrayList<Square> squares;
    private int player;

    public Grid(int gridSize) {
        size = gridSize;
        itemCount = gridSize * gridSize;
        player = 1;
        createGrid();
    }

    public int oneTurn(Square s){
        increment(s, 0);
        switch (player){
            case 1:
                player = 2;
                break;
            case 2:
                player = 1;
                break;
        }
        return player;
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

    private void increment(Square s, int depth) {
        ArrayList<Square> neighbors = findNeighbors(s);
        s.increment();
        s.setPlayer(player);
        if (s.getOverflowStatus() && depth < itemCount) {
            for (Square square : neighbors) {
                increment(square, depth + 1);
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
