# Trafic_Synchronization

   - This program's goal is synchronizing traffic of vehicles over a narrow-light duty bridge, using Netbeans IDE and Java programming language.
    
   - Traffic may only cross the bridge in one direction at a time, and if there are ever more than 3 vehicles on the bridge at one time, it will collapse under their weight.
    
   - In our program, each vehicle will be represented by one thread, which executes the procedure OneVehicle when it arrives at the bridge:
        
         OneVehicle(int vehicle_id,int direc, int time_to_cross) { 
            ArriveBridge(direc); 
            CrossBridge(vehicle_id,direc,time_to_cross); 
            ExitBridge(vehicle_id,direc);
         }
         
   - vehicle_id is an integer which uniquely identifies each vehicle (1, 2, 3... and so on).
      direc is either 0 or 1; it gives the direction in which the vehicle will cross the bridge.
      time_to_cross is the time it takes a vehicle to cross the bridge – we assume that every vehicle takes 5 seconds to cross the bridge.
      
   - We are using semaphores and condition variables in implementing ArriveBridge, CrossBridge and ExitBridge procedures. ArriveBridge procedure must not return until it is safe for the car to cross the bridge in the given direction (it must guarantee that there will be no head-on collisions or bridge collapses. CrossBridge procedure should just delay for time to cross the bridge and print out a debug message. ExitBridge is called to indicate that the caller has finished crossing the bridge.
    
   - In our program, we are going to have 3 test cases, for the three vehicle arrival schedules as follows: 
    
          (i) 5 : DELAY(10) : 5 : DELAY(10) : 5 : DELAY(10) : 5 
          (ii) 10 : DELAY(10) : 10 
          (iii) 20 
      
   - Here the numbers indicate the number of vehicles arriving simultaneously at the bridge, while the numbers in parentheses indicate the delay before the next arrival(s). Note that vehicles arriving simultaneously does not imply that they are all traveling in the same direction (randomly assign a direction to each vehicle).

    
