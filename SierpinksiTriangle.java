import javax.swing.*;
import java.awt.*;

public class SierpinksiTriangle extends JPanel {

    // Constructor for the JPanel
    public SierpinksiTriangle() {
        setPreferredSize(new Dimension(800, 800)); // Set initial panel size
    }

    // Override paintComponent to draw the fractal
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Initial triangle points
        int[] xPoints = {400, 50, 750};
        int[] yPoints = {50, 700, 700};
        drawTriangle(g, xPoints, yPoints);
    }

    // Recursive method to draw the Sierpinski Triangle
    private void drawTriangle(Graphics g, int[] xPoints, int[] yPoints) {
        // Calculate the side length to check the pixel limit
        double sideLength = Math.sqrt(Math.pow(xPoints[1] - xPoints[0], 2) + Math.pow(yPoints[1] - yPoints[0], 2));
        if (sideLength < 4) {
            return; // Stop recursion if the triangle is too small
        }

        // Draw the triangle
        g.setColor(Color.BLACK);
        g.fillPolygon(xPoints, yPoints, 3);

        // Calculate midpoints of the sides
        int midX1 = (xPoints[0] + xPoints[1]) / 2;
        int midY1 = (yPoints[0] + yPoints[1]) / 2;
        int midX2 = (xPoints[1] + xPoints[2]) / 2;
        int midY2 = (yPoints[1] + yPoints[2]) / 2;
        int midX3 = (xPoints[0] + xPoints[2]) / 2;
        int midY3 = (yPoints[0] + yPoints[2]) / 2;

        // Draw an upside-down triangle in the center (different color)
        g.setColor(Color.WHITE);
        int[] innerX = {midX1, midX2, midX3};
        int[] innerY = {midY1, midY2, midY3};
        g.fillPolygon(innerX, innerY, 3);

        // Recursively draw smaller triangles
        drawTriangle(g, new int[]{xPoints[0], midX1, midX3}, new int[]{yPoints[0], midY1, midY3});
        drawTriangle(g, new int[]{midX1, xPoints[1], midX2}, new int[]{midY1, yPoints[1], midY2});
        drawTriangle(g, new int[]{midX3, midX2, xPoints[2]}, new int[]{midY3, midY2, yPoints[2]});
    }

    // Main method to run the application
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sierpinski Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SierpinksiTriangle());
        frame.pack();
        frame.setVisible(true);
    }
}
