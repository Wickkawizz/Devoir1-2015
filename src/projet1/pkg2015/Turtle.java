/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1.pkg2015;

import projet1.pkg2015.Interface.ITurtle;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Stack;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Line;

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
        //System.out.println(x1+ ", " + y1);
        move();// Possibly the reference won't get updated and will need to be stored in a seperate variable.
        double x2 = pos.getX();
        double y2 = pos.getY();
        
    	System.out.println(x1 + " " + y1 + " lineto");
        gc.strokeLine(x1, y1, x2, y2);
        
        //System.out.println("x1 : " + x1 + "\nx2 : " + x2 + "\ny1 : " + y1 + "\ny2 : " + y2);
        //System.out.println("I Drew");
    }

    // Move without drawing
    @Override
    public void move() {
    	//System.out.println("new x : " + (pos.getX() + (step * Math.cos(Math.toRadians(angle_deg)))));
    	//System.out.println("old y = " + pos.getY());
    	//System.out.println("new y : " + (pos.getY() + (step * Math.sin(Math.toRadians(angle_deg)))));
    	double y = pos.getY() + step * Math.sin(Math.toRadians(angle_deg));
    	double x = pos.getX() + step * Math.cos(Math.toRadians(angle_deg));
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
    	//System.out.println("x : " + pos.getX() );
    	//System.out.println("y : " + pos.getY() );
    	//System.out.println("agnle : " + angle_deg );
        stack.push(pos.getY());
        stack.push(angle_deg);
        System.out.println("currentpoint stroke newpath moveto");
        //System.out.println(stack);
    }

    @Override
    public void pop() {
        angle_deg = stack.pop();
        double y = stack.pop();
        double x = stack.pop();
        pos.setLocation(x, y);
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
        System.out.println("%!PS-Adobe-3.0 EPSF-3.0");
        System.out.println("%%BoundingBox (attend)");
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
