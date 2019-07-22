package core;
public class Tag{
    private Tag(String name){
        tag = name.toLowerCase();
    }
    public final String tag;
    public String args = "";
    public String catched = "";
    public boolean in_catch = false;
    public boolean startsWith(String in){
        return in.toLowerCase().startsWith("["+tag);
    }
    public boolean contains(String in){
        return in.toLowerCase().contains(tag);    
    }
    public String get(String in){
        return in.replace("["+tag.toUpperCase()+" ","")
                .replace("["+tag.toLowerCase()+" ","")
                .replace("]","").trim();
    }
    public boolean ends(String in){        
        return in.toLowerCase().contains("/"+tag);
    }
    public static final Tag MAKE = new Tag("MAKE");
    public static final Tag REPEAT = new Tag("REPEAT");
    public static final Tag IF = new Tag("IF");
    public static final Tag ELSE = new Tag("ELSE");
    public static final Tag WAIT = new Tag("WAIT");
    public static final Tag DELAY = new Tag("DELAY");
    public static final Tag VAR = new Tag("VAR");
    public static final Tag CALCULATE = new Tag("CALCULATE");
    public static final Tag LOAD = new Tag("LOAD");
    public static final Tag MOUSE = new Tag("MOUSE");
    public static final Tag NOW = new Tag("NOW");
    public static final Tag STOP = new Tag("STOP");
    public static final Tag BREAK = new Tag("BREAK");
    public static final Tag DESCRIP = new Tag("::DESCRIP");
    public static final Tag LOG = new Tag("LOG");
    public static final Tag COMMANDS = new Tag("COMMANDS");
    public static final Tag SCREEN = new Tag("SCREEN");
}