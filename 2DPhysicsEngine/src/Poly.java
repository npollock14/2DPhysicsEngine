import java.awt.Graphics;
import java.util.ArrayList;

public class Poly {
	private ArrayList<Point> verts = new ArrayList<Point>();
	private ArrayList<Segment> segs = new ArrayList<Segment>();

	public Poly(double... pts) {
		if (pts.length % 2 != 0 || pts.length < 6)
			throw new IllegalArgumentException("Polygon must have at least 3 sets of 2 points");
		for (int i = 0; i < pts.length - 1; i += 2) {
			verts.add(new Point((double) pts[i], (double) (pts[i + 1])));
		}
		for (int i = 0; i < verts.size() - 1; i++) {
			segs.add(new Segment(verts.get(i), verts.get(i + 1)));
		}

	}

	public void draw(Graphics g, boolean drawPoints) {
		for (Segment s : segs) {
			s.draw(g, drawPoints);
			g.drawString(s.getP1().toString(), (int) s.getP1().x + 10, (int) s.getP1().y + 10);
			g.drawString(s.getP2().toString(), (int) s.getP2().x + 10, (int) s.getP2().y + 10);
		}
	}
	public void translate(Vec2 d) {
		//TODO FIX
		for(Point p : verts) {
			p.add(d);
		}
		for(int i = 0; i < segs.size() - 1; i++) {
			segs.get(i).translate(d);
		}
	}

	// public void addVerts(double... pts) {
	// if (pts.length % 2 != 0)
	// throw new IllegalArgumentException("Polygon must be made with points in
	// 2's");
	// for (int i = 0; i < pts.length - 1; i += 2) {
	// verts.add(new Point((double) pts[i], (double) (pts[i + 1])));
	// }
	// }
	//
	// public void addVerts(Point... pts) {
	// for (int i = 0; i < pts.length; i++) {
	// verts.add(new Point(pts[i].x, pts[i].y));
	// }
	// }

	public void addVert(int pos, Point pt) {
		verts.add(pos, new Point(pt.x, pt.y));
		segs.set(pos, new Segment(segs.get(pos).getP1(), pt));
		segs.add(new Segment(pt, segs.get(pos == segs.size() - 1 ? 0 : pos + 1).getP1()));
	}

	public int numVerts() {
		return verts.size();
	}

	public int numEdges() {
		return segs.size();
	}

	public boolean surrounds(Point p) {

		int ct = 0;
		double offset = .001;

		for (Segment seg : segs) {
			if (p.y + offset >= seg.yLow && p.y + offset <= seg.yHi) {

				if ((p.y + offset - seg.getP1().y) / seg.getSlope() + seg.getP1().x + offset >= p.x) {
					ct++;
				}
			}
		}
		return ct % 2 == 1;
	}

	public boolean intersects(Poly p) {
		for (Segment s1 : segs) {
			for (Segment s2 : p.segs) {
				if (s1.intersects(s2)) {
					return true;
				}
			}
		}
		if (p.surrounds(verts.get(0)))
			return true;
		if (this.surrounds(p.verts.get(0)))
			return true;
		return false;

	}

}

class Segment {
	private Point p1, p2;
	private double slope;
	private double b;
	private double rlen;
	private boolean vert = false;
	double yLow, yHi;

	public Segment(double x1, double y1, double x2, double y2) {
		super();
		this.p1 = new Point(x1, y1);
		this.p2 = new Point(x2, y2);

		if (y1 > y2) {
			yHi = y1;
			yLow = y2;
		} else {
			yHi = y2;
			yLow = y1;
		}

		if (p1.x - p2.x == 0)
			vert = true;
		if (!vert) {
			this.slope = (p1.y - p2.y) / (p1.x - p2.x);
		} else {
			this.slope = Double.MAX_VALUE;
		}
		this.b = p1.y - slope * p1.x;

		rlen = p1.relDistanceTo(p2);
	}

	public Segment(Point p1, Point p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;

		if (p1.y > p2.y) {
			yHi = p1.y;
			yLow = p2.y;
		} else {
			yHi = p2.y;
			yLow = p1.y;
		}

		if (p1.x - p2.x == 0)
			vert = true;
		if (!vert) {
			this.slope = (p1.y - p2.y) / (p1.x - p2.x);
		} else {
			this.slope = Double.MAX_VALUE;
		}

		this.b = p1.y - slope * p1.x;
		rlen = p1.relDistanceTo(p2);
	}
	public void translate(Vec2 v) {
		p1.add(v);
		p2.add(v);
		this.b = p1.y - slope * p1.x;
	}

	public void setP1(double x, double y) {
		this.p1 = new Point(x, y);
		if (p1.x - p2.x == 0) {
			vert = true;
		} else {
			vert = false;
		}

		this.slope = (p1.y - p2.y) / (p1.x - p2.x);
		this.b = p1.y - slope * p1.x;

		rlen = p1.relDistanceTo(p2);
	}

	public Point getP1() {
		return p1;
	}

	public Point getP2() {
		return p2;
	}

	public double getSlope() {
		return slope;
	}

	public double getB() {
		return b;
	}

	public double getRlen() {
		return rlen;
	}

	public boolean isVert() {
		return vert;
	}

	public void setP2(double x, double y) {
		this.p2 = new Point(x, y);
		if (p1.x - p2.x == 0) {
			vert = true;
		} else {
			vert = false;
		}
		if (!vert) {
			this.slope = (p1.y - p2.y) / (p1.x - p2.x);
			this.b = p1.y - slope * p1.x;
		}
		rlen = p1.relDistanceTo(p2);
	}

	public void draw(Graphics g, boolean drawPoints) {
		p1.drawLine(g, p2);
		if (drawPoints) {
			p1.drawOval(g, 3);
			p2.drawOval(g, 3);
		}
	}

	public boolean intersects(Segment s) {
		if (!vert && !s.vert) {
			double x = (s.b - b) / (slope - s.slope);
			double y = slope * x + b;
			Point c = new Point(x, y);
			return p1.relDistanceTo(c) < rlen && p2.relDistanceTo(c) < rlen && s.p1.relDistanceTo(c) < s.rlen
					&& s.p2.relDistanceTo(c) < s.rlen;
		} else if (vert && s.vert) {
			return false;
		} else if (!vert && s.vert) {
			double x = s.p1.x;
			double y = slope * x + b;
			Point c = new Point(x, y);
			return p1.relDistanceTo(c) < rlen && p2.relDistanceTo(c) < rlen && s.p1.relDistanceTo(c) < s.rlen
					&& s.p2.relDistanceTo(c) < s.rlen;
		} else {
			double x = p1.x;
			double y = s.slope * x + s.b;
			Point c = new Point(x, y);
			return p1.relDistanceTo(c) < rlen && p2.relDistanceTo(c) < rlen && s.p1.relDistanceTo(c) < s.rlen
					&& s.p2.relDistanceTo(c) < s.rlen;
		}

	}

}
