import core.Bot;
import COM.*;
import COM.Connection.UserConnection;
import gui.*;
import java.awt.AWTException; 
import java.util.Scanner;
import java.io.IOException;

public class Main{
    public static void help(){

    }
    public static void main(String[] args) throws AWTException, IOException, InterruptedException
    { 
        final Bot bot = new Bot();
        bot.load_dictinary(null);
        if(args.length<1)
        { new Admin().setVisible(true);}
        else if(args[0].contains("-d"))
            bot.Do(args.length>1?args[1]:null,args.length>2?args[2]:null);
        else if(args[0].contains("-r"))
            bot.Do_(args[1]);
        else if(args[0].contains("-i")){
            Scanner teclado = new Scanner(System.in);
            String cadena;
            do
            {
                System.out.print(">"); 
                cadena =teclado.nextLine();
                bot.Do_(cadena.toUpperCase());
            }while(!cadena.equals("EXIT"));
        }else if (args[0].contains("-g")){
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Admin().setVisible(true);
                }
            });
        }else if(args[0].contains("-s")){
            Server s = new Server("Server");
            UserConnection u = new UserConnection() {            
                public void write(String data){
                        try{bot.Do_(data);}
                        catch(IOException e){}
                }
                public String read(){return "";}
            };
            s.setUser(u);
            s.startInteractive();
        }
    }
}