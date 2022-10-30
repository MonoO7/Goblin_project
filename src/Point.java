
public class Point {
	private int x;
	private int y;
 
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public int getx() {
		return this.x;
	}
	public int gety() {
		return this.y;
	}
	public boolean equals(Object o) {
		return (o!=null)
				&& (o instanceof Point)
				&& (super.equals(o))
				&& (this.getx()==((Point)o).getx())
				&& (this.gety()==((Point)o).gety());
	}
	public String toString() {
		return "("+this.getx()+", "+this.gety()+")";
	}
	public int longueur(Point p) {
		int x1 = this.getx();
		int y1 = this.gety();
		int x2 = p.getx();
		int y2 = p.gety();
		int x=(x2-x1)*(x2-x1);
		int y=(y2-y1)*(y2-y1);
		return (int)(Math.ceil(Math.sqrt(x+y)));
	}
}

