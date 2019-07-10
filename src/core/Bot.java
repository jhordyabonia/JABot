package core;
 
import java.awt.Robot; 
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.AWTException; 


public class Bot extends Robot 
{
        private String[] dictionary;
        private boolean in_catch=false;
        private static boolean pause=false;
        private static boolean stop=false;
        private String catched="";
        private int repeat;
        public static int DELAY=0;
        public static char MODE='d';
        private final Clipboard io = Toolkit.getDefaultToolkit().getSystemClipboard();
        private String clipboard = "";
        private static int step=0;
        private static String path_fail="";
        public static String getLog(){
            String e=""+path_fail;
            path_fail=""; step=0;
            return e;
        }        
    
        public Bot() throws AWTException
        { super();step=0;}
        public String[] get_dictionary()
        {
            return dictionary;
        }
        public static boolean isPause(){return pause;}
        public static void pause(boolean p){ pause=p;}
        public void stop(boolean p){
             pause=p;stop=true;
        }
        public void  load_dictinary(String file) throws FileNotFoundException, IOException
        {
             if(file==null)
                 file="default.dic";
            
             FileReader f=new FileReader(file);             
             BufferedReader b=new BufferedReader(f);
             //largo de archivo
             int t=Integer.parseInt(b.readLine());
             if(t>202)
                 throw new IOException();
             dictionary=new String[203];
             for(int o=t;o>=0;o--)
             {
                 String g=b.readLine();
                 if(g==null)continue;
                 String r[]=g.split(":");
                 dictionary[Integer.parseInt(r[0])]=r[1];                 
             }
             f.close(); 
             
            System.out.println("JABot 1.5  dictionary:"+file);
        }
        public boolean releaseKey(char key) throws IOException
        {   return releaseKey(get_key(""+key)); }
        public boolean releaseKey(int key)
        { 
            if(key==-1)return false;
            this.keyRelease(get_key(key));
            return true;
        }    
        public static int get_key(int key)
        {                    
         switch(key)
            {                
                case 0: return KeyEvent.KEY_FIRST;
                case 1: return KeyEvent.KEY_LAST;
                //case 2: return KeyEvent.static char;
                case 3: return KeyEvent.CHAR_UNDEFINED;
                case 4: return KeyEvent.KEY_FIRST;
                case 5: return KeyEvent.KEY_LAST;
                case 6: return KeyEvent.KEY_LOCATION_LEFT;
                case 7: return KeyEvent.KEY_LOCATION_NUMPAD;
                case 8: return KeyEvent.KEY_LOCATION_RIGHT;
                case 9: return KeyEvent.KEY_LOCATION_STANDARD;
                case 10: return KeyEvent.KEY_LOCATION_UNKNOWN;
                case 11: return KeyEvent.KEY_PRESSED;
                case 12: return KeyEvent.KEY_RELEASED;
                case 13: return KeyEvent.KEY_TYPED;
                case 14: return KeyEvent.VK_0;
                case 15: return KeyEvent.VK_1;
                case 16: return KeyEvent.VK_2;
                case 17: return KeyEvent.VK_3;
                case 18: return KeyEvent.VK_4;
                case 19: return KeyEvent.VK_5;
                case 20: return KeyEvent.VK_6;
                case 21: return KeyEvent.VK_7;
                case 22: return KeyEvent.VK_8;
                case 23: return KeyEvent.VK_9;
                case 24: return KeyEvent.VK_A;
                case 25: return KeyEvent.VK_ACCEPT;
                case 26: return KeyEvent.VK_ADD;
                case 27: return KeyEvent.VK_AGAIN;
                case 28: return KeyEvent.VK_ALL_CANDIDATES;
                case 29: return KeyEvent.VK_ALPHANUMERIC;
                case 30: return KeyEvent.VK_ALT;
                case 31: return KeyEvent.VK_ALT_GRAPH;
                case 32: return KeyEvent.VK_AMPERSAND;
                case 33: return KeyEvent.VK_ASTERISK;
                case 34: return KeyEvent.VK_AT;
                case 35: return KeyEvent.VK_B;
                case 36: return KeyEvent.VK_BACK_QUOTE;
                case 37: return KeyEvent.VK_BACK_SLASH;
                case 38: return KeyEvent.VK_BACK_SPACE;
                case 39: return KeyEvent.VK_BEGIN;
                case 40: return KeyEvent.VK_BRACELEFT;
                case 41: return KeyEvent.VK_BRACERIGHT;
                case 42: return KeyEvent.VK_C;
                case 43: return KeyEvent.VK_CANCEL;
                case 44: return KeyEvent.VK_CAPS_LOCK;
                case 45: return KeyEvent.VK_CIRCUMFLEX;
                case 46: return KeyEvent.VK_CLEAR;
                case 47: return KeyEvent.VK_CLOSE_BRACKET;
                case 48: return KeyEvent.VK_CODE_INPUT;
                case 49: return KeyEvent.VK_COLON;
                case 50: return KeyEvent.VK_COMMA;
                case 51: return KeyEvent.VK_COMPOSE;
                case 52: return KeyEvent.VK_CONTEXT_MENU;
                case 53: return KeyEvent.VK_CONTROL;
                case 54: return KeyEvent.VK_CONVERT;
                case 55: return KeyEvent.VK_COPY;
                case 56: return KeyEvent.VK_CUT;
                case 57: return KeyEvent.VK_D;
                case 58: return KeyEvent.VK_DEAD_ABOVEDOT;
                case 59: return KeyEvent.VK_DEAD_ABOVERING;
                case 60: return KeyEvent.VK_DEAD_ACUTE;
                case 61: return KeyEvent.VK_DEAD_BREVE;
                case 62: return KeyEvent.VK_DEAD_CARON;
                case 63: return KeyEvent.VK_DEAD_CEDILLA;
                case 64: return KeyEvent.VK_DEAD_CIRCUMFLEX;
                case 65: return KeyEvent.VK_DEAD_DIAERESIS;
                case 66: return KeyEvent.VK_DEAD_DOUBLEACUTE;
                case 67: return KeyEvent.VK_DEAD_GRAVE;
                case 68: return KeyEvent.VK_DEAD_IOTA;
                case 69: return KeyEvent.VK_DEAD_MACRON;
                case 70: return KeyEvent.VK_DEAD_OGONEK;
                case 71: return KeyEvent.VK_DEAD_SEMIVOICED_SOUND;
                case 72: return KeyEvent.VK_DEAD_TILDE;
                case 73: return KeyEvent.VK_DEAD_VOICED_SOUND;
                case 74: return KeyEvent.VK_DECIMAL;
                case 75: return KeyEvent.VK_DELETE;
                case 76: return KeyEvent.VK_DIVIDE;
                case 77: return KeyEvent.VK_DOLLAR;
                case 78: return KeyEvent.VK_DOWN;
                case 79: return KeyEvent.VK_E;
                case 80: return KeyEvent.VK_END;
                case 81: return KeyEvent.VK_ENTER;
                case 82: return KeyEvent.VK_EQUALS;
                case 83: return KeyEvent.VK_ESCAPE;
                case 84: return KeyEvent.VK_EURO_SIGN;
                case 85: return KeyEvent.VK_EXCLAMATION_MARK;
                case 86: return KeyEvent.VK_F;
                case 87: return KeyEvent.VK_F1;
                case 88: return KeyEvent.VK_F10;
                case 89: return KeyEvent.VK_F11;
                case 90: return KeyEvent.VK_F12;
                case 91: return KeyEvent.VK_F13;
                case 92: return KeyEvent.VK_F14;
                case 93: return KeyEvent.VK_F15;
                case 94: return KeyEvent.VK_F16;
                case 95: return KeyEvent.VK_F17;
                case 96: return KeyEvent.VK_F18;
                case 97: return KeyEvent.VK_F19;
                case 98: return KeyEvent.VK_F2;
                case 99: return KeyEvent.VK_F20;
                case 100: return KeyEvent.VK_F21;
                case 101: return KeyEvent.VK_F22;
                case 102: return KeyEvent.VK_F23;
                case 103: return KeyEvent.VK_F24;
                case 104: return KeyEvent.VK_F3;
                case 105: return KeyEvent.VK_F4;
                case 106: return KeyEvent.VK_F5;
                case 107: return KeyEvent.VK_F6;
                case 108: return KeyEvent.VK_F7;
                case 109: return KeyEvent.VK_F8;
                case 110: return KeyEvent.VK_F9;
                case 111: return KeyEvent.VK_FINAL;
                case 112: return KeyEvent.VK_FIND;
                case 113: return KeyEvent.VK_FULL_WIDTH;
                case 114: return KeyEvent.VK_G;
                case 115: return KeyEvent.VK_GREATER;
                case 116: return KeyEvent.VK_H;
                case 117: return KeyEvent.VK_HALF_WIDTH;
                case 118: return KeyEvent.VK_HELP;
                case 119: return KeyEvent.VK_HIRAGANA;
                case 120: return KeyEvent.VK_HOME;
                case 121: return KeyEvent.VK_I;
                case 122: return KeyEvent.VK_INPUT_METHOD_ON_OFF;
                case 123: return KeyEvent.VK_INSERT;
                case 124: return KeyEvent.VK_INVERTED_EXCLAMATION_MARK;
                case 125: return KeyEvent.VK_J;
                case 126: return KeyEvent.VK_JAPANESE_HIRAGANA;
                case 127: return KeyEvent.VK_JAPANESE_KATAKANA;
                case 128: return KeyEvent.VK_JAPANESE_ROMAN;
                case 129: return KeyEvent.VK_K;
                case 130: return KeyEvent.VK_KANA;
                case 131: return KeyEvent.VK_KANA_LOCK;
                case 132: return KeyEvent.VK_KANJI;
                case 133: return KeyEvent.VK_KATAKANA;
                case 134: return KeyEvent.VK_KP_DOWN;
                case 135: return KeyEvent.VK_KP_LEFT;
                case 136: return KeyEvent.VK_KP_RIGHT;
                case 137: return KeyEvent.VK_KP_UP;
                case 138: return KeyEvent.VK_L;
                case 139: return KeyEvent.VK_LEFT;
                case 140: return KeyEvent.VK_LEFT_PARENTHESIS;
                case 141: return KeyEvent.VK_LESS;
                case 142: return KeyEvent.VK_M;
                case 143: return KeyEvent.VK_META;
                case 144: return KeyEvent.VK_MINUS;
                case 145: return KeyEvent.VK_MODECHANGE;
                case 146: return KeyEvent.VK_MULTIPLY;
                case 147: return KeyEvent.VK_N;
                case 148: return KeyEvent.VK_NONCONVERT;
                case 149: return KeyEvent.VK_NUM_LOCK;
                case 150: return KeyEvent.VK_NUMBER_SIGN;
                case 151: return KeyEvent.VK_NUMPAD0;
                case 152: return KeyEvent.VK_NUMPAD1;
                case 153: return KeyEvent.VK_NUMPAD2;
                case 154: return KeyEvent.VK_NUMPAD3;
                case 155: return KeyEvent.VK_NUMPAD4;
                case 156: return KeyEvent.VK_NUMPAD5;
                case 157: return KeyEvent.VK_NUMPAD6;
                case 158: return KeyEvent.VK_NUMPAD7;
                case 159: return KeyEvent.VK_NUMPAD8;
                case 160: return KeyEvent.VK_NUMPAD9;
                case 161: return KeyEvent.VK_O;
                case 162: return KeyEvent.VK_OPEN_BRACKET;
                case 163: return KeyEvent.VK_P;
                case 164: return KeyEvent.VK_PAGE_DOWN;
                case 165: return KeyEvent.VK_PAGE_UP;
                case 166: return KeyEvent.VK_PASTE;
                case 167: return KeyEvent.VK_PAUSE;
                case 168: return KeyEvent.VK_PERIOD;
                case 169: return KeyEvent.VK_PLUS;
                case 170: return KeyEvent.VK_PREVIOUS_CANDIDATE;
                case 171: return KeyEvent.VK_PRINTSCREEN;
                case 172: return KeyEvent.VK_PROPS;
                case 173: return KeyEvent.VK_Q;
                case 174: return KeyEvent.VK_QUOTE;
                case 175: return KeyEvent.VK_QUOTEDBL;
                case 176: return KeyEvent.VK_R;
                case 177: return KeyEvent.VK_RIGHT;
                case 178: return KeyEvent.VK_RIGHT_PARENTHESIS;
                case 179: return KeyEvent.VK_ROMAN_CHARACTERS;
                case 180: return KeyEvent.VK_S;
                case 181: return KeyEvent.VK_SCROLL_LOCK;
                case 182: return KeyEvent.VK_SEMICOLON;
                case 183: return KeyEvent.VK_SEPARATER;
                case 184: return KeyEvent.VK_SEPARATOR;
                case 185: return KeyEvent.VK_SHIFT;
                case 186: return KeyEvent.VK_SLASH;
                case 187: return KeyEvent.VK_SPACE;
                case 188: return KeyEvent.VK_STOP;
                case 189: return KeyEvent.VK_SUBTRACT;
                case 190: return KeyEvent.VK_T;
                case 191: return KeyEvent.VK_TAB;
                case 192: return KeyEvent.VK_U;
                case 193: return KeyEvent.VK_UNDEFINED;
                case 194: return KeyEvent.VK_UNDERSCORE;
                case 195: return KeyEvent.VK_UNDO;
                case 196: return KeyEvent.VK_UP;
                case 197: return KeyEvent.VK_V;
                case 198: return KeyEvent.VK_W;
                case 199: return KeyEvent.VK_WINDOWS;
                case 200: return KeyEvent.VK_X;
                case 201: return KeyEvent.VK_Y;
                case 202: return KeyEvent.VK_Z;
            }
            return -1;
        }
        public boolean pressKey(char key) throws IOException 
        {return pressKey(get_key(""+key));}
        public boolean pressKey(int key) 
        {
            if(key==-1)return false;
            try{
            this.keyPress(get_key(key));
            }catch(Exception e)
            {/*System.out.
                    println("get_key(key) > "
                    +get_key(key)+"/ key > "+key);*/
            }
            return true;
        }        
       
