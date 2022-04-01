package traffic_sync;

import java.util.*;
import java.util.concurrent.*;

public class traffic_sync {

//  CREATING A LIST TO KEEP TRACK OF VEHICULES EXITING THE BRIDGE
    public static List<Integer> departure_index = new ArrayList<>();

//  CREATING THE BRIDGE STRUCTURE
    public static Bridge bridge = new Bridge();


// IMPLEMENTING THE VEHICULE STRUCTURE
    public static class Vehicule extends Thread {

        private int vehicule_id;
        private int direc;
        private int time_to_cross; 
        private Bridge bridge;

        
        public Vehicule(int vehicule_id, int direc, int time_to_cross, Bridge bridge) {

            this.vehicule_id = vehicule_id;
            this.direc = direc;
            this.time_to_cross = time_to_cross;
            this.bridge = bridge;

        }

        public void OneVehicule() {
        
            this.ArriveBridge();
            this.CrossBridge();
            this.ExitBridge();

        }

        @Override
        public void run(){

            this.OneVehicule();

        }
        
        public void ArriveBridge() {
        
            System.out.println("Vehicule " + vehicule_id + " arrived to the bridge...");
            
        }

        public void CrossBridge() { 
      

// THIS IS THE CRITICAL SECTION WHERE THE VEHICULES ARE CROSSING
            try {   
            
                        bridge.curr_direc.acquire();
                        TimeUnit.SECONDS.sleep(time_to_cross);
                        bridge.curr_direc.release();
         
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                System.out.println("Something went wrong...");
            }
   
        }

        public void ExitBridge() {

            departure_index.add(vehicule_id);
            System.out.println("Vehicule " + vehicule_id + " exited the bridge...");
            
        }

        @Override
        public String toString(){
            return "Vehicule_id = " + vehicule_id + ", direc = " + direc + " "
                        + ", time_to_cross = " + time_to_cross +"; ";
                }
    }
   
// IMPLEMENTING THE BRIDGE STURCTURE THAT HOLDS SHARED RESOURCES
    public static class Bridge {
              
        Semaphore right_direc = new Semaphore(3);
        Semaphore left_direc = new Semaphore (3); 
        Semaphore curr_direc = new Semaphore(1);
        Semaphore sem = new Semaphore(1);
        
    }

//  CREATING THE LIST OF VEHICULES
    public static List<Vehicule> vehicules = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

//INITIALIZING THE LIST OF VEHICULES
           for (int k = 0; k < 20; k++) {
            
            Random rand = new Random();
            Vehicule veh = new Vehicule(k+1,(int) rand.nextInt(2), 5, bridge);
            vehicules.add(veh);

            System.out.println(veh.toString());
        }

        int n;
        System.out.println("1.Case 1");
        System.out.println("2.Case 2");
        System.out.println("3.Case 3");
        System.out.println("Enter your selection:" );
        Scanner choice = new Scanner(System.in);   
        n = choice.nextInt();

        switch(n) {

          case 1:

            for (int p = 0; p < 5; p++) 
            { 
            vehicules.get(p).start();   // STarting 5 vehicule threads
            }            
            TimeUnit.SECONDS.sleep(10); // Delaying for 10 seconds

            for (int p = 5; p < 10; p++)
            {
            vehicules.get(p).start();
            }
            TimeUnit.SECONDS.sleep(10); 

            for (int p = 10; p < 15; p++) 
            {
            vehicules.get(p).start(); 
            }
            TimeUnit.SECONDS.sleep(10); 

            for (int p = 15; p < 20; p++)
            {
            vehicules.get(p).start();            
            }
            break;
          case 2:

            for (int p = 0; p < 10; p++) 
            { 
            vehicules.get(p).start();
            } 
            TimeUnit.SECONDS.sleep(10);
            for (int p = 10; p < 20; p++) 
            { 
            vehicules.get(p).start();
            }   
            break;
          case 3:
          
            for (int p = 0; p < 20; p++) 
            { 
            vehicules.get(p).start();
            } 
            break;

        }
                // stoping all threads execution
            for (Vehicule v : vehicules) {
            v.join();
            }
        

        System.out.println("Departure order: " + departure_index);
            

    } // END OF MAIN

}