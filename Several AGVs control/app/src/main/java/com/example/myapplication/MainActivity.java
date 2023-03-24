package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.message.Move;
import com.example.myapplication.rosbridge.ROSBridgeClient;
import com.Vector3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_result;
    private TextView control_car;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = findViewById(R.id.tv_result);
        control_car = findViewById(R.id.control_car);
        ROSBridgeClient client = new ROSBridgeClient("ws://192.168.1.104:9090");
        client.connect();
        Button btn_click_car1 = findViewById(R.id.btn_click_car1);
        Button btn_click_car2 = findViewById(R.id.btn_click_car2);
        //com.example.myapplication.Topic<Move> moveTopic = new com.example.myapplication.Topic<Move>("/none", Move.class, client);
        //com.example.myapplication.Topic<Move> moveTopic = new com.example.myapplication.Topic<Move>("/none", Move.class, client);
        btn_click_car1.setOnClickListener(new View.OnClickListener() {
            Button btn_click_up = findViewById(R.id.btn_click_up);
            Button btn_click_down = findViewById(R.id.btn_click_down);
            Button btn_click_left = findViewById(R.id.btn_click_left);
            Button btn_click_right = findViewById(R.id.btn_click_right);
            @Override
            public void onClick(View view) {
                com.example.myapplication.Topic<Move> moveTopic = new com.example.myapplication.Topic<Move>("/car1/turtle1/cmd_vel", Move.class, client);
                String desc = "您当前正在控制小车1";
                control_car.setText(desc);
                btn_click_up.setOnClickListener(new MyOnClickListener(tv_result, 1, moveTopic));
                btn_click_down.setOnClickListener(new MyOnClickListener(tv_result, 2, moveTopic));
                btn_click_left.setOnClickListener(new MyOnClickListener(tv_result, 3, moveTopic));
                btn_click_right.setOnClickListener(new MyOnClickListener(tv_result, 4, moveTopic));
                moveTopic.advertise();
            }

        });
        btn_click_car2.setOnClickListener(new View.OnClickListener() {
            Button btn_click_up = findViewById(R.id.btn_click_up);
            Button btn_click_down = findViewById(R.id.btn_click_down);
            Button btn_click_left = findViewById(R.id.btn_click_left);
            Button btn_click_right = findViewById(R.id.btn_click_right);
            @Override
            public void onClick(View view) {
                String desc = "您当前正在控制小车2";
                control_car.setText(desc);
                com.example.myapplication.Topic<Move> moveTopic = new com.example.myapplication.Topic<Move>("/car2/turtle1/cmd_vel", Move.class, client);
                btn_click_up.setOnClickListener(new MyOnClickListener(tv_result, 1, moveTopic));
                btn_click_down.setOnClickListener(new MyOnClickListener(tv_result, 2, moveTopic));
                btn_click_left.setOnClickListener(new MyOnClickListener(tv_result, 3, moveTopic));
                btn_click_right.setOnClickListener(new MyOnClickListener(tv_result, 4, moveTopic));
                moveTopic.advertise();
            }
        });


    }
    static public Move move = new Move();

    static class MyOnClickListener implements View.OnClickListener {
        private final TextView tv_result;
        private final int choice;
        private  final com.example.myapplication.Topic<Move> moveTopic;
        // private final com.example.myapplication.Topic<Move> moveTopic;
        public MyOnClickListener(TextView tv_result, int choice,com.example.myapplication.Topic<Move> moveTopic) {
            this.tv_result = tv_result;
            this.choice = choice;
            this.moveTopic = moveTopic;
        }
        @Override
        public void onClick(View v) {
            String desc = String.format("您点击了按钮： %s", ((Button) v).getText());
            tv_result.setText(desc);
            if (choice == 1) {
                move.linear = new Vector3();
                move.linear.x = 2.0;
                move.linear.y = 0.0;
                move.linear.z = 0.0;
                move.angular = new Vector3();
                move.angular.x = 0.0;
                move.angular.y = 0.0;
                move.angular.z = 0.0;
            }
            else if (choice == 2) {
                move.linear = new Vector3();
                move.linear.x = -2.0;
                move.linear.y = 0.0;
                move.linear.z = 0.0;
                move.angular = new Vector3();
                move.angular.x = 0.0;
                move.angular.y = 0.0;
                move.angular.z = 0.0;
            }
            else if (choice == 3) {
                move.linear = new Vector3();
                move.linear.x = 0.0;
                move.linear.y = 0.0;
                move.linear.z = 0.0;
                move.angular = new Vector3();
                move.angular.x = 0.0;
                move.angular.y = 0.0;
                move.angular.z = 2.0;
            }
            else if (choice == 4) {
                move.linear = new Vector3();
                move.linear.x = 0.0;
                move.linear.y = 0.0;
                move.linear.z = 0.0;
                move.angular = new Vector3();
                move.angular.x = 0.0;
                move.angular.y = 0.0;
                move.angular.z = -2.0;
            }
            moveTopic.publish(move);
        }
    }
}