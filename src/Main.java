import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {


	public static int demandeTaillesCSV() {
		System.out.println("Veuillez choisir la taille à traiter : ");
		System.out.println("1 : Petite taille");
		System.out.println("2 : Moyenne taille");
		System.out.println("3 : Grande taille");
		int nombre = Clavier.lireInt();
		return nombre;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Bonjour et bienvenue! ");
		System.out.println("Cet outil permet de résoudre un problème du type SSCFLP ");
		System.out.println("Voulez-vous lancer la lecture de la BDD ?");
		System.out.println("1 : Oui");
		System.out.println("2 : Non (Prend la petite instance par défaut)");
		int lancementBDD = Clavier.lireInt();
		switch (lancementBDD) {
		case 1 : 
			LireCSV2.chargerDonnee(demandeTaillesCSV());
			BasesJDBC.lancementBDD();
			break;
		case 2 :
			System.out.println("Comme nous prennons nos liste et non la BDD, nous precedons quand meme"
					+ "au lancement de la BDD dans ce programme meme si 'Non' est selectionné");
			BasesJDBC.lancementBDD();
			break;
		}
		System.out.println("Le programme est en cours ...");
		//Creation du fichier JSON
		System.out.println("En cours serializationToJson ...");
		ToJSON.serializationToJson();

		//Execution de la commande pour lancer le solveur en python
		System.out.println("En cours ex commande...");
		ToJSON.exCommande();

		//Lecture du fichier JSON de resolution
		System.out.println("En cours deserialization...");
		FromJSON.deserializationFromJson();

		//Ecriture, lecture et création du fichier CSV
		System.out.println("En cours Java To CSV...");
		JavaToCSV csvLibrary =  new JavaToCSV();
		JavaToCSV.writeToCsv();
		JavaToCSV.readCSVFile();
		System.out.println();



		FromJSON fj = FromJSON.deserializationFromJson();
		fj.getNomClient();
		System.out.println("Comfirmez-vous ces livraions et voulez-vous proceder aux modifications des stocks entrepot ? ");
		System.out.println("1 : Oui");
		System.out.println("2 : Non ");

		int nombre = Clavier.lireInt();

		switch (nombre) {
		case 1 :
			Class.forName( "org.hsqldb.jdbcDriver");
			String url = "jdbc:hsqldb:file:database"+File.separator+"basic;shutdown=true";
			String login = "sa";
			String password = "";
			try (Connection connection = DriverManager.getConnection( url, login, password )){
				String requete = null;
		
				FromJSON js=FromJSON.deserializationFromJson();
				ArrayList<Integer> stockAEnlever=LireTxt2.demandesFromBordereau();
				ArrayList<Integer> entrepotLivrant=js.getIdEntrepot();
				for(int i = 0;i< stockAEnlever.size();i++) {
					requete="UPDATE entrepot SET stock = stock - " + stockAEnlever.get(i)
					+ "    WHERE id_entrepot = " + LireTxt2.getIdOrigines().get(fj.getDeliveriesfor_customer().get(i));
					try ( Statement statement = connection.createStatement() ) {
						statement.executeUpdate( requete );
					}
				}
			}
		case 2: 
			System.out.println();
		}
		System.out.println("Traitement terminé, merci et a bientot ! ");

	}

}
