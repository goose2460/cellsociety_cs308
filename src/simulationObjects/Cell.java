package simulationObjects;

import java.util.ArrayList;

public abstract class Cell {
    protected Patch myPatch;
    protected int myX;
    protected int myY;
    protected int myState;
    public Cell() {

    }

    

    public abstract int getState();

    public abstract void setState(int state);

    public abstract Patch update(Patch currentPatch, ArrayList<Patch> neighbors);

    public abstract void prepareToUpdate(ArrayList<Cell> neighbors);

    public abstract boolean needUpdate(ArrayList<Cell> neighbors);

    public int getX() {
	return myX;
    }

    public int getY() {
	return myY;
    }

    public void setX(int x) {
	myX = x;
    }

    public void setY(int y) {
	myY = y;
    }

}
