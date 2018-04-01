import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Controleur implements Initializable {

	public static final int NONE = -1 ;
	public static final int BEGIN = 0 ;
	public static final int END = 1 ;
	public static final int LEDS = 2 ;
	public static final int DELAY = 3 ;
	public static final int LOOP = 4 ;
	public static final int BEAGLE_BONE = 5 ;
	public static final int ARDUINO = 6 ;
	public static final int CONNECTION = 7 ;
	
	@FXML
	public Label simLabel;
	
	@FXML
	public Pane plateau;
	
	@FXML
	public ScrollPane scroll;
	
	public static int step = 0;
	public static int wireType = 0;
	
	protected int choix ;
	
	public void addElementOnPanel(MouseEvent e) {
		if(choix == BEGIN) {
			BlocBegin blocBegin = new BlocBegin(e.getX(), e.getY());
			plateau.getChildren().add(blocBegin);
			blocBegin.allToFront();
			choix = NONE;
		}
		if(choix == END) {
			BlocEnd blocEnd = new BlocEnd(e.getX(), e.getY());
			plateau.getChildren().add(blocEnd);
			blocEnd.allToFront();
		}
		if(choix == LEDS) {
			BlocLeds blocLeds = new BlocLeds(e.getX(), e.getY());
			plateau.getChildren().add(blocLeds);
			blocLeds.allToFront();
		}
		if(choix == DELAY) {
			BlocDelay blocDelay = new BlocDelay(e.getX(), e.getY());
			plateau.getChildren().add(blocDelay);
			blocDelay.allToFront();
		}
		if(choix == LOOP) {
			BlocLoop blocLoop = new BlocLoop(e.getX(), e.getY());
			plateau.getChildren().add(blocLoop);
			blocLoop.allToFront();
		}
		if(choix == BEAGLE_BONE) {
			BeagleBone beagle_bone = new BeagleBone(e.getX(), e.getY());
			plateau.getChildren().add(beagle_bone);
		}
		if(choix == ARDUINO) {
			Arduino arduino = new Arduino(e.getX(), e.getY());
			plateau.getChildren().add(arduino);
		}
	}
		
	public void createWire(Wire wire) {
		plateau.getChildren().add(wire);
		wire.toBack();
		wire.draw();
	}
	
	public void updateWires() {
		for(Node n : plateau.getChildren()) {
			if(n instanceof Wire) {
				Wire w = (Wire) n;
				w.draw();
			}
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choix = NONE;
		simLabel.setVisible(false);
		Main.controleur = this;
	}	

	@FXML
	public void addLedBloc() {
		choix = LEDS;
		plateau.setCursor(Cursor.HAND);	
	}
	
	@FXML
	public void addDelayBloc() {
		choix = DELAY;
		plateau.setCursor(Cursor.HAND);
	}
	
	@FXML
	public void addLoopBloc() {
		choix = LOOP;
		plateau.setCursor(Cursor.HAND);
	}
	
	@FXML
	public void addBeagleBoneBloc() {
		choix = BEAGLE_BONE;
		plateau.setCursor(Cursor.HAND);
	}
	
	@FXML
	public void addArduinoBloc() {
		choix = ARDUINO;
		plateau.setCursor(Cursor.HAND);
	}
	
	@FXML
	public void addConnection1Bloc() {
		choix = CONNECTION;
		wireType = Wire.C_DIRECT;
		step = 1;
		plateau.setCursor(Cursor.CROSSHAIR);
	}
	
	@FXML
	public void addConnection2Bloc() {
		choix = CONNECTION;
		wireType = Wire.C_INDIRECT;
		step = 1;
		plateau.setCursor(Cursor.CROSSHAIR);
	}
	
	@FXML
	public void addConnection3Bloc() {
		choix = CONNECTION;
		wireType = Wire.C_LOOP;
		step = 1;
		plateau.setCursor(Cursor.CROSSHAIR);
	}
	
	@FXML
	public void addBeginBloc() {
		choix = BEGIN;
		plateau.setCursor(Cursor.HAND);
	}
	
	@FXML
	public void addEndBloc() {
		choix = END;
		plateau.setCursor(Cursor.HAND);
	}
			
	@FXML
	public void simulate() {
		BeagleBone beagle_bone = null;
		Arduino arduino = null;
		for(Node node : plateau.getChildren()) {
			if(node instanceof BeagleBone)
				beagle_bone = (BeagleBone) node;
			if(node instanceof Arduino)
				arduino = (Arduino) node;
		}
		if(beagle_bone != null)
			SimulationPlatform.card1 = beagle_bone;
		if(arduino != null)
			SimulationPlatform.card2 = arduino;
		Simulator simulation = new Simulator();
		simulation.start();
	}
	
	public void deleteFreeWires(Pin p) {
		ArrayList<Wire> tWires = new ArrayList<Wire>();
		for(Node n : plateau.getChildren() ) {
			if(n instanceof Wire) {
				Wire w = (Wire) n;
				if(w.getP1()==p || w.getP2()==p) {
					tWires.add(w);
				}
			}
		}
		for(Wire w : tWires) {
			plateau.getChildren().remove(w);
		}
	}
	
	public BlocBegin getBeginBloc() {
		for(Node node : plateau.getChildren()) {
			if(node instanceof BlocBegin) {
				return ((BlocBegin) node);
			}
		}
		return null;
	}
	
	public void keyCb(KeyEvent e) {
		choix = NONE;
		plateau.setCursor(Cursor.DEFAULT);
	}
	
}
