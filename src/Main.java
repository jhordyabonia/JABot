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


import java.net.*;
import java.util.Scanner;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.Robot; 
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.ByteBuffer.*;

public class Main{

    public static boolean run = false;
    public static int error = 0;
    public static String[] HELP ={
        "",Bot.JABOT_VERSION,"",
        "h \tDisplay this help","\tUse OPTION, to show detailed help","\tEx: h j //(Show detailed help to Run on Server Image mode)","",
        "d \tRun File or Block into File ","\tUse  d <file> <block> ","\tEx: d SYSTEM RUN ","",
        "r \tRun any cammand ","\tUse  r COMMAND ","\tEx: r [MOUSE M 0 0]","",
        "i \tRun interactive mode","\ttype any command one and other, and other... ","",
        "g \tRun graphic mode","",
        "s \tRun server mode","",
        "j \tRun server mode with display (images)","\tUse j T D\n\tT is display divide, (2 is default)\n\tD is delay foreach sending (5 is default)","\tEx: j 2 5\n","",
        "c \tRun client mode","\tUse c <server>","\tEx: c 192.168.0.18","",
        "p \tRun phanton mode","\tUse p <server>","\tEx: p 192.168.0.18","",
        "v \tRun viewer mode","\tUse v <server> T COMPLETEDMODE\n\tT is display divide, should be like the server (2 is default)",        
            "\tCOMPLETEDMODE is optional, if is not present, send not mouse mevements, only the clicks","\tEx: v 192.168.0.18 2 COMPLETEDMODE","",
        "t \tSet delay between each action","\tUse ?t#### \tWhere # is integer 0-1","\tEx: st0 \tset delay 0","\tEx: st100 \tset delay 100","",
        "l \tActive Error Log ","\tUse ?l <agrs> ","\tEx: dl SYSTEM RUN ","",        
        "a \tApi vision","\tUse a? <name-file> \n\n\tOptions:",Eyes.HELP,"\tEx: a0 picture.jpg",
        "",
        ""
    };    
    public static String help(String in){
       
        if(in.isEmpty()){
            return String.join("\n",HELP);
        }else{
            String out = "";boolean readAbled = false;
            for (String line : HELP) {
                if(line.isEmpty()&&!out.isEmpty()){
                    readAbled = false;
                    break;
                }else if(line.startsWith(in)){
                    readAbled = true;
                }
                if(readAbled){
                    out += line+"\n";
                }
            }
            return out;
        }
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
    private static void viewer(Bot bot,String host,int seccions,boolean mode){
        try{
            Viewer v = new Viewer(seccions);  
            v.COMPLETED_MODE = mode;    
            v.connect(host);
            run = true;
        }catch(AWTException e){}
    }
    private static void serverImage(Bot bot,String host,int seccions,double delay){
        try{
            ServerImage s = new ServerImage(seccions); 
            s.DELAY = delay;
            s.start();
            server(bot);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
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
    private static void eyes(int api,String filename)throws FileNotFoundException{
        if(filename.isEmpty()){
            Scanner teclado = new Scanner(System.in);
            System.out.println("Ingresar ruta a la imagen:");
            filename = teclado.nextLine();
        }
        eyes.Eyes.Asynchtask callBack = new eyes.Eyes.Asynchtask(){
            @Override
            public void processFinish(String result){                            
                try{
                    System.out.println(result);
                    JSONObject obj = new JSONObject(result);
                    //System.out.println(obj.getString("requestId"));
                }catch(Exception e){
                    System.err.println(e.getMessage());
                }
            }
        };
        Eyes var_eyes  = new Eyes(api, callBack, filename);
        new Thread(var_eyes).start();
        System.err.println(eyes.Eyes.methods[api]+"\t"+filename);
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
               String _api = args[0].replace("a","").replace("-","");
               if(_api.isEmpty()){
                   _api = "0";
               }
               int api = Integer.parseInt(_api);
               eyes(api,args.length>1?args[1]:"");
            }else if(args[0].contains("h")){
                System.out.println(help(args.length>1?args[1]:""));
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
            }else if(args[0].contains("v")){
                Bot.MODE = 'v';
                String host = args.length>1?args[1]:"localhost";
                int seccions = args.length>2?Integer.parseInt(args[2]):2;
                boolean mode = false;
                for (String param : args) {
                    if(param.replace("_","").toUpperCase().contains("COMPLETED")){
                        mode = true; break;
                    }
                }
                viewer(bot,host,seccions,mode);     
            }else if(args[0].contains("j")){
                Bot.MODE = 'j';
                String host = args.length>1?args[1]:"localhost";
                int seccions = args.length>2?Integer.parseInt(args[2]):2;
                double deleay = args.length>3?Double.parseDouble(args[3]):5;
                serverImage(bot,host,seccions,deleay);     
            }else if(args[0].contains("q")){
                Bot.MODE = 'q';
                reciveImage(args);     
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

    public static void reciveImage(String[] args) throws Exception {
        try{
            Socket socket = new Socket("localhost", Connection.PORT);
            InputStream inputStream = socket.getInputStream(); 
            BufferedImage image = ImageIO.read(inputStream);
 
            //System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
            ImageIO.write(image, "png", new File("result.png"));

            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}