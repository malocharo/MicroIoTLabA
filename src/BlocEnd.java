import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BlocEnd extends Bloc {

	protected PinIn pinIn ;
	
	public void draw() {
		gc.clearRect(0, 0, sizeX, sizeY);
		gc.setFill(new Color(0.8, 0.4, 0, 0.4));		
		gc.fillRoundRect(1, 1, sizeX - 2, sizeY - 2, 10, 10);
		gc.setLineWidth(1.5);
		if(drawContour) {
			gc.setStroke(new Color(0.8, 0.4, 0, 0.3));
		}
		else {
			gc.setStroke(new Color(0.8, 0.4, 0, 0.9));
			
		}
		gc.strokeRoundRect(1, 1, sizeX - 2, sizeY - 2, 10, 10);

		gc.setFont(Font.font("Arial", FontWeight.BOLD, 9));
		gc.setFill(Color.BLACK);
		gc.fillText("END", 50, 20);
	}
	
	public void allToFront() {
		pinIn.toFront();
	}
	
	public BlocEnd(double x, double y) {
		super(x,y);
		sizeX = 120;
		sizeY = 30;
		init();
	}
	
	@Override
	public void init() {
		super.init();
		pinIn = new PinIn(x, y-14, this);
		Main.controleur.plateau.getChildren().add(pinIn);
		
		setLayoutX(x - sizeX / 2);
		setLayoutY(y - sizeY / 2);
		setWidth(sizeX);
		setHeight(sizeY);
		initMouseListener();
		draw();
	}
	
	@Override
	public void init2() {}
	
	public void initMouseListener() {
		addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Effacer le dessin d'avant
				// Activier l'affichage des contours 
				// Afficher le curseur main
				// Dessiner
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Activer le scroll du panneau
				// Afficher le curseur main
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Dans le cas où le nombre de clics est égal à 2
					// Supprimer le bloc actuel
					// Supprimer ses pins
					// Supprimer les connections liés
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Effacer le dessin d'avant
				// Désactiver le dessin des contours
				// Afficher le curseur par défaut
				// Dessiner
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Mettre à jour le décalage du dessin par rapport à la souris : dx, dy
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Désactiver le scroll du panneau
				// Afficher le curseur Main
				// Mettre à jour la position du bloc
				// Mettre à jour la positions de ses pins
				// Mettre à jour les connections
			}
		});
	}
		
	public Pin getPinIn() {
		return pinIn;
	}
	
	@Override
	public BlocToExecute next() {
		return null;
	}
	
	@Override
	public String toString() {
		return "END";
	}

	@Override
	public void execute() {}

	@Override
	public void initForSimulation() {}
	
}
