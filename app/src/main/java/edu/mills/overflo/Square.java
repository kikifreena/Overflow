package edu.mills.overflo;

class Square {
    private boolean overflowed;
    private int value;
    private int id;
    private int player;

    Square(int id) {
        this.value = 0;
        overflowed = false;
        this.id = id;
        player = 0;
    }

    int getValue() {
        return value;
    }

    int getPlayer() {
        return player;
    }

    void setPlayer(int id) {
        player = id;
    }

    @Override
    public String toString() {
        return "ID: " + id + " Val: " + value;
    }

    boolean getOverflowStatus() {
        return overflowed;
    }


    int getId() {
        return id;
    }

    void increment() {
        value++;
        if (value > 2) {
            value = 0;
            overflowed = true;
        } else {
            overflowed = false;
        }
    }
}
