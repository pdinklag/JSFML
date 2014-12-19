package org.jsfml.audio;

import org.jsfml.internal.SFMLNative;
import org.jsfml.internal.SFMLNativeObject;
import org.jsfml.system.Time;

/**
 * Abstract base class for sound recorders, which provide functionality to capture audio data.
 */
public abstract class SoundRecorder extends SFMLNativeObject {
    static {
        SFMLNative.loadNativeLibraries();
    }

    /**
     * Checks whether audio capturing is available on this system.
     *
     * @return {@code true} if audio capturing is available, {@code false} otherwise.
     */
    public static native boolean isAvailable();

    /**
     * Gets a list of names of the available audio capture devices for this system.
     *
     * @return a list of names of the available audio capture devices.
     */
    public static native String[] getAvailableDevices();

    /**
     * Gets the name of the default audio capture device.
     *
     * @return the name of the default audio capture device.
     */
    public static native String getDefaultDevice();

    private int sampleRate = 0;
    private String device = getDefaultDevice();

    /**
     * Constructs a sound recorder.
     */
    protected SoundRecorder() {
        super();
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native long nativeCreate();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeSetExPtr();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeDelete();

    private native boolean nativeStart(int sampleRate);

    /**
     * Starts capturing audio data with the specified sample rate.
     *
     * @param sampleRate the sample rate in samples per second.
     * @throws SoundRecorderException in case audio capture failed to start.
     */
    public final void start(int sampleRate) throws SoundRecorderException {
        if (nativeStart(sampleRate)) {
            this.sampleRate = sampleRate;
        } else {
            throw new SoundRecorderException("Failed to start audio capture with sample rate " + sampleRate);
        }
    }

    /**
     * Starts capturing audio data with a sample rate of 44,100 Hz.
     *
     * @throws SoundRecorderException in case audio capture failed to start.
     */
    public final void start() throws SoundRecorderException {
        start(44100);
    }

    /**
     * Stops capturing audio data.
     */
    public final native void stop();

    /**
     * Gets the sample rate that audio is being captured with.
     *
     * @return the audio sample rate in samples per second.
     */
    public final int getSampleRate() {
        return sampleRate;
    }

    /**
     * Called when the sound recorder starts recording.
     * <p/>
     * This method can be implemented by deriving classes to perform any actions
     * necessary before audio recording actually starts.
     *
     * @return {@code true} to start recording after this method is done, {@code false} to cancel.
     */
    protected abstract boolean onStart();

    /**
     * Called when a new batch of audio samples comes in.
     * <p/>
     * Implementing classes can then process the captured audio data.
     * <p/>
     * Note that this method will be called in a separate audio capturing thread.
     * <p/>
     * Also note that this method is currently hardcoded to be called every 100ms, which might
     * be changed in the future.
     *
     * @param samples the 16-bit mono samples that were captured.
     * @return {@code true} to continue recording after this method is done, {@code false}
     * to stop recording.
     */
    protected abstract boolean onProcessSamples(short[] samples);

    /**
     * Called when the audio capture has stopped.
     * <p/>
     * Note that this method will be called in a separate audio capturing thread.
     */
    protected abstract void onStop();

    private native void nativeSetProcessingInterval(long interval);

    /**
     * Sets the processing interval of the recorder.
     * <p/>
     * The processing interval controls the period between calls to the onProcessSamples function.
     * You may want to use a small interval if you want to process the recorded data in real time, for example.
     * <p/>
     * Note: this is only a hint, the actual period may vary. So don't rely on this parameter to implement
     * precise timing.
     * <p/>
     * The default processing interval is 100 ms.
     *
     * @param processingInterval the new processing interval.
     */
    protected final void setProcessingInterval(Time processingInterval) {
        nativeSetProcessingInterval(processingInterval.asMicroseconds());
    }

    private native boolean nativeSetDevice(String device);

    /**
     * Sets the audio capture device.
     * <p/>
     * This can be performed on the fly while recording. In this case, if the device could not be set,
     * the recording stops.
     *
     * @param device the name of the new audio capture device.
     * @throws SoundRecorderException in case the capture device could not be set.
     */
    public void setDevice(String device) throws SoundRecorderException {
        if (nativeSetDevice(device)) {
            this.device = device;
        } else {
            throw new SoundRecorderException("Failed to set capturing device to \"" + device + "\"");
        }
    }

    /**
     * Gets the current audio capture device.
     *
     * @return the current audio capture device.
     */
    public String getDevice() {
        return device;
    }
}
