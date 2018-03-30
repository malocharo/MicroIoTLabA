import javafx.scene.image.Image;

public class Arduino extends CardWith4Leds {

	public void draw() {
		image = new Image("arduino.png");
		super.draw();
	}
	
	public Arduino(double x, double y) {
		super(x, y);		
	}
	
}
