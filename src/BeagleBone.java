import javafx.scene.image.Image;

public class BeagleBone extends CardWith4Leds {

	public void draw() {
		image = new Image("beaglebone_black.png");
		super.draw();
	}
	
	public BeagleBone(double x, double y) {
		super(x, y);		
	}
	
}
