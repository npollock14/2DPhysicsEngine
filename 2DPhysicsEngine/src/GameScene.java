import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameScene extends Scene {
	Poly p; 
	Poly p2; 
	Vec2 vel = new Vec2(0,0);
	int velMult = 4;
	Point center = new Point(Driver.screenWidth/2, Driver.screenHeight/2);

	@Override
	public void draw(Graphics g) {
		p2.draw(g, true);
		p.draw(g, true);
		g.setFont(Misc.fBig);
		//g.drawString(""+p.intersects(p2), 500, 500);
		
		//center.drawOval(g, 10);
		//g.drawString(""+center.angleTo(InputManager.mPos), 100, 100);
		
		
		
	}

	@Override
	public void update() {
		
		if(InputManager.keys[81]) {
			p.rotate(.01);
		}
		if(InputManager.keys[69]) {
			p.rotate(-.01);
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
		//p = new Poly(500,500,600,500,600,600,500,600,500,500);
//		p2 = new Poly(500,500,700,500,700,800,500,800,500,500);
		p = new Poly(1184.0,-116.0,1234.0,-76.0,1034.0,24.0,984.0,184.0,1284.0,284.0,1284.0,384.0,1484.0,484.0,1084.0,734.0,884.0,134.0,1184.0,-116.0);
		p2 = new Poly(700,300,800,500,700,550,600,800, 400,500,200,500,700,300);
	}

}
