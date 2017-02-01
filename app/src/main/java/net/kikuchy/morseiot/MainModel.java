package net.kikuchy.morseiot;

import java.util.List;

/**
 * Created by kikuchy on 2017/01/31.
 */

public class MainModel implements MainModelContract {
    private MainPresenterContract presenter;
    private List<PressSnapshot> presses;

    public MainModel(MainPresenterContract presenter) {
        this.presenter = presenter;
    }

    @Override
    public void pushSnapshot(PressSnapshot snapshot) {
        presses.add(snapshot);
        // TODO: 解析
        if (false) {
            presenter.handleOnScanned("SOS");
        }
    }
}
