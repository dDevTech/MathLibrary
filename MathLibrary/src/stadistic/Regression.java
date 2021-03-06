
package stadistic;

import java.util.ArrayList;

import general.MathUtils;
import utils.matrix.Matrix;

public class Regression {
	public static String[]funcLetters= {"f","g","h","a","b","c","m","n","o","p","q","t","v"};
	public static double[] executeLinearRegression(Dataset dataset) {

		Matrix[] matrixXY = convertDatasetToMatrixLinearRegression(dataset);

		return Regression.regression(matrixXY[0], matrixXY[1]);
	}

	public static double[] executePolynomialRegression(Dataset dataset, int n) {

		Matrix[] matrixXY = convertDatasetToMatrixPolynomialRegression(dataset, n);

		return Regression.regression(matrixXY[0], matrixXY[1]);
	}

	public static double[] executeLinearRegression(double[] Xvalues, double[] Yvalues) {

		double[][] xNewValues = new double[Xvalues.length][2];
		for (int i = 0; i < Xvalues.length; i++) {
			xNewValues[i][0] = 1;
			xNewValues[i][1] = Xvalues[i];
		}
		Matrix X = new Matrix(xNewValues);
		Matrix Y = new Matrix(Yvalues);
		return Regression.regression(X, Y);
	}
	
	/**
	 * Execute polynomial regression. To have an accurate outputs you should have a dataset size equals or higher of the grade of the equation
	 * For drawing the function check draw methods.
	 * @param Xvalues - Array with the XValues
	 * @param Yvalues - Array with the YValues
	 * @param n number of outputs including the constants for example ax2+bx+c n=3
	 * @return outputs coefficients of the regression function
	 */
	public static double[] executePolynomialRegression(double[] Xvalues, double[] Yvalues, int n) {
		
		double[][] xNewValues = new double[Xvalues.length][n];
		for (int i = 0; i < Xvalues.length; i++) {
			xNewValues[i][0] = 1;
			for (int j = 1; j < n; j++) {
				xNewValues[i][j] = Math.pow(Xvalues[i], j);
			}
		}
		Matrix X = new Matrix(xNewValues);
		Matrix Y = new Matrix(Yvalues);
	
		return Regression.regression(X, Y);
	}

	public static double[] executeLinearRegression(Matrix X, Matrix Y) {
		return Regression.regression(X, Y);
	}

	private static double[] regression(Matrix X, Matrix Y) {
		long init=System.currentTimeMillis();
		// Y=AX+E
		// Find A
		Matrix transposed = X.transpose();
		Matrix A = transposed.multiply(X).getInverseMatrix().multiply(transposed).multiply(Y);

		double[] outputs = new double[A.getRows()];
		for (int i = 0; i < A.getRows(); i++) {
			outputs[i] = A.getValue(i, 0);
		}
		System.out.println("Regression successfully done in "+((System.currentTimeMillis()-init)/1000f)+"s");
		return outputs;
	}

	public static void printRegression(double[] regressionValues,int precision) {
		System.out.println("--------------");
		System.out.println("Regression equation");
		System.out.print("y = ");
		for (int i = regressionValues.length - 1; i >= 0; i--) {
			if (regressionValues[i] > 0) {
				if (i == 0) {
					System.out.print(MathUtils.roundDecimals(precision, regressionValues[i]));
				} else if (i == regressionValues.length - 1) {
					System.out.print(MathUtils.roundDecimals(precision, regressionValues[i]) + "x" + i + " + ");
				} else {
					System.out.print(MathUtils.roundDecimals(precision, regressionValues[i]) + "x" + i + " + ");
				}
			} else if (regressionValues[i] < 0) {
				if (i == 0) {
					System.out.print(MathUtils.roundDecimals(precision, Math.abs(regressionValues[i])));
				} else if (i == regressionValues.length - 1) {
					System.out.print(MathUtils.roundDecimals(precision, Math.abs(regressionValues[i])) + "x" + i + " - ");
				} else {
					System.out.print(MathUtils.roundDecimals(precision, Math.abs(regressionValues[i])) + "x" + i + " - ");
				}
			}

		}
		System.out.println();
		System.out.println("--------------");
	}
	public static String getStringFunction(double[] regressionValues,int precision,int id) {
		String equation="";
		equation+=funcLetters[id]+"(x)= ";
		for (int i = regressionValues.length - 1; i >= 0; i--) {
			if (regressionValues[i] > 0) {
				if (i == 0) {
					equation+=(MathUtils.roundDecimals(precision, regressionValues[i]));
				} else if (i == regressionValues.length - 1) {
					equation+=(MathUtils.roundDecimals(precision, regressionValues[i]) + "x" + i + " + ");
				} else {
					equation+=(MathUtils.roundDecimals(precision, regressionValues[i]) + "x" + i + " + ");
				}
			} else if (regressionValues[i] < 0) {
				if (i == 0) {
					equation+=(MathUtils.roundDecimals(precision, Math.abs(regressionValues[i])));
				} else if (i == regressionValues.length - 1) {
					equation+=(MathUtils.roundDecimals(precision, Math.abs(regressionValues[i])) + "x" + i + " - ");
				} else {
					equation+=(MathUtils.roundDecimals(precision, Math.abs(regressionValues[i])) + "x" + i + " - ");
				}
			}

		}
		return equation;
	}
	public static Matrix[] convertDatasetToMatrixLinearRegression(Dataset dataset) {
		ArrayList<Data> data = dataset.getDataset();
		Matrix[] matrixXY = new Matrix[2];
		Matrix mX = new Matrix(data.size(), 2);
		Matrix mY = new Matrix(data.size(), 1);
		for (Data d : data) {
			mX.setValue(data.indexOf(d), 0, 1);
			mX.setValue(data.indexOf(d), 1, d.getX());
			mY.setValue(data.indexOf(d), 0, d.getY());
		}
		matrixXY[0] = mX;
		matrixXY[1] = mY;
		return matrixXY;

	}

	public static Matrix[] convertDatasetToMatrixPolynomialRegression(Dataset dataset, int n) {
		ArrayList<Data> data = dataset.getDataset();
		Matrix[] matrixXY = new Matrix[2];
		Matrix mX = new Matrix(data.size(), n);
		Matrix mY = new Matrix(data.size(), 1);
		for (Data d : data) {
			mX.setValue(data.indexOf(d), 0, 1);
			for (int i = 1; i < n; i++) {
				mX.setValue(data.indexOf(d), i, Math.pow(d.getX(), i));
			}
			mY.setValue(data.indexOf(d), 0, d.getY());
		}
		mX.printMatrix();
		mY.printMatrix();
		matrixXY[0] = mX;
		matrixXY[1] = mY;
		return matrixXY;

	}

}
