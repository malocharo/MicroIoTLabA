import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public abstract class Pin extends Canvas {

	protected double size = 14;
	protected double dx, dy;
	protected double x, y;
	
	protected GraphicsContext gc ;
	protected boolean drawContour = false;
	
	protected BlocToExecute bloc;
	
	public void draw() {		
		gc.setFill(new Color(1, 1, 1, 1));		
		gc.fillOval(1, 1, size - 2, size - 2);
		gc.setLineWidth(1);
		if(drawContour) {
			gc.setStroke(new Color(1,1,1,0.9));
		}
		else {
			gc.setStroke(new Color(0.5,0.5,0.5,0.9));
		}
		gc.strokeOval(1, 1, size - 2, size - 2);
	}
	
	public Pin(double x, double y, BlocToExecute bloc) {
		this.x = x;
		this.y = y;
		this.bloc = bloc;
		init();
	}

	public void init() {
		setLayoutX(x - size / 2);
		setLayoutY(y - size / 2);
		setWidth(size);
		setHeight(size);
		gc = getGraphicsContext2D();
		initMouseListener();
		draw();
	}
	
	public void initMouseListener() {
		addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				if(t.getClickCount()==2) {
					Main.controleur.deleteFreeWires(Pin.this);
				}
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				gc.clearRect(0, 0, 14, 14);
				drawContour=true;
				draw();
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				gc.clearRect(0, 0, 14, 14);
				drawContour=false;
				draw();
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				dx = t.getX();
				dy = t.getY();
				if(Controleur.step == 3) {
					Controleur.step=1;
				}
				if(Controleur.step == 2) {
					if(Pin.this instanceof PinIn) {
						if(Wire.tmpWire.getP1() != Wire.tmpWire.getP2()) {
							Controleur.step = 3;
							Wire.tmpWire.setP2(Pin.this);
							Main.controleur.createWire(Wire.tmpWire);
						}
					}
				}
				if(Controleur.step == 1) {
					if((Pin.this instanceof PinOut) || (Pin.this instanceof PinLoop)) {
						Controleur.step = 2;
						if(Controleur.wireType == Wire.C_DIRECT) Wire.tmpWire = new WireDirect();
						if(Controleur.wireType == Wire.C_INDIRECT) Wire.tmpWire = new WireIndirect();
						if(Controleur.wireType == Wire.C_LOOP) Wire.tmpWire = new WireLoop();
						Wire.tmpWire.setP1(Pin.this);
					}
				}
			}
		});
		

		
	}
	
	public double getSize() {
		return size ;
	}
	
	public BlocToExecute getBloc() {
		return bloc;
	}
	
}
