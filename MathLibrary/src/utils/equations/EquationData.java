/*******************************************************************************
 * Copyright (C) 2018-2019 Retopall Services
 * For more information please check www.retopall.com
 * RetopMathUtils can not be copied and/or distributed without the express
 * permission of dDev Tech
 ******************************************************************************/
package utils.equations;



public class EquationData{
	private double[] coefficients;
	private int incognites=0;
	public EquationData(double[]coefficients) {
		this.coefficients = coefficients;
		this.incognites=coefficients.length;
		
	}
	public double[] getCoefficients() {
		return coefficients;
	}
	public void setCoefficients(double[] coefficients) {
		this.coefficients = coefficients;
	}
	public int getIncognites() {
		return incognites;
	}
	public void setIncognites(int incognites) {
		this.incognites = incognites;
	}
	
}
