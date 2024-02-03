package pt.ipleiria.estg.dei.brindeszorro.utils;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class MqttManager implements MqttCallback{
    MqttClient mqttClient;
    final String subscriptionTopic = "ARTIGOSTOCK";
    private Context context = null;

    public MqttManager(Context context, String host, String clientId) {
        this.context = context;

        try {
            mqttClient = new MqttClient(host, clientId, null);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

        try {
            mqttClient = new MqttClient(host, clientId, null);
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setAutomaticReconnect(true);
            mqttConnectOptions.setCleanSession(true);

            mqttClient.connect(mqttConnectOptions);
            Toast.makeText(context, "Successfully connected to the broker", Toast.LENGTH_SHORT).show();

            mqttClient.setCallback(this);
            subscribeToTopic(subscriptionTopic);
        } catch (MqttException ex) {
            ex.printStackTrace();
        }
    }


    public void subscribeToTopic( String topic) {
        try {
            mqttClient.subscribe(topic, 0);
            System.out.println("subscribe mqtt");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            context.getMainExecutor().execute(() -> // executado de forma assíncrona no thread principal
                    Toast.makeText(context, "Connection Lost", Toast.LENGTH_SHORT).show()
            );
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            context.getMainExecutor().execute(() -> // executado de forma assíncrona no thread principal
                    Toast.makeText(context, "O seu artigo voltou novamente em stock ", Toast.LENGTH_LONG).show()
                    );
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
