package com.example.myapplication.message;

import java.util.Vector;
import com.Vector3;
@MessageType(string = "geometry_msgs/Twist")
public class Move extends Message {
    public Vector3 linear;
    public Vector3 angular;

    //public int angular_x;
    //public int angular_y;
    //public int angular_z;
}
