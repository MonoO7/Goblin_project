import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FromBDDToList {
	private ArrayList<Entrepot> entrepots;
	private ArrayList<Routes> routes;
	private ArrayList<Client> clients;
	private ArrayList<Site> sites;


	public static ArrayList<Entrepot> entrepotEnListe() throws SQLException, ClassNotFoundException{
		Class.forName( "org.hsqldb.jdbcDriver");
		String url = "jdbc:hsqldb:file:database"+File.separator+"basic;shutdown=true";
		String login = "sa";
		String password = "";
		ArrayList<Entrepot> entrepots = new ArrayList<Entrepot>();
		try (Connection connection = DriverManager.getConnection( url, login, password )){
			String requete = "SELECT * FROM entrepot";
			try ( Statement statement = connection.createStatement() ) {
				try (ResultSet resultSet = statement.executeQuery( requete ) ) {
					while( resultSet.next() ) {
						int id_entrepot = resultSet.getInt( "id_entrepot" );
						int id_site = resultSet.getInt( "id_site" );
						int cout_site = resultSet.getInt( "cout_site" );
						int stock = resultSet.getInt( "stock" );
						entrepots.add(new Entrepot(id_entrepot,id_site,cout_site,stock));
					}
				}
			}
		}
		return entrepots;
	}
	
	public static ArrayList<Routes> routeEnListe() throws SQLException, ClassNotFoundException{
		Class.forName( "org.hsqldb.jdbcDriver");
		String url = "jdbc:hsqldb:file:database"+File.separator+"basic;shutdown=true";
		String login = "sa";
		String password = "";
		ArrayList<Routes> routes = new ArrayList<Routes>();
		try (Connection connection = DriverManager.getConnection( url, login, password )){
			String requete = "SELECT * FROM route";
			try ( Statement statement = connection.createStatement() ) {
				try (ResultSet resultSet = statement.executeQuery( requete ) ) {
					while( resultSet.next() ) {
						int origine = resultSet.getInt( "origine" );
						int destination = resultSet.getInt( "destination" );
						
						routes.add(new Routes(origine,destination));
					}
				}
			}
		}
		return routes;
	}
	
	public static ArrayList<Client> clientEnListe() throws SQLException, ClassNotFoundException{
		Class.forName( "org.hsqldb.jdbcDriver");
		String url = "jdbc:hsqldb:file:database"+File.separator+"basic;shutdown=true";
		String login = "sa";
		String password = "";
		ArrayList<Client> clients = new ArrayList<Client>();
		try (Connection connection = DriverManager.getConnection( url, login, password )){
			String requete = "SELECT * FROM client";
			try ( Statement statement = connection.createStatement() ) {
				try (ResultSet resultSet = statement.executeQuery( requete ) ) {
					while( resultSet.next() ) {
						String mail = resultSet.getString( "mail" );
						String nom = resultSet.getString( "nom" );
						int id_site = resultSet.getInt( "id_site" );

						clients.add(new Client(mail,nom,id_site));
					}
				}
			}
		}
		return clients;
	}
	
	public static ArrayList<Site> siteEnListe() throws SQLException, ClassNotFoundException{
		Class.forName( "org.hsqldb.jdbcDriver");
		String url = "jdbc:hsqldb:file:database"+File.separator+"basic;shutdown=true";
		String login = "sa";
		String password = "";
		ArrayList<Site> sites = new ArrayList<Site>();
		try (Connection connection = DriverManager.getConnection( url, login, password )){
			String requete = "SELECT * FROM site";
			try ( Statement statement = connection.createStatement() ) {
				try (ResultSet resultSet = statement.executeQuery( requete ) ) {
					while( resultSet.next() ) {
						int id_site = resultSet.getInt( "id_site" );
						int x = resultSet.getInt( "x" );
						int y = resultSet.getInt( "y" );
						sites.add(new Site(id_site,new Point(x,y)));
					}
				}
			}
		}
		return sites;
	}
	
	public static ArrayList<Entrepot> getEntrepots() throws ClassNotFoundException, SQLException{
		return entrepotEnListe();
	}
	public static ArrayList<Routes> getRoutes() throws ClassNotFoundException, SQLException{
		return routeEnListe();
	}
	public static ArrayList<Site> getSites() throws ClassNotFoundException, SQLException{
		return siteEnListe();
	}
	public static ArrayList<Client> getClients() throws ClassNotFoundException, SQLException{
		return clientEnListe();
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		BasesJDBC.lancementBDD();
		System.out.println(getClients());
		System.out.println(routeEnListe());
		System.out.println(siteEnListe());
		System.out.println(clientEnListe());
		
	}

}
