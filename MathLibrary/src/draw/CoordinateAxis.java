/*******************************************************************************
 * Copyright (C) 2018-2019 Retopall Services
 * For more information please check www.retopall.com
 * RetopMathUtils can not be copied and/or distributed without the express
 * permission of dDev Tech
 ******************************************************************************/

package draw;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import general.MathUtils;

public class CoordinateAxis implements Drawable{
	public static int MARGIN=50;
	public static int LINESX=10;
	public static int LINESY=10;
	private double XAxisReal;
	private double YAxisReal;
	private double defaultXAxis;
	private double defaultYAxis;
	private double initialXAxisReal;
	private double initialYAxisReal;
	public CoordinateAxis() {
		update();
		XAxisReal=MathViewerConstants.WIDTH_SCREEN-2*MARGIN;
		YAxisReal=MathViewerConstants.HEIGHT_SCREEN-2*MARGIN;
		initialXAxisReal=XAxisReal;
		initialYAxisReal=YAxisReal;
	}
	public void update() {
		setDefaultXAxis(MathViewerConstants.WIDTH_SCREEN-2*MARGIN);
		setDefaultYAxis(MathViewerConstants.HEIGHT_SCREEN-2*MARGIN);
		
	

	}
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2f));
		g.setColor(MathViewerConstants.mainColor);
		g.drawLine(MARGIN, MathViewerConstants.HEIGHT_SCREEN-MARGIN, MathViewerConstants.WIDTH_SCREEN-MARGIN, MathViewerConstants.HEIGHT_SCREEN-MARGIN);
		g.drawLine(MARGIN, MathViewerConstants.HEIGHT_SCREEN-MARGIN, MARGIN, MARGIN);
		g.drawString(Double.toString(MathUtils.roundDecimals(2,XAxisReal)), MathViewerConstants.WIDTH_SCREEN-MARGIN, MathViewerConstants.HEIGHT_SCREEN-MARGIN+10);
		g.drawString(Double.toString(MathUtils.roundDecimals(2,YAxisReal)),MARGIN, MARGIN-10);
		double x=defaultXAxis/(double)LINESX;;
		g2.setStroke(new BasicStroke(1f));
		for(int i=0;i<LINESX;i++) {
			g.drawLine((int)(i*x+CoordinateAxis.MARGIN), MathViewerConstants.HEIGHT_SCREEN-CoordinateAxis.MARGIN, (int)(i*x+CoordinateAxis.MARGIN), MathViewerConstants.HEIGHT_SCREEN-CoordinateAxis.MARGIN+5);
			g.drawString(Double.toString(MathUtils.roundDecimals(2, (x*i*XAxisReal/(double)defaultXAxis))),(int)(i*x+CoordinateAxis.MARGIN)+3, MathViewerConstants.HEIGHT_SCREEN-CoordinateAxis.MARGIN+15);
		}
		double y=defaultYAxis/(double)LINESY;
		
		for(int i=0;i<LINESY;i++) {
			
			g.drawLine(CoordinateAxis.MARGIN,(int)(MathViewerConstants.HEIGHT_SCREEN-i*y-CoordinateAxis.MARGIN) , (int)(CoordinateAxis.MARGIN-5), (int)(MathViewerConstants.HEIGHT_SCREEN-i*y-CoordinateAxis.MARGIN));
			g.drawString(Double.toString(MathUtils.roundDecimals(2, (y*i*YAxisReal/(double)defaultYAxis))),CoordinateAxis.MARGIN-40,(int)(MathViewerConstants.HEIGHT_SCREEN-i*y-CoordinateAxis.MARGIN-3));
		}
	}
	public double getXAxis() {
		return XAxisReal;
	}
	public void setXAxis(double xAxis) {
		XAxisReal = xAxis;
	}
	public double getYAxis() {
		return YAxisReal;
	}
	public void setYAxis(double yAxis) {
		this.YAxisReal = yAxis;
	}
	public double getDefaultYAxis() {
		return defaultYAxis;
	}
	public void setDefaultYAxis(double defaultYAxis) {
		this.defaultYAxis = defaultYAxis;
	}
	public double getDefaultXAxis() {
		return defaultXAxis;
	}
	public void setDefaultXAxis(double defaultXAxis) {
		this.defaultXAxis = defaultXAxis;
	}
	public double getInitialYAxisReal() {
		return initialYAxisReal;
	}
	public void setInitialYAxisReal(double initialYAxisReal) {
		this.initialYAxisReal = initialYAxisReal;
	}
	public double getInitialXAxisReal() {
		return initialXAxisReal;
	}
	public void setInitialXAxisReal(double initialXAxisReal) {
		this.initialXAxisReal = initialXAxisReal;
	}
	
}
