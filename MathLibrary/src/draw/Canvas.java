/*******************************************************************************
 * Copyright (C) 2018-2019 Retopall Services
 * For more information please check www.retopall.com
 * RetopMathUtils can not be copied and/or distributed without the express
 * permission of dDev Tech
 ******************************************************************************/

package draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Random;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.JPanel;

import drawable.Function;
import drawable.GraphicalPoint;
import general.MathUtils;
import stadistic.Data;
import stadistic.Dataset;

public class Canvas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread render;
	private Graphics g;
	private float fontSize=14;
	private CoordinateAxis axis;
	private ArrayList<GraphicalPoint> points = new ArrayList<>();
	private ArrayList<Function> functions = new ArrayList<>();
	private double zoom=1d;
	public Canvas() {
		this.setBackground(Color.BLACK);

		render = new Thread(new Runnable() {

			@Override
			public void run() {
				render(g);

			}
		});

	}

	public void setup() {
		axis = new CoordinateAxis();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		this.g = g;
		render.run();
		repaint();
	}

	public void update() {
	
		axis.update();
		fontSize=MathViewerConstants.WIDTH_SCREEN/(float)MathViewerConstants.INITIAL_WIDTH_SCREEN*MathViewerConstants.initialFontSize/2f+MathViewerConstants.initialFontSize/2f;
		if(MathViewerConstants.HEIGHT_SCREEN/(float)MathViewerConstants.INITIAL_WIDTH_SCREEN*MathViewerConstants.initialFontSize/2f+MathViewerConstants.initialFontSize/2f<fontSize) {
			fontSize=MathViewerConstants.HEIGHT_SCREEN/(float)MathViewerConstants.INITIAL_HEIGHT_SCREEN*MathViewerConstants.initialFontSize/2f+MathViewerConstants.initialFontSize/2f;
		}
		for (GraphicalPoint point : getPoints()) {
			point.update(axis);
		}
		for (Function function : getFunctions()) {
			function.update(axis);
			
		}
		
	}

	public void render(Graphics g) {
		g.setFont(new Font("Calibri", Font.BOLD,(int) fontSize));
		axis.render(g);
		setZoom((axis.getInitialXAxisReal()-axis.getXAxis())/axis.getInitialXAxisReal()*100+100f);
		for (GraphicalPoint point : getPoints()) {
			point.render(g);
		}
		for (Function function : getFunctions()) {
			
			function.render(g);
			function.setIncreaseFunction((float)(100f/zoom));
		}
		drawSettings(g);
		
	}
	public void drawSettings(Graphics g) {
		g.setColor(MathViewerConstants.mainColor);
		g.drawString(Double.toString(MathUtils.roundDecimals(2, getZoom()))+"%", MathViewerConstants.WIDTH_SCREEN-80, MathViewerConstants.HEIGHT_SCREEN-100);
		g.drawString(Integer.toString(CoordinateAxis.LINESX), MathViewerConstants.WIDTH_SCREEN-80, MathViewerConstants.HEIGHT_SCREEN-80);
		g.drawString(Double.toString(MathUtils.roundDecimals(2,(100f/zoom))),MathViewerConstants.WIDTH_SCREEN-80, MathViewerConstants.HEIGHT_SCREEN-120);
		
	}

	/**
	 * Add data and resize the value of each point depending on the max value of the
	 * dataset
	 * 
	 * @param dataset
	 */
	public void addDataset(Dataset dataset) {
		ArrayList<Data> data = dataset.getDataset();
		double maxX = 0;
		double maxY = 0;
		for (int i = 0; i < data.size(); i++) {
			maxX = Math.max(data.get(i).getX(), maxX);
			maxY = Math.max(data.get(i).getY(), maxY);
		}
		if (MathViewerConstants.AUTOMATIC) {
			axis.setYAxis(maxY);
			axis.setXAxis(maxX);
			axis.setInitialXAxisReal(maxX);
			axis.setInitialYAxisReal(maxY);
		}

		for (int i = 0; i < data.size(); i++) {
			getPoints().add(new GraphicalPoint(dataset.get(i).getX(), dataset.get(i).getY(), axis));
		}
	}

	public void addPoint(double x, double y) {
		getPoints().add(new GraphicalPoint(x + CoordinateAxis.MARGIN,
				MathViewerConstants.HEIGHT_SCREEN - y - CoordinateAxis.MARGIN, axis));
	}

	public void addFunction(double[] polynomialValues) {
		Function f = new Function(polynomialValues, axis, getFunctions().size());
		if (f.getId() != 0) {
			Random random = new Random();
			float hue = random.nextFloat();
			// Saturation between 0.1 and 0.3
			float saturation = (random.nextInt(2000) + 1000) / 10000f;
			float luminance = 0.9f;
			f.setColorFunction(Color.getHSBColor(hue, saturation, luminance));
		}
		getFunctions().add(f);
	}

    public BufferedImage getScreenShot(){
        BufferedImage bi = new BufferedImage(
            this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.paint(bi.getGraphics());
        return bi;
    }
    public void printImage(BufferedImage image) {
    	

    	PrinterJob printJob = PrinterJob.getPrinterJob();
    	printJob.setPrintable(new Printable() {
    	        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
    	                if (pageIndex != 0) {
    	                    return NO_SUCH_PAGE;
    	                }
    	                graphics.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    	                return PAGE_EXISTS;
    	        }
	       
    	});  
    	 if (printJob.printDialog()) {
	            try {
	              printJob.print();
	            } catch (Exception PrintException) {
	              PrintException.printStackTrace();
	            }
	          }
    	
  
    }
	public void removePoint(GraphicalPoint p) {
		getPoints().remove(p);
	}

	public void clearCanvas() {
		getPoints().clear();
	}

	public CoordinateAxis getAxis() {
		return axis;
	}

	public void setAxis(CoordinateAxis axis) {
		this.axis = axis;
	}

	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	public ArrayList<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(ArrayList<Function> functions) {
		this.functions = functions;
	}

	public ArrayList<GraphicalPoint> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<GraphicalPoint> points) {
		this.points = points;
	}
	
}
