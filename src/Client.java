import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Client {
	
	private String nom, mail;
	private int id_site;
	
	public Client(String nom, String mail, int id_site) {
		this.nom = nom;
		this.mail = mail;
		this.id_site = id_site;
	}
	public String getNom() {
		return nom;
	}
	public String getMail() {
		return mail;
	}
	public int getId_site() {
		return id_site;
	}
	
	public String toString() {
		return "("+"'"+this.getMail()+"'"+","+"'"+this.getNom()+"'"+","+this.getId_site()+")";
	}
	
	/**
	 * 
	 * @param mail du client dont on veut le site
	 * @return Le site qui est associé au mail d'un client
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static int getSiteViaMail(String mail) throws ClassNotFoundException, SQLException {
		int a = 0;
		ArrayList<Client> clients = FromBDDToList.getClients();
		for(Client cl : clients) {
			//System.out.println(cl.getMail());
			if((cl.getMail()).equals(mail)) {
				a = cl.getId_site();
			}
		}
		return a;
	}
	
	/**
	 * 
	 * @return La liste des sites des clients du bordereau de commande Txt
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static ArrayList<Integer> getListeSiteClientViaMail() throws IOException, ClassNotFoundException, SQLException {
		ArrayList<Integer> s = new ArrayList<Integer>();
		ArrayList<String> mails = LireTxt2.getMails();
		for (String cl : mails) {
			s.add(getSiteViaMail(cl));
		}
		return s;
	}

}
