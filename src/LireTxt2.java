

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LireTxt2 {

	static String nomBordereau=LireCSV2.nomBordereau;

	/**
	 * 
	 * @return La liste des mails du bordereau
	 * @throws IOException
	 */
	public static ArrayList<String> mailsFromBordereau() throws IOException{
		Date date = null; 
		ArrayList<String> mails = new ArrayList<String>();
		ArrayList<Integer> demandes = new ArrayList<Integer>();
		ArrayList<Integer> id_origines = new ArrayList<Integer>();

		String[] mesEntrepots;

		BufferedReader br = new BufferedReader(new FileReader(nomBordereau));
		try{
			//StringBuilder sb = new StringBuilder();
			String LectureDeLaLigne = br.readLine();

			while (LectureDeLaLigne != null) {
				//System.out.println(LectureDeLaLigne);

				//Assignation de la date
				date = new SimpleDateFormat("dd/MM/yyyy").parse(br.readLine());

				int nbClients = Integer.parseInt(br.readLine());
				int compteur=1;

				//On fait une boucle for < nbClients
				while(compteur <= nbClients) {
					String[] mesDemandes = br.readLine().split(" : ");
					mails.add(mesDemandes[0]);
					demandes.add(Integer.parseInt(mesDemandes[1]));
					compteur+=1;
				}
				//Lecture des entrepots
				mesEntrepots = br.readLine().split(",");
				for (String entrepot : mesEntrepots) {
					id_origines.add(Integer.parseInt(entrepot));
				}

				/* autre façon de faire 
    	    	for (int i =0; i<mesEntrepots.size(); i++) {
    	    		id_origines.add(Integer.parseInt(mesEntrepots.get(i)));
    	    	}*/

				//On sort de la boucle while
				LectureDeLaLigne = br.readLine();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return mails;
	}
	
	/**
	 * 
	 * @return La liste des demandes clients du bordereau
	 * @throws IOException
	 */
	public static ArrayList<Integer> demandesFromBordereau() throws IOException{
		Date date = null; 
		ArrayList<String> mails = new ArrayList<String>();
		ArrayList<Integer> demandes = new ArrayList<Integer>();
		ArrayList<Integer> id_origines = new ArrayList<Integer>();

		String[] mesEntrepots;

		BufferedReader br = new BufferedReader(new FileReader(nomBordereau));
		try{
			//StringBuilder sb = new StringBuilder();
			String LectureDeLaLigne = br.readLine();

			while (LectureDeLaLigne != null) {
				//System.out.println(LectureDeLaLigne);

				//Assignation de la date
				date = new SimpleDateFormat("dd/MM/yyyy").parse(br.readLine());

				int nbClients = Integer.parseInt(br.readLine());
				int compteur=1;

				//On fait une boucle for < nbClients
				while(compteur <= nbClients) {
					String[] mesDemandes = br.readLine().split(" : ");
					mails.add(mesDemandes[0]);
					demandes.add(Integer.parseInt(mesDemandes[1]));
					compteur+=1;
				}
				//Lecture des entrepots
				mesEntrepots = br.readLine().split(",");
				for (String entrepot : mesEntrepots) {
					id_origines.add(Integer.parseInt(entrepot));
				}

				/* autre façon de faire 
    	    	for (int i =0; i<mesEntrepots.size(); i++) {
    	    		id_origines.add(Integer.parseInt(mesEntrepots.get(i)));
    	    	}*/

				//On sort de la boucle while
				LectureDeLaLigne = br.readLine();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return demandes;
	}

	/**
	 * 
	 * @return La liste des idOrigines du bordereau
	 * @throws IOException
	 */
	public static ArrayList<Integer> id_originesFromBordereau() throws IOException{
		Date date = null; 
		ArrayList<String> mails = new ArrayList<String>();
		ArrayList<Integer> demandes = new ArrayList<Integer>();
		ArrayList<Integer> id_origines = new ArrayList<Integer>();

		String[] mesEntrepots;

		BufferedReader br = new BufferedReader(new FileReader(nomBordereau));
		try{
			//StringBuilder sb = new StringBuilder();
			String LectureDeLaLigne = br.readLine();

			while (LectureDeLaLigne != null) {
				//System.out.println(LectureDeLaLigne);

				//Assignation de la date
				date = new SimpleDateFormat("dd/MM/yyyy").parse(br.readLine());

				int nbClients = Integer.parseInt(br.readLine());
				int compteur=1;

				//On fait une boucle for < nbClients
				while(compteur <= nbClients) {
					String[] mesDemandes = br.readLine().split(" : ");
					mails.add(mesDemandes[0]);
					demandes.add(Integer.parseInt(mesDemandes[1]));
					compteur+=1;
				}
				//Lecture des entrepots
				mesEntrepots = br.readLine().split(",");
				for (String entrepot : mesEntrepots) {
					id_origines.add(Integer.parseInt(entrepot));
				}

				/* autre façon de faire 
    	    	for (int i =0; i<mesEntrepots.size(); i++) {
    	    		id_origines.add(Integer.parseInt(mesEntrepots.get(i)));
    	    	}*/

				//On sort de la boucle while
				LectureDeLaLigne = br.readLine();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return id_origines;
	}
	
	/**
	 * 
	 * @return La liste des commandes du bordereau
	 * @throws IOException
	 */
	public static ArrayList<Commande> getCommande() throws IOException{
		Date date = null; 
		ArrayList<String> mails = new ArrayList<String>();
		ArrayList<Integer> demandes = new ArrayList<Integer>();
		ArrayList<Integer> id_origines = new ArrayList<Integer>();
		ArrayList<Commande> commandes = new ArrayList<Commande>();
		String[] mesEntrepots;

		BufferedReader br = new BufferedReader(new FileReader(nomBordereau));
		try{
			//StringBuilder sb = new StringBuilder();
			String LectureDeLaLigne = br.readLine();

			while (LectureDeLaLigne != null) {
				//System.out.println(LectureDeLaLigne);

				//Assignation de la date
				date = new SimpleDateFormat("dd/MM/yyyy").parse(br.readLine());

				int nbClients = Integer.parseInt(br.readLine());
				int compteur=1;

				//On fait une boucle for < nbClients
				while(compteur <= nbClients) {
					String[] mesDemandes = br.readLine().split(" : ");
					mails.add(mesDemandes[0]);
					demandes.add(Integer.parseInt(mesDemandes[1]));
					compteur+=1;
				}
				//Lecture des entrepots
				mesEntrepots = br.readLine().split(",");
				for (String entrepot : mesEntrepots) {
					id_origines.add(Integer.parseInt(entrepot));
				}

				/* autre façon de faire 
    	    	for (int i =0; i<mesEntrepots.size(); i++) {
    	    		id_origines.add(Integer.parseInt(mesEntrepots.get(i)));
    	    	}*/

				//On sort de la boucle while
				LectureDeLaLigne = br.readLine();
				//commandes.add(new Commande(date, ))
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return commandes;
	}

	public static ArrayList<String> getMails() throws IOException{
		return mailsFromBordereau();
	}
	public static ArrayList<Integer> getDemandes() throws IOException{
		return demandesFromBordereau();
	}
	public static ArrayList<Integer> getIdOrigines() throws IOException{
		return id_originesFromBordereau();
	}
}