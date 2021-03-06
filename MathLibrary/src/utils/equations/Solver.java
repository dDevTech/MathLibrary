/*******************************************************************************
 * Copyright (C) 2018-2019 Retopall Services
 * For more information please check www.retopall.com
 * RetopMathUtils can not be copied and/or distributed without the express
 * permission of dDev Tech
 ******************************************************************************/
package utils.equations;

import java.util.ArrayList;

import utils.matrix.Matrix;

public class Solver {
		public static void solver(ArrayList<EquationData>equations) {
			//A-1*B
			double [][]coefficients= new double[equations.size()][equations.get(0).getIncognites()-2];
			double [][]constants =new double [equations.size()][1];
			for(int i=0;i<equations.size();i++) {
				for(int j=0;j<equations.get(i).getIncognites()-2;j++) {
					coefficients[i][j]=equations.get(i).getCoefficients()[j];
				}
				constants[i][0]=equations.get(i).getCoefficients()[equations.get(0).getIncognites()-1];
			}
			Matrix matrix = new Matrix(coefficients);
			Matrix matrix2 = new Matrix(constants);
			matrix.printMatrix();
			matrix.getInverseMatrix().multiply(matrix2).printMatrix();
		
		}

}
