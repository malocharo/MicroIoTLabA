import javafx.scene.Cursor;
import javafx.scene.Node;

public class Simulator extends Thread {
	
	@Override
	public void run() {
		Main.scene.setCursor(Cursor.WAIT);
		Main.controleur.plateau.setCursor(Cursor.WAIT);
		
		if(SimulationPlatform.card1!=null) {
			SimulationPlatform.card1.initForSimulation();
			SimulationPlatform.card1.setSimulationEnCours(true);
		}
		if(SimulationPlatform.card2!=null) {
			SimulationPlatform.card2.initForSimulation();
			SimulationPlatform.card2.setSimulationEnCours(true);
		}
		Main.controleur.simLabel.setVisible(true);
		BlocToExecute bloc = Main.controleur.getBeginBloc();
		System.out.println(bloc);
		initSimulation();
		

		// LANCER LA SIMULATION ICI
		
		if(SimulationPlatform.card1!=null) {
			SimulationPlatform.card1.setSimulationEnCours(false);
		}
		if(SimulationPlatform.card2!=null) {
			SimulationPlatform.card2.setSimulationEnCours(false);
		}
		
		Main.controleur.simLabel.setVisible(false);
		Main.scene.setCursor(Cursor.DEFAULT);
		Main.controleur.plateau.setCursor(Cursor.DEFAULT);
	}
	
	public void initSimulation() {
		for(Node n : Main.controleur.plateau.getChildren()) {
			if(n instanceof BlocToExecute)
				((BlocToExecute) n).initForSimulation();
		}
	}
	
}
