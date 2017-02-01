package net.kikuchy.morseiot;

/**
 * Created by kikuchy on 2017/01/31.
 */

public interface MainPresenterContract {
    void onChangePress(boolean isPressing, long timestamp);

    void handleOnScanned(String scanned);
}
