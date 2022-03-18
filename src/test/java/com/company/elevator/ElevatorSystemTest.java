package com.company.elevator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorSystemTest {

    private ElevatorSystem elevatorSystem;

    @BeforeEach
    void setUp() {
        elevatorSystem = new ElevatorSystem();
        elevatorSystem.start();
    }

    @Test
    public void testNumberOfElevators() {
        assertEquals(7, elevatorSystem.getAvailableElevators().size());
        elevatorSystem.checkAvailableElevators();
    }

    @Test
    void testAddRequest() throws InterruptedException {
        elevatorSystem.addRequest(2, 22);
        assertThrows(InterruptedException.class, () -> elevatorSystem.addRequest(-3, 50));
        assertThrows(InterruptedException.class, () -> elevatorSystem.addRequest(0, 56));
        elevatorSystem.addRequest(4, 35);
    }
}