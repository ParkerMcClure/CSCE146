/*
 * Parker McClure
 */
import java.awt.*;
import javax.swing.*;
import java.util.*;
public class manyTriangles extends Canvas{

	public static void main(String[] args) {
		JFrame frame = new JFrame("Sierpinski's Triangle");
		//starting size 1000
		frame.setSize(1000,1000);
		manyTriangles triangles = new manyTriangles();
		frame.add(triangles);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void paint(Graphics g)
	{
		//triangle starts 100 pixels from borders to look better
		int[] x = new int[] {500, 100, 900};
		int[] y = new int[] {100, 900, 900};
		g.fillPolygon(x, y, 3); //first black triangle
		g.setColor(Color.white); //change color to white
		drawTriangles(x,y,g);
	}
	public void drawTriangles(int[] x, int[] y, Graphics g)
	{ 
		//check pixel 
		if(Math.sqrt(Math.pow(x[2]-x[0], 2) + Math.pow(y[1]-y[0], 2)) < 4)
			return;
		//pixels are not at 4 yet, calculate midpoints
        int X0 = (x[0] + x[1])/2;
        int Y0 = (y[0] + y[1])/2;
        int X1 = (x[1] + x[2])/2;
        int Y1 = (y[1] + y[2])/2;
        int X2 = (x[0] + x[2])/2;
        int Y2 = (y[0] + y[2])/2;
        //draw smaller triangle with new midpoints
        g.fillPolygon(new int[]{X0, X1, X2}, new int[]{Y0, Y1, Y2}, 3);


        //recursively draw triangles
        drawTriangles(new int[]{x[0], X0, X2}, new int[]{y[0], Y0, Y2}, g);
        drawTriangles(new int[]{X0, x[1], X1}, new int[]{Y0, y[1], Y1}, g);
        drawTriangles(new int[]{X2, X1, x[2]}, new int[]{Y2, Y1, y[2]}, g);
	}

}
