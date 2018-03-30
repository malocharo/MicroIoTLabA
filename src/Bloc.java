import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public abstract class Bloc extends Canvas implements BlocToExecute {

	protected double sizeX = 200;
	protected double sizeY = 40;
	protected double dx, dy;
	protected double x, y;
	
	protected GraphicsContext gc ;
	protected boolean drawContour = false;
	
	protected abstract void draw();
	protected abstract void init2();
		
	public Bloc(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void init() {
		setLayoutX(x - sizeX / 2);
		setLayoutY(y - sizeY / 2);
		setWidth(sizeX);
		setHeight(sizeY);		
		gc = getGraphicsContext2D();
	}
		
}
