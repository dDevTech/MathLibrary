/*******************************************************************************
 * Copyright (C) 2018-2019 Retopall Services
 * For more information please check www.retopall.com
 * RetopMathUtils can not be copied and/or distributed without the express
 * permission of dDev Tech
 ******************************************************************************/

package drawable;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;


import draw.CoordinateAxis;
import draw.Drawable;
import draw.MathViewerConstants;
import space.Point;

public class GraphicalPoint extends Point implements Drawable{

	private int SIZE=3;
	private CoordinateAxis axis;
	public GraphicalPoint(double x, double y,CoordinateAxis axis) {
		super(x, y);
		this.axis = axis;
	}
	public void update(CoordinateAxis axis) {
		this.axis = axis;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(MathViewerConstants.datasetColor);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1f));

	
		int x=(int) (CoordinateAxis.MARGIN+MathViewerConstants.MAXIMUM*(getX())*axis.getDefaultXAxis()/(double)axis.getXAxis()-SIZE/2);
		int y=(int)(MathViewerConstants.HEIGHT_SCREEN -CoordinateAxis.MARGIN-SIZE/2-getY()*axis.getDefaultYAxis()/(double)axis.getYAxis());
	
		g2.fillOval(x,y , SIZE, SIZE);
		if(MathViewerConstants.SHOWTEXT) {
		g2.drawString(getX()+" , "+getY(), x, y);
		}
		g.setColor(MathViewerConstants.helpLines);
		g.drawLine(x+SIZE/2, y+SIZE/2, CoordinateAxis.MARGIN, y+SIZE/2);
		g.drawLine(x+SIZE/2, y+SIZE/2, x+SIZE/2, MathViewerConstants.HEIGHT_SCREEN-CoordinateAxis.MARGIN);
		
	}
	
}
