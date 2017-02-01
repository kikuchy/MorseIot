package net.kikuchy.morseiot;

/**
 * Created by kikuchy on 2017/01/31.
 */

public class MainPresenter implements MainPresenterContract {
    private MainModelContract model;

    @Override
    public void onChangePress(boolean isPressing, long timestamp) {
        model.pushSnapshot(new PressSnapshot(timestamp, isPressing));
    }

    @Override
    public void handleOnScanned(String scanned) {
        // TODO: Viewへ反映
    }
}
