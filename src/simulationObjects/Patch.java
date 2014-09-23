package simulationObjects;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import controller.GridManager;

/**
 * 
 * @author Will Chang
 *
 */
public class Patch extends Group {
    protected Cell myCell;
    protected int xCoord;
    protected int yCoord;
    protected GridManager manager;
    protected ArrayList<Patch> myNeighbors;
    protected Image image;
    protected ImageView myView;
    protected State myState;
    protected PatchBody myBody;

    private int myPreviousCellState;

    private int REMOVE_ME = 0;
    /**
     * Constructors
     */
    public Patch() {
	super();
    }

    public Patch(int x, int y, GridManager m) {
	super();
	xCoord = x;
	yCoord = y;
	manager = m;
	myState = State.EMPTY;

    }
    
    public void createBody(){
        int patchHeight = (int) (manager.getMinHeight()/manager.getGridHeight());
        int patchWidth = (int) (manager.getMinWidth()/manager.getGridWidth());
        myBody = new PatchBody(xCoord,yCoord,patchHeight,patchWidth);
        getChildren().add(myBody);
    }

    protected enum State {
	EMPTY, GENERATING, OCCUPIED, EMPTYING
    }

    /**
     * For Special patch simulations.
     * 
     * @param x
     *            loc
     * @param y
     *            loc
     * @param m
     *            manager
     */
    public void initialize(int x, int y, GridManager m) {
	xCoord = x;
	yCoord = y;
	manager = m;
	myState = State.EMPTY;
    }

    /**
     * Returns surrounding Patches
     */
    public void getNeighbors() {
	myNeighbors = manager.getNeighborsAround(xCoord, yCoord);
    }

    /**
     * Gets the cell
     * 
     * @return myCell
     */
    public Cell getCell() {
	return myCell;
    }

    public int getGridX() {
	return xCoord;
    }

    public int getGridY() {
	return yCoord;
    }

    /**
     * Sets up the update
     */
    public void prepareToUpdate() {
	if (myCell != null) {
	    myCell.prepareToUpdate(this, myNeighbors);
	}
    }

    /**
     * Updates patch and the cell
     */
    public void update() {
        // Update this
        if (myCell != null) {
            myCell.update(this, myNeighbors);
            //TODO put 0 into the GridInfo/GameInfo
            if (myCell!= null && myCell.getState() == 0) {
                this.removeCell();
            }
        }

    }

    // modified the order for processing 4 directions
    protected enum relativePosition {
	NORTH, SOUTH, EAST, WEST, SOUTHEAST, NORTHEAST, SOUTHWEST, NORTHWEST
    }

    public boolean isEmpty() {
	return myCell == null;
    }

    /**
     * Adds cell along with GUI info.
     * 
     * @param cell
     *            to occupy patch
     */
    public void addCell(Cell cell) {
	if (myCell != null) {
	    removeCell();
	}
	cell.setHeight(myBody.getHeight());
	cell.setWidth(myBody.getWidth());
	cell.setLayoutX(myBody.getStartX());
	cell.setLayoutY(myBody.getStartY());
	cell.setArcHeight(cell.getHeight());
	cell.setArcWidth(cell.getWidth());
	getChildren().add(cell);
	myState = State.OCCUPIED;
	myCell = cell;
    }

    public void removeCell() {
	if (myCell != null) {
	    getChildren().remove(myCell);
	    myCell = null;
	    myState = State.EMPTY;
	}
    }

    public Patch randomEmptyPatch() {
	// get an empty patch from grid manager
	return manager.findEmptyPatch();
    }

    public int getPreviousState() {
	return myPreviousCellState;
    }

    public void setPreviousState(int myState) {
	myPreviousCellState = myState;
    }

}
