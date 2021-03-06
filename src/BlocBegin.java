import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BlocBegin extends Bloc {

	protected PinOut pinOut ;
	private int count;
	
	public void draw() {
		gc.clearRect(0, 0, sizeX, sizeY);
		gc.setFill(new Color(0, 0.6, 0.3, 0.4));		
		gc.fillRoundRect(1, 1, sizeX - 2, sizeY - 2, 10, 10);
		gc.setLineWidth(1.5);
		if(drawContour) {
			gc.setStroke(new Color(0, 0.6, 0.3, 0.3));
		}
		else {
			gc.setStroke(new Color(0, 0.6, 0.3, 0.9));
			
		}
		gc.strokeRoundRect(1, 1, sizeX - 2, sizeY - 2, 10, 10);
		
		gc.setFont(Font.font("Arial", FontWeight.BOLD, 9));
		gc.setFill(Color.BLACK);
		gc.fillText("BEGIN", 46, 18);
	}
	
	public void allToFront() {
		pinOut.toFront();
	}
	
	public BlocBegin(double x, double y) {
		super(x, y);
		count = 0;
		sizeX = 120;
		sizeY = 30;
		init();		
	}

	public void init2() {}
	
	public void init() {
		super.init();
		pinOut = new PinOut(x, y+14, this);
		Main.controleur.plateau.getChildren().add(pinOut);		
		init2();
		initMouseListener();
		draw();
	}
	
	public void initMouseListener() {
		addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Effacer le dessin d'avant
				// Activier l'affichage des contours
                drawContour = true;
				// Afficher le curseur main

                Main.controleur.plateau.setCursor(Cursor.HAND);
				// Dessiner
                draw();
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Activer le scroll du panneau
				BlocBegin.this.setCursor(Cursor.HAND);
				// Afficher le curseur main
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Dans le cas où le nombre de clics est égal à 2
				if (t.getClickCount() == 2) {
					Main.controleur.plateau.getChildren().remove(BlocBegin.this);
					Main.controleur.plateau.getChildren().remove(BlocBegin.this.pinOut);
					Main.controleur.deleteFreeWires(BlocBegin.this.pinOut);
				}

					// Supprimer le bloc actuel
					// Supprimer ses pins
					// Supprimer les connections liés

			}
		});
		
		addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Effacer le dessin d'avant
				//Main.controleur.choix = Controleur.BEGIN;
				// Désactiver le dessin des contours
				BlocBegin.this.gc.clearRect(0.0D, 0.0D, BlocBegin.this.sizeX, BlocBegin.this.sizeY);
				BlocBegin.this.drawContour = false;
				BlocBegin.this.setCursor(Cursor.DEFAULT);
				BlocBegin.this.draw();
				// Afficher le curseur par défaut
				// Dessiner

			}
		});

		addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Mettre à jour le décalage du dessin par rapport à la souris : dx, dy
				BlocBegin.this.dx = t.getX();
				BlocBegin.this.dy = t.getY();


			}
		});
		
		addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {

				// Désactiver le scroll du panneau
				Main.controleur.scroll.setPannable(false);
				// Afficher le curseur Main
               Main.controleur.plateau.setCursor(Cursor.CLOSED_HAND);
				// Mettre à jour la position du bloc
				BlocBegin.this.setLayoutX(t.getX() + BlocBegin.this.getLayoutX() - BlocBegin.this.dx);
				BlocBegin.this.setLayoutY(t.getY() + BlocBegin.this.getLayoutY() - BlocBegin.this.dy);
				BlocBegin.this.pinOut.setLayoutX(t.getX() + BlocBegin.this.pinOut.getLayoutX() - BlocBegin.this.dx);
				BlocBegin.this.pinOut.setLayoutY(t.getY() + BlocBegin.this.pinOut.getLayoutY() - BlocBegin.this.dy);
				Main.controleur.updateWires();


				draw();
				// Mettre à jour la positions de ses pins
				// Mettre à jour les connections


			}
		});
	}
		
	public Pin getPinOut() {
		return pinOut;
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
	
	@Override
	public String toString() {
		return "BEGIN";
	}
	
	@Override
	public void execute() {}
	
	@Override
	public void initForSimulation() {}
	
}
