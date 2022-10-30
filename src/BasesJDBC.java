import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BasesJDBC {
	
	public static void lancementBDD() throws SQLException, ClassNotFoundException {
		Class.forName( "org.hsqldb.jdbcDriver" );
		String url = "jdbc:hsqldb:file:database"+File.separator+"basic;shutdown=true";
		String login = "sa";
		String password = "";
		try (Connection connection = DriverManager.getConnection( url, login, password )){
				String requete = "DROP TABLE site IF EXISTS;";
				try ( Statement statement = connection.createStatement() ) {
				statement.executeUpdate( requete );
				}
				
				requete = "CREATE TABLE site ("
						+"id_site int,"
						+"x int,"
						+"y int,"
						+"PRIMARY KEY(id_site))";
						try ( Statement statement = connection.createStatement() ) {
							statement.executeUpdate( requete );
							}
	
				for(Site s : LireCSV2.sitesFromCSV()) {
					requete = "INSERT INTO site (id_site, x, y) VALUES"
							+"("+s.getId()+"," +s.getPoint().getx()+","+s.getPoint().gety()+")";
						try ( Statement statement = connection.createStatement() ) {
							statement.executeUpdate( requete );
							}
				}
					
					
				requete = "DROP TABLE commande IF EXISTS;";
					try ( Statement statement = connection.createStatement() ) {
					statement.executeUpdate( requete );
					}
				requete = "CREATE TABLE commande("
						+"mail varchar(256),"
						+"id_entrepot int,"
						+"date DATE,"
						+"demande int,"
						+"PRIMARY KEY(mail, date))";
				try ( Statement statement = connection.createStatement() ) {
					statement.executeUpdate( requete );
					}
					//INSERT A FAIRE PLUS TARD
				
				
				requete = "DROP TABLE client IF EXISTS;";
				try ( Statement statement = connection.createStatement() ) {
				statement.executeUpdate( requete );
				}				
				requete = "CREATE TABLE client("
						+"mail varchar(256),"
						+"nom varchar(50),"
						+"id_site int,"
						+"PRIMARY KEY(mail))";
				try ( Statement statement = connection.createStatement() ) {
					statement.executeUpdate( requete );
					}
				
				for(int i =0; i<LireCSV2.clientsFromCSV().size();i++) {
					requete = "INSERT INTO client (mail,nom,id_site) VALUES"
							+LireCSV2.clientsFromCSV().get(i).toString();	
				try ( Statement statement = connection.createStatement() ) {
						statement.executeUpdate( requete );
						}			
				}
				
				
				
				requete = "DROP TABLE entrepot IF EXISTS;";
				try ( Statement statement = connection.createStatement() ) {
				statement.executeUpdate( requete );
				}
				requete = "CREATE TABLE entrepot("
						+"id_entrepot int,"
						+"id_site int,"
						+"cout_site int,"
						+"stock int,"
						+"PRIMARY KEY(id_entrepot))";
				try ( Statement statement = connection.createStatement() ) {
					statement.executeUpdate( requete );
					}
				for(Entrepot e : LireCSV2.entrepotsFromCSV()) {
					requete = "INSERT INTO entrepot (id_entrepot, id_site, cout_site, stock) VALUES"
							+e.toString();							
							
				try ( Statement statement = connection.createStatement() ) {
						statement.executeUpdate( requete );
						}
				}
				
				
				requete = "DROP TABLE route IF EXISTS;";
				try ( Statement statement = connection.createStatement() ) {
				statement.executeUpdate( requete );
				}
				requete = "CREATE TABLE route("
						+"origine int,"
						+"destination int,"
						+"PRIMARY KEY(origine, destination))";
				try ( Statement statement = connection.createStatement() ) {
					statement.executeUpdate( requete );
					}
				for(Routes r : LireCSV2.routesFromCSV()) {
					requete = "INSERT INTO route (origine, destination) VALUES"
							+r.toString();							
							
				try ( Statement statement = connection.createStatement() ) {
						statement.executeUpdate( requete );
						}
				}
				
				
			}
	}
	public static void main(String[] args) throws Exception {
		lancementBDD();
				
		}
	
}
