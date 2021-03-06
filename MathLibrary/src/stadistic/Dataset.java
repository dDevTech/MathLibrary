/*******************************************************************************
 * Copyright (C) 2018-2019 Retopall Services
 * For more information please check www.retopall.com
 * RetopMathUtils can not be copied and/or distributed without the express
 * permission of dDev Tech
 ******************************************************************************/

package stadistic;

import java.util.ArrayList;
public class Dataset {

	private ArrayList<Data> dataset;
	public Dataset(ArrayList<Data>dataset) {
		this.dataset = dataset;	
	}
	public Dataset() {
		dataset= new ArrayList<>();
	}
	public static Dataset arrayToDataset(double[]xValues,double[]yValues) {
		ArrayList<Data>data = new ArrayList<>();
		if(xValues.length==yValues.length) {
		for(int i=0;i<xValues.length;i++) {
			data.add(new Data(xValues[i],yValues[i]));
		}
		}else {
			System.out.println("Not done. Size of X and Y values must be the same");
		}
		return new Dataset(data);
		
	}
	public void addData(Data data) {
		dataset.add(data);
	}
	public void addData(double x,double y) {
		dataset.add(new Data(x,y));
	}
	public Data get(int pos) {
		return dataset.get(pos);
	}
	public ArrayList<Data> getDataset() {
		return dataset;
	}
}
