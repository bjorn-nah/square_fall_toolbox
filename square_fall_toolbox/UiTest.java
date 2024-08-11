package square_fall_toolbox;

import java.util.Iterator;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class UiTest extends Application{
	
	int currentColor = 0;
	
	/*
	int[][] essai = {
			{0,0,0,0,0},
			{1,1,1,1,0},
			{2,1,2,2,1},
			{4,4,4,4,1}
	};*/
	int[][] essai = {
			{1,  1,  2,  2,  2,  2,  1,  1, 3},
			{1,  2,  2,  2,  2,  2,  2,  1, 3},
			{2,  2,  2,  2,  2,  2,  2,  2, 3},
			{2,  2,  1,  2,  1,  2,  2,  2, 3},
			{2,  2,  1,  2,  1,  2,  2,  2, 3},
			{2,  2,  2,  1,  2,  2,  2,  1, 3},
			{1,  2,  2,  2,  2,  2,  1,  1, 3},
			{1,  2,  2,  2,  2,  2,  1,  1, 3},
			{1,  2,  1,  2,  1,  2,  1,  1, 3}
	};
	Playfield playfield = new Playfield(essai);
	Group rootGroup = new Group();

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
		//Creating a Group object  
	    Group paletteGroup = new Group();
		for(int i = 0; i<5; i++) {
			addPalette(paletteGroup, i,i);			
		}
		
		//Group boutonGroup = new Group();
		
		Button buttonRefresh = new Button("Refresh");
		buttonRefresh.setId("Refresh");
		Button buttonClear = new Button("Clear");
		buttonClear.setId("Clear");
		ButtonHandler buttonHandler = new ButtonHandler();
		buttonRefresh.addEventHandler(MouseEvent.MOUSE_CLICKED, buttonHandler);
		buttonClear.addEventHandler(MouseEvent.MOUSE_CLICKED, buttonHandler);
		ButtonBar newButtonbar = new ButtonBar();
		newButtonbar.setPadding(new Insets(10));
		newButtonbar.getButtons().add(buttonRefresh);
		newButtonbar.getButtons().add(buttonClear);
		
		//transformations
		Rotate rotate = new Rotate();
		rotate.setAngle(-45);
	    rotate.setPivotX(0); 
	    rotate.setPivotY(0);
	    squareGroup.getTransforms().add(rotate);
	    squareGroup.setLayoutY(playfield.yLength()*50/Math.sqrt(2));
		
		paletteGroup.setLayoutX(700);
		newButtonbar.setLayoutX(700);
		newButtonbar.setLayoutY(300);
		
		//add subgroups to rootgroup
		rootGroup.getChildren().add(squareGroup);
		rootGroup.getChildren().add(paletteGroup);
		rootGroup.getChildren().addAll(newButtonbar);
		
		//Creating a scene object 
	    Scene scene = new Scene(rootGroup, 800, 600);
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
	    rectangle.setFill(getColor(sqaureColor));
	    rectangle.setId(X+"-"+Y);
	    
	    squareHandler.setXY(X,Y);
	    rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, squareHandler);
	    
	    groupe.getChildren().add(rectangle);
		
	}
	
	public void addPalette(Group groupe, int Y, int paletteColor) {
		Rectangle rectangle = new Rectangle();
		PaletteHandler paletteHandler = new PaletteHandler();
		
		rectangle.setX(50); 
	    rectangle.setY(Y*50); 
	    rectangle.setWidth(45); 
	    rectangle.setHeight(45);
	    rectangle.setFill(getColor(paletteColor));
	    
	    paletteHandler.setColor(paletteColor);
	    rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, paletteHandler);
	    groupe.getChildren().add(rectangle);
	}
	
	public void refreshSquares() {
		for(int i = 0; i<playfield.yLength(); i++) {
			for(int j = 0; j<playfield.xLength(i); j++) {
				Node rectangle = rootGroup.lookup("#"+j+"-"+i);
				((Shape) rectangle).setFill(getColor(playfield.getRow(j, i)));
			}
		}

	}
	
	public Color getColor(int i) {
	    switch(i) {
	    case 0:
	    	return Color.WHITE; 
	    case 1:
	    	return Color.BLUE; 
	    case 2:
	    	return Color.RED;
	    case 3:
	    	return Color.GREEN;
	    case 4:
	    	return Color.YELLOW;
	    default:
	    	return Color.BLACK;
	    }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	class SquareHandler implements EventHandler<MouseEvent> {
		private int x,y; 
		
		@Override
		public void handle(MouseEvent event) {
		System.out.println("Handling event " + event.getEventType() + " on " + x + " " + y);
		//((Shape) event.getSource()).setFill(getColor(currentColor));
		playfield.setRow(x, y, currentColor);
		System.out.println(playfield.toString());
		event.consume();
		}
		
		public void setXY(int i, int j) {
			x=i;
			y=j;
		}
	}
	
	class PaletteHandler implements EventHandler<MouseEvent> {
		private int c;
		
		@Override
		public void handle(MouseEvent event) {
		System.out.println("Handling event " + event.getEventType() + " on " + c);
		currentColor = c;
		event.consume();
		}
		
		public void setColor(int i) {
			c=i;
		}
		
	}
	
	class ButtonHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			switch (((Node) event.getSource()).getId()) {
			case "Refresh": {
				System.out.println("Refresh");
				refreshSquares();
				event.consume();
				break;
			}
			case "Clear": {
				System.out.println("Clear");
				// todo
				event.consume();
				break;
			}
			default:
				System.out.println("Pop");
			}

		}
	}
	
}



