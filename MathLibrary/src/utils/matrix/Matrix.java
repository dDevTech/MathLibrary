/*******************************************************************************
 * Copyright (C) 2018-2019 Retopall Services
 * For more information please check www.retopall.com
 * RetopMathUtils can not be copied and/or distributed without the express
 * permission of dDev Tech
 ******************************************************************************/

package utils.matrix;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Matrix {
	private int rows;
	private int columns;
	private double[][] values;
	private static int DEFAULT_DECIMALS = 2;
	private double determinant = 0;
	private static boolean DEBUG = false;

	public Matrix(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		values = new double[rows][columns];

	}

	public Matrix(int size) {
		this.rows = size;
		this.columns = size;
		values = new double[size][size];

	}

	public Matrix(final Matrix matrix) {

		values = matrix.getValues();
		columns = matrix.getColumns();
		rows = matrix.getRows();

	}

	public Matrix(double[][] values) {
		this.values = values;
		rows = values.length;
		columns = values[0].length;

	}

	public Matrix(double[] values) {
		rows = values.length;
		columns = 1;
		this.values = new double[rows][columns];
		for (int i = 0; i < values.length; i++) {
			this.values[i][0] = values[i];
		}

	}

	public void setZeros() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				values[i][j] = 0.0d;
			}
		}
	}

	public void setOnes() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				values[i][j] = 1.0d;
			}
		}
	}

	public void setNumbers(int n) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				values[i][j] = n;
			}
		}
	}

	public void fillMatrix(boolean gaussian, double max, double min) {
		Random random = new Random();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (!gaussian) {
					values[i][j] = min + Math.random() * (max - min);
				} else {
					values[i][j] = min + random.nextGaussian() * (max - min);
				}
			}
		}
		printStatement("filled");

	}

	public Matrix add(Matrix matrix) {
		if (areSameSize(matrix)) {
			Matrix m = new Matrix(matrix.getRows(), matrix.getColumns());

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					m.setValue(i, j, getValue(i, j) + matrix.getValue(i, j));
				}
			}
			return m;
		} else {
			return null;
		}
	}

	public Matrix subtract(Matrix matrix) {
		if (areSameSize(matrix)) {
			Matrix m = new Matrix(matrix.getRows(), matrix.getColumns());

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					m.setValue(i, j, getValue(i, j) - matrix.getValue(i, j));
				}
			}
			return m;
		} else {
			return null;
		}
	}

	public Matrix multiply(Matrix matrix) {
		Matrix m = new Matrix(rows, matrix.getColumns());
		if (areMultiplicable(matrix)) {
			for (int k = 0; k < rows; k++) {
				for (int i = 0; i < matrix.getColumns(); i++) {
					double sum = 0;
					for (int j = 0; j < matrix.getRows(); j++) {
						double value = getValue(k, j) * matrix.getValue(j, i);

						sum += value;
					}
					m.setValue(k, i, sum);
				}

			}

			return m;
		} else {
			return null;
		}
	}

	public Matrix getDiagonalMatrix() {

		if (getRows() != getColumns()) {
			try {
				throw new MatrixException("Matrix must be square", getReference(this));
			} catch (MatrixException e) {

			}
		}
		Matrix m = new Matrix(getRows(), getColumns());
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				if (i != j) {
					m.values[i][j] = 0;
				} else {
					m.values[i][j] = getValue(i, j);
				}
			}
		}
		return m;
	}

	public Matrix getUpperTriangularMatrix() {

		if (getRows() != getColumns()) {
			try {
				throw new MatrixException("Matrix must be square", getReference(this));
			} catch (MatrixException e) {

			}
		}
		Matrix m = new Matrix(getRows(), getColumns());
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				if (i < j) {
					m.values[i][j] = 0;
				} else {
					m.values[i][j] = getValue(i, j);
				}
			}
		}
		return m;
	}

	public Matrix getLowerTriangularMatrix() {

		if (getRows() != getColumns()) {
			try {
				throw new MatrixException("Matrix must be square", getReference(this));
			} catch (MatrixException e) {

			}
		}
		Matrix m = new Matrix(getRows(), getColumns());
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				if (i > j) {
					m.values[i][j] = 0;
				} else {
					m.values[i][j] = getValue(i, j);
				}
			}
		}
		return m;
	}

	public Matrix getSubMatrix(int removeRow, int removeColumn) {
		Matrix res = new Matrix(rows - 1, columns - 1);
		if (!outOfBounds(removeRow, removeColumn)) {
			for (int i = 0; i < getRows(); i++) {
				if (removeRow == i)
					continue;
				for (int j = 0; j < getColumns(); j++) {
					if (removeColumn == j)
						continue;

					res.setValue(removeRow > i ? i : i - 1, removeColumn > j ? j : j - 1, getValue(i, j));
				}
			}
		}
		return res;
	}

	public static Matrix genIdentityMatrix(int size) {
		Matrix m = new Matrix(size, size);

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					m.values[i][j] = 1;
				} else {
					m.values[i][j] = 0;
				}
			}
		}
		return m;

	}

	public Matrix scalar(double c) {
		Matrix m = new Matrix(getRows(), getColumns());

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				m.setValue(i, j, getValue(i, j) * c);
			}
		}
		return m;
	}

	public Matrix LUDecomposition() {
		Matrix result = null;
		if (isSquare()) {
			Matrix lower = getLowerTriangularMatrix();
			Matrix upper = getLowerTriangularMatrix();
			result = lower.multiply(upper);
		} else {
			try {
				throw new MatrixException("Matrix must be square", getReference(this));
			} catch (MatrixException e) {

			}
		}
		return result;
	}

	public double iterativeDeterminant(Matrix matrix, boolean sign) {

		if (isSquare()) {
			if (matrix.rows == 1) {
				return matrix.getValue(0, 0);
			}
			if (matrix.rows == 2) {
				return matrix.getValue(0, 0) * matrix.getValue(1, 1) - matrix.getValue(1, 0) * matrix.getValue(0, 1);

			} else {

				boolean newSign = sign;

				for (int j = 0; j < matrix.getColumns(); j++) {
					double determinant = matrix.iterativeDeterminant(matrix.getSubMatrix(j, 0), true);
					if (newSign) {
						if (Matrix.DEBUG) {
							System.out.println("v+/columns: " + matrix.getColumns() + "/value: "
									+ matrix.getValue(j, 0) * matrix.determinant);
						}
						matrix.determinant += matrix.getValue(j, 0) * determinant;
					} else {
						if (Matrix.DEBUG) {
							System.out.println("v-/columns: " + matrix.getColumns() + "/value: "
									+ -matrix.getValue(j, 0) * matrix.determinant);
						}
						matrix.determinant += -matrix.getValue(j, 0) * determinant;
					}
					newSign = !newSign;

				}
			}
			if (Matrix.DEBUG) {
				matrix.printMatrix();
				System.out.println("Determinant: " + determinant + " Matrix size: " + matrix.getColumns());
			}
		} else {
			try {
				throw new MatrixException("Matrix must be square", getReference(this));
			} catch (MatrixException e) {

			}
		}
		return matrix.determinant;
	}

	public Matrix getCofactors() {
		Matrix minorMatrix = new Matrix(getRows(), getColumns());
		boolean sign = true;
		if (isSquare()) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {

					if (sign) {
						minorMatrix.setValue(i, j, getSubMatrix(i, j).iterativeDeterminant(getSubMatrix(i, j), true));

					} else {
						minorMatrix.setValue(i, j, -getSubMatrix(i, j).iterativeDeterminant(getSubMatrix(i, j), true));
					}

					sign = !sign;
				}
				if (rows % 2 == 0) {
					sign = !sign;
				}
			}
		} else {
			try {
				throw new MatrixException("Matrix must be square", getReference(this));
			} catch (MatrixException e) {

			}
		}
		return minorMatrix;
	}

	public double getDeterminant() {
		return iterativeDeterminant(this, true);
	}

	// Pending to revise
	public int getRankMatrix() {
		int maxRank = columns >= rows ? rows : columns;
		int minRank = 1;
		int maxMatrixSize=1;
		int rank =0;
		if(maxRank==columns) {
			maxMatrixSize = rows;
		}else {
			maxMatrixSize = columns;
		}
		for(int i=1;i<maxMatrixSize;i++) {
			for(int j=0;j<columns-maxMatrixSize+1;j++) {
				List<double[]>columns = new ArrayList<>();
				columns.add(getColumn(0));
				for(int k=0;k<i;k++) {
					double[]column=getColumn(j+k);
					columns.add(column);
				}
			
			}
		}
		return 3;
	}
	//Pending to revise
	public static Matrix join(List<double[]>subMatrixs) {
		//Check
		
		for(int i=1;i<subMatrixs.size();i++) {
			if(subMatrixs.get(i).length!=subMatrixs.get(i-1).length) {
				try {
					throw new MatrixException("Submatrixs must have the same rows", "");
				} catch (MatrixException e) {

					e.printStackTrace();
				}
				return null;
			}
		}
		double values[][]= new double[subMatrixs.size()][subMatrixs.get(0).length];
		for(int i=0;i<subMatrixs.size();i++) {
			values[i]=subMatrixs.get(i);
		}
		return new Matrix(values).transpose();
	}
	// Pending to revise
	public Matrix swapRows(int row1, int row2) {
		Matrix m = new Matrix(rows, columns);
		double[] copy1 = values[row1];
		double[] copy2 = values[row2];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (i == row1) {
					m.values[i] = copy2;
				} else if (i == row2) {
					m.values[i] = copy1;
				} else {
					m.setValue(i, j, values[i][j]);
				}

			}
		}
		return m;
	}

	// Pending to revise
	public Matrix swapColumns(int column1, int column2) {
		Matrix m = new Matrix(rows, columns);
		double[] copy1 = getColumn(column1);
		double[] copy2 = getColumn(column2);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (j == column1) {
					m.setValue(i, j, copy2[i]);
				} else if (j == column2) {
					m.setValue(i, j, copy1[i]);
				} else {
					m.setValue(i, j, values[i][j]);
				}
			}
		}
		return m;
	}

	public Matrix transpose() {
		Matrix m = new Matrix(getColumns(), getRows());

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				m.setValue(j, i, getValue(i, j));
			}
		}
		return m;
	}

	// Pending to revise
	public double[] getColumn(int column) {
		double[] columnInPosition = new double[rows];
		int counter = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (j == column) {
					columnInPosition[counter] = values[i][j];
					counter++;
				}
			}
		}
		return columnInPosition;
	}

	public boolean isSquare() {
		if (getRows() == getColumns()) {
			return true;
		} else {

			return false;
		}
	}

	public boolean equalsTo(Matrix m) {
		boolean res = true;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (m.getValue(i, j) != getValue(i, j)) {
					res = false;
				}
			}
		}
		return res;
	}

	public boolean areSameSize(Matrix m) {
		if (m.getRows() == getRows() && m.getColumns() == getColumns()) {
			return true;
		} else {
			try {
				throw new MatrixException("Matrixes are not the same size", getReference(this));
			} catch (MatrixException e) {

				e.printStackTrace();
			}
			return false;
		}
	}

	public boolean outOfBounds(int rowPos, int columnPos) {
		if (rowPos >= getRows() || columnPos >= getColumns()) {
			try {
				throw new MatrixException("Out of bounds exception", getReference(this));
			} catch (MatrixException e) {

				e.printStackTrace();
			}
			return true;
		} else {

			return false;
		}
	}

	public boolean areMultiplicable(Matrix m) {
		if (m.getRows() == this.getColumns()) {
			return true;
		} else {
			try {
				throw new MatrixException("Matrixes cannot be operated with * or /", getReference(this));
			} catch (MatrixException e) {

				e.printStackTrace();
			}
			return false;
		}
	}

	public Matrix adJoint() {
		Matrix m = new Matrix(rows, columns);

		if (isSquare()) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if (i == j) {
						m.setValue(i, j, getValue(i, j));
					} else {
						m.setValue(j, i, getValue(i, j));
					}
				}
			}
		} else {
			try {
				throw new MatrixException("Matrix must be square", getReference(this));
			} catch (MatrixException e) {

			}
		}
		return m;
	}

	public Matrix getInverseMatrix() {
		return getCofactors().adJoint().scalar(1 / this.getDeterminant());
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public double[][] getValues() {
		return values;
	}

	public void setValues(double[][] values) {
		this.values = values;
	}

	public void printStatement(String function) {
		System.out.println("Matrix " + this.getClass() + " " + function);
	}

	public double getValue(int rowPos, int columnPos) {
		return values[rowPos][columnPos];
	}

	public void setValue(int rowPos, int columnPos, double value) {
		values[rowPos][columnPos] = value;
	}

	public String getReference(Object o) {
		return (Integer.toHexString(System.identityHashCode(o)));
	}

	public void printMatrix() {
		System.out.println("-----------------");
		System.out.println("Printing matrix: #" + getReference(this) + "#");
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		for (int i = 0; i < rows; i++) {

			System.out.print("|");

			for (int j = 0; j < columns; j++) {
				double pow = Math.pow(10, DEFAULT_DECIMALS);
				double value = ((int) (values[i][j] * pow)) / (double) pow;
				String add = (value >= 0) ? "+" : "";
				System.out.print(add + decimalFormat.format(value) + " ");
			}
			System.out.println("|");
		}
		System.out.println("-----------------");
	}

}
