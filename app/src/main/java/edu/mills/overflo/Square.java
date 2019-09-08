package edu.mills.overflo;

class Square {
    private boolean overflowed;
    private int value;
    private int id;

    public Square(int id) {
        this.value = 0;
        overflowed = false;
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ID: " + id + " Val: " + value;
    }

    public boolean getOverflowStatus() {
        return overflowed;
    }

    public void overflowResolved(){
        overflowed = false;
    }

    public int getId() {
        return id;
    }

    public void increment() {
        value++;
        if (value > 2) {
            value = value % 3;
            overflowed = true;
        }
    }
}
