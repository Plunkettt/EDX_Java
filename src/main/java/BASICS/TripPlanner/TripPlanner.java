package BASICS;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author Ja on 08/05/2018.
 * @project EDX,
 */

class Main {
    Greeter greeter = new Greeter();
    Detailer detailer = new Detailer();
    UselessInfo uselessInfo = new UselessInfo();
    Area area = new Area();
    Haversine haversine = new Haversine();
}

class Greeter {
    Greeter (){
        System.out.println("Welcome to Trip Planner EXTREME!\nPlease enter your name: (Camel style, no white characters. Yes, I'm lazy like that.)");
        Scanny scanny = new Scanny();
        System.out.println("Hello there " + scanny.scanny()+". Where are you travelling to?");
        System.out.println(scanny.scanny()+" is the place to BE!");
    }
}

class Detailer {

    int daysTravelled = 0;
    double ownCurrency = 0;
    double currencyRate = 0;
    String currencySymbol = "";

    Detailer (){
        Formatter formatter = new Formatter();
        Scanny scanny = new Scanny();
        System.out.println("How many days will you be travelling?");
        daysTravelled = Integer.parseInt(scanny.scanny());
        System.out.println("How much of DALLORS are you going to take with?");
        ownCurrency = Double.parseDouble(scanny.scanny());
        System.out.println("What is the currency symbol of your destination country (expressed in 3 uppercase please)");
        currencySymbol=scanny.scanny();
        System.out.println("What is the rate of DALLORS/"+currencySymbol+" conversion? (How many "+currencySymbol+" you have to pay per ONE DALLOR.)");
        currencyRate=Double.parseDouble(scanny.scanny());

        //Output of Detailer

        System.out.println("Your Journey is going to be "+daysTravelled+" days. Which equals to: "+daysConversion(daysTravelled, true, false)+" hours or "+ daysConversion(daysTravelled, false, true)+" minutes.");
        System.out.println("Taking "+ownCurrency+" DALLORS, you will have "+formatter.twodecimals.format(dallorPerDay(ownCurrency, daysTravelled))+" at your disposal each day.");
        System.out.println("This amount of DALLORS converts to "+alienCurrencyAmount(ownCurrency, currencyRate)+" "+currencySymbol+", it is "+formatter.twodecimals.format(alienCurrencyPerDay(alienCurrencyAmount(ownCurrency, currencyRate), daysTravelled))+".");
    }

    public int daysConversion (int days, boolean hours, boolean minutes){
        int out = 0;
        if (hours==true && minutes ==false){
            out=days*24; }
        else { out=days*24*60;
        }return out;
    }
    public double dallorPerDay (double dallors, int days){
        return dallors/days;
    }
    public double alienCurrencyAmount (double dallors, double alienRate){
        return dallors*alienRate;
    }
    public double alienCurrencyPerDay (double alienCurrency, int days){
        return alienCurrency/days;
    }
}

class UselessInfo {
    int hourAdj = 0;

    UselessInfo(){
        Scanny scanny = new Scanny();
        System.out.println("\nPlease enter the timezone difference (up to -12 or +12)");
        hourAdj =Integer.parseInt(scanny.scanny());
        System.out.println("When at your place it is noon, at your destination it is: "+timeConvNoon(hourAdj)+".");
        System.out.println("When at your place it is midnight, at your destination it is: "+timeCOnvMidn(hourAdj)+".");

    }

    public String timeConvNoon (int adjust){
        int out=0;

        if (adjust<-12 || adjust>12) return " ERROR: Timezone difference is out of range, please provide a number between -12 and 12";

        if (adjust==12) return String.valueOf(0);

        else out=12+adjust;

        return String.valueOf(out);
    }
    public String timeCOnvMidn (int adjust){
        int out = 0;

        if (adjust<-12 || adjust>12) return " ERROR: Timezone difference is out of range, please provide a number between -12 and 12";

        if (adjust>=-12 && adjust <=-1) {
            out=24+adjust;
        }
        else out=(24+adjust)%24;
        return String.valueOf(out);
    }
}

class Area {
    Formatter formatter = new Formatter();
    final double kmToMilesSquared = Math.pow(0.62137119, 2);
    double areaInKM = 0;
    BigDecimal inMiles = BigDecimal.valueOf(0);

    Area (){
        Scanny scanny = new Scanny();
        System.out.println("Please enter area of your destination country in square kilometers.");
        areaInKM = Integer.parseInt(scanny.scanny());
        inMiles = BigDecimal.valueOf(areaInKM* kmToMilesSquared);
        System.out.println("Area of your destination country is "+formatter.twodecimals.format(inMiles)+" in square miles.");
    }
}

class Haversine {

    final int earthRadius = 6371; //Since earth is not a perfect sphere, this approx is fine.

    double latitude1=0;
    double longitude1=0;

    double latitude2=0;
    double longitude2=0;

    double deltaLatRad = 0;
    double deltaLongRad = 0;

    double latRad1=0;
    double latRad2=0;

    double distance=0;

    Haversine (){
        Formatter formatter = new Formatter();
        Scanny scanny = new Scanny();

        System.out.println("Please enter your location below:");
        System.out.print("Latitude of your current location: ");
        latitude1=Double.parseDouble(scanny.scanny());
        System.out.print("Longitude of your current location: ");
        longitude1=Double.parseDouble(scanny.scanny());
        System.out.println("Now please enter your destination:");
        System.out.print("Latitude of your destination: ");
        latitude2=Double.parseDouble(scanny.scanny());
        System.out.print("Longitude of your destination: ");
        longitude2=Double.parseDouble(scanny.scanny());

        deltaLatRad =Math.toRadians(latitude2-latitude1);
        deltaLongRad =Math.toRadians(longitude2-longitude1);
        latRad1=Math.toRadians(latitude1);
        latRad2=Math.toRadians(latitude2);

        double a = Math.pow(Math.sin(deltaLatRad/2), 2) + Math.pow(Math.sin(deltaLongRad/2), 2) * Math.cos(latRad1) * Math.cos(latRad2);
        double c = 2 * Math.asin(Math.sqrt(a));

        distance=earthRadius*c;

        System.out.println("The distance between your current location and your destination is approximately : "+formatter.twodecimals.format(distance)+ " kilomneters.");
    }

    //sources:
    //https://bigdatanerd.wordpress.com/2011/11/03/java-implementation-of-haversine-formula-for-distance-calculation-between-two-points/
    //https://rosettacode.org/wiki/Haversine_formula#Java
}

class Formatter{
    DecimalFormat twodecimals = new DecimalFormat("#.##");
}

class Scanny {

    public String scanny (){
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();

        return str;

    }

}


public class TripPlanner {

    public static void main(String[] args) {
        Main main = new Main();

    }
}


