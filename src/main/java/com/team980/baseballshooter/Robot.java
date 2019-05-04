/*
 *  MIT License
 *
 *  Copyright (c) 2019 FRC Team 980 ThunderBots
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.team980.baseballshooter;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import static com.team980.baseballshooter.Parameters.*;

public class Robot extends IterativeRobot {

    private DifferentialDrive robotDrive;

    private Joystick xboxController;

    private Talon winchMotor;
    private Relay actuatorRelay;

    private boolean canFire = true;
    private boolean firing = false;

    private Timer firingTimer;

    public Robot() {
        Jaguar leftDriveController = new Jaguar(LEFT_DRIVE_PWM_ID);
        Jaguar rightDriveController = new Jaguar(RIGHT_DRIVE_PWM_ID);

        robotDrive = new DifferentialDrive(leftDriveController, rightDriveController);
        robotDrive.setMaxOutput(MAX_DRIVE_OUTPUT);
        robotDrive.setName("Robot Drive");

        xboxController = new Joystick(XBOX_CONTROLLER_CHANNEL);

        winchMotor = new Talon(WINCH_PWM_CHANNEL);
        winchMotor.setName("Winch Motor");
        actuatorRelay = new Relay(ACTUATOR_RELAY_CHANNEL);
        actuatorRelay.setName("Actuator Relay");

        firingTimer = new Timer();
    }

    public void teleopInit() {
        firingTimer.start();
    }

    public void teleopPeriodic() {
        if (xboxController.getRawButton(CONTROLLER_WINCH_PULL_BUTTON)) { //Pull the winch back to prime shooter
            winchMotor.set(1.0);
        } else if (xboxController.getRawButton(CONTROLLER_WINCH_RELEASE_BUTTON)) { //Release the winch to reload
            winchMotor.set(-1.0);
        } else {
            winchMotor.set(0);
        }

        if (xboxController.getRawAxis(CONTROLLER_FIRE_AXIS_A) > CONTROLLER_FIRE_AXIS_THRESHOLD
                && xboxController.getRawAxis(CONTROLLER_FIRE_AXIS_B) > CONTROLLER_FIRE_AXIS_THRESHOLD
                && canFire) { //Set firing state
            firing = true;
            canFire = false;

            firingTimer.reset();
            firingTimer.start();
        }

        if (firing && firingTimer.hasPeriodPassed(ACTUATOR_STOP_TIME) || (xboxController.getRawButton(CONTROLLER_E_STOP_BUTTON))) { //Stop firing when timer is up
            firing = false;
            firingTimer.stop();
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

        winchMotor.stopMotor();
        actuatorRelay.stopMotor();
    }

}