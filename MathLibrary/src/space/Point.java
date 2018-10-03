/*******************************************************************************
 * Copyright (C) 2018-2019 Retopall Services
 * For more information please check www.retopall.com
 * RetopMathUtils can not be copied and/or distributed without the express
 * permission of dDev Tech
 ******************************************************************************/

package space;

import utils.vector.Vector;

public class Point {
	private double x;
	private double y;
	private double z;
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
		this.z=0;
	}

	public Point(double x, double y,double z ) {
		this.x = x;
		this.y = y;
		this.z=z;

	}
	public double getDistance(Point p) {
		return new Vector(p.x-x, p.y-y,p.z-z).getDistance();
	}
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
}
