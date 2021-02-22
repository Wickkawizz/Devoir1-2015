package projet1.pkg2015;

import projet1.pkg2015.Interface.ITurtle;

import java.awt.geom.Point2D;
import java.util.Stack;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author WickkaWizz
 * @author Arkapin£
 */
public class Turtle implements ITurtle{

    double step; // Move unit
    double delta; // Angle unit
    Point2D pos; // Turtle position
    double angle_deg; // Angle in degrees (90=up, 0=right)
    Stack<Double> stack; // To save the turtle state
    GraphicsContext gc;
    
    public Turtle() {
        stack = new Stack<>();
        setUnits(0.0, 0.0);
        this.angle_deg = 0.00;
        pos = new Point2D.Double(0.00, 0.00);
    }

    public Turtle(double step, double delta, Point2D pos, double angle_deg, GraphicsContext board) {
        stack = new Stack<>();
        setUnits(step, delta);
        init(pos, angle_deg);
    }
    
    // Move with drawing
    @Override
    public void draw() {
        double x1 = pos.getX();
        double y1 = pos.getY();

        move();
        double x2 = pos.getX();
        double y2 = pos.getY();

		// Postscript output
    	System.out.println(x1 + " " + y1 + " lineto");
        gc.strokeLine(x1, y1, x2, y2);
    }

    // Move without drawing
    @Override
    public void move() {
    	double y = pos.getY() + step * Math.sin(Math.toRadians(angle_deg));
    	double x = pos.getX() + step * Math.cos(Math.toRadians(angle_deg));

		// Postscript output
    	System.out.println(x + " " + y + " moveto");
        pos.setLocation(x, y);
    }

    @Override
    public void turnR() {
        angle_deg = angle_deg - delta;
    }

    @Override
    public void turnL() {
        angle_deg = angle_deg + delta;
    }

    @Override
    public void push() {
        stack.push(pos.getX());
        stack.push(pos.getY());
        stack.push(angle_deg);
        
		// Postscript output
        System.out.println("currentpoint stroke newpath moveto");
    }

    @Override
    public void pop() {
        angle_deg = stack.pop();
        double y = stack.pop();
        double x = stack.pop();
        pos.setLocation(x, y);
        
		// Postscript output
        System.out.println("stroke " + x + " " + y + " newpath moveto");
    }

    @Override
    public void stay() {
        System.out.println("Let the Turtle R E L A X");
        System.out.println("(You should take a break too)");
    }

    @Override
    public void init(Point2D pos, double angle_deg) {
        this.pos = pos;
        this.angle_deg = angle_deg;
        this.stack.clear();

		// Postscript output
        System.out.println("%!PS-Adobe-3.0 EPSF-3.0");
        System.out.println("%%BoundingBox (atend)");
        System.out.println("newpath " + pos.getX() + " " + pos.getY() + " moveto");
    }

    @Override
    public Point2D getPosition() {
        return pos;
    }

    @Override
    public double getAngle() {
        return angle_deg;
    }

    @Override
    public void setUnits(double step, double delta) {
        this.step = step;
        this.delta = delta;
    }
    
    public void setDrawingBoard(GraphicsContext board){
        this.gc = board;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
}
