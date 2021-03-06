/*******************************************************************************
 * Copyright (C) 2018-2019 Retopall Services
 * For more information please check www.retopall.com
 * RetopMathUtils can not be copied and/or distributed without the express
 * permission of dDev Tech
 ******************************************************************************/

package utils.matrix;

public class MatrixException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MatrixException(String error,String ref) {
		System.err.println("Matrix error in reference "+ref);
		System.out.println("Error: "+error);
	}
}
