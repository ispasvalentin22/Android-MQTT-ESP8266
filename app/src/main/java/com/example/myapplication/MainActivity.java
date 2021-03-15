package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MainActivity extends AppCompatActivity {
    static String MQTTHOST = "tcp://broker.mqtt-dashboard.com:1883";
    static String USERNAME = "x";
    static String PASSWORD = "x";
    String topicStr = "LED";
    MqttAndroidClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());

        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(MainActivity.this,"Conectat la MQTT",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(MainActivity.this,"Nu s-a conectat la MQTT",Toast.LENGTH_LONG).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void Aprinde1(View v){
        String topic = topicStr;
        String message = "L1";
        try {
            client.publish(topic, message.getBytes(),0,false    );
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public void Stinge1(View v){
        String topic = topicStr;
        String message = "D1";
        try {
            client.publish(topic, message.getBytes(),0,false    );
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public void Aprinde2(View v){
        String topic = topicStr;
        String message = "L2";
        try {
            client.publish(topic, message.getBytes(),0,false    );
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public void Stinge2(View v){
        String topic = topicStr;
        String message = "D2";
        try {
            client.publish(topic, message.getBytes(),0,false    );
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}