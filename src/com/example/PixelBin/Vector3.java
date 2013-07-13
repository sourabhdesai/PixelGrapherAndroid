package com.example.PixelBin;

public class Vector3 {
	private double x;
	private double y;
	private double z;
	
	public Vector3(double x1, double y1, double z1)	{
		x=x1;
		y=y1;
		z=z1;
	}
	
	public Vector3(Vector3 v)	{
		x=v.x;
		y=v.y;
		z=v.z;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	public Vector3 add(Vector3 v) {
		return new Vector3(this.x+v.x,this.y+v.y,this.z+v.z);
	}
	
	public Vector3 subtract(Vector3 v) {
		return new Vector3(this.x-v.x,this.y-v.y,this.z-v.z);
	}
	
	public double magnitude()	{
		return Math.sqrt((x*x)+(y*y)+(z*z));
	}
	
	public double magnitudeSquared()	{
		return ((x*x)+(y*y)+(z*z));
	}
	
	public Vector3 normalized()	{
	double magnitude=this.magnitude();
	return new Vector3(x/magnitude,y/magnitude,z/magnitude);
	}
	
	public double dot(Vector3 v)	{
		return (v.x*this.x)+(v.y*this.y)+(v.z*this.z);
	}
	
	public Vector3 cross(Vector3 v){
		//u X v=(u1*v2-u2*v1)k + (u3*v1-u1*v3)j + (u2*v3-u3*v2)i
		return new Vector3((this.y*v.z)-(this.z*v.y),(this.z*v.x)-(this.x*v.z),(this.x*v.y)-(this.y*v.x));
	}
	
	public static double radiansBetween(Vector3 v1, Vector3 v2)	{
		//v1 X v2= |v1|*|v2|*sin(theta)*normalVector
		double magCross= v1.cross(v2).magnitude();
		double product=v1.magnitude()*v2.magnitude();
		return Math.asin(magCross/product);
	}
	
	
}
