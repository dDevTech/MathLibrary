/*******************************************************************************
 * Copyright (C) 2018-2019 Retopall Services
 * For more information please check www.retopall.com
 * RetopMathUtils can not be copied and/or distributed without the express
 * permission of dDev Tech
 ******************************************************************************/

package draw;

import javax.swing.JFrame;

public class MathViewer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Canvas canvas;

	public MathViewer(int width, int height) {
		this.setTitle("Function Viewer Retopall");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MathViewerConstants.WIDTH_SCREEN = width;
		MathViewerConstants.HEIGHT_SCREEN = height;
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		canvas = new Canvas();
		this.setContentPane(canvas);
		this.setVisible(true);
	}

	public void changeSize(int width, int height) {
		MathViewerConstants.WIDTH_SCREEN = width;
		MathViewerConstants.HEIGHT_SCREEN = height;
		this.setSize(width, height);
	}
	public Canvas draw() {
		return canvas;
	}
}
