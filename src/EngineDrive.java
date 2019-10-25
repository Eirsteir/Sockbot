import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.motor.Motor;

public class EngineDrive {
    private final NXTRegulatedMotor right;
    private final NXTRegulatedMotor left;
    private final int RPM;
    public static int antallSvinger; // hold styr på hvor lenge den skal kjøre, TODO: lage en setter for denne?

    static {
        antallSvinger = 10;
    }

    public EngineDrive(NXTRegulatedMotor right, NXTRegulatedMotor left, int RPM) {
        this.right = right;
        this.left = left;
        this.RPM = RPM;
        this.InitializeEngine();
    }

    public EngineDrive() {
        this(Motor.A, Motor.B, 450);
    }

    private void InitializeEngine(){  //Initializes the motor values and speed
        this.left.setSpeed(this.RPM);
        this.right.setSpeed(this.RPM);
    }

    public void forward() {
        this.right.forward();
        this.left.forward();
        this.right.synchronizeWith(new NXTRegulatedMotor[]{this.left});
    }

    public void stop() {
        this.right.stop();
        this.left.stop();
        this.right.synchronizeWith(new NXTRegulatedMotor[]{this.left});
    }

    public void turnRight(int angle) {
        this.stop();
        this.right.rotate(angle);
        this.forward();
    }

    public void turnLeft(int angle) {
        this.stop();
        this.left.rotate(angle);
        this.forward();
    }
}
