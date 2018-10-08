package loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import stadistic.Dataset;

public class CSVLoader {
	public static Dataset loadData(String xNameLabel,String yNameLabel,String pathFile) {
		File f = new File(pathFile);
		Dataset data = new Dataset();
		if(pathFile.endsWith(".csv")) {
			
			try {
				FileReader fr = new FileReader(f);
				BufferedReader br =new BufferedReader(fr);
				String line;
				int posX=0;
				int posY=0;
				int counter=0;
				while((line=br.readLine())!=null) {
					String[]segments=line.split(",");
					double dataX=0;
					double dataY=0;
					
					for(int i=0;i<segments.length;i++) {
						
						String segment=segments[i].replace("\"", "");
						if(!segment.isEmpty()) {
						if(counter==0) {
						if(segment.equals(xNameLabel)) {
							posX=i;
						}
						if(segment.equals(yNameLabel)) {
							posY=i;
						}
						}else {
							if(i==posX) {
								dataX=Double.parseDouble(segment);
							}
							if(i==posY) {
								dataY=Double.parseDouble(segment);
							}
						}
						}
					}
					
					if(counter!=0) {
						data.addData(dataX, dataY);
					}
					counter++;
					//System.out.println(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println("Must be a .csv file");
		}
		return data;
	}
	public static Dataset loadDataByCountingYear(String xYearLabel,String yNameLabel,String pathFile) {
		File f = new File(pathFile);
		Dataset data = new Dataset();
		if(pathFile.endsWith(".csv")) {
			
			try {
				FileReader fr = new FileReader(f);
				BufferedReader br =new BufferedReader(fr);
				String line;
				int posX=0;
				int posY=0;
				int counter=0;
				while((line=br.readLine())!=null) {
					String[]segments=line.split(",");
					double dataX=0;
					double dataY=0;
					counter++;
					for(int i=0;i<segments.length;i++) {
						
						String segment=segments[i].replace("\"", "");
						if(!segment.isEmpty()) {
						if(counter==0) {
						if(segment.equals(xYearLabel)) {
							posX=i;
						}
						if(segment.equals(yNameLabel)) {
							posY=i;
						}
						}else {
							if(i==posX) {
								dataX=Double.parseDouble(segment);
							}
							if(i==posY) {
								dataY=Double.parseDouble(segment);
							}
						}
						}
					}
					if(counter!=0) {
						data.addData(dataX, dataY);
					}
					//System.out.println(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println("Must be a .csv file");
		}
		return data;
	}
}
