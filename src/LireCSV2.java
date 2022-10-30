

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class LireCSV2 {	
	static String nomFichierSite = "Petit"+File.separator+"init-sites-30-Carre.csv";
	static String nomFichierClient= "Petit"+File.separator+"init-clients-30-10-Carre.csv";
	static String nomFichierRoute="Petit"+File.separator+"init-routes-30-45-Carre.csv";
	static String nomFichierEntrepot="Petit"+File.separator+"init-entrepots-30-5-Carre.csv";
	static String nomBordereau = "Petit"+File.separator+"init-bordereau-commande-2021-12-25.txt";
			
	public static void chargerDonnee(int taille) {
		switch (taille) {
		case 1 :
			nomFichierSite = "Petit"+File.separator+"init-sites-30-Carre.csv";
			nomFichierClient= "Petit"+File.separator+"init-clients-30-10-Carre.csv";
			nomFichierRoute="Petit"+File.separator+"init-routes-30-45-Carre.csv";
			nomFichierEntrepot="Petit"+File.separator+"init-entrepots-30-5-Carre.csv";
			nomBordereau = "Petit"+File.separator+"init-bordereau-commande-2021-12-25.txt";
			break;
		case 2 :
			nomFichierSite = "Moyen"+File.separator+"init-sites-200-Carre.csv";
			nomFichierClient= "Moyen"+File.separator+"init-clients-200-50-Carre.csv";
			nomFichierRoute="Moyen"+File.separator+"init-routes-200-300-Carre.csv";
			nomFichierEntrepot="Moyen"+File.separator+"init-entrepots-200-20-Carre.csv";
				System.out.println("Date du bordereau");
				System.out.println("1 : init-bordereau-commande-2021-06-03");
				System.out.println("2 : init-bordereau-commande-2021-06-14");
				int a = Clavier.lireInt();
				switch (a) {
				case 1 :
					nomBordereau="Moyen"+File.separator+"init-bordereau-commande-2021-06-03.txt";
					break;
				case 2 :
					nomBordereau="Moyen"+File.separator+"init-bordereau-commande-2021-06-14.txt";
					break;	
				}
			break;
		case 3:
			nomFichierSite = "Grand"+File.separator+"init-sites-500-Carre.csv";
			nomFichierClient= "Grand"+File.separator+"init-clients-500-200-Carre.csv";
			nomFichierRoute="Grand"+File.separator+"init-routes-500-750-Carre.csv";
			nomFichierEntrepot="Grand"+File.separator+"init-entrepots-500-100-Carre.csv";
			System.out.println("Date du bordereau");
			System.out.println("1 : init-bordereau-commande-2021-01-14");
			System.out.println("2 : init-bordereau-commande-2021-01-18");
			int b = Clavier.lireInt();
			switch (b) {
			case 1 :
				nomBordereau="Grand"+File.separator+"init-bordereau-commande-2021-01-14.txt";
			case 2 :
				nomBordereau="Grand"+File.separator+"init-bordereau-commande-2021-01-18.txt";
			break;
			}
		}
	}
	    
	public static ArrayList<Integer> getSiteViaMail(List<Client> clientList) throws ClassNotFoundException, SQLException {
		int a = -1;
		ArrayList<Integer> s = new ArrayList<Integer>();
		ArrayList<Client> clients =FromBDDToList.getClients();
		for(Client cl : clients) {
			for(Client cl2 : clientList) {
			//System.out.println(cl.getMail());
				if((cl.getMail())==cl2.getMail()) {
					a = cl.getId_site();
					s.add(a);
				}
			}
			
		}
	return s;
	}
	
		public static ArrayList<Site> sitesFromCSV(){
		 	ArrayList<Site> sites = new ArrayList<Site>();
			int id_site; 
			int x; 
			int y; 
			try {
				FileReader filereader = new FileReader(nomFichierSite);
				CSVParser parser = new CSVParserBuilder()
										.withSeparator(';')
										.build();
				
				CSVReader csvReader = new CSVReaderBuilder(filereader)
										.withCSVParser(parser)
										.build();
				
				List<String[]> allData = csvReader
										.readAll();
	
				int ligne = 0; 
				for (String[] row : allData) {
					if (ligne!=0) {
						id_site = Integer.parseInt(row[0]); 
						x = Integer.parseInt(row[1]);
						y = Integer.parseInt(row[2]); 
						Point p= new Point (x,y);
						Site s = new Site(id_site,p);
						sites.add(s);
						
	
					}
					ligne+=1; 
				}
	
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return sites; 
		}
		
		public static ArrayList<Client> clientsFromCSV(){
			ArrayList<Client> clients = new ArrayList<Client>();
			String nom; 
			String mail; 
			int id_site; 
			try {
				FileReader filereader = new FileReader(nomFichierClient);
				CSVParser parser = new CSVParserBuilder()
										.withSeparator(';')
										.build();
				
				CSVReader csvReader = new CSVReaderBuilder(filereader)
										.withCSVParser(parser)
										.build();
				
				List<String[]> allData = csvReader
										.readAll();
	
				int ligne = 0; 
				for (String[] row : allData) {
					if (ligne!=0) {
						nom = row[0]; 
						mail = row[1];
						id_site = Integer.parseInt(row[2]); 
						clients.add(new Client(nom, mail, id_site));
					}
					ligne+=1; 
				}
	
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return clients; 
		}
		
			
		public static List<Routes> routesFromCSV(){
			List<Routes> routes = new ArrayList<Routes>();
			int origine; 
			int destination; 
			
			try {
				FileReader filereader = new FileReader(nomFichierRoute);
				CSVParser parser = new CSVParserBuilder()
										.withSeparator(';')
										.build();
				
				CSVReader csvReader = new CSVReaderBuilder(filereader)
										.withCSVParser(parser)
										.build();
				
				List<String[]> allData = csvReader
										.readAll();
	
				int ligne = 0; 
				for (String[] row : allData) {
					if (ligne!=0) {
						origine = Integer.parseInt(row[0]); 
						destination = Integer.parseInt(row[1]);
						routes.add(new Routes(origine, destination));
					}
					ligne+=1; 
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return routes; 
		}
		
		
		
		public static ArrayList<Entrepot> entrepotsFromCSV(){
			ArrayList<Entrepot> entrepots = new ArrayList<Entrepot>();
			int id_entrepot, id_site, cout_fixe, stock; 
			
			try {
				FileReader filereader = new FileReader(nomFichierEntrepot);
				CSVParser parser = new CSVParserBuilder()
										.withSeparator(';')
										.build();
				
				CSVReader csvReader = new CSVReaderBuilder(filereader)
										.withCSVParser(parser)
										.build();
				
				List<String[]> allData = csvReader
										.readAll();
	
				int ligne = 0; 
				for (String[] row : allData) {
					if (ligne!=0) {
						id_entrepot = Integer.parseInt(row[0]); 
						id_site = Integer.parseInt(row[1]);
						cout_fixe = Integer.parseInt(row[2]); 
						stock = Integer.parseInt(row[3]);
						entrepots.add(new Entrepot(id_entrepot, id_site, cout_fixe, stock));
	
					}
					ligne+=1; 
				}
	
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return entrepots; 
		}
		
		public static ArrayList<Integer> longueurRoute() throws ClassNotFoundException, SQLException{
			ArrayList<Integer> longueurRoute = new ArrayList<Integer>();
			ArrayList<Routes> routes =FromBDDToList.getRoutes();
			for(Routes r : routes) {
				int l = r.longueur();
				longueurRoute.add(l);
				}
			return longueurRoute;
			}
		

		

}
