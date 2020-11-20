package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="launcherTeleOp")
public class BasicTeleop extends OpMode {

    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor conveyorBelt;
    DcMotor launcher;
    DcMotor loaderMotor;

    public void BasicTeleop() {

    }

    @Override
    public void init() {
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        
        conveyorBelt = hardwareMap.dcMotor.get("conveyorBelt");
        launcher = hardwareMap.dcMotor.get("launcher");
        loaderMotor = hardwareMap.dcMotor.get("loaderMotor");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        
        conveyorBelt.setDirection(DcMotor.Direction.FORWARD);
        launcher.setDirection(DcMotor.Direction.FORWARD);
        loaderMotor.setDirection(DcMotor.Direction.FORWARD);

        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        conveyorBelt.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        loaderMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        telemetry.addData("Init finished.", "");
        telemetry.update();
    }

    bool islauncherRunning = false;
    bool isConveyerLoading = false;
    bool isLoaderRunning = false;
    
    // Change these numbers if the conveyor/loader is going too slow/fast.
    float conveyorSpeed = 0.5;
    float loaderSpeed = 1;
    
    /*
     *
     * Control map:
     *
     * Left Stick: Left Drive Motor
     * Right Stick : Right Drive Motor
     *
     * X Button: Toggle launcher 
     * Y Button: Toggle conveyor belt
     * Z Button: Toggle loader
     *
     */
    
    @Override
    public void loop() {
        float left = gamepad1.left_stick_y;
        float right = gamepad1.right_stick_y;

        left = Range.clip(left, -1, 1);
        right = Range.clip(right, -1, 1);

        leftDrive.setPower(left);
        rightDrive.setPower(right);
        
        if (gamepad1.x) {
            launcher.setPower(islauncherRunning?0:1);
            islauncherRunning = !islauncherRunning;
        }
        
        if (gamepad1.y) {
            conveyorBelt.setPower(isConveyorRunning?0:conveyorSpeed);
            isConveyorRunning = !isConveyorRunning;
        }
        
        if (gamepad1.b) {
            loaderMotor.setPower(isLoaderRunning?0:loaderSpeed);
            isLoaderRunning = !isLoaderRunning;
        }
    }

    @Override
    public void stop() {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
}
