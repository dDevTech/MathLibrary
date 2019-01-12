/*******************************************************************************
 * Copyright (C) 2018-2019 Retopall Services
 * For more information please check www.retopall.com
 * RetopMathUtils can not be copied and/or distributed without the express
 * permission of dDev Tech
 ******************************************************************************/

package drawable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import draw.CoordinateAxis;
import draw.Drawable;
import draw.MathViewerConstants;
import stadistic.Regression;

public class Function implements Drawable {
	private double[] polynomial;
	private CoordinateAxis axis;
	private BasicStroke stroke;
	private int id = 0;
	private float increaseFunction = 1f;
	private Color colorFunction = MathViewerConstants.drawColor;

	public Function(double[] polynomial, CoordinateAxis axis, int id) {
		this.polynomial = polynomial;
		this.axis = axis;
		stroke = new BasicStroke(1.5f);
		this.setId(id);
	}

	public void update(CoordinateAxis axis) {
		this.axis = axis;
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
	
		g2.setStroke(stroke);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(getColorFunction());
		String equation = Regression.getStringFunction(polynomial, 5, id);
		g.drawString(equation, 200, 40 + getId() * 15);
		int previousX = 0;
		int previousY = 0;
		// axis.getXAxis() / (double) axis.getDefaultXAxis()
		for (double i = 0; i < axis.getXAxis(); i +=increaseFunction) {
			double fx = 0;
			for (int j = polynomial.length - 1; j >= 0; j--) {
				if (j == 0) {
					fx += polynomial[j];
				} else {
					fx += polynomial[j] * Math.pow(i, j);
				}

			}

			int x = (int) (CoordinateAxis.MARGIN
					+ MathViewerConstants.MAXIMUM * i * (double) axis.getDefaultXAxis() / (double) axis.getXAxis());
			int y = (int) (MathViewerConstants.HEIGHT_SCREEN - CoordinateAxis.MARGIN
					- (fx * (double) axis.getDefaultYAxis() / (double) axis.getYAxis()));
			if (i == 0) {
				previousX = x;
				previousY = y;

			} else {
				if (x != previousX) {
					if (y > CoordinateAxis.MARGIN && y<MathViewerConstants.HEIGHT_SCREEN-CoordinateAxis.MARGIN) {
						if (i > 0) {
							g2.drawLine(x, y, previousX, previousY);

							previousX = x;
							previousY = y;
						}
					} else if(y < CoordinateAxis.MARGIN ){
						if (previousY > CoordinateAxis.MARGIN || y > CoordinateAxis.MARGIN) {
							y = CoordinateAxis.MARGIN;
							g2.drawLine(x, y, previousX, previousY);
							previousX = x;
							previousY = y;
						}
					}else {
						
						if (previousY < MathViewerConstants.HEIGHT_SCREEN-CoordinateAxis.MARGIN) {
							y = MathViewerConstants.HEIGHT_SCREEN-CoordinateAxis.MARGIN;
							g2.drawLine(x, y, previousX, previousY);
							previousX = x;
							previousY = y;
						}
					}
				}
			}
		}

	}

	public Color getColorFunction() {
		return colorFunction;
	}

	public void setColorFunction(Color colorFunction) {
		this.colorFunction = colorFunction;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getIncreaseFunction() {
		return increaseFunction;
	}

	public void setIncreaseFunction(float increaseFunction) {
		this.increaseFunction = increaseFunction;
	}

}
