/*******************************************************************************
 * Copyright (C) 2018-2019 Retopall Services
 * For more information please check www.retopall.com
 * RetopMathUtils can not be copied and/or distributed without the express
 * permission of dDev Tech
 ******************************************************************************/

package draw;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MathViewer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Canvas canvas;
	private Menu menu;

	public MathViewer(int width, int height) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setTitle(MathViewerConstants.name+" "+MathViewerConstants.version+" "+MathViewerConstants.company);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		canvas = new Canvas();

		this.setContentPane(canvas);

		this.setVisible(true);
		MathViewerConstants.INITIAL_WIDTH_SCREEN = (int) canvas.getWidth();
		MathViewerConstants.INITIAL_HEIGHT_SCREEN = (int) canvas.getHeight();
		MathViewerConstants.WIDTH_SCREEN = (int) canvas.getWidth();
		MathViewerConstants.HEIGHT_SCREEN = (int) canvas.getHeight();

		canvas.setup();
		menu = new Menu(canvas);
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				MathViewerConstants.WIDTH_SCREEN = (int) canvas.getWidth();
				MathViewerConstants.HEIGHT_SCREEN = (int) canvas.getHeight();

				canvas.update();
			}
		});
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					canvas.getAxis().setXAxis(canvas.getAxis().getInitialXAxisReal());
					canvas.getAxis().setYAxis(canvas.getAxis().getInitialYAxisReal());
				}

				super.mouseClicked(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				showPopup(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				showPopup(e);
			}

			private void showPopup(MouseEvent e) {
			
				if (e.isPopupTrigger()) {
					menu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

		});
		this.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.isControlDown()) {
					if (e.getWheelRotation() > 0) {
						CoordinateAxis.LINESX--;
						CoordinateAxis.LINESY--;
					} else {
						CoordinateAxis.LINESX++;
						CoordinateAxis.LINESY++;
					}
				} else {
					if (e.isAltDown()) {

						if (e.getWheelRotation() > 0) {

							if (canvas.getZoom() < 195f) {
								canvas.getAxis().setXAxis(canvas.getAxis().getXAxis() - 5f);
								canvas.getAxis().setYAxis(canvas.getAxis().getYAxis() - 5f);
							}

						} else {
							if (canvas.getZoom() > 5f) {
								canvas.getAxis().setXAxis(canvas.getAxis().getXAxis() + 5f);
								canvas.getAxis().setYAxis(canvas.getAxis().getYAxis() + 5f);
							}
						}

					} else {
						if (e.getWheelRotation() > 0) {
							if (canvas.getZoom() < 199f) {
								canvas.getAxis().setXAxis(canvas.getAxis().getXAxis() - 1f);
								canvas.getAxis().setYAxis(canvas.getAxis().getYAxis() - 1f);
							}

						} else {
							if (canvas.getZoom() > 1f) {
								canvas.getAxis().setXAxis(canvas.getAxis().getXAxis() + 1f);
								canvas.getAxis().setYAxis(canvas.getAxis().getYAxis() + 1f);
							}
						}

					}
				}
			}

		});

	}

	public void changeSize(int width, int height) {
		MathViewerConstants.WIDTH_SCREEN = (int) canvas.getWidth();
		MathViewerConstants.HEIGHT_SCREEN = (int) canvas.getHeight();

		this.setSize(width, height);
	}

	public Canvas draw() {
		return canvas;
	}
}
