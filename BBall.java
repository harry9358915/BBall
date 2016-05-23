import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


//create a class extends Pane
class BallPane extends Pane {

	//the radius for the ball
	private double radius = 15;
	private double [][]xy = {{radius,radius},{radius*9,radius*15},{radius*20,radius*30},{radius,radius*8},{radius,radius*2},{radius*3,radius*6},{radius*1,radius*68},{radius*20,radius*2}};
	//a array for the xy move
	private double[][] dxy = {{1,1},{1,1},{1,1},{1,1},{1,1},{1,1},{1,1},{1,1},{1,1},{1,1},{1,1}};
	//create ten balls
	private Circle[] circle = {new Circle(xy[0][0], xy[0][1], radius),new Circle(xy[1][0], xy[1][1], radius*1.5),
			new Circle(xy[2][0], xy[2][1], radius*2),new Circle(xy[3][0], xy[3][1], radius*1.5),new Circle(xy[4][0], xy[4][1], radius*1.5),new Circle(xy[5][0], xy[5][1], radius*3),new Circle(xy[6][0], xy[6][1], radius*1.5)};
	private Timeline animation;
	private Timeline animation1;
	//constructor
	public BallPane() {
		//add balls
		for(int i = 0; i<6;i++){
			this.getChildren().addAll(circle[i]);
		}
		// Create an animation for moving the ball
		animation = new Timeline(new KeyFrame(Duration.millis(10), e -> moveBall()));
		animation1 = new Timeline(new KeyFrame(Duration.millis(500), e -> changeColor()));
		animation1.setCycleCount(Timeline.INDEFINITE);
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		animation1.play();
		// Start animation
	}

	//create a method to play
	public void play() {
		animation.play();
	}

	//create a method to pause
	public void pause() {
		animation.pause();
	}

	//create a method to increaseSpeed
	public void increaseSpeed() {
		animation.setRate(animation.getRate() + 0.1);
	}

	//create a method to decreaseSpeed
	public void decreaseSpeed() {
		animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
	}

	//create a method to rateProperty
	public DoubleProperty rateProperty() {
		return animation.rateProperty();
	}

	//create a method to move ball
	protected void moveBall() {
		// Check boundaries
		
		for(int i = 0;i<6;i++){
			
			if (xy[i][0] < radius || xy[i][0] > getWidth() - radius) {
				dxy[i][0] *= -1; // Change ball move direction
			}
			if (xy[i][1] < radius || xy[i][1] > getHeight() - radius) {
				dxy[i][1] *= -1; // Change ball move direction
			}


			// Adjust ball position
			xy[i][0] += dxy[i][0];
			xy[i][1] += dxy[i][1];
			circle[i].setCenterX(xy[i][0]);
			circle[i].setCenterY(xy[i][1]);
		}
	}
	public void changeColor(){
		 Color color = new Color(Math.random(), 
			        Math.random(), Math.random(),Math.random());
		 circle[(int)(Math.random()*6)].setFill(color);
	}
}
public class BBall extends Application {
	  @Override // Override the start method in the Application class
	  public void start(Stage primaryStage) {
	    BallPane ballPane = new BallPane(); // Create a ball pane

	    // Pause and resume animation
	    ballPane.setOnMousePressed(e -> ballPane.pause());
	    ballPane.setOnMouseReleased(e -> ballPane.play());

	    // Increase and decrease animation   
	    ballPane.setOnKeyPressed(e -> {
	      if (e.getCode() == KeyCode.UP) {
	        ballPane.increaseSpeed();
	      } 
	      else if (e.getCode() == KeyCode.DOWN) {
	        ballPane.decreaseSpeed();
	      }
	    });

	    // Create a scene and place it in the stage
	    Scene scene = new Scene(ballPane, 300, 250);
	    primaryStage.setTitle("U10316050"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	    
	    // Must request focus after the primary stage is displayed
	    ballPane.requestFocus();
	  }

	  /**
	   * The main method is only needed for the IDE with limited
	   * JavaFX support. Not needed for running from the command line.
	   */
	  public static void main(String[] args) {
	    launch(args);
	  }
	}