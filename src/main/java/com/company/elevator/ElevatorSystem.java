package com.company.elevator;

import com.company.enums.DCTowerSpecs;
import com.company.enums.ElevatorStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ElevatorSystem {
    private final BlockingQueue<Elevator> elevators;
    private final BlockingQueue<Request> requests;
    private final ExecutorService executorService;

    public ElevatorSystem() {
        this.elevators = new LinkedBlockingQueue<>(getAvailableElevators());
        this.requests = new LinkedBlockingQueue<>();
        this.executorService = Executors.newFixedThreadPool(DCTowerSpecs.NUMBER_OF_ELEVATORS);
    }

    /**
     * using lambda expression, a thread was created for the requests
     */
    public void start() {
        new Thread(() -> {
            while (true) {
                processRequest();
            }
        }).start();
    }

    /**
     * This method ensures that the execute method of the executor service is constantly called.
     * If there is a request but not an elevator available at the moment, then nothing happens until there is an elevator available.
     * If there is an elevator available and there are requests, then the first element of the elevator-list and
     * the first element of the request-list get removed and are saved as variables.
     * Estimation time of the elevator's journey, for some more realistic touch.
     * After the thread continues, the current floor of the request is updated and the elevators is added to the list of free elevators.
     *
     */
    private void processRequest() {
        try {
            final var nextRequest = requests.take();
            final var nextElevator = elevators.take();

            executorService.submit(() -> {
                if (nextElevator.getCurrentFloor() < nextRequest.getCurrentFloor()) {
                    nextElevator.setStatus(ElevatorStatus.UP);
                } else if (nextElevator.getCurrentFloor() > nextRequest.getCurrentFloor()) {
                    nextElevator.setStatus(ElevatorStatus.DOWN);
                } else if (nextElevator.getCurrentFloor() == nextRequest.getCurrentFloor()) {
                    nextElevator.setStatus(ElevatorStatus.HOLD);
                }
                print(nextElevator, "moving ", nextRequest);
                try {
                    int arrivalTime = Math.abs(nextElevator.getCurrentFloor() - nextRequest.getCurrentFloor());
                    int transportTime = Math.abs(nextElevator.getCurrentFloor() - nextRequest.getDestinationFloor());
                    long time = arrivalTime + transportTime;

                    Thread.sleep(time * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                nextElevator.setCurrentFloor(nextRequest.getDestinationFloor());

                if (nextRequest.getDestinationFloor() < nextElevator.getCurrentFloor())
                    nextElevator.setStatus(ElevatorStatus.DOWN);
                if (nextRequest.getDestinationFloor() > nextElevator.getCurrentFloor())
                    nextElevator.setStatus(ElevatorStatus.UP);
                try {
                    elevators.put(nextElevator);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                nextElevator.setStatus(ElevatorStatus.HOLD);
                print(nextElevator, "arrived at destination ", nextRequest);
                checkAvailableElevators();
                nextElevator.setStatus(ElevatorStatus.HOLD);
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method prints to the console information about the elevator, current & destination floor, state of the elevator, direction etc
     *
     * @param elevator       current Elevator in thread
     * @param printStatement action the elevator is currently taking
     * @param request        The previous and next request the elevator receives respectively, to make the current status of the elevator known
     */
    private void print(final Elevator elevator, final String printStatement, final Request request) {
        System.out.printf("%s - %s %s - %s.\n", elevator.print(), printStatement, elevator.getStatus(), request.printStatus());
    }

    /**
     * This method adds requests to the request list
     *
     * @param currentFloor     current Floor where the elevator is at.
     * @param destinationFloor where the elevator is heading.
     * @throws InterruptedException if any interrupted exceptions
     */
    public void addRequest(int currentFloor, int destinationFloor) throws InterruptedException {
        Request request = new Request(currentFloor, destinationFloor);
        requests.put(request);
    }

    /**
     * This method creates a list of the existing 7 Elevators in the building and returns them
     */
    public List<Elevator> getAvailableElevators() {
        List<Elevator> availableElevators = new ArrayList<>();
        for (int i = 0; i < DCTowerSpecs.NUMBER_OF_ELEVATORS; i++) {
            Elevator elevator = new Elevator();
            availableElevators.add(elevator);
        }

        return availableElevators;
    }

    public void checkAvailableElevators(){
        long i = 0;
        System.out.println("Available Elevators: ");
        for (Elevator elevator : elevators){
            i++;
            System.out.println("Elevator id: [" + elevator.getElevatorId() + "]");
        }
        System.out.println("Number of Available Elevators: " + "[" + i + "]");
    }
}
