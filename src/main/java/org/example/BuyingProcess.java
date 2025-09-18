package org.example;

public class BuyingProcess {
    public static Boolean BuyingProcess(int price, int NetWorth, Strategy strategy, int BuyCounter) {
        switch (strategy) {
            case GREEDY:
                if (price <=NetWorth){
                    return true;
                }
                else{
                    return false;
                }
            case CAREFUL:
                if (price <=NetWorth/2){
                    return true;
                }
                else{
                    return false;
                }
            case TACTICAL:
                if (price <=NetWorth && BuyCounter % 2 == 0){
                    return true;
                }
                else{
                    return false;
                }



        }
        return null;
    }
}
