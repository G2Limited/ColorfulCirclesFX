package colorfulcircles;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;
import static java.lang.Math.random;


public class ColorfulCircles extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setScene(scene);
        Group circles = new Group();
        for (int i = 0; i < 30; i++) {
            Circle circle = new Circle(150, Color.web("white", 0.05));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white", 0.16));
            circle.setStrokeWidth(4);
            circles.getChildren().add(circle);
        }
        /*This code creates a group named circles, then uses a for loop to add 30 circles to the
        group. Each circle has a radius of 150, fill color of white, and opacity level of 5 percent,
        meaning it is mostly transparent.*/

        //root.getChildren().add(circles);
        //Replace the two lines of code root.getChildren().add(colors);
        //root.getChildren().add(circles);


        Rectangle colors = new Rectangle(scene.getWidth(), scene.getHeight(),
            new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE, new Stop[]{
                new Stop(0, Color.web("#f8bd55")),
                new Stop(0.14, Color.web("#c0fe56")),
                new Stop(0.28, Color.web("#5dfbc1")),
                new Stop(0.43, Color.web("#64c2f8")),
                new Stop(0.57, Color.web("#be4af7")),
                new Stop(0.71, Color.web("#ed5fc2")),
                new Stop(0.85, Color.web("#ef504c")),
                new Stop(1, Color.web("#f2660f")),}));
        /*This code creates a rectangle named colors. The rectangle is the same width and height as
        the scene and is filled with a linear gradient that starts in the lower left-hand corner
        (0, 1) and ends in the upper right-hand corner (1, 0). The value of true means the
        gradient is proportional to the rectangle, and NO_CYCLE indicates that the color cycle
        will not repeat. The Stop[] sequence indicates what the gradient color should be at a
        particular spot.*/
        colors.widthProperty().bind(scene.widthProperty());
        colors.heightProperty().bind(scene.heightProperty());
        /*The next two lines of code make the linear gradient adjust as the size of the scene
        changes by binding the width and height of the rectangle to the width and height of the
        scene*/

        //root.getChildren().add(colors);
        //The final line of code adds the colors rectangle to the root node.
        //Replace the two lines of code root.getChildren().add(colors);
        //root.getChildren().add(circles);

        Group blendModeGroup =
            new Group(new Group(new Rectangle(scene.getWidth(), scene.getHeight(),
                Color.BLACK), circles), colors);
        colors.setBlendMode(BlendMode.OVERLAY);
        root.getChildren().add(blendModeGroup);
        /*The group blendModeGroup sets up the structure for the overlay blend. The group contains
        two children. The first child is a new (unnamed) Group containing a new (unnamed) black
        rectangle and the previously created circles group. The second child is the previously
        created colors rectangle.
        The setBlendMode() method applies the overlay blend to the colors rectangle. The final line
        of code adds the blendModeGroup to the scene graph as a child of the root node*/

        circles.setEffect(new BoxBlur(10, 10, 3));
        /*This code sets the blur radius to 10 pixels wide by 10 pixels high, and the blur iteration
        to 3, making it approximate a Gaussian blur. This blurring technique produces a smoothing
        effect on the edge of the circles*/

        Timeline timeline = new Timeline();
        for (Node circle : circles.getChildren()) {
            timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, // set start position at 0
                    new KeyValue(circle.translateXProperty(), random() * 800),
                    new KeyValue(circle.translateYProperty(), random() * 600)),
                new KeyFrame(new Duration(40000), // set end position at 40s
                    new KeyValue(circle.translateXProperty(), random() * 800),
                    new KeyValue(circle.translateYProperty(), random() * 600)));
        }
        /*Animation is driven by a timeline, so this code creates a timeline, then uses a for loop
        to add two key frames to each of the 30 circles. The first key frame at 0 seconds uses the
        properties translateXProperty and translateYProperty to set a random position of the circles
        within the window. The second key frame at 40 seconds does the same. Thus, when the timeline
        is played, it animates all circles from one random position to another over a period of 40
        seconds.*/
        // play 40s of animation
        timeline.play();
        primaryStage.show();
    }
}
