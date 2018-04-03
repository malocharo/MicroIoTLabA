import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class BlocLeds extends BlocWith2Pins {

	private String leds = "0000";
	
	@Override
	public void draw() {
		gc.clearRect(0, 0, sizeX, sizeY);
		gc.setFill(new Color(0.78, 0.78, 0.66, 0.7));		
		gc.fillRoundRect(1, 1, sizeX - 2, sizeY - 2, 10, 10);
		gc.setLineWidth(1.5);
		if(drawContour) {
			gc.setStroke(new Color(0.78, 0.78, 0.66, 0.3));
		}
		else {
			gc.setStroke(new Color(0.58, 0.58, 0.39, 0.9));
			
		}
		gc.strokeRoundRect(1, 1, sizeX - 2, sizeY - 2, 10, 10);
		gc.setLineWidth(1.4);
		gc.setStroke(new Color(1, 1, 1, 0.5));
		for(int i=0; i<29; i+=4) {
			gc.strokeLine(160, 6+i, 190, 6+i);	
		}		
	}

	@Override
	public void init2() {
		tf = new TextField("1000");
		tf.setLayoutX(x-sizeX/2+10);
		tf.setLayoutY(y-sizeY/2+9);
		tf.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #FFFFFF9F; -fx-font-size: 10");
		Main.controleur.plateau.getChildren().add(tf);
	}
	
	@Override
	public void initForSimulation() {
		leds = tf.getText();
	}
		
	public BlocLeds(double x, double y) {
		super(x,y);
	}
	
	@Override
	public String toString() {
		return "LEDS "+leds;
	}
	
	@Override
	public void execute() {
		// Mettre à jour les leds
		if (SimulationPlatform.card1 != null) {
			SimulationPlatform.card1.runCommand(this.leds);
		}

		if (SimulationPlatform.card2 != null) {
			SimulationPlatform.card2.runCommand(this.leds);
		}
	}
		
}
