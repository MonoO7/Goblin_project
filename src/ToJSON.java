import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.*;

public class ToJSON {
	private ArrayList<Integer> capacity_facility;
	private ArrayList<Integer> fixed_cost_facility;
	private ArrayList<Integer> demand_customer;
	private int[][] cost_matrix;
	private int num_facility_locations;
	private int num_customers;
	/**
	 * @param capacity_facility
	 * @param fixed_cost_facility
	 * @param demand_customer
	 * @param cost_matrix
	 * @param num_facility_locations
	 * @param num_customers
	 */
	
	public ToJSON(ArrayList<Integer> capacity_facility, ArrayList<Integer> fixed_cost_facility,
			ArrayList<Integer> demand_customer, int[][] cost_matrix, int num_facility_locations, int num_customers) {
		this.capacity_facility = capacity_facility;
		this.fixed_cost_facility = fixed_cost_facility;
		this.demand_customer = demand_customer;
		this.cost_matrix = cost_matrix;
		this.num_facility_locations = num_facility_locations;
		this.num_customers = num_customers;
	}

	/**
	 * serializationToJson(), mets les donnés au format pour le solveur en python --> https://www.youtube.com/watch?v=BbI8FdQOKNs
	 * @return Le fichier de donnés au format Json pour le solveur
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void serializationToJson() throws IOException, ClassNotFoundException, SQLException {
		ToJSON solveurPourPython = new ToJSON(Entrepot.getListStockOuvert(), Entrepot.getListCoutFixeEntrepotOuvert(),LireTxt2.getDemandes(),Site.getMatrixCost(),Entrepot.getListCoutFixeEntrepotOuvert().size(),LireTxt2.getDemandes().size());
		Gson gson = new GsonBuilder()
					.setPrettyPrinting()
					.create();
		
		String solveurEnJson = gson.toJson(solveurPourPython);
		//System.out.println(solveurEnJson);
		String cheminFichier = "res" + File.separator + "donnees.json";
		try (Writer w = new FileWriter(cheminFichier)){
			w.write(solveurEnJson);
			w.close();
		}
	}
	
	/**
	 * Execution du panneau de commande, lancement du solveur sur Python, et création des fichiers 
	 * @throws IOException
	 */
	public static void exCommande() throws IOException  {
		String[] cmdArray = {"python",
				"res"+File.separator+"SSCFLP.py",
				"res"+File.separator+"donnees.json",
				"res"+File.separator+"resolution.json",
				"exit()"
		};
		Process process = Runtime.getRuntime().exec(cmdArray);
		while(process.isAlive()) {}
	} 
}
