package ECHO;

import java.io.Serializable;

//Using our serializable data, we can send our data easily with bit stream
//Yet each time a class is sent, it would create a new object ... so not the best solution

public class Card implements Serializable {
    public char suit;
    public int num;
    public Card(char s, int n){
        num = n;
        suit = s;
    }
}
