import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameScene extends Scene {
	BufferedImage map;
	double sizeRatio = 1.2;
	Color darkGrey = new Color(40, 40, 40); // background color
	Color darkRed = new Color(139, 0, 0); // line color

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) (g);
		g.setColor(darkGrey);
		g.fillRect(0, 0, Driver.screenWidth, Driver.screenHeight);
		g.drawImage(map, 300, 0, (int) (map.getWidth() / sizeRatio), (int) (map.getHeight() / sizeRatio), null);
		g2.setStroke(new BasicStroke(6));
		g.setColor(darkRed);
		g2.drawLine(0, Driver.screenHeight - 200, Driver.screenWidth, Driver.screenHeight - 200);

	}

	@Override
	public void update() {

	}

	@Override
	public void init() {
		map = Misc.loadImage("/USA-states.png");
	}

}
