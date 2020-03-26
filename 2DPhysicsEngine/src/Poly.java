import java.awt.Graphics;
import java.util.ArrayList;

public class Poly {
	private ArrayList<Point> verts = new ArrayList<Point>();

	public Poly(double... pts) {
		if (pts.length % 2 != 0)
			throw new IllegalArgumentException("Polygon must be made with points in 2's");
		for (int i = 0; i < pts.length - 1; i += 2) {
			verts.add(new Point((double) pts[i], (double) (pts[i + 1])));
		}
	}

	public void draw(Graphics g, boolean drawPoints) {
		for (int i = 0; i < verts.size() - 1; i++) {
			verts.get(i).drawLine(g, verts.get(i + 1));

		}
		if (drawPoints) {
			for (Point p : verts) {
				p.fillOval(g, 12);
				g.drawString(p.toString(), (int) p.x, (int) p.y - 20);
			}
		}

	}

	public void addVerts(double... pts) {
		if (pts.length % 2 != 0)
			throw new IllegalArgumentException("Polygon must be made with points in 2's");
		for (int i = 0; i < pts.length - 1; i += 2) {
			verts.add(new Point((double) pts[i], (double) (pts[i + 1])));
		}
	}

	public void addVerts(Point... pts) {
		for (int i = 0; i < pts.length; i++) {
			verts.add(new Point(pts[i].x, pts[i].y));
		}
	}

	public void addVert(int pos, Point pt) {
		verts.add(pos, new Point(pt.x, pt.y));
	}

	public int numVerts() {
		return verts.size();
	}

	public boolean surrounds(Point p) {
		
		int ct = 0;
		double offset = .1;

		for (int i = 0; i < verts.size() - 1; i++) {
			double ylow = (verts.get(i).y < verts.get(i + 1).y ? verts.get(i).y : verts.get(i + 1).y);
			double yhi = (verts.get(i).y > verts.get(i + 1).y ? verts.get(i).y : verts.get(i + 1).y);
			if (p.y + offset >= ylow && p.y + offset <= yhi) {
				double s = (verts.get(i).y - verts.get(i + 1).y) / (verts.get(i).x - verts.get(i + 1).x);
				if ((p.y + offset - verts.get(i).y) / s + verts.get(i).x >= p.x) {
					ct++;
				}

			}

		}
		return ct % 2 == 1;
	}

}
