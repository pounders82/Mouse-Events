import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;


// Name:    Nick Pounders

// Brief Description: This program creates an application that allows the user to click on and move 
//either circle.  The line attached to the circle will follow and the distance in pixels will update
// with movement.


public class MouseLab extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Create the pane
        Pane pane = new Pane();

        //Create two circles
        Circle one = new Circle(40,40,10);
        one.setFill(Color.SKYBLUE);
        one.setStroke(Color.RED);
        one.setStrokeWidth(1);
        Circle two = new Circle(120,150,10);
        two.setFill(Color.SKYBLUE);
        two.setStroke(Color.RED);
        two.setStrokeWidth(1);

        Integer distance = (int) Math.sqrt(Math.pow(two.getCenterX()-one.getCenterX(),2)+ Math.pow(two.getCenterY()-
                one.getCenterY(),2));

        Text text = new Text(two.getCenterX() - ((two.getCenterX()-one.getCenterX())/2)
                ,one.getCenterY()+((two.getCenterY()-one.getCenterY())/2), distance.toString());
        Line line = new Line(one.getCenterX(),one.getCenterY(),two.getCenterX(),two.getCenterY());

        //Events with clicked on and dragged
        one.setOnMouseDragged(event -> {
            one.setCenterX(event.getX());
            one.setCenterY(event.getY());
            line.setStartX(event.getX());
            line.setStartY(event.getY());
            text.setX(two.getCenterX() - ((two.getCenterX()-one.getCenterX())/2));
            text.setY(one.getCenterY()+((two.getCenterY()-one.getCenterY())/2));
            text.setText(String.valueOf(getDistance(one,two)));
        } );

        two.setOnMouseDragged(event -> {
            two.setCenterX(event.getX());
            two.setCenterY(event.getY());
            line.setEndX(event.getX());
            line.setEndY(event.getY());
            text.setX(two.getCenterX() - ((two.getCenterX()-one.getCenterX()) / 2));
            text.setY(one.getCenterY()+((two.getCenterY()-one.getCenterY())/2));
            text.setText(String.valueOf(getDistance(one, two)));
        });

        //Adds elements to the pane
        pane.getChildren().add(line);
        pane.getChildren().add(one);
        pane.getChildren().add(two);
        pane.getChildren().add(text);


        //Sets and shows the elements
        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setTitle("Mouse Events"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show();
    }

    //Gets the distance between the two circles
    public static int getDistance(Circle one, Circle two){
        Integer distance = (int) Math.sqrt(Math.pow(two.getCenterX()-one.getCenterX(),2)+ Math.pow(two.getCenterY()-
                one.getCenterY(),2));
        return distance;
    }

    //Gets the angle between the two circles
    public static double getAngle(Circle p1, Circle p2)
    {
        double xDiff = p2.getCenterX() - p1.getCenterX();
        double yDiff = p2.getCenterY() - p1.getCenterY();
        return Math.toRadians(Math.atan2(yDiff, xDiff));
    }
}
