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
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le block Begin
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le block Begin
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le block Begin
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le block Begin
				// Ici il y a un composant en plus : le TextField
			}
		});
				
		addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le block Begin
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
