import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameScene extends Scene {
	Poly p = new Poly(500,500,700,500,700,700,500,700,500,500);
	

	@Override
	public void draw(Graphics g) {
		
		p.draw(g, true);
		g.setFont(Misc.fBig);
		g.drawString(""+p.surrounds(InputManager.mPos), 500, 500);
	}

	@Override
	public void update() {
	}

	@Override
	public void init() {
		
	}

}
