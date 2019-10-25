import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class EngineArm {
    private final NXTRegulatedMotor motorPinch;
    private final NXTRegulatedMotor motorLift;
    private int RPM;

    public EngineArm(NXTRegulatedMotor motorPinch, NXTRegulatedMotor motorLift, int RPM) {
        this.motorPinch = motorPinch;
        this.motorLift = motorLift;
        this.RPM = RPM;
        this.InitializeEngine();
    }

    public EngineArm() {
        this(Motor.A, Motor.B, 450);
    }

    private void InitializeEngine(){  // Initializes the motor speeds
        this.motorLift.setSpeed(this.RPM);
        this.motorPinch.setSpeed(this.RPM);
    }

    public void pickUpSock() {
        this.pinch();
        this.lift();
    }

    public void pinch() {
        
    }

    public void lift() {

    }
}
