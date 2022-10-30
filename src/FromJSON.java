import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.ProcessBuilder.Redirect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.Gson.*;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class FromJSON {
	private String status;
	private ArrayList<Integer> openfacilities;
	private ArrayList<Integer> deliveriesfor_customer;
	private int objective;

	/**
	 * @param status
	 * @param openfacilities
	 * @param deliveriesfor_customer
	 * @param objective
	 */
	public FromJSON(String status, ArrayList<Integer> openfacilities, ArrayList<Integer> deliveriesfor_customer,int objective) {
		this.status = status;
		this.openfacilities = openfacilities;
		this.deliveriesfor_customer = deliveriesfor_customer;
		this.objective = objective;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @return the openfacilities
	 */
	public ArrayList<Integer> getOpenfacilities() {
		return openfacilities;
	}


	/**
	 * @return the deliveriesfor_customer
	 */
	public ArrayList<Integer> getDeliveriesfor_customer() {
		return deliveriesfor_customer;
	}


	/**
	 * @return the objective
	 */
	public int getObjective() {
		return objective;
	}

	/**
	 * Recupere le json de resolution, le lit et le mets en forme
	 * @return resultat
	 */
	public static FromJSON deserializationFromJson() throws IOException {
		String cheminFichierResolution = "res"+File.separator+"resolution.json";
		FromJSON res;
		try (Reader r = new FileReader(cheminFichierResolution)){
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			res = gson.fromJson(r, FromJSON.class);
			r.close();
			return res;
		}
	}
	
	@Override
	public String toString() {
		return "status=" + getStatus() + ", openfacilities=" + getOpenfacilities() + ", deliveriesfor_customer="
				+ getDeliveriesfor_customer() + ", objective=" + getObjective();
	}
	/**
	 * @return la liste des entrepots qui livre
	 */
	public ArrayList<Integer> getEntrepotLivrant(){
		ArrayList<Integer> listEntrepotLivrant = new ArrayList<Integer>();
		for(Integer eL : getOpenfacilities()) {
			listEntrepotLivrant.add(eL);
		}
		return listEntrepotLivrant;
	}
	
	/**
	 * @return la liste des ID entrepot des entrepots livrant
	 */
	public ArrayList<Integer> getIdEntrepot() throws IOException {
        ArrayList<Integer> liste = LireTxt2.getIdOrigines();
        ArrayList<Integer> IdEntrepot = getEntrepotLivrant();
        ArrayList<Integer> listEntrepotLivrant = new ArrayList<Integer>();
        for(Integer e : IdEntrepot) {
        	listEntrepotLivrant.add(liste.get(e));
        }
        return listEntrepotLivrant;
    }
	
	/**
	 * @return la liste des demandes clients
	 */
	public ArrayList<Integer> getClientLivree(){
		ArrayList<Integer> listClientLivree = new ArrayList<Integer>();
		for(Integer eL : getDeliveriesfor_customer()) {
			listClientLivree.add(eL);
		}
		return listClientLivree;
	}
	

	/**
	 * @see le mail, et l'ID entrepot de l'entrepot qui livre le client
	 */
	public void getNomClient() throws IOException{
		ArrayList<String> mails = LireTxt2.getMails();
		FromJSON fj = deserializationFromJson();
		int i =0;
		for(String f : mails) {
			System.out.println(f +" est livré par l'entrepot " + LireTxt2.getIdOrigines().get(fj.getDeliveriesfor_customer().get(i)));
			i++;
		}
	}	

	
//	public void miseAjourStock() throws Exception{
//        Class.forName( "org.hsqldb.jdbcDriver");
//        FromJSON fj = deserializationFromJson();
//        String url = "jdbc:hsqldb:file:database"+File.separator+"goblin;shutdown=true";
//        String login = "sa";
//        String password = "";
//        try (Connection connection = DriverManager.getConnection( url, login, password )){
//            for (Integer d : LireTxt2.getDemandes()) {
//                String requete = "UPDATE Entrepots SET stock = stock - " + d
//                + "    WHERE id_entrepot = " + fj.getEntrepotLivrant();
//                try ( Statement statement = connection.createStatement() ) {
//                    statement.executeUpdate( requete );
//                }
//            }
//        }
//    }
	

}
