
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;



public class PointsARelier extends JFrame { // Extends JFrame indique que PointsARelier sera une fenetre
//========================================  // extends sera explique dans le cours de programmation par objets
   private static final long serialVersionUID = 1L;

   //   VARIABLE D'INSTANCE
   private List<Point> liste ;

   //   CONSTRUCTEUR
   public PointsARelier(String nomFichier) {
   //-------------------------------------
      super("Points � relier");
      addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e) {System.exit(0);}});
   // Initialise sa liste de positions a partir des coordonnees precisees dans nomFichier.
      this.liste = charger(nomFichier);
   }

   public List<Point> charger(String nomFichier) {
   // Retourne une liste de positions contenant les coordonnees indiquees dans le fichier nomFichier
      List<Point> l = new ArrayList<Point>();
      try {
         BufferedReader aLire= new BufferedReader(new FileReader(nomFichier));
         int col,lig;
         String ligne;
         do {
            ligne = aLire.readLine();

            if (ligne!=null) {
               col = Integer.parseInt(ligne);
               lig = Integer.parseInt( aLire.readLine());
               l.add(new Point(col,lig));
            }
         } while (ligne!=null);
         aLire.close( );
      }
      catch (IOException e) { 
         System.out.println("Une operation sur les fichiers a leve l'exception "+e);
      } 
      return l;
   }

}

