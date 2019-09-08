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

    public void increment(Square s, ArrayList<Square> neighbors, ArrayList<Square> neighborsToSkip1, ArrayList<Square> neighborsToSkip2) {
        s.increment();
        if (s.getOverflowStatus()) {
            //ArrayList<Square> neighborsToSkip = new ArrayList<>();
            for (Square square : neighbors) {
                ArrayList<Square> neighborsToSkip = new ArrayList<>();
                for (Square sq : neighbors) {
                    int adjNeighbor = incrementAdjacentNeighbors(sq, square, s);
                    if (adjNeighbor != -1) {
                        Square adjSquare = getSquares().get(adjNeighbor);
                        neighborsToSkip.add(getSquares().get(adjNeighbor));
                        System.out.println(adjSquare);
                        adjSquare.increment();
                        adjSquare.increment();
                        System.out.println(adjSquare);
                    }
                }
                if (!neighborsToSkip1.contains(square)){
                    neighbors.remove(neighborsToSkip1);
                    increment(square, findNeighbors(square), neighborsToSkip2, neighborsToSkip);
                }
            }
            s.overflowResolved();
        }
    }

    private int incrementAdjacentNeighbors(Square s1, Square s2, Square current_square) {
        if (s1.getValue() == s2.getValue() && s1.getValue() == 2) {
            int a = s1.getId();
            int b = s2.getId();
            int c = current_square.getId();
            int d = -1;
            if (b - a == 1) {
                d = c + 1;
            }
            if (a - b == 1) {
                d = c - 1;
            }
            if (a - c == 1) {
                d = b - 1;
            }
            if (c - a == 1) {
                d = b + 1;
            }
            if (d < size * size ){
                return d;
            }
        }
        return -1;
    }


     ArrayList<Square> findNeighbors(Square square) {
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
