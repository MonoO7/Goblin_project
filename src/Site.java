import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Site {

	private int id;
	private Point point;
	
	public Site(int id, Point point) {
		this.id = id;
		this.point=point;
	}


	/**
	 * @return the point
	 */
	public Point getPoint() {
		return point;
	}


	public int getId() {
		return id;
	}

	
	public String toString() {
		return this.getId()+" "+this.getPoint();
	}
	
	/**
	 * 
	 * @return Les matrices des couts associés à chaque client par entrepot
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static int[][] getMatrixCost() throws IOException, ClassNotFoundException, SQLException{
		int [][] matriceFloyd = Routes.testFloydWarshall(Routes.AdjacencyMatrixUndirectedGraph());
		ArrayList<Integer> idOrigines = Entrepot.getListeIdEntrepot();
		ArrayList<Integer> listeSiteClient = Client.getListeSiteClientViaMail();
		int[][] matriceDesCouts = new int[idOrigines.size()][listeSiteClient.size()];
		for (int i = 0; i<idOrigines.size();i++) {
			for (int j = 0; j<listeSiteClient.size();j++) {
				matriceDesCouts[i][j]=matriceFloyd[idOrigines.get(i)-1][listeSiteClient.get(j)-1];
			}	
		}		
		return matriceDesCouts;
	}
	
	
}
