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
		this.setTitle("Retop Math Viewer - 1.1.0");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		canvas = new Canvas();
	
		this.setContentPane(canvas);
		
		this.setVisible(true);
		MathViewerConstants.WIDTH_SCREEN =(int) canvas.getWidth();
		MathViewerConstants.HEIGHT_SCREEN = (int)canvas.getHeight();
		canvas.setup();
		
	}

	public void changeSize(int width, int height) {
		MathViewerConstants.WIDTH_SCREEN =(int) canvas.getWidth();
		MathViewerConstants.HEIGHT_SCREEN = (int)canvas.getHeight();
	
		this.setSize(width, height);
	}
	public Canvas draw() {
		return canvas;
	}
}
