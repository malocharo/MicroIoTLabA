public class WireIndirect extends Wire {

	public void draw() {		
		init();
		
		double dSize1 = p1.getSize()/2;
		double dSize2 = p2.getSize()/2;
		
		double x1 = Math.min(p1.getLayoutX()+dSize1, p2.getLayoutX()+dSize2)-1;
		double x2 = Math.max(p1.getLayoutX()+dSize1, p2.getLayoutX()+dSize2)+1;
		double y1 = Math.min(p1.getLayoutY()+dSize1, p2.getLayoutY()+dSize2)-1;
		double y2 = Math.max(p1.getLayoutY()+dSize1, p2.getLayoutY()+dSize2)+1;
		
		setLayoutX(x1);
		setLayoutY(y1);
		
		double w ;
		double h ;
		if(x2-x1==0) w = 4; else w = x2-x1;
		if(y2-y1==0) h = 4;	else h = y2-y1;
		setWidth(w);
		setHeight(h);

		gc.clearRect(0, 0, w, h);
		gc.strokeLine(p1.getLayoutX()-x1+dSize1, p1.getLayoutY()-y1+dSize1, p1.getLayoutX()-x1+dSize1, p2.getLayoutY()-y1+dSize2-h/2);
		gc.strokeLine(p1.getLayoutX()-x1+dSize1, p2.getLayoutY()-y1+dSize2-h/2, p2.getLayoutX()-x1+dSize2, p2.getLayoutY()-y1+dSize2-h/2);
		gc.strokeLine(p2.getLayoutX()-x1+dSize2, p2.getLayoutY()-y1+dSize2-h/2, p2.getLayoutX()-x1+dSize2, p2.getLayoutY()-y1+dSize2);
	}
	
}
