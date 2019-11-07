/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COM;

import java.net.*;
import java.util.Scanner;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import COM.Connection;

/**
 *
 * @author jhord_000
 */
public class Server extends Connection
{
    protected ServerSocket sc;

    public Server(String n){
        name=n;
    } 
    
    @Override
    public void init()
    {
        try
        {
            sc = new ServerSocket(PORT);
            System.out.println("Wait conecion:");
            so = sc.accept();
            acepted();
        }catch(Exception e ){}
    }
    
    @Override
    public void close(){       
        try {sc.close();}
        catch (IOException ex) {}
        super.close();
    }
    
    @Override
    public void startInteractive(){
        super.startInteractive();
        init();
    }
   
    public static void main(String []args){
        Server s = new Server("Server");
        s.startInteractive();
    }

}