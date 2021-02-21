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
        this.step = 0.00;
        this.delta = 0.00;
        this.angle_deg = 0.00;
        pos = new Point2D.Double(0.00, 0.00);
    }

    public Turtle(double step, double delta, Point2D pos, double angle_deg, GraphicsContext board) {
        setUnits(step, delta);
        stack = new Stack<>();
        init(pos, angle_deg);
    }
    
    // Move with drawing
    @Override
    public void draw() {
        double x1 = pos.getX();
        double y1 = pos.getY();
        System.out.println(x1+ ", " + y1);
        move();// Possibly the reference won't get updated and will need to be stored in a seperate variable.
        double x2 = pos.getX();
        double y2 = pos.getY();
        gc.strokeLine(x1, y1, x2, y2);
        //System.out.println("I Drew");
    }

    // Move without drawing
    @Override
    public void move() {
        pos.setLocation((pos.getX() + step) * Math.cos(angle_deg),(pos.getY() + step) * Math.sin(angle_deg));
    }

    @Override
    public void turnR() {
        angle_deg= angle_deg - delta;
    }

    @Override
    public void turnL() {
        angle_deg= angle_deg + delta;
    }

    @Override
    public void push() {
        stack.push(pos.getX());
        stack.push(pos.getY());
        stack.push(angle_deg);
        //System.out.println(stack);
    }

    @Override
    public void pop() {
        angle_deg = stack.pop();
        double y = stack.pop();
        double x = stack.pop();
        pos.setLocation(x, y);
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
