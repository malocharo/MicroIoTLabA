public class WireLoop extends Wire {

	public void draw() {		
		init();
		
		double dSize1 = p1.getSize()/2;
		double dSize2 = p2.getSize()/2;
		
		double x1 = Math.min(p1.getLayoutX()+dSize1, p2.getLayoutX()+dSize2)-1;
		double x2 = Math.max(p1.getLayoutX()+dSize1, p2.getLayoutX()+dSize2)+1;
		double y1 = Math.min(p1.getLayoutY()+dSize1, p2.getLayoutY()+dSize2)-1;
		double y2 = Math.max(p1.getLayoutY()+dSize1, p2.getLayoutY()+dSize2)+1;		
		
		double w ;
		double h ;
		if(x2-x1==0) w = 4; else w = x2-x1;
		if(y2-y1==0) h = 4;	else h = y2-y1;
		
		double d = 70;
		
		if(p1.getLayoutX()-p2.getLayoutX()>-d) d=2*d+(p1.getLayoutX()-p2.getLayoutX());
		
		setLayoutX(x1-d);
		setLayoutY(y1-50);
		
		setWidth(w+d);
		setHeight(h+50);
		gc.clearRect(0, 0, w+d, h+50);
		
		gc.strokeLine(p1.getLayoutX()-x1+dSize1, p1.getLayoutY()-y1+dSize1+50, p1.getLayoutX()-x1+dSize1+d, p1.getLayoutY()-y1+dSize1+50);
		gc.strokeLine(p1.getLayoutX()-x1+dSize1, p1.getLayoutY()-y1+dSize1+50, p1.getLayoutX()-x1+dSize1, p2.getLayoutY()-y1+dSize2+30);
		gc.strokeLine(p1.getLayoutX()-x1+dSize1, p2.getLayoutY()-y1+dSize2+30, p2.getLayoutX()-x1+dSize2+d, p2.getLayoutY()-y1+dSize2+30);
		gc.strokeLine(p2.getLayoutX()-x1+dSize2+d, p2.getLayoutY()-y1+dSize2+30, p2.getLayoutX()-x1+dSize2+d, p2.getLayoutY()-y1+dSize2+50);
	}
	
}
