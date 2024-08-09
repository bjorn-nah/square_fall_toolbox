package square_fall_toolbox;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class UiTest extends Application{
	
	int[][] essai = {
			{0,0,0,0,0},
			{1,1,1,1,0},
			{2,1,2,2,1},
			{4,4,4,4,1}
	};
	Playfield playfield = new Playfield(essai);

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Creating a Group object  
	    Group squareGroup = new Group();
		for(int i = 0; i<playfield.yLength(); i++) {
			for(int j = 0; j<playfield.xLength(i); j++) {
				//this.playfield[i][j] = p.getRow(j, i);
				//addRectangle(squareGroup, j*50,i*50,45,45,playfield.getRow(j, i));
				addRectangle(squareGroup, j,i,playfield.getRow(j, i));
			}
		}
		//Creating a scene object 
	    Scene scene = new Scene(squareGroup, 600, 300);
	    primaryStage.setTitle("Playfield");
	    
		primaryStage.setScene(scene); 
		primaryStage.show();
		
	}
	
	//public void addRectangle(Group groupe, double X, double Y, double Width, double Height, int sqaureColor) {
	public void addRectangle(Group groupe, int X, int Y, int sqaureColor) {
		Rectangle rectangle = new Rectangle();
		SquareHandler squareHandler = new SquareHandler();
		
	    //Setting the properties of the rectangle 
		/*
	    rectangle.setX(X); 
	    rectangle.setY(Y); 
	    rectangle.setWidth(Width); 
	    rectangle.setHeight(Height);
	    */
		rectangle.setX(X*50); 
	    rectangle.setY(Y*50); 
	    rectangle.setWidth(45); 
	    rectangle.setHeight(45);
	    switch(sqaureColor) {
	    case 0:
	    	rectangle.setFill(Color.WHITE); 
	    	break;
	    case 1:
	    	rectangle.setFill(Color.BLUE); 
	    	break;
	    case 2:
	    	rectangle.setFill(Color.RED); 
	    	break;
	    case 3:
	    	rectangle.setFill(Color.GREEN); 
	    	break;
	    case 4:
	    	rectangle.setFill(Color.YELLOW); 
	    	break;
	    default:
	    	rectangle.setFill(Color.BLACK);
	    }
	    squareHandler.setXY(X,Y);
	    rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, squareHandler);
	    
	    groupe.getChildren().add(rectangle);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	class SquareHandler implements EventHandler<MouseEvent> {
		private int x,y; 
		@Override
		public void handle(MouseEvent event) {
		System.out.println("Handling event " + event.getEventType() + " on " + x + " " + y);
		((Shape) event.getSource()).setFill(Color.BLACK);
		event.consume();
		}
		
		public void setXY(int i, int j) {
			x=i;
			y=j;
		}
	}

	
}



