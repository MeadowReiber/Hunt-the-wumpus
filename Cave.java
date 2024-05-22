// The Cave Object keeps track of the cave, including data that describes the “connectivity information” for adjacent rooms.  Adjacent rooms may either be connected by way of a tunnel, or not connected at all (separated by a wall). The tasks it performs are as follows:



// Read and parse cave data from a file
// Keeps an internal data representation of the cave sufficient for representing connections for each room in the cave
// Exposes appropriate methods and/or attributes for other objects and the main program of the Hunt The Wumpus game.


import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Cave {
    private static final int hexagonSize = 50;
    private static final int hexagonHeight = (int) (Math.sqrt(3) * hexagonSize);
    private static final int hexagonWidth = (int) (2 * hexagonSize * Math.cos(Math.PI / 6));
    private static final int gridWidth = 10;
    private static final int gridHeight = 10;
    private static final int moveIt = hexagonWidth / 4;

    public void showCaveWindow(Stage primaryStage, String caveName) {
        Pane root = new Pane();
        root.setPrefSize(gridWidth * hexagonWidth + moveIt, gridHeight * hexagonHeight);

        // Draw hexagons directly on the pane
        for (int row = 0; row < gridHeight; row++) {
            int yMoveIt = row * hexagonHeight;
            int xMoveIt = (row % 2 == 0) ? moveIt : 0;
            for (int col = 0; col < gridWidth; col++) {
                int x = col * hexagonWidth + xMoveIt;
                int y = yMoveIt;

                Color color = Color.WHITE;

                if (col > 1 && col < 9 && row > 1 && row < 9) {
                    color = Color.WHITE;
                } else {
                    color = Color.TURQUOISE;
                }

                // Create hexagonal shape
                Polygon hexagon = new Polygon();
                hexagon.getPoints().addAll(
                        (double) (x + hexagonWidth / 4), (double) y,
                        (double) (x + hexagonWidth / 4 * 3), (double) y,
                        (double) (x + hexagonWidth), (double) (y + hexagonHeight / 2),
                        (double) (x + hexagonWidth / 4 * 3), (double) (y + hexagonHeight),
                        (double) (x + hexagonWidth / 4), (double) (y + hexagonHeight),
                        (double) x, (double) (y + hexagonHeight / 2)
                );
                hexagon.setStroke(Color.BLACK);
                hexagon.setFill(color);

                root.getChildren().add(hexagon);
            }
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cave 1");
        primaryStage.show();
    }
}
