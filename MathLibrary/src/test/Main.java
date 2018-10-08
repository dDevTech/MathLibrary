/*******************************************************************************
 * Copyright (C) 2018-2019 Retopall Services
 * For more information please check www.retopall.com
 * RetopMathUtils can not be copied and/or distributed without the express
 * permission of dDev Tech
 ******************************************************************************/
package test;


import java.util.ArrayList;

import draw.MathViewer;
import draw.MathViewerConstants;
import loader.CSVLoader;
import stadistic.Dataset;
import stadistic.Regression;
import stadistic.Stadistic;
import utils.equations.EquationData;
import utils.equations.Solver;
import utils.matrix.Matrix;
import utils.vector.Vector;

public class Main {

	public static void main(String[] args) {
		double[][]values= {{1,1,1,-1},{1,1,-1,1},{1,-1,1,1},{-1,1,1,1}};
		double []outputsX= {49,69,89,99,109,20,30,10};
		double []outputsY= {124,95,36,45,55,30,20,20};
//		double []outputsY1= {124,45,71,45,18,30};
//		double []outputsY2= {16,95,71,45,18,30};
//		ArrayList<EquationData>data = new ArrayList<>();
//		
//			data.add(new EquationData(outputsX));
//			data.add(new EquationData(outputsY));
//			data.add(new EquationData(outputsY2));
//			data.add(new EquationData(outputsY2));
//		Solver.solver(data);
		MathViewer viewer= new MathViewer(1980,1080);
		Dataset d=CSVLoader.loadData("x", "y", "aleatorio.csv");
		double solutions[]=Regression.executePolynomialRegression(d, 5);
		Regression.printRegression(solutions, 2);
		viewer.draw().addDataset(d);
		MathViewerConstants.SHOWTEXT=false;
		viewer.draw().addFunction(solutions);
		
	}

}
