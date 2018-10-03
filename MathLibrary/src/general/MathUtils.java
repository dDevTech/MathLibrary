/*******************************************************************************
 * Copyright (C) 2018-2019 Retopall Services
 * For more information please check www.retopall.com
 * RetopMathUtils can not be copied and/or distributed without the express
 * permission of dDev Tech
 ******************************************************************************/

package general;

public class MathUtils {
	public static double roundDecimals(int n,double number) {
		double m=Math.pow(10, n);
		return ((int)(number*m)/m);
	}
}
