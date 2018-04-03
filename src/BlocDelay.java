import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class BlocDelay extends BlocWith2Pins {

	private int delay = 1000;
	
	public void draw() {
		gc.clearRect(0, 0, sizeX, sizeY);
		gc.setFill(new Color(0.52, 0.58, 0.78, 0.5));		
		gc.fillRoundRect(1, 1, sizeX - 2, sizeY - 2, 10, 10);
		gc.setLineWidth(1.5);
		if(drawContour) {
			gc.setStroke(new Color(0.52, 0.58, 0.78, 0.3));
		}
		else {
			gc.setStroke(new Color(0.52, 0.58, 0.78, 0.9));
			
		}
		gc.strokeRoundRect(1, 1, sizeX - 2, sizeY - 2, 10, 10);
		gc.setLineWidth(1.4);
		gc.setStroke(new Color(1, 1, 1, 0.5));
		for(int i=0; i<29; i+=4) {
			gc.strokeLine(160, 6+i, 190, 6+i);	
		}
	}
	
	public void init2() {
		delay = 1000;
		tf = new TextField(""+delay);
		tf.setLayoutX(x-sizeX/2+10);
		tf.setLayoutY(y-sizeY/2+9);
		tf.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #FFFFFF9F; -fx-font-size: 10");
		Main.controleur.plateau.getChildren().add(tf);
	}
		
	public void initForSimulation() {
		delay = Integer.valueOf(tf.getText());
	}
	
	public BlocDelay(double x, double y) {
		super(x,y);
	}

	@Override
	public String toString() {
		return "DELAY "+delay;
	}
		
	@Override
	public void execute() {
		// Mettre une attente égale à delay
		try {
			Thread.sleep((long)this.delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
}
