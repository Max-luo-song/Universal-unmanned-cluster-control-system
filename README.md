# Project Description
This project is about how to use a device with Android to control a robot with ROS. That's a basic vision, it can only control the AGV to move.We develop a Android app by ourselves. Anyone can use the app to control the robot. There are two types control mode.The first type can only control one robot, detail in /Single AGV control the second type can control more than one robots, detail in /Several AGVs control.

# Development Environment
Android: windows10+Android Studio
AGV: Ubuntu18.04+melodic
Connection:rosbridge
About the connection, we use a tool called [RosbridgeClient](https://github.com/djilk/ROSBridgeClient)

# Usage
if you want to control a AGV, please use the /Single AGV control
if you want to control several AGVs, please use the /Several AGVs control

**Attention:Before you use it, make sure your AGV has installed the ROS meoldic and ros bridge**

you can use Android Studio to make the project to your device and then just use it as a app

# before you use it, you should do your own configuration

if you use serveral please change the /Several AGVs control/app/src/main/java/com/example/myapplication/MainActivity.java
line 23:ROSBridgeClient client = new ROSBridgeClient("ws://192.168.1.104:9090"); **change your own ip**
line 36:com.example.myapplication.Topic<Move> moveTopic = new com.example.myapplication.Topic<Move>("/car1/turtle1/cmd_vel", Move.class, client); **change your topic**

if you use signal please change the /Single AGV control/app/src/main/java/com/example/myapplication/MainActivity.java 
line 22:ROSBridgeClient client = new ROSBridgeClient("ws://192.168.1.104:9090"); **change your own ip**
line 24:com.example.myapplication.Topic<Move> moveTopic = new com.example.myapplication.Topic<Move>("/turtle1/cmd_vel", Move.class, client); **change your own topic**
  
run the instruction as follow:
```
roscore
roslaunch rosbridge_server rosbridge_websocket.launch
```
control the AGV by app

# UI
### Signal
<img width="416" alt="image" src="https://github.com/Max-luo-song/Universal-unmanned-cluster-control-system/blob/main/image/singal.png">

### several
<img width="416" alt="image" src="https://github.com/Max-luo-song/Universal-unmanned-cluster-control-system/blob/main/image/several.png">


  

