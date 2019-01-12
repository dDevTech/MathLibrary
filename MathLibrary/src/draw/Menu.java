package draw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

public class Menu extends JPopupMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Canvas canvas;

	public Menu(Canvas canvas) {
		this.canvas = canvas;
		JMenu submenu = new JMenu("Scale");
		JMenuItem reset = new JMenuItem("Reset");
		JMenuItem print = new JMenuItem("Print");
		KeyStroke ctrlPKeyStroke = KeyStroke.getKeyStroke("control P");
		print.setAccelerator(ctrlPKeyStroke);
		JMenuItem help = new JMenuItem("Help");
		JMenuItem save = new JMenuItem("Save");
		KeyStroke ctrlXKeyStroke = KeyStroke.getKeyStroke("control S");
		save.setAccelerator(ctrlXKeyStroke);
		KeyStroke ctrlHKeyStroke = KeyStroke.getKeyStroke("control H");
		help.setAccelerator(ctrlHKeyStroke);

		add(reset);
		add(print);
		add(help);
		add(save);
		
		JMenuItem m1 = new JMenuItem("50%");
		JMenuItem m2 = new JMenuItem("100%");
		JMenuItem m3 = new JMenuItem("150%");
		JMenuItem m4 = new JMenuItem("125%");
		JMenuItem m5 = new JMenuItem("175%");
		JMenuItem m6 = new JMenuItem("75%");
		JMenuItem m7 = new JMenuItem("25%");
		submenu.add(m1);
		submenu.add(m2);
		submenu.add(m3);
		submenu.add(m4);
		submenu.add(m5);
		submenu.add(m6);
		submenu.add(m7);
		this.add(submenu);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedImage image = canvas.getScreenShot();
				JFileChooser jfc = new JFileChooser();
				jfc.setSelectedFile(new File("Untilted.png"));
				int retVal = jfc.showSaveDialog(null);
				if (retVal == JFileChooser.APPROVE_OPTION) {
					try {
						ImageIO.write(image, "png", jfc.getSelectedFile());
					} catch (IOException e1) {

						e1.printStackTrace();
					}
				}

			}
		});
		print.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BufferedImage image = canvas.getScreenShot();
				
				canvas.printImage(image);

				
			}
		});
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.getFunctions().clear();
				canvas.getPoints().clear();

			}
		});
		help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Application written by DDevTech - Retopall \n"
						+""+MathViewerConstants.company+ " "+MathViewerConstants.name+"\n"
						+ "Ctrl+WheelMove to remove or add number marks \n"
						+ "WheelMove to zoom in and out \n"
						+ "Alt+WheelMove for fast zooming \n"
						+ "Right Click for more option \n"
						+ "Do you want to use it for development? Go to www.retopall.com for more information", "Help "+MathViewerConstants.name, JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		m1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setZoom(50f);

			}
		});
		m2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setZoom(100f);

			}
		});
		m3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setZoom(150f);

			}
		});
		m4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setZoom(125f);

			}
		});
		m5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setZoom(175f);

			}
		});
		m6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setZoom(75f);

			}
		});
		m7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setZoom(25f);

			}
		});

	}

	public void setZoom(float zoom) {
		double value = -(canvas.getAxis().getInitialXAxisReal() * (zoom - 100)) / 100f
				+ (canvas.getAxis().getInitialXAxisReal());
		canvas.getAxis().setXAxis(value);
		double value2 = -(canvas.getAxis().getInitialYAxisReal() * (zoom - 100)) / 100f
				+ (canvas.getAxis().getInitialYAxisReal());
		canvas.getAxis().setYAxis(value);
	}
}
