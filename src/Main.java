import core.Bot;
import Tools.*;
import COM.*;
import COM.Connection.UserConnection;
import eyes.*;
import gui.*;
import java.awt.AWTException; 
import java.util.Scanner;
import java.io.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Main{

    public static boolean run = false;
    public static int error = 0;
    public static String[] HELP ={
        "",Bot.JABOT_VERSION,"",
        "h \tDisplay this help","",
        "d \tRun File or Block into File ","\tUse  d <file> <block> ","\tEx: d SYSTEM RUN ","",
        "r \tRun any cammand ","\tUse  r COMMAND ","\tEx: r [MOUSE M 0 0]","",
        "i \tRun interactive mode","\ttype any command one and other, and other... ","",
        "g \tRun graphic mode","",
        "s \tRun server mode","",
        "c \tRun client mode","\tUse c <server>","\tEx: c 192.168.0.18","",
        "p \tRun phanton mode","\tUse p <server>","\tEx: p 192.168.0.18","",
        "t \tSet delay between each action","\tUse ?t#### \tWhere # is integer 0-1","\tEx: st0 \tset delay 0","\tEx: st100 \tset delay 100","",
        "l \tActive Error Log ","\tUse ?l <agrs> ","\tEx: dl SYSTEM RUN ","",        
        "",
        "",
        ""
    };    
    public static String help(){
        return String.join("\n",HELP);
    }
    private static void graphicMode(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }
    private static void interactive(Bot bot) throws AWTException, IOException, InterruptedException{
        Scanner teclado = new Scanner(System.in);
        String cadena;
        run = true;
        do
        {
            System.out.print(">"); 
            cadena = teclado.nextLine();
            bot.Do_(cadena);
        }while(!cadena.toUpperCase().equals("EXIT"));
    }
    private static void phanton(Bot bot,String host){
        try{
            Phanton p = new Phanton();
            run = true;
            p.connect(host);
        }catch(AWTException e){}
    }
    private static void server(Bot bot){
       
        UserConnection u = new UserConnection() {            
            public void write(String data){
                try{bot.Do_(data);}
                catch(IOException e){}
            }
            public void start(Connection c){}
        };
        Server s = new Server("Server");
        s.setUser(u);
        run = true;
        s.startInteractive();      
    }
    private static void client(String host){
        new Client(host).startInteractive();
    }
    private static void setDelay(String args){
        String num="";int error=0;
        for(char c:args.toCharArray()){
            try{
                Integer.parseInt(""+c);
                num += c;
                error = 1;
            }catch(NumberFormatException e){
                error= error+error;
                if(error>0)
                    break;
            }
        }
        try{
            Bot.DELAY= Integer.parseInt(num);
        }catch(NumberFormatException e){}
    }
    public static void main(String[] args) 
    { 
        try{
            final Bot bot = new Bot();
            bot.load_dictinary(null);
            if(args.length<1){
                graphicMode();
                return;
            }
            
            if(args[0].contains("l")){
                Bot.LOG=true;
            }
            if(args[0].contains("t")){
               setDelay(args[0]);
            }
            
            if(args[0].contains("a")){
                eyes.Eyes.Asynchtask callBack = new eyes.Eyes.Asynchtask(){
                    @Override
                    public void processFinish(String result){
                            //System.out.println(result);
                        try{
                            //result = "{\"phonetype\":\"N95\",\"cat\":\"WP\"}";
                            //System.out.println(result);
                            JSONObject obj = new JSONObject(result);
                            System.out.println(obj.getString("requestId"));
                        }catch(Exception e){
                            System.err.println(e.getMessage());
                        }
                    }
                };
                String filename = "0.png";
                String url = eyes.Eyes.uriBase+eyes.Eyes.requestParameters;
                Eyes eyes  = new Eyes(url,callBack, filename);
                new Thread(eyes).start();
            }else if(args[0].contains("h")){
                System.out.println(help());
            }else if(args[0].contains("d")){
                Bot.MODE = 'd';
                bot.Do(args.length>1?args[1]:null,args.length>2?args[2]:null);
            }else if(args[0].contains("r")){
                Bot.MODE = 'r';
                bot.Do_(args[1]);
            }else if(args[0].contains("i")){
                Bot.MODE = 'i';
                interactive(bot);
            }else if(args[0].contains("g")){
                Bot.MODE = 'g';
                graphicMode();
            }else if(args[0].contains("s")){
                Bot.MODE = 's';
                server(bot);
            }else if(args[0].contains("c")){
                Bot.MODE = 'c';
                client(args.length>1?args[1]:"localhost");
            }else if(args[0].contains("p")){
                Bot.MODE = 'p';
                phanton(bot,args.length>1?args[1]:"localhost");     
            }  
        }catch(Exception e){
            System.err.println(e.getMessage());
            System.out.println(error+")Bot Error:\n"+Bot.getLog());
            System.out.println("Faltal error");
            if(error++<3&run){
                System.out.println("Reloading...");
                main(args);
            }
        } 
    }
}