import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MenuScene extends Scene {
	
	Button buttonStart;
	
	Font fntSmall = new Font("Impact", 0, 35);
	public void draw(Graphics g) {
		g.setColor(new Color(75, 0, 0));
		g.fillRect(0, 0, Driver.screenWidth, Driver.screenHeight);
		
		buttonStart.draw(g, 110, 38);
	}

	
	public void update() {
		buttonStart.update();
		
		if (buttonStart.clicked) {
			
			SceneManager.gs.setActive(true);
			SceneManager.ms.setActive(false);
			
		}
	}

	public void init() {
		buttonStart = new Button(new Rect(850, 400, 300, 50), null, 0, "Start", fntSmall, Color.GRAY, true, false);
	}

}
