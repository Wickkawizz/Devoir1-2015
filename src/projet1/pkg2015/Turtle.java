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

/**
 *
 * @author WickkaWizz
 */
public class Turtle implements ITurtle{

    double step; // Move unit
    double delta; // Angle unit
    Point2D pos; // Turtle position
    double angle_deg; // Angle in degrees (90=up, 0=right)
    Stack<Double> stack; // To save the turtle state
    
    public Turtle() {
        stack = new Stack<>();
        this.step = 0.00;
        this.delta = 0.00;
        this.angle_deg = 0.00;
        pos.setLocation(0.00, 0.00);
    }

    public Turtle(double step, double delta, Point2D pos, double angle_deg) {
        this.step = step;
        this.delta = delta;
        this.pos = pos;
        this.angle_deg = angle_deg;
        stack = new Stack<>();
    }
    
    // Move with drawing
    // TODO: Implement the drawing portion
    @Override
    public void draw() {
        
        pos.setLocation((pos.getX() + step) * Math.cos(delta),(pos.getY() + step) * Math.sin(delta));
    }

    // Move without drawing
    @Override
    public void move() {
        pos.setLocation((pos.getX() + step) * Math.cos(delta),(pos.getY() + step) * Math.sin(delta));
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
        System.out.print("Let the Turtle R E L A X");
        System.out.print("(You should take a break too)");
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
    
}
