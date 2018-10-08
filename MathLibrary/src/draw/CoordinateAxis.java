/*******************************************************************************
 * Copyright (C) 2018-2019 Retopall Services
 * For more information please check www.retopall.com
 * RetopMathUtils can not be copied and/or distributed without the express
 * permission of dDev Tech
 ******************************************************************************/

package draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class CoordinateAxis implements Drawable{
	public static int MARGIN=50;
	public static int LINESX=10;
	public static int LINESY=10;
	private int XAxisReal;
	private int YAxisReal;
	private int defaultXAxis;
	private int defaultYAxis;
	public CoordinateAxis() {
	
		setDefaultXAxis(MathViewerConstants.WIDTH_SCREEN-2*MARGIN);
		setDefaultYAxis(MathViewerConstants.HEIGHT_SCREEN-2*MARGIN);
		XAxisReal=MathViewerConstants.WIDTH_SCREEN-2*MARGIN;
		YAxisReal=MathViewerConstants.HEIGHT_SCREEN-2*MARGIN;
	}
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2f));
		g.setColor(Color.black);
		g.drawLine(MARGIN, MathViewerConstants.HEIGHT_SCREEN-MARGIN, MathViewerConstants.WIDTH_SCREEN-MARGIN, MathViewerConstants.HEIGHT_SCREEN-MARGIN);
		g.drawLine(MARGIN, MathViewerConstants.HEIGHT_SCREEN-MARGIN, MARGIN, MARGIN);
		g.drawString(Integer.toString(XAxisReal), MathViewerConstants.WIDTH_SCREEN-MARGIN, MathViewerConstants.HEIGHT_SCREEN-MARGIN+10);
		g.drawString(Integer.toString(YAxisReal),MARGIN, MARGIN-10);
		double x=defaultXAxis/LINESX;;
		for(int i=0;i<LINESX;i++) {
			g.drawLine((int)(i*x+CoordinateAxis.MARGIN), MathViewerConstants.HEIGHT_SCREEN-CoordinateAxis.MARGIN, (int)(i*x+CoordinateAxis.MARGIN), MathViewerConstants.HEIGHT_SCREEN-CoordinateAxis.MARGIN+10);
			g.drawString(Integer.toString((int)(x*i*XAxisReal/defaultXAxis)),(int)(i*x+CoordinateAxis.MARGIN)+3, MathViewerConstants.HEIGHT_SCREEN-CoordinateAxis.MARGIN+15);
		}
		double y=defaultYAxis/LINESY;;
		for(int i=0;i<LINESX;i++) {
			g.drawLine(CoordinateAxis.MARGIN,(int)(MathViewerConstants.HEIGHT_SCREEN-i*y-CoordinateAxis.MARGIN) , (int)(CoordinateAxis.MARGIN-10), (int)(MathViewerConstants.HEIGHT_SCREEN-i*y-CoordinateAxis.MARGIN));
			g.drawString(Integer.toString((int)(y*i*YAxisReal/defaultYAxis)),CoordinateAxis.MARGIN-20,(int)(MathViewerConstants.HEIGHT_SCREEN-i*y-CoordinateAxis.MARGIN-3));
		}
	}
	public int getXAxis() {
		return XAxisReal;
	}
	public void setXAxis(int xAxis) {
		XAxisReal = xAxis;
	}
	public int getYAxis() {
		return YAxisReal;
	}
	public void setYAxis(int yAxis) {
		this.YAxisReal = yAxis;
	}
	public int getDefaultYAxis() {
		return defaultYAxis;
	}
	public void setDefaultYAxis(int defaultYAxis) {
		this.defaultYAxis = defaultYAxis;
	}
	public int getDefaultXAxis() {
		return defaultXAxis;
	}
	public void setDefaultXAxis(int defaultXAxis) {
		this.defaultXAxis = defaultXAxis;
	}
	
}
