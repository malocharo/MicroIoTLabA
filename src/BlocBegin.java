import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BlocBegin extends Bloc {

	protected PinOut pinOut ;
	
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
				Main.controleur.scroll.setPannable(true);


                Main.controleur.plateau.setCursor(Cursor.HAND);
                draw();
				// Afficher le curseur main
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Dans le cas où le nombre de clics est égal à 2
                if(t.getClickCount() == 2)
                {
                    Main.controleur.plateau.getChildren().remove(BlocBegin.this);
                    Main.controleur.deleteFreeWires(getPinOut());
                    Main.controleur.plateau.getChildren().remove(getPinOut());
                    draw();

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
                drawContour = false;
                Main.controleur.plateau.setCursor(Cursor.DEFAULT);
				// Afficher le curseur par défaut
				// Dessiner
                draw();
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Mettre à jour le décalage du dessin par rapport à la souris : dx, dy
                dx = t.getX() - x;
                dy = t.getY() - y;


                draw();

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
                x =  t.getX() - dx;
                y = t.getY() - dy ;
                BlocBegin.this.setTranslateX(x);
                BlocBegin.this.setTranslateY(y);
                System.out.println("x =" +x+ " y ="+y);
                System.out.println("Mx = " + t.getX() + "My" + t.getY());


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
