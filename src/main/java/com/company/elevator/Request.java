package com.company.elevator;

import com.company.enums.DCTowerSpecs;
import com.company.enums.ElevatorStatus;


import java.util.UUID;

public class Request {
    private final String requestId;
    private int currentFloor;
    private int destinationFloor;
    public ElevatorStatus elevatorStatus;

    public Request(int currentFloor, int destinationFloor) throws InterruptedException {
        this.requestId = UUID.randomUUID().toString();
        setCurrentFloor(currentFloor);
        setDestinationFloor(destinationFloor);

        if (currentFloor > destinationFloor){
            elevatorStatus = ElevatorStatus.DOWN;
        } else {
            elevatorStatus = ElevatorStatus.UP;
        }

    }

    public static String ordinal(int floor) {
        String[] suffixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        switch (floor % 100) {
            case 0:
                if (floor == 0){
                    return "Ground";
                }
            case 11:
            case 12:
            case 13:
                return floor + "th";
            default:
                return floor + suffixes[floor % 10];
        }
    }

    public String printStatus(){
        return "to the " + ordinal(currentFloor) + " Floor, then " + elevatorStatus + " to the " + ordinal(destinationFloor) + " Floor";
    }

    public String getRequestId() {
        return requestId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) throws InterruptedException {
        if (currentFloor < DCTowerSpecs.MIN_FLOOR || currentFloor > DCTowerSpecs.MAX_FLOOR){
            throw new InterruptedException("Out of bound");
        } else {
            this.currentFloor = currentFloor;
        }
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) throws InterruptedException {
        if (destinationFloor < DCTowerSpecs.MIN_FLOOR || destinationFloor > DCTowerSpecs.MAX_FLOOR){
            throw new InterruptedException("Out of bound");
        } else {
            this.destinationFloor = destinationFloor;
        }
    }
}
