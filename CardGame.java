//Homework project for Operating System class
//Using multithreading

import java.util.*;
import java.io.*;

public class CardGame
{

static String Gameover = null;
static boolean Semaphore = true;

class Dealer implements Runnable
    {
        String[] deck;
        Random random;

        public Dealer()
        {
            this.deck = new String[]
            {
               "Ace", "King", "Queen"
            };
            this.random = new Random();
        }

        public void deal()
        {
            String[] Cards = new String[this.deck.length-1];
            int firstpick = random.nextInt(3);
            int secondpick = random.nextInt(3);

            
            while(secondpick == firstpick)
            {
                secondpick = random.nextInt(2);
            }

            Cards[0] = this.deck[firstpick];
            Cards[1] = this.deck[secondpick];
            System.out.println("Dealer places " + Cards[0] + " and " + Cards[1] + " on the table");

            for(int i = 0; i < this.deck.length; i++)
            {
                if(!Arrays.asList(Cards).contains(this.deck[i]))
                {
                    Gameover = this.deck[i];
                }
            }
        }

        public void run()
        {
            System.out.println("Dealer Thread Running ");
            do
            {
                if(Semaphore)
                {
                    System.out.println("\n Next Thread ");
                    
                    deal();
                    Semaphore = false;
                }
            }while(true);
        }
       
    }
    
     public static void main(String[] args)
    {
        CardGame game = new CardGame();
        Dealer dealer = game. new Dealer();
        Player p1 = game. new Player(dealer.deck[0]);
        Player p2 = game. new Player(dealer.deck[1]);
        Player p3 = game. new Player(dealer.deck[2]);

        Thread threadDealer = new Thread(dealer);
        Thread threadp1 = new Thread(p1);
        Thread threadp2 = new Thread(p2);
        Thread threadp3 = new Thread(p3);

        threadDealer.start();
        threadp1.start();
        threadp2.start();
        threadp3.start();
    }

     class Player implements Runnable
    {
        String card;

        public Player(String card)
        {
            this.card = card;
        }

        public void play()
        {
            System.out.println("Player with " + this.card  + " places his card on the table.");
            System.out.println("Winner is the " + this.card + "  Card ");
        }
        
          public void run()
        {
            System.out.println("Player Thread with the " + this.card + " \n");
            do
            {
                if(!Semaphore && this.card.equals(Gameover))
                {
                    play();
                    Semaphore= true;
                }
            }while(true);
        }


           }
           
    
    }




