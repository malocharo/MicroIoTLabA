import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Wire extends Canvas {

	public static final int C_DIRECT = 20 ;
	public static final int C_INDIRECT = 21 ;
	public static final int C_LOOP = 22 ;
	
	protected GraphicsContext gc ;
	protected Pin p1;
	protected Pin p2;
	
	public static Wire tmpWire = null;
	
	public abstract void draw();
	
	public void init() {
		gc = getGraphicsContext2D();
		gc.setStroke(Color.DARKSLATEBLUE);
		gc.setLineWidth(2);
	}
	
	public void setP1(Pin p) {
		this.p1 = p;
	}
	
	public void setP2(Pin p) {
		this.p2 = p;
	}
	
	public Pin getP1() {
		return p1;
	}
	
	public Pin getP2() {
		return p2;
	}
	
}