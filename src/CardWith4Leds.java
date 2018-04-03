import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class CardWith4Leds extends Canvas implements Card {
	
	public static double sizeX = 300;
	public static double sizeY = 100;
	protected double dx, dy;
	protected double x, y;
	
	protected GraphicsContext gc ;
	protected boolean drawContour = false;
	protected Image image;
	
	protected boolean led1 = false;
	protected boolean led2 = false;
	protected boolean led3 = false;
	protected boolean led4 = false;
	
	protected CheckBox cb1 ;
	protected CheckBox cb2 ;
	protected CheckBox cb3 ;
	protected CheckBox cb4 ;
	
	protected Sphere sp1 ;
	protected Sphere sp2 ;
	protected Sphere sp3 ;
	protected Sphere sp4 ;
	
	protected PhongMaterial material1;
	protected PhongMaterial material2;
	
	protected boolean simulationEnCours = false;

	public void draw() {		
		gc.clearRect(0, 0, sizeX, sizeY+10);
		
		gc.setFill(new Color(0, 0, 0, 0.5));		
		gc.fillRoundRect(1, 1, sizeX -2, sizeY, 50, 50);

		gc.setFill(new Color(1, 1, 1, 0.8));
		gc.fillRoundRect(160, 20, 130, 30, 10, 10);
		
		gc.setLineWidth(1);	
		gc.setStroke(new Color(0, 0, 0, 0.5));
		gc.strokeRoundRect(160, 20, 130, 30, 10, 10);
		
		drawContours();
		drawLeds();
		
		cb1.toFront();
		cb2.toFront();
		cb3.toFront();
		cb4.toFront();
		sp1.toFront();
		sp2.toFront();
		sp3.toFront();
		sp4.toFront();
	}
	
	public void drawContours() {
		gc.setLineWidth(3);
		if(simulationEnCours)
			gc.setStroke(new Color(0.8, 0, 0, 0.9));
		else 
			gc.setStroke(new Color(0.2, 0.2, 0.2, 0.9));
		gc.strokeRoundRect(1, 1, sizeX -2, sizeY, 50, 50);
	}
	
	public void drawLeds() {
		gc.setFill(new Color(0, 0, 0, .9));
		gc.fillOval(170, 60, 26, 26);
		gc.setFill(new Color(0, 0, 0, .9));
		gc.fillOval(200, 60, 26, 26);
		gc.setFill(new Color(0, 0, 0, .9));
		gc.fillOval(230, 60, 26, 26);
		gc.setFill(new Color(0, 0, 0, .9));
		gc.fillOval(260, 60, 26, 26);
		
		gc.drawImage(image, 5, 6);
		sp1.setMaterial(led1?material1:material2);
		sp2.setMaterial(led2?material1:material2);
		sp3.setMaterial(led3?material1:material2);
		sp4.setMaterial(led4?material1:material2);
		
	}
		
	public CardWith4Leds(double x, double y) {
		this.x = x;
		this.y = y;		
		init();		
	}
	
	public void init() {
		gc = getGraphicsContext2D();
		setLayoutX(x - sizeX / 2 );
		setLayoutY(y - sizeY / 2 );
		setWidth(sizeX);
		setHeight(sizeY+10);		
		initMouseListener();
		
		cb1 = new CheckBox();
		cb2 = new CheckBox();
		cb3 = new CheckBox();
		cb4 = new CheckBox();
		
		cb1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				CardWith4Leds.this.led1 = cb1.isSelected();
				CardWith4Leds.this.drawLeds();
				SerialCommunication.envoyer(cb1.isSelected()?'a':'b');
			}
		});
		
		cb2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				CardWith4Leds.this.led2 = cb2.isSelected();
				CardWith4Leds.this.drawLeds();
				SerialCommunication.envoyer(cb2.isSelected()?'c':'d');
			}
		});
		
		cb3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				CardWith4Leds.this.led3 = cb3.isSelected();
				CardWith4Leds.this.drawLeds();
				SerialCommunication.envoyer(cb3.isSelected()?'e':'f');
			}
		});
		
		cb4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				CardWith4Leds.this.led4 = cb4.isSelected();
				CardWith4Leds.this.drawLeds();
				SerialCommunication.envoyer(cb4.isSelected()?'g':'h');
			}
		});
		
		cb1.setLayoutX(x+20);
		cb1.setLayoutY(y-24);
		
		cb2.setLayoutX(x+50);
		cb2.setLayoutY(y-24);
		
		cb3.setLayoutX(x+80);
		cb3.setLayoutY(y-24);
		
		cb4.setLayoutX(x+110);
		cb4.setLayoutY(y-24);
		
		Main.controleur.plateau.getChildren().add(cb1);
		Main.controleur.plateau.getChildren().add(cb2);
		Main.controleur.plateau.getChildren().add(cb3);
		Main.controleur.plateau.getChildren().add(cb4);
		
		sp1 = new Sphere(10);
		sp2 = new Sphere(10);
		sp3 = new Sphere(10);
		sp4 = new Sphere(10);
		
		material1 = new PhongMaterial();
	    material1.setDiffuseColor(Color.RED);
	    material1.setSpecularColor(Color.WHITE);
	    
	    material2 = new PhongMaterial();
	    material2.setDiffuseColor(Color.GRAY);
	    material2.setSpecularColor(Color.LIGHTGRAY);
	    	    
	    sp1.setMaterial(material1);
	    sp2.setMaterial(material2);
	    sp3.setMaterial(material2);
	    sp4.setMaterial(material2);
		
		sp1.setLayoutX(x+33);
		sp1.setLayoutY(y+23);
		Main.controleur.plateau.getChildren().add(sp1);
		
		sp2.setLayoutX(x+63);
		sp2.setLayoutY(y+23);
		Main.controleur.plateau.getChildren().add(sp2);
		
		sp3.setLayoutX(x+93);
		sp3.setLayoutY(y+23);
		Main.controleur.plateau.getChildren().add(sp3);
		
		sp4.setLayoutX(x+123);
		sp4.setLayoutY(y+23);
		Main.controleur.plateau.getChildren().add(sp4);
		
		draw();		
		
	}
	
	public void initMouseListener() {
		addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le block Begin
				if (t.getClickCount() == 2) {
					Main.controleur.plateau.getChildren().remove(CardWith4Leds.this);
					Main.controleur.plateau.getChildren().remove(CardWith4Leds.this.image);
					Main.controleur.plateau.getChildren().remove(CardWith4Leds.this.cb1);
					Main.controleur.plateau.getChildren().remove(CardWith4Leds.this.cb2);
					Main.controleur.plateau.getChildren().remove(CardWith4Leds.this.cb3);
					Main.controleur.plateau.getChildren().remove(CardWith4Leds.this.cb4);
					Main.controleur.plateau.getChildren().remove(CardWith4Leds.this.sp1);
					Main.controleur.plateau.getChildren().remove(CardWith4Leds.this.sp2);
					Main.controleur.plateau.getChildren().remove(CardWith4Leds.this.sp3);
					Main.controleur.plateau.getChildren().remove(CardWith4Leds.this.sp4);
				}
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le block Begin
				CardWith4Leds.this.drawContour = true;
				CardWith4Leds.this.setCursor(Cursor.HAND);
				CardWith4Leds.this.draw();
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le block Begin
				Main.controleur.scroll.setPannable(true);
				CardWith4Leds.this.setCursor(Cursor.HAND);
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le block Begin
				CardWith4Leds.this.drawContour = false;
				CardWith4Leds.this.setCursor(Cursor.DEFAULT);
				CardWith4Leds.this.draw();
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le block Begin
				CardWith4Leds.this.dx = t.getX();
				CardWith4Leds.this.dy = t.getY();
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Voir le block Begin
				Main.controleur.scroll.setPannable(false);
				CardWith4Leds.this.setCursor(Cursor.CLOSED_HAND);
				CardWith4Leds.this.setLayoutX(t.getX() + CardWith4Leds.this.getLayoutX() - CardWith4Leds.this.dx);
				CardWith4Leds.this.setLayoutY(t.getY() + CardWith4Leds.this.getLayoutY() - CardWith4Leds.this.dy);
				CardWith4Leds.this.cb1.setLayoutX(t.getX() + CardWith4Leds.this.cb1.getLayoutX() - CardWith4Leds.this.dx);
				CardWith4Leds.this.cb1.setLayoutY(t.getY() + CardWith4Leds.this.cb1.getLayoutY() - CardWith4Leds.this.dy);
				CardWith4Leds.this.cb2.setLayoutX(t.getX() + CardWith4Leds.this.cb2.getLayoutX() - CardWith4Leds.this.dx);
				CardWith4Leds.this.cb2.setLayoutY(t.getY() + CardWith4Leds.this.cb2.getLayoutY() - CardWith4Leds.this.dy);
				CardWith4Leds.this.cb3.setLayoutX(t.getX() + CardWith4Leds.this.cb3.getLayoutX() - CardWith4Leds.this.dx);
				CardWith4Leds.this.cb3.setLayoutY(t.getY() + CardWith4Leds.this.cb3.getLayoutY() - CardWith4Leds.this.dy);
				CardWith4Leds.this.cb4.setLayoutX(t.getX() + CardWith4Leds.this.cb4.getLayoutX() - CardWith4Leds.this.dx);
				CardWith4Leds.this.cb4.setLayoutY(t.getY() + CardWith4Leds.this.cb4.getLayoutY() - CardWith4Leds.this.dy);
				CardWith4Leds.this.sp1.setLayoutX(t.getX() + CardWith4Leds.this.sp1.getLayoutX() - CardWith4Leds.this.dx);
				CardWith4Leds.this.sp1.setLayoutY(t.getY() + CardWith4Leds.this.sp1.getLayoutY() - CardWith4Leds.this.dy);
				CardWith4Leds.this.sp2.setLayoutX(t.getX() + CardWith4Leds.this.sp2.getLayoutX() - CardWith4Leds.this.dx);
				CardWith4Leds.this.sp2.setLayoutY(t.getY() + CardWith4Leds.this.sp2.getLayoutY() - CardWith4Leds.this.dy);
				CardWith4Leds.this.sp3.setLayoutX(t.getX() + CardWith4Leds.this.sp3.getLayoutX() - CardWith4Leds.this.dx);
				CardWith4Leds.this.sp3.setLayoutY(t.getY() + CardWith4Leds.this.sp3.getLayoutY() - CardWith4Leds.this.dy);
				CardWith4Leds.this.sp4.setLayoutX(t.getX() + CardWith4Leds.this.sp4.getLayoutX() - CardWith4Leds.this.dx);
				CardWith4Leds.this.sp4.setLayoutY(t.getY() + CardWith4Leds.this.sp4.getLayoutY() - CardWith4Leds.this.dy);
				Main.controleur.updateWires();
			}
		});
	}
	
	@Override
	public void runCommand(String leds) {
		led1=(leds.charAt(0) == '1');
		led2=(leds.charAt(1) == '1');
		led3=(leds.charAt(2) == '1');
		led4=(leds.charAt(3) == '1');
		
		// mettre à jour les leds
		this.cb1.setSelected(leds.charAt(0) == '1');
		this.cb2.setSelected(leds.charAt(1) == '1');
		this.cb3.setSelected(leds.charAt(2) == '1');
		this.cb4.setSelected(leds.charAt(3) == '1');
		if (SerialCommunication.sender != null) {
			SerialCommunication.envoyer((char)(leds.charAt(0) == '1' ? 'a' : 'b'));
			SerialCommunication.envoyer((char)(leds.charAt(1) == '1' ? 'c' : 'd'));
			SerialCommunication.envoyer((char)(leds.charAt(2) == '1' ? 'e' : 'f'));
			SerialCommunication.envoyer((char)(leds.charAt(3) == '1' ? 'g' : 'h'));
		}

		this.drawContours();
		this.drawLeds();

	}
		
	@Override
	public void setSimulationEnCours(boolean b) {
		simulationEnCours = b;
		drawContours();
		drawLeds();
	}
	
	@Override
	public void initForSimulation() {
		led1=false;
		led2=false;
		led3=false;
		led4=false;
		cb1.setSelected(false);
		cb2.setSelected(false);
		cb3.setSelected(false);
		cb4.setSelected(false);
		drawLeds();
	}

	@Override
	public void close() {

	}
}
