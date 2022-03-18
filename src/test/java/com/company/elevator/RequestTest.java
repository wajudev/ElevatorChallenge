package com.company.elevator;

import com.company.enums.ElevatorStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    @Test
    @DisplayName("Tests to see if a request is created")
    public void createARequest() throws InterruptedException {
        Request request = new Request(2, 12);
        assertNotNull(request.getRequestId());
        assertEquals(2, request.getCurrentFloor());
        assertEquals(12, request.getDestinationFloor());
        assertEquals(ElevatorStatus.UP, request.elevatorStatus);

    }

    @Test
    @DisplayName("Tests if the start & destination floor are within the given range (0,55)")
    public void testThrowsOutOfBoundsException(){
        assertThrows(InterruptedException.class, () -> new Request(-4, 88));
    }

}