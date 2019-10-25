import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;

public class SockBot {

    public static void main(String[] args) {
        start();
    }

    public static void start(){
        try {
            // Hent brikken og portene
            Brick brick = BrickFinder.getDefault();
            Port sensColor   = brick.getPort("S1"); // fargesensor
            Port sensUS = brick.getPort("S2");    // Ultralydsensor

            EV3 ev3 = (EV3) BrickFinder.getLocal();
            TextLCD lcd = ev3.getTextLCD();

            // Fargesensor på port S1
            ColorSensor colorSens = new ColorSensor(sensColor);

            // Ultralydsensor på port S2
            UltraSonicSensor ultraSens = new UltraSonicSensor(sensUS);

            // Kjøremotorer
            EngineDrive driveEng = new EngineDrive(Motor.A, Motor.B, 450);

            // Gripearmmotorer
            EngineArm armEngine = new EngineArm(Motor.C, Motor.D, 450);

            lcd.drawString("Rydder opp...", 0, 1);
            while (EngineDrive.antallSvinger-- > 0) {
                driveEng.forward();

                /* kjør fram til robot
                    -   møter linjen
                        - snu
                        - kjør videre
                    - treffer en sokk - måler avstand mindre enn
                        - stopp
                        - plukk opp sokken
                        - sleng den på lasteplanet
                        - kjør videre
                    -
                 */

                if (colorSens.turnRight(colorSens.getSample())) {
                    driveEng.turnRight(45);
                    EngineDrive.antallSvinger--;
                } else if (colorSens.turnLeft(colorSens.getSample())) {
                    driveEng.turnLeft(45);
                    EngineDrive.antallSvinger--;
                }

                if (ultraSens.foundObject()) {
                    performPickup(driveEng, armEngine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Feil: " + e );
        } finally {
            System.out.println("Avslutter program :)");
        }
    }

    public static void performPickup(EngineDrive driveEng, EngineArm armEngine) {
        driveEng.stop();
        armEngine.pickUpSock();
        driveEng.forward();
    }
}
