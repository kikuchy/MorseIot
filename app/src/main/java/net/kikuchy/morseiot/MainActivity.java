package net.kikuchy.morseiot;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.GpioCallback;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static private final String BUTTON_PIN_NAME = "BCM21";

    private View indicator;

    private Gpio buttonPin;
    private PeripheralManagerService manager;

    private GpioCallback onButtonPressed = new GpioCallback() {
        @Override
        public boolean onGpioEdge(Gpio gpio) {
            try {
                Log.d("hoge", "now button " + (gpio.getValue() ? "pressed" : "released"));
                indicator.setVisibility(gpio.getValue() ? View.VISIBLE : View.GONE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        public void onGpioError(Gpio gpio, int error) {
            super.onGpioError(gpio, error);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicator = findViewById(R.id.morseIndicator);
        indicator.setVisibility(View.GONE);

        manager = new PeripheralManagerService();
        List<String> pins = manager.getGpioList();
        Log.d("hoge", pins.toString());
        try {
            buttonPin = manager.openGpio(BUTTON_PIN_NAME);
            buttonPin.setDirection(Gpio.DIRECTION_IN);
            buttonPin.setEdgeTriggerType(Gpio.EDGE_BOTH);
            buttonPin.setActiveType(Gpio.ACTIVE_HIGH);
        } catch (IOException e) {
            Log.e("hoge", "fail to init", e);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            buttonPin.registerGpioCallback(onButtonPressed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        buttonPin.unregisterGpioCallback(onButtonPressed);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (buttonPin != null) {
            try {
                buttonPin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
