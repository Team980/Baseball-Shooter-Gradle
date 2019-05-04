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

public class Parameters {

    // --- DRIVE SYSTEM ---
    public static final int LEFT_DRIVE_PWM_ID = 0;
    public static final int RIGHT_DRIVE_PWM_ID = 1;
    public static final double MAX_DRIVE_OUTPUT = 0.4;

    // --- PWM CHANNELS ---
    public static final int WINCH_PWM_CHANNEL = 2;

    // --- RELAYS ---
    public static final int ACTUATOR_RELAY_CHANNEL = 1;

    // --- TIMINGS ---
    public static final double ACTUATOR_STOP_TIME = 1.9;

    // --- JOYSTICKS / DRIVER INPUTS ---
    public static final int XBOX_CONTROLLER_CHANNEL = 2;

    public static final int CONTROLLER_DRIVE_AXIS = 1; //Left stick - Y
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