package cinema;
import java.util.*;
import java.text.*;
public class Cinema {
    static Scanner s = new Scanner(System.in);
    static int rows;
    static int cols;
    static String[][] cinema;
    static int capacity;
    static int count = 0;
    static int curr_income = 0;
    static int total_income = 0;
    static NumberFormat US = NumberFormat.getCurrencyInstance(Locale.US);
    static void create(){
        System.out.println("Enter the number of rows:");
        rows = s.nextInt();
        System.out.println("Enter the number of seats in each row:");
        cols = s.nextInt();
        System.out.println();
        capacity = rows*cols;
        cinema = new String[rows+1][cols+1];
        for(int i = 0; i < rows+1; i++){
            for(int j = 0; j < cols+1; j++) {
                if (i == 0 && j == 0) {
                    cinema[i][j] = " ";
                } else if (i == 0) {
                    cinema[i][j] = Integer.toString(j);
                } else if (j == 0) {
                    cinema[i][j] = Integer.toString(i);
                } else {
                    cinema[i][j] = "S";
                }
            }
        }
    }
    static void display(){
        System.out.println("Cinema:");
        for(int i = 0; i < rows+1; i++){
            for(int j = 0; j < cols+1; j++){
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }
    /*
    static void input(){

        System.out.println("Enter the number of rows:");
        rows = s.nextInt();
        System.out.println("Enter the number of seats in each row:");
        cols = s.nextInt();
    }
    */
    static void calculate_profit(){
        capacity = rows*cols;
        int profit;
        if(capacity<=60){
            profit = 10*capacity;
        }
        else{
            if(rows%2==0){
                profit = 10*(rows/2)*cols + 8*(rows/2)*cols;
            }
            else{
                profit = 10*(rows/2)*cols + 8*((rows+1)/2)*cols;
            }
        }
        System.out.println("Total income: $" + profit);
        //System.out.println(US.format(profit));
    }
    static int tktprice(int rowchoice){
        if(capacity<60){
            return 10;
        }
        else{
            if(rowchoice<=rows/2)
                return 10;
            else
                return 8;
        }
    }
    static void book(){
        int flag = 0;
        do{
            System.out.println("Enter a row number:");
            int rowchoice = s.nextInt();
            System.out.println("Enter a seat number in that row:");
            int colchoice = s.nextInt();
            //System.out.println(rowchoice);

            /*if(rowchoice>rows || colchoice>cols){
                System.out.println("Wrong input!");
            }*/
            try {
                if(cinema[rowchoice][colchoice] == "B"){
                    System.out.println("That ticket has already been purchased!");
                }
                else {
                    flag = 1;
                    cinema[rowchoice][colchoice] = "B";
                    count++;
                    curr_income += tktprice(rowchoice);
                    System.out.println("Ticket price: " + US.format(tktprice(rowchoice)));
                }
            } catch(Exception e) {
                System.out.println("Wrong input!");
            }
        } while(flag == 0);
    }
    static void statistics() {
        System.out.println("Number of purchased tickets: " + count);
        double percent = ((double)count/(double)capacity) * 100;
        System.out.printf("Percentage: %.2f%%\n", percent);
        System.out.println("Current income: $" + curr_income);
        calculate_profit();
    }
    public static void main(String[] args) {
        create();
        int ch;
        do{
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            ch = s.nextInt();
            switch(ch){
                case 1 :
                    display();
                    break;
                case 2 :
                    book();
                    break;
                case 3 :
                    statistics();
                    break;
            }
        }while(ch!=0);
    }
}