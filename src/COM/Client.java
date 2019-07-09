/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COM;

/**
 *
 * @author jhord_000
 */
import java.net.*;
import java.util.Scanner;
import java.io.*;


public class Client extends Connection
{    
    private String server="";

    public Client(String s){
        server = s;
        name = s;
    } 

    public Client(String s,String n){
        server = s;
        name = n;
    }  
  
    public void init() 
    {
        try
        {
            so = new Socket( server , PORT ); 
            acepted();
        }catch(Exception e ){}
    }

    public static void main(String []args){
        Client c= new Client("localhost");
        c.startInteractive();    
    }
}
