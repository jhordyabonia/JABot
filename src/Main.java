import core.Bot;
import Tools.*;
import COM.*;
import COM.Connection.UserConnection;
import gui.*;
import java.awt.AWTException; 
import java.util.Scanner;
import java.io.*;



public class Main{
    public static String[] HELP ={
        "",Bot.JABOT_VERSION,"",
        "-h \tDisplay this help","",
        "-d \tRun File or Block into File ","\tUse   -d <file> <block> ","\tEx: -d SYSTEM RUN ","",
        "-r \tRun any cammand ","\tUse   -r COMMAND ","\tEx: -r [MOUSE M 0 0]","",
        "-i \tRun interactive mode","\ttype any command one and other, and other... ","",
        "-g \tRun graphic mode","",
        "-s \tRun server mode","",
        "-c \tRun client mode","\tUse -c <server>","\tEx: -c 192.168.0.18","",
        "-p \tRun phanton mode","\tUse -p <server>","\tEx: -p 192.168.0.18","",
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
    private static void interactive(Bot bot) throws IOException{
        Scanner teclado = new Scanner(System.in);
        String cadena;
        do
        {
            System.out.print(">"); 
            cadena = teclado.nextLine();
            bot.Do_(cadena.toUpperCase());
        }while(!cadena.equals("EXIT"));
    }
    private static void phanton(Bot bot,String host){
        try{
            Phanton p = new Phanton();
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
        s.startInteractive();
    }
    private static void client(String host){
        new Client(host).startInteractive();
    }
    public static void main(String[] args) throws AWTException, IOException, InterruptedException
    { 
        final Bot bot = new Bot();
        bot.load_dictinary(null);
        if(args.length<1)
            graphicMode();
        else if(args[0].startsWith("-h"))
            System.out.println(help());
        else if(args[0].startsWith("-d")){
            Bot.MODE = 'd';
            bot.Do(args.length>1?args[1]:null,args.length>2?args[2]:null);
        }else if(args[0].startsWith("-r")){
            Bot.MODE = 'r';
            bot.Do_(args[1]);
        }else if(args[0].startsWith("-i")){
            Bot.MODE = 'i';
            interactive(bot);
        }else if(args[0].startsWith("-g")){
            Bot.MODE = 'g';
            graphicMode();
        }else if(args[0].startsWith("-s")){
            Bot.MODE = 's';
            server(bot);
        }else if(args[0].startsWith("-c")){
            Bot.MODE = 'c';
            client(args.length>1?args[1]:"localhost");
        }else if(args[0].startsWith("-p")){
            Bot.MODE = 'p';
            phanton(bot,args.length>1?args[1]:"localhost");     
        }   
    }
}