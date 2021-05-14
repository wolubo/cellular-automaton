/**
 * @author Wolfgang Bongartz
 */
package de.wolfgang_bongartz.cellular_automaton.gui;

import de.wolfgang_bongartz.cellular_automaton.automaton.Coordinate;
import de.wolfgang_bongartz.cellular_automaton.automaton.Habitat;
import de.wolfgang_bongartz.cellular_automaton.automaton.Inhabitant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * @author Wolfgang Bongartz
 *
 */
public class DrawPanel extends JPanel  implements ComponentListener {
	
	private static final long serialVersionUID = 6304707211618785868L;
	private Habitat _habitat;

	/**
	 * Constructor 
	 */
	public DrawPanel() {
		init();
	}

	/**
	 * Constructor 
	 * @param arg0
	 */
	public DrawPanel(LayoutManager arg0) {
		super(arg0);
		init();
	}

	/**
	 * Constructor 
	 * @param arg0
	 */
	public DrawPanel(boolean arg0) {
		super(arg0);
		init();
	}

	/**
	 * Constructor 
	 * @param arg0
	 * @param arg1
	 */
	public DrawPanel(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		init();
	}

	private void init() {
		this.addComponentListener(this);
		_habitat=null;
	}
	
	public void setHabitat(Habitat h) {
		_habitat = h;
		repaint();
	}
	
	@Override
	  protected void paintComponent( Graphics g )
	  {
	    super.paintComponent( g );
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setColor(Color.BLUE);
	    if(_habitat!=null) {
	    	for(int x=_habitat.getMinX(); x<_habitat.getMaxX(); x++) {
		    	for(int y=_habitat.getMinY(); y<_habitat.getMaxY(); y++) {
		    		Coordinate c = new Coordinate(x,y,null);
			    	Inhabitant i = _habitat.getInhabitant(c);
			    	if(i!=null) {
			    		Color col = i.getColor();
			    	    g2.setColor(col); 
			    		g2.drawLine(2*x,2*y,2*x,2*y+1);
			    		g2.drawLine(2*x+1,2*y,2*x+1,2*y+1);
			    	}
		    	}
	    	}
	    }
	  }

	@Override
	public void componentHidden(ComponentEvent arg0) {
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
//		Dimension d = getSize();
//		if(_habitat!=null) {
//			_habitat.resize(d.width, d.height);
//		}
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
	}

}
