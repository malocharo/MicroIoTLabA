import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public abstract class BlocWith2Pins extends Bloc {

	protected boolean drawContour = false;
	protected TextField tf ;
	
	protected PinIn pinIn ;
	protected PinOut pinOut ;
		
	public void allToFront() {
		pinIn.toFront();
		pinOut.toFront();
		tf.toFront();
	}
	
	public BlocWith2Pins(double x, double y) {
		super(x, y);
		init();
	}
	
	public void init() {
		super.init();
		pinIn = new PinIn(x, y-18, this);
		pinOut = new PinOut(x, y+18, this);
		Main.controleur.plateau.getChildren().add(pinIn);
		Main.controleur.plateau.getChildren().add(pinOut);
		init2();
		initMouseListener();
		draw();
	}
	
	public void initMouseListener() {
		addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {				
				// Voir le block Begin
				BlocWith2Pins.this.drawContour = true;
				BlocWith2Pins.this.setCursor(Cursor.HAND);
				BlocWith2Pins.this.draw();
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le block Begin
				Main.controleur.scroll.setPannable(true);
				BlocWith2Pins.this.setCursor(Cursor.HAND);
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le block Begin
				BlocWith2Pins.this.drawContour = false;
				BlocWith2Pins.this.setCursor(Cursor.DEFAULT);
				BlocWith2Pins.this.draw();
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le block Begin
				BlocWith2Pins.this.dx = t.getX();
				BlocWith2Pins.this.dy = t.getY();
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le block Begin
				// Ici il y a un composant en plus : le TextField
				if (t.getClickCount() == 2) {
					Main.controleur.plateau.getChildren().remove(BlocWith2Pins.this);
					Main.controleur.plateau.getChildren().remove(BlocWith2Pins.this.pinIn);
					Main.controleur.plateau.getChildren().remove(BlocWith2Pins.this.pinOut);
					Main.controleur.plateau.getChildren().remove(BlocWith2Pins.this.tf);
					Main.controleur.deleteFreeWires(BlocWith2Pins.this.pinIn);
					Main.controleur.deleteFreeWires(BlocWith2Pins.this.pinOut);
				}
			}
		});
				
		addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le block Begin
				Main.controleur.scroll.setPannable(false);
				BlocWith2Pins.this.setCursor(Cursor.CLOSED_HAND);
				BlocWith2Pins.this.setLayoutX(t.getX() + BlocWith2Pins.this.getLayoutX() - BlocWith2Pins.this.dx);
				BlocWith2Pins.this.setLayoutY(t.getY() + BlocWith2Pins.this.getLayoutY() - BlocWith2Pins.this.dy);
				BlocWith2Pins.this.pinIn.setLayoutX(t.getX() + BlocWith2Pins.this.pinIn.getLayoutX() - BlocWith2Pins.this.dx);
				BlocWith2Pins.this.pinIn.setLayoutY(t.getY() + BlocWith2Pins.this.pinIn.getLayoutY() - BlocWith2Pins.this.dy);
				BlocWith2Pins.this.pinOut.setLayoutX(t.getX() + BlocWith2Pins.this.pinOut.getLayoutX() - BlocWith2Pins.this.dx);
				BlocWith2Pins.this.pinOut.setLayoutY(t.getY() + BlocWith2Pins.this.pinOut.getLayoutY() - BlocWith2Pins.this.dy);
				BlocWith2Pins.this.tf.setLayoutX(t.getX() + BlocWith2Pins.this.tf.getLayoutX() - BlocWith2Pins.this.dx);
				BlocWith2Pins.this.tf.setLayoutY(t.getY() + BlocWith2Pins.this.tf.getLayoutY() - BlocWith2Pins.this.dy);
				Main.controleur.updateWires();
			}
		});
	}
	
	public BlocToExecute next() {
		Pin p = null;
		for(Node n : Main.controleur.plateau.getChildren()) {
			if(n instanceof Wire) {
				Wire w = (Wire) n;
				if(w.getP1()==pinOut) {
					p = w.getP2();
					break;
				}
				if(w.getP2()==pinOut) {
					p = w.getP1();
					break;
				}	
			}
		}
		if(p==null) return null;
		return (BlocToExecute) p.bloc;
	}
	
}
