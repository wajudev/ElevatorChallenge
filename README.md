# DC Tower Elevator Challenge 
My solution to the IBM DC Tower Challenge.  

- Elevator Logic : The elevator can only move between two floors at a time. 
- Elevator Requests : I used threads to manage the requests.

## How to run
- The application is a normal Java console application, so import and start in the environment(with SDK JAVA 17) of your choice.

## How it works
- The addRequest method in ElevatorSystem creates a request and inserts it directly into a queue. If the queue is full, it waits for an elevator to be available.
This queue is processed by the ExecutorService along with the lists of available elevators. 
- **Simulation**: The time it takes an elevator to reach a certain floor is calculated using
the difference between the floors and then converted to milliseconds. The generated value is then used by the thread's sleep function.
After the sleep function is done waiting, the elevator is considered to have reached it destination and the corresponding elevator is added to the list of available elevators.

## 
Object-Oriented

## Testing
- The code was tested using the console and a few test cases with JUNIT was written
