
public interface Card extends ISimulation {
	
	public void runCommand(String leds);
	public void close();
	public void setSimulationEnCours(boolean b);

}
