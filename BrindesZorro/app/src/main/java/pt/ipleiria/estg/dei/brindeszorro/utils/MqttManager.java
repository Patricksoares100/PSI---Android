package pt.ipleiria.estg.dei.brindeszorro.utils;
import android.annotation.SuppressLint;
import android.content.Context;
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


public class MqttManager{
    MqttClient mqttClient;
    final String subscriptionTopic = "ARTIGOSTOCK";
    private Context context;

    public MqttManager(Context context, String host, String clientId) {
        this.context = context;

        try {
            mqttClient = new MqttClient(host, clientId, null);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

        try {
            mqttClient = new MqttClient(host, clientId, null);
            if (mqttClient != null) {
                MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
                mqttConnectOptions.setAutomaticReconnect(true);
                mqttConnectOptions.setCleanSession(true);

                mqttClient.connect(mqttConnectOptions);
                Toast.makeText(context, "Successfully connected to the broker", Toast.LENGTH_SHORT).show();

                mqttClient.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable cause) {
                        Toast.makeText(context, "Connection lost", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage message) {
                        String payload = new String(message.getPayload());
                        //new Handler(Looper.getMainLooper()).post(() -> BetterToast(context, payload));
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }

                });
                subscribeToTopic(subscriptionTopic);
            }
        } catch (MqttException ex) {
            ex.printStackTrace();
        }

        /*mqttClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                    subscribe();
                    System.out.println("--> Connected to: " + serverURI);
            }

            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("--> Connection lost");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("--> Msg recebida   " + new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("-->Entregou alguma coisa");
            }
        });

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(false);*/

    }

    public void subscribeToTopic( String topic) {
        try {
            mqttClient.subscribe(topic, 0);
            System.out.println("subscribe mqtt");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
