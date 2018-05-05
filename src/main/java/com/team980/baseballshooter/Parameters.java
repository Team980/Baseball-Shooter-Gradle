package com.team980.baseballshooter;

public class Parameters {

    // --- DRIVE SYSTEM ---
    public static final int LEFT_DRIVE_PWM_ID = 0;
    public static final int RIGHT_DRIVE_PWM_ID = 1;
    public static final double MAX_DRIVE_OUTPUT = 0.4;

    // --- RELAYS ---
    public static final int WINCH_RELAY_CHANNEL = 0;
    public static final int ACTUATOR_RELAY_CHANNEL = 1;

    // --- TIMINGS ---
    public static final double ACTUATOR_STOP_TIME = 1.9;

    // --- JOYSTICKS / DRIVER INPUTS ---
    public static final int XBOX_CONTROLLER_CHANNEL = 2;

    public static final int CONTROLLER_DRIVE_AXIS = 1; //Left stick - Y (invert)
    public static final int CONTROLLER_TURN_AXIS = 4; //Right stick - X

    public static final int CONTROLLER_WINCH_PULL_BUTTON = 5; //Left shoulder
    public static final int CONTROLLER_WINCH_RELEASE_BUTTON = 6; //Right shoulder

    public static final int CONTROLLER_FIRE_AXIS_A = 2; //Left trigger
    public static final int CONTROLLER_FIRE_AXIS_B = 3; //Right trigger

    public static final double CONTROLLER_FIRE_AXIS_THRESHOLD = 0.9;

    public static final int CONTROLLER_ACTUATOR_PRIME_BUTTON = 1; //A face button
    public static final int CONTROLLER_ACTUATOR_RELEASE_BUTTON = 2; //B face button
    public static final int CONTROLLER_E_STOP_BUTTON = 3; //X face button
}