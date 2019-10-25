import lejos.hardware.sensor.*;
import lejos.hardware.port.*;
import lejos.robotics.SampleProvider;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ColorSensor {

    private EV3ColorSensor sensor;
    private SensorMode sensorMode;
    private SampleProvider dataSampler;
    private float[] data;
    private static final double TAPE_HØYRE;
    private static final double TAPE_VENSTRE;
    private static final double HVIT;

    static {
        // TODO: Endre på verdiene til disse
        TAPE_HØYRE = 1;
        TAPE_VENSTRE = 0;
        HVIT = 1;
    }

    public ColorSensor (Port port){
        sensor = new EV3ColorSensor(port);
        dataSampler = sensor.getMode("RGB");  // svart = 0.01.. NB: sensor.getMode?
        data = new float[dataSampler.sampleSize()];  // tabell som innholder avlest verdier
    }

    public float getSample (){
        dataSampler.fetchSample(data,0);
        return data[0];
    }

    public boolean turnRight(double colour) {
        throw new NotImplementedException();
    }

    public boolean turnLeft(double colour) {
        throw new NotImplementedException();
    }
}