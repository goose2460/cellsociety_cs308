// This entire file is part of my masterpiece.
// Will Chang
package controller;

import java.util.List;

import simulationObjects.Patch;

/**
 * Toroidal grid rules
 *
 * @author Will Chang
 *
 */
public class ToroidalEdgeRules extends GridEdgeRules {

    public ToroidalEdgeRules (int x, int y, Grid g) {
	super(x, y, g);
    }

    /**
     * Sets up the Toroidal edge rules
     */
    @Override
    public List<Patch> applyRulesAndGetNeighbors (int nextX, int nextY,
	    List<Patch> neighbors) {
	int xWrapped = nextX;
	int yWrapped = nextY;
	if (isOutOfBounds(nextX, nextY)) {
	    xWrapped = wrapCoordAround(nextX, myXBound);
	    yWrapped = wrapCoordAround(nextY, myYBound);
	}
	neighbors.add(myGrid.getPatchAtPoint(xWrapped, yWrapped));
	return neighbors;
    }

    /**
     * Wraps a coordinate around the edges of the grid.
     * Made Public for JUnit Tests
     *
     * @param coord
     *            out of bounds coordinate to wrap around
     * @param max
     *            boundary reference
     * @return the wrapped around coordinate
     */
    public int wrapCoordAround (int coord, int max) {
	int wrappedCoord = coord;
	if (coord > max - 1) {
	    wrappedCoord = 0;
	} 
	else if (coord < 0) {
	    wrappedCoord = max - 1;
	}
	return wrappedCoord;
    }
}
