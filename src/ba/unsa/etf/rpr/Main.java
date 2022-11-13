package ba.unsa.etf.rpr;

import java.util.Scanner;

public class Main {

    public static int sumaCifara(int broj)
    {
        int suma = 0;
        while (broj != 0)
        {
            suma+= broj%10;
            broj/=10;
        }
        return suma;
    }
    public static void main(String[] args) {
        System.out.println("Unesite broj n: ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int i = 1; i <= n; i++)
        {
            if (i % sumaCifara(i) == 0)
                System.out.println(i);
        }
    }
}
