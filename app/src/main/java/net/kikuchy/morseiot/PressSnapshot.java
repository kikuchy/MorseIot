package net.kikuchy.morseiot;

/**
 * Created by kikuchy on 2017/01/31.
 */

public final class PressSnapshot {
    private long timestamp;
    private boolean isPressing;

    public PressSnapshot(long timestamp, boolean isPressing) {
        this.timestamp = timestamp;
        this.isPressing = isPressing;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isPressing() {
        return isPressing;
    }
}
