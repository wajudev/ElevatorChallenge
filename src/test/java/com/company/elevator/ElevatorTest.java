package com.company.elevator;

import com.company.enums.ElevatorStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {

    @Test
    @DisplayName("Elevator default test")
    public void createAnElevator(){
        Elevator elevator = new Elevator();
        assertNotNull(elevator.getElevatorId());
        assertEquals(0, elevator.getCurrentFloor());
        assertEquals(ElevatorStatus.HOLD, elevator.getStatus());
    }

}