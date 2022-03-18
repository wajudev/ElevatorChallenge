package com.company;

import com.company.elevator.ElevatorSystem;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ElevatorSystem elevatorSystem = new ElevatorSystem();
        elevatorSystem.start();

        elevatorSystem.addRequest(0, 35);
        elevatorSystem.addRequest(35, 0);
        elevatorSystem.addRequest(55, 0);
        elevatorSystem.addRequest(5, 45);
        elevatorSystem.addRequest(19,7);
        elevatorSystem.addRequest(15, 4);
        elevatorSystem.addRequest(5, 15);
        Thread.sleep(2000);
        elevatorSystem.addRequest(12, 2);
        elevatorSystem.addRequest(43, 5);
        elevatorSystem.addRequest(10, 11);
        elevatorSystem.addRequest(0, 2);
        elevatorSystem.addRequest(4, 3);
        elevatorSystem.addRequest(5, 1);
        elevatorSystem.addRequest(22, 12);
        elevatorSystem.addRequest(13, 55);
    }
}
