package demo.rate;

/**
 * @Author: Niko Zhao
 * @Date: Create in 10:18 12/27/17
 * @Email: nikoz@synnex.com
 */
public class RateService {
    public static double pRate = 0.054;
    public static double gRate = 0.04;
    public static double calculate(double money,double pMoney,int month){
        double total =0;
        for(int i =0;i<month;i++){
            if(money>0)
            total += (money * gRate)/12;
            money = money-2331;
        }
        return total;
    }

    public static void main(String[] args){
        double d =calculate(308268,2331,201);
        System.out.println(d);
    }
}
