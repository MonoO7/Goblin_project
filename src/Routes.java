import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Routes {
	
	private int origine, destination;

	public Routes(int origine, int destination) {
		this.origine = origine;
		this.destination = destination;
	}

	public int getOrigine() {
		return origine;
	}

	public int getDestination() {
		return destination;
	}
	
	public String toString() {
		return "("+this.getOrigine()+","+this.getDestination()+")";
	}
	
	/**
	 * Permet d'obtenir les x et y des sites d'origine et de destination
	 * @param id de la destination puis de l'origine
	 * @return Le site correnpondant à l'id entré en paramètre 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static Site getSite(int id) throws ClassNotFoundException, SQLException {
		List<Site> sites = FromBDDToList.siteEnListe();
		for(Site s : sites) {
			if (id==s.getId()) {
				return s;
			}
		}
		return null;
	}
	/**
	 * Calcul de la longueur des routes 
	 * @param x the vertex selected
	 * @return a list of vertices which are the neighbours of x
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int longueur() throws ClassNotFoundException, SQLException {
		//	List<Site> sites = LireCSV2.sitesFromCSV();
			int x1 = getSite(getDestination()).getPoint().getx();
			int y1 = getSite(getDestination()).getPoint().gety();
			int x2 = getSite(getOrigine()).getPoint().getx();
			int y2 = getSite(getOrigine()).getPoint().gety();
			int x=(x2-x1)*(x2-x1);
			int y=(y2-y1)*(y2-y1);
			
			return (int)(Math.ceil(Math.sqrt(x+y)));
		}
	/**
	 * Remplissage de la matrice d'Adjacency avec les distances pour données
	 * @return La matrice avec en tant que donnés les couts fixes entre chaque site
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	 public static int[][] AdjacencyMatrixUndirectedGraph() throws ClassNotFoundException, SQLException{//List<Site> sites, List<Routes> routes){
		int taille=FromBDDToList.getSites().size();
		int[][] matriceLongueur = new int[taille][taille];
		ArrayList<Integer> longRoute =LireCSV2.longueurRoute();
		for(int i = 0; i<taille; i++){
				for(int j = i; j<taille; j++){
					matriceLongueur[i][j] = 0;
				}
			}
		int i =0;
		ArrayList<Routes> routes = FromBDDToList.getRoutes();
		for (Routes r : routes) {
			matriceLongueur[r.getOrigine()-1][r.getDestination()-1] = longRoute.get(i);
			matriceLongueur[r.getDestination()-1][r.getOrigine()-1] = longRoute.get(i);
			i++;
		}
		return matriceLongueur;
	}

		/**
		 * Implementation de l'algorithme floyd warshall afin de touver les plus courts chemin entre les sites inspiré entre autre de : https://www.programiz.com/dsa/floyd-warshall-algorithm
		 * @param graph[][] la matrice d'adjacency
		 * @return La liste des plus courts chemins entre les sites
		 * @throws SQLException 
		 * @throws ClassNotFoundException 
		 */
	  public static int[][] testFloydWarshall(int graph[][]) throws ClassNotFoundException, SQLException {
	    int i, j, k;
	    int nodes = AdjacencyMatrixUndirectedGraph().length;
	    for (i = 0; i < nodes; i++) {
	    	for (j = 0; j < nodes; j++) {
	    		if(i!=j) {
	    			if(graph[i][j]==0) {
	    			  graph[i][j] = (int) (Math.ceil(Integer.MAX_VALUE/2));
	    			}
	    		}
	    	}
	    }
	    for (k = 0; k < nodes; k++){ 
	    	for (i = 0; i < nodes; i++){ 
	    		for (j = 0; j < nodes; j++){ 
	    			// If vertex k is on the shortest path from 
	    			// i to j, then update the value of dist[i][j] 
	    			if (graph[i][k] + graph[k][j] < graph[i][j]) {
	    				graph[i][j] = graph[i][k] + graph[k][j]; 
	    			}
	    		} 
	    	} 
	    } 
	    return graph;
	  } 
	
}