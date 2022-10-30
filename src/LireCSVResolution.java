import java.io.File;
import javax.swing.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class LireCSVResolution {

	
	public static ArrayList<Site> lireBordereauRes(){
		ArrayList<Site> sites = new ArrayList<Site>();
		int id_site; 
		int x; 
		int y; 
		try {
			FileReader filereader = new FileReader("res"+File.separator+"donnees.csv");
			CSVParser parser = new CSVParserBuilder()
									.withSeparator(',')
									.build();
			
			CSVReader csvReader = new CSVReaderBuilder(filereader)
									.withCSVParser(parser)
									.build();
			
			List<String[]> allData = csvReader
									.readAll();

			int ligne = 0; 
			for (String[] row : allData) {
				if (ligne!=0) {

					System.out.println(row[0]);

				}
				ligne+=1; 
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return sites; 
	}
	
}
