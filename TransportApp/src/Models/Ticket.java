package Models;

import java.util.Random;

public class Ticket {
    private static String code;
    private String type;
    private String origin;
    private String destination;
    private String time;
    private String date;
    private int price;
    private int numberOfTicket;

    public Ticket(String code,String type, String origin, String destination, String time,String date, int price,int numberOfTicket) {
        this.code = code;
        this.type = type;
        this.origin = origin;
        this.destination = destination;
        this.time = time;
        this.date = date;
        this.price = price;
        this.numberOfTicket = numberOfTicket;
    }

    public static void generateCode() {
        Random random = new Random();
        int number = 10000 + random.nextInt(90000);  // 5 haneli rastgele sayı
        code = String.valueOf(number);
    }

    public static String getCode(){
        return code;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getPrice() {
        return price;
    }

    public int getNumberOfTicket() {
        return numberOfTicket;
    }
}
