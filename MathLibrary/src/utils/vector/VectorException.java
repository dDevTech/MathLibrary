/*******************************************************************************
 * Copyright (C) 2018-2019 Retopall Services
 * For more information please check www.retopall.com
 * RetopMathUtils can not be copied and/or distributed without the express
 * permission of dDev Tech
 ******************************************************************************/

package utils.vector;

public class VectorException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VectorException(String error,String ref) {
		System.err.println("Vector error in reference "+ref);
		System.out.println("Error: "+error);
	}
}
