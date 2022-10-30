import java.util.Date;
import java.util.List;

public class Commande {
	private Date date;
	private List<String> mail;
	private List<Integer>  demande;
	private List<Integer>  id_origine;
	/**
	 * @param date
	 * @param mail
	 * @param demande
	 * @param id_origine
	 */
	public Commande(Date date, List<String> mail, List<Integer> demande, List<Integer> id_origine) {
		this.date = date;
		this.mail = mail;
		this.demande = demande;
		this.id_origine = id_origine;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @return the mail
	 */
	public List<String> getMail() {
		return mail;
	}
	/**
	 * @return the demande
	 */
	public List<Integer> getDemande() {
		return demande;
	}
	/**
	 * @return the id_livrant
	 */
	public List<Integer> getId_origine() {
		return id_origine;
	}
	
	public String toString() {
		return this.getDate()+" "+this.getMail()+" "+this.getDemande()+" "+this.getId_origine();
	}
	
}
