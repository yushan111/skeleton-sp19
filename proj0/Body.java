import examples.StdDraw;

import java.lang.Math;

public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11;

    /** constructor 1 */
    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
        // or this(xP, yP, xV, yV, m, img);
    }

    /** constructor 2 */
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double dx = b.xxPos - xxPos;
        double dy = b.yyPos - yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Body b){
        double r = calcDistance(b);
        double F = (G * mass * b.mass)/ (r * r);
        return F;
    }

    public double calcForceExertedByX(Body b){
        double F = calcForceExertedBy(b);
        double dx = b.xxPos - xxPos;
        double r = calcDistance(b);
        double Fx = F * dx / r;
        return Fx;
    }

    public double calcForceExertedByY(Body b){
        double F = calcForceExertedBy(b);
        double dy = b.yyPos - yyPos;
        double r = calcDistance(b);
        double Fy = F * dy / r;
        return Fy;
    }

    public double calcNetForceExertedByX(Body[] bodies){
        double Fx = 0.0;
        for (Body b : bodies) {
            if (! b.equals(this)) {
                Fx = Fx + calcForceExertedByX(b);
            }
        }
        return Fx;
    }

    public double calcNetForceExertedByY(Body[] bodies){
        double Fy = 0.0;
        for (Body b : bodies){
            if (! b.equals(this)) {
                Fy = Fy + calcForceExertedByY(b);
            }
        }
        return Fy;
    }

    public void update(double dt, double fX, double fY){
        double ax = fX / mass;
        double ay = fY / mass;
        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }

}
