import java.io.File;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

public class Entrepot {

	private int id_entrepot, id_site, cout_fixe, stock;

	public Entrepot(int id_entrepot, int id_site, int cout_fixe, int stock) {
		this.id_entrepot = id_entrepot;
		this.id_site = id_site;
		this.cout_fixe = cout_fixe;
		this.stock = stock;
	}


	public int getId_entrepot() {
		return id_entrepot;
	}

	public int getId_site() {
		return id_site;
	}

	public int getCout_fixe() {
		return cout_fixe;
	}

	public int getStock() {
		return stock;
	}

	/**
	 * @return La liste des stocks des entrepots
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static ArrayList<Integer> getListStock() throws ClassNotFoundException, SQLException {
		ArrayList<Integer> stockEntrepots = new ArrayList<Integer>();
		ArrayList<Entrepot> entrepotsFormCSV = FromBDDToList.getEntrepots();
		for (Entrepot e : entrepotsFormCSV) {
			stockEntrepots.add(e.getStock());
		}
		return stockEntrepots;
	}

	/**
	 * @return La liste des couts fixe des entrepots
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static ArrayList<Integer> getCoutFixe() throws ClassNotFoundException, SQLException {
		ArrayList<Integer> coutFixeEntrepots = new ArrayList<Integer>();
		ArrayList<Entrepot> entrepotsFromCSV = FromBDDToList.getEntrepots();
		for (Entrepot e : entrepotsFromCSV) {
			coutFixeEntrepots.add(e.getCout_fixe());
		}
		return coutFixeEntrepots;
	}

	public String toString() {
		return "("+this.getId_entrepot()+","+this.getId_site()+","+this.getCout_fixe()+","+this.getStock()+")";
	}

	/**
	 * @param int n --> Si on veut obtenir l'ID Site d'un entrepot pour lequel on connais l'ID entrepot
	 * @return L'ID site de entrepot grace à son ID entrepot
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static int getIdentrepot(int n) throws ClassNotFoundException, SQLException {
		int a = 0;
		ArrayList<Entrepot> entrepotsFormCSV = FromBDDToList.getEntrepots();
		for(Entrepot e : entrepotsFormCSV) {
			//System.out.println(cl.getMail());
			if(e.getId_entrepot()==n) {
				return e.getId_site();
			}
		}
		return a;
	}
	/**
	 * 
	 * @return La liste des id des entrepots du bordereau de commande Txt
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static ArrayList<Integer> getListeIdEntrepot() throws IOException, ClassNotFoundException, SQLException {
		ArrayList<Integer> s = new ArrayList<Integer>();
		ArrayList<Integer> idOrigines = LireTxt2.getIdOrigines();
		for (Integer eId : idOrigines) {
			s.add(getIdentrepot(eId));
		}
		return s;
	}

	/**
	 * @return La liste des stocks des entrepots
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static ArrayList<Integer> getListStockOuvert() throws IOException, ClassNotFoundException, SQLException {
		ArrayList<Integer> stockEntrepots = new ArrayList<Integer>();
		ArrayList<Integer> idOrigines = LireTxt2.getIdOrigines();
		ArrayList<Entrepot> entrepotsFromCSV = FromBDDToList.getEntrepots();
		for (int i = 0;i<idOrigines.size();i++) {
			//for (int j = 0;j<LireCSV2.entrepotsFromCSV().size();j++) {
			for (Entrepot e : entrepotsFromCSV) {
				if(idOrigines.get(i)==e.getId_entrepot()) {
					stockEntrepots.add(e.getStock());
				}	
			}
		}
		return stockEntrepots;
	}

	/**
	 * @return La liste des couts fixes des entrepots ouvert
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static ArrayList<Integer> getListCoutFixeEntrepotOuvert() throws IOException, ClassNotFoundException, SQLException {
		ArrayList<Integer> idOrigines = LireTxt2.getIdOrigines();
		ArrayList<Integer> coutFixeEntrepots = new ArrayList<Integer>();
		ArrayList<Entrepot> entrepotsFormCSV = FromBDDToList.getEntrepots();
		for (int i = 0;i<idOrigines.size();i++) {
			for (Entrepot e : entrepotsFormCSV) {
				if(idOrigines.get(i)==e.getId_entrepot()) {
					coutFixeEntrepots.add(e.getCout_fixe());
				}	
			}
		}
		return coutFixeEntrepots;
	}
	
	
	public static Entrepot getEntrepot(int id) throws ClassNotFoundException, SQLException {
        ArrayList<Entrepot> liste = FromBDDToList.getEntrepots();
        for(Entrepot e : liste) {
            if(e.getId_entrepot()==id) {
                return e;
            }
        }
        return null;
    }

}
