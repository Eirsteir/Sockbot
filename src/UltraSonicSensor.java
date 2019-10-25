import lejos.hardware.sensor.*;
import lejos.hardware.port.*;
import lejos.robotics.SampleProvider;

public class UltraSonicSensor {

    private EV3UltrasonicSensor sensor; // SensorModes?
    private SampleProvider datasampler;
    private float[] data;
    private static final double ABS_DISTANCE;

    static {
        ABS_DISTANCE = 0.3;
    }

    public UltraSonicSensor (Port port){
        sensor = new EV3UltrasonicSensor(port);
        datasampler = sensor.getDistanceMode();
        data = new float[datasampler.sampleSize()];
    }

    public float getDistanceData() {
        datasampler.fetchSample(data,0);
        return data[0];
    }

    public boolean foundObject() {
        return this.getDistanceData() <= ABS_DISTANCE;
    }
}