        private int get_key(String key) throws IOException
        {
            int out=-1;
            for(String c:dictionary)
            {
                out++;
                if(c==null)continue;
                
                if(c.equals(tranform(key)))
                    return out;
            }
            String []h=key.split(" ");
            Do(h[0],h.length>1?h[1]:null);
            
            return -1;
        }        
        private String tranform(String key)
        {
            switch(key.charAt(0))
            {
                case ' ': return "SPACE";
                case '.': return "PERIOD";
                case ',': return "COMMA";
                case '{': return "BRACELEFT";
                case '}': return "BRACERIGHT";
                case '[': return "OPEN_BRACKET";
                case ']': return "CLOSE_BRACKET";
                case '(': return "LEFT_PARENTHESIS";
                case ')': return "RIGHT_PARENTHESIS";
                case '!': return "EXCLAMATION_MARK";
                case '"': return "SPACE";
                case '=': return "EQUALS";
                case '\'': return "QUOTE";
                case '?': return "SPACE";
                case '¿': return "SPACE";
                case '¡': return "SPACE";
                case '\\': return "BACK_SLASH";
                case '/': return "DIVIDE";
                case ':': return "COLON";
                case '_': return "UNDERSCORE";
                case ';': return "SEMICOLON";
                case '-': return "SUBTRACT";
                case '%': return "SPACE";
                case '$': return "DOLLAR";
                case '#': return "NUMBER_SIGN";
                case '@': return "AT";
                case '&': return "AMPERSAND";
                case '*': return "ASTERISK";
                case '+': return "PLUS";
            }
            return key;
        }
        public boolean type(String text) throws IOException
        {
            return type(text,DELAY);
        }
        public boolean type(String text,int t) throws IOException 
        {
            text=text.toUpperCase();
            for(int c=0;c<text.length();c++)
            {
                    this.delay(t);
                    int tt=get_key(""+text.charAt(c));
                    pressKey(tt);
                    releaseKey(tt);
            }
            return true;
        }
        public String Do(String file,String  block) throws IOException
        {   return Do(file,block,true);} 
        public String Do(String file,String  block,boolean r) throws IOException
        {    
            if(file==null)
                file="default.do";
            FileReader f= new FileReader(file);
             BufferedReader b=new BufferedReader(f);
             String c = "",out="";
             if(block==null)
             {
                String before="";int line=0; 
                do{      
                    try {c=b.readLine(); } catch (IOException ex) 
                    {System.out.println("Err at time readig the File\nLine: "+line+"\nLast line Ok: "+before);}
                    if(r)Do_(c);
                    else out+=c;
                    before=c;
                }while(c!=null);
             }else 
             {
               boolean active=false;
               
               String before="";int line=0; 
               do
               {      
                   try {  c=b.readLine(); } catch (IOException ex) 
                   {System.out.println("Err at time readig the File\nLine: "+line+"\nLast line Ok: "+before);}
                    
                   if(c==null)break;
                   if(c.contains("::"+block))
                     active=!active;
                   if(active)
                    if(r)Do_(c);
                    else out+=c;
                   
                    before=c;
                }while(true);
             }
            try {f.close();} catch (IOException ex) {}
             return out;
        }
        public void click(int btn,int x)
        {
             if(x==0)
             { this.mousePress(btn);}
             else if(x==1)
             {this.mouseRelease(btn); }
             else{this.mousePress(btn);this.mouseRelease(btn); }
        }
        public void mouse_do(String to_do)
        {
            to_do=to_do.replace("[MOUSE ","").replace("]", "");
            String []data=to_do.split(" ");
            int y=-1,x=-1; char c='L';
            if(data.length>2)
                y=Integer.parseInt(data[2]);
            if(data.length>1)
                x=Integer.parseInt(data[1]);
            if(data[0]!=null)
                c=data[0].charAt(0);
                        
            switch(c)
            {                
                case 'L':                    
                    click(InputEvent.BUTTON1_DOWN_MASK,x);  
                    break;
                case 'R':
                    click(InputEvent.BUTTON3_DOWN_MASK,x);
                    break;
                case 'W':
                    if(x!=-1)
                        mouseWheel(x);
                    else click(InputEvent.BUTTON2_DOWN_MASK,x);  
                    break;  
                default: 
                        mouseMove(x,y); 
            }       
        }    
        public void Do_(String in) throws IOException 
        {          
            path_fail+="\n#"+step++;
            for(int u=step;u>=0;u--)
                path_fail+=" ";
            path_fail+="-";
            
            while(pause)
             try {Thread.sleep(100);if(stop)System.exit(0);} 
             catch (InterruptedException ex) {}
            if(in==null)return;
            
            do
                in=in.replace("  ", " ");
            while(in.contains("  "));
            
            for(String to_do:in.split(","))
            {
                path_fail+=">>"+to_do;
                if(to_do==null)continue;
                if(to_do.startsWith("[STOP"))
                {return;}
                else if(in_catch)
                {    
                    if(to_do.startsWith("::DESCRIP"))
                    {   in_catch=false;      } 
                    else if(to_do.contains("/REPEAT"))
                    {    
                        in_catch=false;
                        for(int i=0;i<repeat;i++)
                         Do_(catched);
                        catched="";
                    }else
                    if(to_do.contains("/MAKE"))
                    {
                        in_catch=false;
                        make(catched,to_do);
                        catched="";
                    }
                    else{ catched+=","+to_do; }              
                } 
                else if(to_do.startsWith("<"))
                {      continue;         }   
                else if(to_do.startsWith("::DESCRIP"))
                {   in_catch=true;       } 
                else if(to_do.startsWith("::"))
                {      continue;         }   
                else if(to_do.contains("[LOAD"))
                {   load_dictinary(to_do.replace("[LOAD ","").replace("]", ""));  }
                else if(to_do.contains("[REPEAT"))
                {     
                    repeat=Integer.parseInt(to_do.replace("[REPEAT ","").replace("]", ""));
                    in_catch=true;                 
                }
                else if(to_do.contains("[MAKE"))
                {                                     
                    catched=to_do.replace("[MAKE ","").replace("]", "");
                    in_catch=true;
                }else if(to_do.contains("NOW"))
                {  run(to_do); }
                else if(to_do.contains("MOUSE"))
                {  mouse_do(to_do);
                }else if(to_do.contains("[IF "))
                {                if_(to_do);
                }else if(to_do.contains("WAIT"))
                {                
                    to_do=to_do.replace("[WAIT ","").replace("]", "");
                    this.delay(Integer.parseInt(to_do));
                }else if(to_do.endsWith("/]"))
                {
                    to_do=to_do.replace("[","").replace("/]", "");
                    pressKey(get_key(to_do));
                    releaseKey(get_key(to_do));
                }else if(to_do.startsWith("[/"))
                {
                    to_do=to_do.replace("[/","").replace("]", "");
                    releaseKey(get_key(to_do));
                }else if(to_do.startsWith("["))
                {            
                    to_do=to_do.replace("[","").replace("]", "");
                    pressKey(get_key(to_do));
                }else
                {
                    String []type =to_do.split("::");

                    if(type.length>1)
                        type(type[0],Integer.parseInt(type[1]));
                    else type(type[0]); 
                }
            }
        }
        public void make(String name,String param) 
        {      
            boolean append=param.contains("+");
             
             String[] data=name.split(",");
             name=data[0];
             
             FileWriter f=null;
            try {f = new FileWriter(name);} catch (IOException ex) 
            {System.out.println("Err: Command no made");}
             for(int i=1;i<data.length;i++)
                try {
                    f.write(data[i]+'\n');            
                    f.close();
                } catch (IOException ex)
                {System.out.println("Err: Writing commad no made");}
        }                
        private void run(String in)
        {   
            Transferable t = io.getContents(this);

            DataFlavor dataFlavorStringJava=null;
            try 
            {
                dataFlavorStringJava = new DataFlavor( "application/x-java-serialized-object; class=java.lang.String");
            } catch (ClassNotFoundException ex){}
            if (dataFlavorStringJava==null)return;

            try { clipboard = (String) t.getTransferData(dataFlavorStringJava);}
            catch (UnsupportedFlavorException ex) {} catch (IOException ex){}
            if(clipboard==null)
            { System.out.println("\nFormat err.");return;   }
            
            try 
            { 
                clipboard=clipboard.toLowerCase();
                in=in.replace("[NOW ", "");
                in=in.substring(0,in.length()-1);
                in=in.toLowerCase();
                String _else="";
                if (t.isDataFlavorSupported(dataFlavorStringJava))
                {   
                    for(String d:in.split(" "))
                        if(d.startsWith("+"))
                        {_else=d.substring(1);}
                        else if(clipboard.equals(d))
                        {Do_(clipboard);return;}
                }
                if(!_else.equals(""))
                {Do_(_else);return;}
                Do_(clipboard);
            } catch (IOException ex) { }     
            
        }        
        public void if_(String to_do){
            System.out.println(to_do);
        }
	
} 