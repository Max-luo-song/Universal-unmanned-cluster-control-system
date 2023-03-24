// 
// Decompiled by Procyon v0.5.30
// 

package com;

public class Vector3
{
    public double x;
    public double y;
    public double z;
    
    public Vector3() {
        final double x = 0.0;
        this.z = x;
        this.y = x;
        this.x = x;
    }
    
    public Vector3(final Vector3 other) {
        this.set(other);
    }
    
    public Vector3(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void set(final Vector3 src) {
        this.x = src.x;
        this.y = src.y;
        this.z = src.z;
    }
    
    public double x() {
        return this.x;
    }
    
    public double y() {
        return this.y;
    }
    
    public double z() {
        return this.z;
    }
    
    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }
    
    public void add(final Vector3 other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
    }
    
    public void subtract(final Vector3 other) {
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
    }
    
    public void multiply(final double n) {
        this.x *= n;
        this.y *= n;
        this.z *= n;
    }
    
    public void divide(final double n) {
        this.x /= n;
        this.y /= n;
        this.z /= n;
    }
    
    public void normalize() {
        final double scale = 1.0 / this.length();
        this.multiply(scale);
    }
    
    public double dot(final Vector3 other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Vector3 vector = (Vector3)o;
        return Double.compare(vector.x, this.x) == 0 && Double.compare(vector.y, this.y) == 0 && Double.compare(vector.z, this.z) == 0;
    }
    
    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(this.x);
        int result = (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(this.y);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(this.z);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        return result;
    }
    
    @Override
    public String toString() {
        return "Vector3{x=" + this.x + ", y=" + this.y + ", z=" + this.z + '}';
    }
}
