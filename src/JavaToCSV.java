import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaToCSV {
    
	/**
	 * Transformation du fichier Java en CSV --> https://www.delftstack.com/fr/howto/java/java-write-to-csv/#:~:text=Nous%20cr%C3%A9ons%20une%20instance%20writer,ferme%20le%20flux%20d'%C3%A9criture.
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void writeToCsv() throws IOException, ClassNotFoundException, SQLException{
	    String fichierCSV = "res"+File.separator+"donnees.csv";
	    FromJSON fj = FromJSON.deserializationFromJson();
	    try{
	    	FileWriter outputfile = new FileWriter(fichierCSV);
			//CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
			CSVWriter writer = new CSVWriter(outputfile,';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

			int i =0;
			String [] enTete = {"Mail Client", "Demande Du Client","ID Entrepot","Stock de l'entrepot"};
			writer.writeNext(enTete);
			ArrayList<String> mails = LireTxt2.getMails();
			ArrayList<Integer> demandes =LireTxt2.getDemandes() ; 
			ArrayList<Integer> idOrigines = LireTxt2.getIdOrigines();
			for (String m : mails) {
				String [] record = { m, demandes.get(i)+"",idOrigines.get(fj.getDeliveriesfor_customer().get(i))+"",
						Entrepot.getListStockOuvert().get(fj.getDeliveriesfor_customer().get(i))+"" };
                writer.writeNext(record);
                i++;
            }
	  
	
	        writer.close();
	    }catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void readCSVFile(){
        CSVReader reader = null;
        String fichierCSV = "res"+File.separator+"donnees.csv";
        try {
            reader = new CSVReader(new FileReader(fichierCSV));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String[]> allRows = null;
        try {
            allRows = reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

        for(String[] row : allRows){
            System.out.println(Arrays.toString(row));
        }
    }

}
