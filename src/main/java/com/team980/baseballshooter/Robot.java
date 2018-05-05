package com.team980.baseballshooter;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import static com.team980.baseballshooter.Parameters.*;

public class Robot extends IterativeRobot {

    private DifferentialDrive robotDrive;

    private Joystick xboxController;

    private Relay winchRelay;
    private Relay actuatorRelay;

    private boolean canFire = true;
    private boolean firing = false;
    private Timer firingTimer;
    private double stopTime;

    public Robot() {
        Jaguar leftDriveController = new Jaguar(LEFT_DRIVE_PWM_ID);
        Jaguar rightDriveController = new Jaguar(RIGHT_DRIVE_PWM_ID);

        robotDrive = new DifferentialDrive(leftDriveController, rightDriveController);
        robotDrive.setMaxOutput(MAX_DRIVE_OUTPUT);

        xboxController = new Joystick(XBOX_CONTROLLER_CHANNEL);

        winchRelay = new Relay(WINCH_RELAY_CHANNEL);
        actuatorRelay = new Relay(ACTUATOR_RELAY_CHANNEL);

        firingTimer = new Timer();
    }

    public void teleopInit() {
        firingTimer.start();
    }

    public void teleopPeriodic() {
        if (xboxController.getRawButton(CONTROLLER_WINCH_PULL_BUTTON)) { //Pull the winch back to prime shooter
            winchRelay.set(Relay.Value.kForward);
        } else if (xboxController.getRawButton(CONTROLLER_WINCH_RELEASE_BUTTON)) { //Release the winch to reload
            winchRelay.set(Relay.Value.kReverse);
        } else {
            winchRelay.set(Relay.Value.kOff);
        }

        if (xboxController.getRawAxis(CONTROLLER_FIRE_AXIS_A) > CONTROLLER_FIRE_AXIS_THRESHOLD
                && xboxController.getRawAxis(CONTROLLER_FIRE_AXIS_B) > CONTROLLER_FIRE_AXIS_THRESHOLD
                && canFire) { //Set firing state
            firing = true;
            canFire = false;

            stopTime = firingTimer.get() + ACTUATOR_STOP_TIME;
        }

        if (firing && ((firingTimer.get() >= stopTime) || (xboxController.getRawButton(CONTROLLER_E_STOP_BUTTON)))) { //Stop firing when timer is up
            firing = false;
        }

        if (firing) { //Fire the shooter!
            actuatorRelay.set(Relay.Value.kForward);

        } else if (xboxController.getRawButton(CONTROLLER_E_STOP_BUTTON)) { //Emergency stop on controller
            firing = false;
            canFire = false;
            actuatorRelay.set(Relay.Value.kOff);

        } else if (xboxController.getRawButton(CONTROLLER_ACTUATOR_PRIME_BUTTON)) { //Pull the actuator back to prime shooter
            canFire = true;
            actuatorRelay.set(Relay.Value.kReverse);

        } else if (xboxController.getRawButton(CONTROLLER_ACTUATOR_RELEASE_BUTTON)) { //Release the actuator to de-prime shooter
            canFire = false;
            actuatorRelay.set(Relay.Value.kForward);

        } else {
            actuatorRelay.set(Relay.Value.kOff);
        }

        robotDrive.arcadeDrive(xboxController.getRawAxis(CONTROLLER_DRIVE_AXIS), xboxController.getRawAxis(CONTROLLER_TURN_AXIS));
    }

    public void disabledInit() { //Stop ALL the motors
        robotDrive.stopMotor();

        winchRelay.stopMotor();
        actuatorRelay.stopMotor();
    }

}