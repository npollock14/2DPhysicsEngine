import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameScene extends Scene {
	Poly p = new Poly(500,500,700,500,700,700,500,700,500,500);
	Poly p2 = new Poly(700,700,600,900,400,550,700,700);
	boolean added = false;
	Vec2 vel = new Vec2(0,0);
	int velMult = 4;

	@Override
	public void draw(Graphics g) {
		p2.draw(g, true);
		p.draw(g, true);
		g.setFont(Misc.fBig);
		g.drawString(""+p.intersects(p2), 500, 500);
	}

	@Override
	public void update() {
		if(!added && InputManager.keys[32]) {
			p.addVert(2, new Point(600,800));
			added = true;
		}
		if(InputManager.keys[38]) {
			vel.y -= velMult;
		}
			if(InputManager.keys[40]) {
				vel.y +=velMult;
			}
			if(InputManager.keys[37]) {
				vel.x -=velMult;
			}
				if(InputManager.keys[39]) {
					vel.x += velMult;
				}
		p.translate(vel);
		vel.x = 0;
		vel.y = 0;
	}

	@Override
	public void init() {
		
	}

}
