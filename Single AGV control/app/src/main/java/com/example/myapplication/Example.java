/**
 * Copyright (c) 2014 Jilk Systems, Inc.
 * 
 * This file is part of the Java ROSBridge Client.
 *
 * The Java ROSBridge Client is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Java ROSBridge Client is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Java ROSBridge Client.  If not, see http://www.gnu.org/licenses/.
 * 
 */
package com.example.myapplication;

import com.example.myapplication.message.Move;
import com.example.myapplication.message.Clock;
import com.example.myapplication.rosapi.message.Empty;
import com.example.myapplication.rosapi.message.Topics;
import com.example.myapplication.rosapi.message.Topic;
import com.example.myapplication.rosapi.message.Type;
import com.example.myapplication.rosapi.message.MessageDetails;
import com.example.myapplication.rosapi.message.TypeDef;
import com.example.myapplication.rosapi.message.GetTime;
import com.example.myapplication.rosbridge.ROSBridgeClient;
import com.example.myapplication.message.Log;

public class Example {
    
    public Example() {}
    
    public static void main(String[] args) {        
        ROSBridgeClient client = new ROSBridgeClient("ws://162.243.238.80:9090");
        client.connect();
        com.example.myapplication.Topic<Move> moveTopic = new com.example.myapplication.Topic<Move>("/geometry_msgs/Twist", Move.class, client);
        moveTopic.advertise();
    }            

    
    public static void testTopic(ROSBridgeClient client) {
        com.example.myapplication.Topic<Clock> clockTopic = new com.example.myapplication.Topic<Clock>("/clock", Clock.class, client);
        clockTopic.subscribe();
        try {Thread.sleep(20000);} catch(InterruptedException ex) {}
        Clock cl = null;
        try {
            cl = clockTopic.take(); // just gets one
        }
        catch (InterruptedException ex) {}
        cl.print();
        cl.clock.nsecs++;
        clockTopic.unsubscribe();
        clockTopic.advertise();
        clockTopic.publish(cl);
        clockTopic.unadvertise();
    }
}
