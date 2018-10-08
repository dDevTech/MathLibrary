package calculus;

public class Derivatives {
	/**
	 * Derivative of a polynome of grade coefficients-1.
	 * You should put the coefficiens in decrecient mode.
	 * ax^2+bx+c : {a,b,c}
	 * @param coefficients
	 * @return new coeffs - {a,b} coefficients.length-1
	 */
	public double[] polynomialDerivation(double []coefficients) {
		double[]newCoeffs = new double[coefficients.length-1];
		for(int i=0;i<coefficients.length-2;i++) {
			newCoeffs[i]=coefficients[i]*(coefficients.length-i-1);
		}
		return newCoeffs;
	}
	
}
