import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * --- Day 18: Lavaduct Lagoon ---
 *
 *
 * https://adventofcode.com/2023/day/18
 **/

public class PartOne {

    /**
     * A Point class to represent a point in 2D space.
     */
    record Point(int x, int y) {
        public Point add(Point other) {
            return new Point(x + other.x, y + other.y);
        }
    }

    /**
     * An enum to represent the direction of a point.
     */
    enum DirectionEnum {
        UP(new Point(-1, 0)),
        DOWN(new Point(1, 0)),
        LEFT(new Point(0, -1)),
        RIGHT(new Point(0, 1));

        /**
         * The direction of the point.
         */
        private final Point direction;

        /**
         * Constructor for the enum.
         *
         * @param direction The direction of the point.
         */
        DirectionEnum(Point direction) {
            this.direction = direction;
        }

        /**
         * Getter for the direction of the point.
         *
         * @return The direction of the point.
         */
        public Point getDirection() {
            return direction;
        }

        /**
         * Getter for the y value of the point.
         *
         * @return The y value of the point.
         */
        public int getX() {
            return direction.x;
        }

        /**
         * Getter for the x value of the point.
         *
         * @return The x value of the point.
         */
        public int getY() {
            return direction.y;
        }

        /**
         * Returns the enum from a string.
         *
         * @param direction The string to convert to an enum.
         * @return The enum from the string.
         */
        public static DirectionEnum fromString(String direction) {
            switch (direction) {
                case "U":
                    return UP;
                case "D":
                    return DOWN;
                case "L":
                    return LEFT;
                case "R":
                    return RIGHT;
                default:
                    throw new IllegalArgumentException("Invalid direction: " + direction);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        List<Point> intersections = new ArrayList<>();
        intersections.add(new Point(0, 0));
        int totalCubicMeters = 0;

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(" ");
                String directionLetter = split[0];
                int distance = Integer.parseInt(split[1]);

                totalCubicMeters += distance;

                DirectionEnum direction = DirectionEnum.fromString(directionLetter);
                Point lastPoint = intersections.get(intersections.size() - 1);
                int lastX = lastPoint.x;
                int lastY = lastPoint.y;

                // Calculate the next point (Last point + direction * distance)
                int nextX = lastX + direction.getX() * distance;
                int nextY = lastY + direction.getY() * distance;
                Point nextPoint = new Point(nextX, nextY);
                intersections.add(nextPoint);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        int sum = 0;

        // Use the shoelace formula to calculate the area of the polygon.
        for (int i = 0; i < intersections.size() - 1; i++) {
            Point point1 = intersections.get(i);
            Point point2 = intersections.get(i + 1);
            sum += point1.x * point2.y - point1.y * point2.x;
        }
        int area = Math.abs(sum) / 2;
        // Use Pick's theorem to calculate the amount of points inside the polygon.
        int pointsInside = ((area - (totalCubicMeters / 2)) + 1);
        int result = totalCubicMeters + pointsInside;
        System.out.println(result);
    }
}
