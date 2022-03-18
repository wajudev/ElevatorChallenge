package com.company.elevator;

import com.company.enums.ElevatorStatus;

import java.util.UUID;

public class Elevator {
    private final String elevatorId;
    private int currentFloor;
    public ElevatorStatus status;

    public Elevator (){
        this.elevatorId = UUID.randomUUID().toString();
        this.currentFloor = 0;
        this.status = ElevatorStatus.HOLD;
    }

    public String getElevatorId() {
        return elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public ElevatorStatus getStatus() {
        return status;
    }

    public void setStatus(ElevatorStatus status) {
        this.status = status;
    }

    public String print(){
        return "Elevator(id: " + elevatorId + ", Current Floor: " + currentFloor + ")";
    }
}
