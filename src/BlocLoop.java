import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BlocLoop extends BlocWith2Pins {

	protected PinLoop pinLoop ;
	protected int iterMax = 0;
	protected int iter = 0;
	
	public void draw() {
		gc.clearRect(0, 0, sizeX, sizeY);
		gc.setFill(new Color(0.78, 0.62, 0.62, 0.5));		
		gc.fillRoundRect(1, 1, sizeX - 2, sizeY - 2, 10, 10);
		gc.setLineWidth(1.5);
		if(drawContour) {
			gc.setStroke(new Color(0.78, 0.62, 0.62, 0.3));
		}
		else {
			gc.setStroke(new Color(0.78, 0.62, 0.62, 0.9));
			
		}
		gc.strokeRoundRect(1, 1, sizeX - 2, sizeY - 2, 10, 10);
		gc.setLineWidth(1.4);
		gc.setStroke(new Color(1, 1, 1, 0.5));
		for(int i=0; i<29; i+=4) {
			gc.strokeLine(160, 6+i, 190, 6+i);	
		}
		gc.setFill(Color.BLACK);
		gc.fillText(""+iter, 15, 25);
	}

	public void init2() {
		pinLoop = new PinLoop(x-100, y, this);
		Main.controleur.plateau.getChildren().add(pinLoop);
		tf = new TextField("10");
		tf.setLayoutX(x-sizeX/2+50);
		tf.setLayoutY(y-sizeY/2+9);
		tf.setMaxWidth(100);
		tf.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #FFFFFF2F; -fx-font-size: 10");
		
		Main.controleur.plateau.getChildren().add(tf);
	}
		
	public BlocLoop(double x, double y) {
		super(x,y);
	}
	
	public void initMouseListener() {
		addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Si nombre de clics == 2
					// Supprimer le bloc
				if (t.getClickCount() == 2) {
					Main.controleur.plateau.getChildren().remove(BlocLoop.this);
					Main.controleur.plateau.getChildren().remove(BlocLoop.this.pinIn);
					Main.controleur.plateau.getChildren().remove(BlocLoop.this.pinOut);
					Main.controleur.plateau.getChildren().remove(BlocLoop.this.pinLoop);
					Main.controleur.plateau.getChildren().remove(BlocLoop.this.tf);
					Main.controleur.deleteFreeWires(BlocLoop.this.pinIn);
					Main.controleur.deleteFreeWires(BlocLoop.this.pinOut);
					Main.controleur.deleteFreeWires(BlocLoop.this.pinLoop);
				}
			}
		});
				
		addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Déplacer le bloc
				Main.controleur.scroll.setPannable(false);
				BlocLoop.this.setCursor(Cursor.CLOSED_HAND);
				BlocLoop.this.setLayoutX(t.getX() + BlocLoop.this.getLayoutX() - BlocLoop.this.dx);
				BlocLoop.this.setLayoutY(t.getY() + BlocLoop.this.getLayoutY() - BlocLoop.this.dy);
				BlocLoop.this.pinIn.setLayoutX(t.getX() + BlocLoop.this.pinIn.getLayoutX() - BlocLoop.this.dx);
				BlocLoop.this.pinIn.setLayoutY(t.getY() + BlocLoop.this.pinIn.getLayoutY() - BlocLoop.this.dy);
				BlocLoop.this.pinOut.setLayoutX(t.getX() + BlocLoop.this.pinOut.getLayoutX() - BlocLoop.this.dx);
				BlocLoop.this.pinOut.setLayoutY(t.getY() + BlocLoop.this.pinOut.getLayoutY() - BlocLoop.this.dy);
				BlocLoop.this.pinLoop.setLayoutX(t.getX() + BlocLoop.this.pinLoop.getLayoutX() - BlocLoop.this.dx);
				BlocLoop.this.pinLoop.setLayoutY(t.getY() + BlocLoop.this.pinLoop.getLayoutY() - BlocLoop.this.dy);
				BlocLoop.this.tf.setLayoutX(t.getX() + BlocLoop.this.tf.getLayoutX() - BlocLoop.this.dx);
				BlocLoop.this.tf.setLayoutY(t.getY() + BlocLoop.this.tf.getLayoutY() - BlocLoop.this.dy);
				Main.controleur.updateWires();
			}
		});
			
		addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le bloc Begin
				BlocLoop.this.gc.clearRect(0.0D, 0.0D, BlocLoop.this.sizeX, BlocLoop.this.sizeY);
				BlocLoop.this.drawContour = true;
				BlocLoop.this.setCursor(Cursor.HAND);
				BlocLoop.this.draw();
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le bloc Begin
				Main.controleur.scroll.setPannable(true);
				BlocLoop.this.setCursor(Cursor.HAND);
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le bloc Begin
				BlocLoop.this.gc.clearRect(0.0D, 0.0D, BlocLoop.this.sizeX, BlocLoop.this.sizeY);
				BlocLoop.this.drawContour = false;
				BlocLoop.this.setCursor(Cursor.DEFAULT);
				BlocLoop.this.draw();
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le bloc Begin
				BlocLoop.this.dx = t.getX();
				BlocLoop.this.dy = t.getY();
			}
		});

	}

	public BlocToExecute next() {
		// A vous de jouer ...
		return null; // A supprimer
	}
	
	public void initForSimulation() {
		iter = 0;
		iterMax = Integer.valueOf(tf.getText());
		draw();
	}
	
	@Override
	public String toString() {
		return "LOOP "+iter+"/"+iterMax;
	}
	
	public void allToFront() {
		super.allToFront();
		pinLoop.toFront();
	}
	
	@Override
	public void execute() {
		draw();
	}
	
}
