package edu.mills.overflo;

class Square {
    private boolean overflowed;
    private int value;
    private int id;

    Square(int id) {
        this.value = 0;
        overflowed = false;
        this.id = id;
    }

    int getValue() {
        return value;
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
