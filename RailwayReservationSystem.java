import java.util.Scanner;

class Passenger {
    int pnr;
    String name;
    int age;
    String gender;
    String trainName;
    String source;
    String destination;
    int seatNo;
    double fare;
    boolean booked;

    Passenger() {
        booked = false;
    }
}

public class RailwayReservationSystem {

    static Scanner sc = new Scanner(System.in);

    static Passenger[] passengers = new Passenger[100];

    static int totalSeats = 50;
    static int availableSeats = 50;
    static int pnrCounter = 1001;

    public static void initialize() {
        for (int i = 0; i < passengers.length; i++) {
            passengers[i] = new Passenger();
        }
    }

    public static void displayTrains() {
        System.out.println("\n========== TRAIN LIST ==========");
        System.out.println("1. Rajdhani Express");
        System.out.println("   Source      : Kolkata");
        System.out.println("   Destination : Delhi");
        System.out.println("   Fare        : Rs.1500");

        System.out.println("\n2. Duronto Express");
        System.out.println("   Source      : Kolkata");
        System.out.println("   Destination : Mumbai");
        System.out.println("   Fare        : Rs.1800");

        System.out.println("\n3. Shatabdi Express");
        System.out.println("   Source      : Kolkata");
        System.out.println("   Destination : Patna");
        System.out.println("   Fare        : Rs.900");

        System.out.println("\nAvailable Seats : " + availableSeats);
    }

    public static double calculateFare(int choice) {
        switch(choice) {
            case 1: return 1500;
            case 2: return 1800;
            case 3: return 900;
            default: return 0;
        }
    }

    public static String trainName(int choice) {
        switch(choice) {
            case 1: return "Rajdhani Express";
            case 2: return "Duronto Express";
            case 3: return "Shatabdi Express";
            default: return "";
        }
    }

    public static String source(int choice) { return "Kolkata"; }

    public static String destination(int choice) {
        switch(choice) {
            case 1: return "Delhi";
            case 2: return "Mumbai";
            case 3: return "Patna";
            default: return "";
        }
    }

    public static void reserveTicket() {
        if(availableSeats==0){
            System.out.println("Sorry! No Seats Available.");
            return;
        }

        displayTrains();

        System.out.print("\nChoose Train: ");
        int choice=sc.nextInt();
        sc.nextLine();

        for(int i=0;i<passengers.length;i++){
            if(!passengers[i].booked){
                System.out.print("Passenger Name: ");
                passengers[i].name=sc.nextLine();

                System.out.print("Age: ");
                passengers[i].age=sc.nextInt();
                sc.nextLine();

                System.out.print("Gender: ");
                passengers[i].gender=sc.nextLine();

                passengers[i].pnr=pnrCounter++;
                passengers[i].seatNo=totalSeats-availableSeats+1;
                passengers[i].trainName=trainName(choice);
                passengers[i].source=source(choice);
                passengers[i].destination=destination(choice);
                passengers[i].fare=calculateFare(choice);
                passengers[i].booked=true;

                availableSeats--;

                System.out.println("\n========= TICKET BOOKED =========");
                System.out.println("PNR : "+passengers[i].pnr);
                System.out.println("Seat No : "+passengers[i].seatNo);
                System.out.println("Passenger : "+passengers[i].name);
                System.out.println("Train : "+passengers[i].trainName);
                System.out.println("From : "+passengers[i].source);
                System.out.println("To : "+passengers[i].destination);
                System.out.println("Fare : Rs."+passengers[i].fare);
                return;
            }
        }
    }

    public static void cancelTicket(){
        System.out.print("Enter PNR Number: ");
        int pnr=sc.nextInt();

        for(int i=0;i<passengers.length;i++){
            if(passengers[i].booked && passengers[i].pnr==pnr){
                passengers[i].booked=false;
                availableSeats++;
                System.out.println("Ticket Cancelled Successfully.");
                return;
            }
        }
        System.out.println("PNR Not Found.");
    }

    public static void searchTicket(){
        System.out.print("Enter PNR Number: ");
        int pnr=sc.nextInt();

        for(int i=0;i<passengers.length;i++){
            if(passengers[i].booked && passengers[i].pnr==pnr){
                System.out.println("\n=========== TICKET DETAILS ===========");
                System.out.println("PNR : "+passengers[i].pnr);
                System.out.println("Passenger : "+passengers[i].name);
                System.out.println("Age : "+passengers[i].age);
                System.out.println("Gender : "+passengers[i].gender);
                System.out.println("Train : "+passengers[i].trainName);
                System.out.println("Source : "+passengers[i].source);
                System.out.println("Destination : "+passengers[i].destination);
                System.out.println("Seat : "+passengers[i].seatNo);
                System.out.println("Fare : Rs."+passengers[i].fare);
                return;
            }
        }
        System.out.println("Ticket Not Found.");
    }

    public static void viewAllBookings(){
        boolean found=false;
        System.out.println("\n============= BOOKING LIST =============");

        for(int i=0;i<passengers.length;i++){
            if(passengers[i].booked){
                found=true;
                System.out.println("--------------------------------");
                System.out.println("PNR : "+passengers[i].pnr);
                System.out.println("Passenger : "+passengers[i].name);
                System.out.println("Age : "+passengers[i].age);
                System.out.println("Gender : "+passengers[i].gender);
                System.out.println("Train : "+passengers[i].trainName);
                System.out.println("Seat : "+passengers[i].seatNo);
                System.out.println("Fare : Rs."+passengers[i].fare);
            }
        }

        if(!found) System.out.println("No Booking Available.");
    }

    public static void seatStatus(){
        System.out.println("\n========= SEAT STATUS =========");
        System.out.println("Total Seats : "+totalSeats);
        System.out.println("Booked Seats : "+(totalSeats-availableSeats));
        System.out.println("Available Seats : "+availableSeats);
    }

    public static void main(String args[]) {
        initialize();
        int choice;

        do{
            System.out.println("\n========================================");
            System.out.println("     RAILWAY RESERVATION SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Display Train List");
            System.out.println("2. Reserve Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Search Ticket by PNR");
            System.out.println("5. View All Bookings");
            System.out.println("6. Seat Availability");
            System.out.println("7. Exit");

            System.out.print("Enter Choice: ");
            choice=sc.nextInt();

            switch(choice){
                case 1: displayTrains(); break;
                case 2: reserveTicket(); break;
                case 3: cancelTicket(); break;
                case 4: searchTicket(); break;
                case 5: viewAllBookings(); break;
                case 6: seatStatus(); break;
                case 7: System.out.println("Thank You."); break;
                default: System.out.println("Invalid Choice.");
            }
        } while(choice!=7);
    }
}
