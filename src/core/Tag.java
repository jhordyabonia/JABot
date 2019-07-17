package core;
public class Tag{
    private Tag(String name){
        tag = name.toLowerCase();
    }
    String tag = "";
    String args = "";
    String catched = "";
    boolean in_catch = false;
    boolean startsWith(String in){
        return in.toLowerCase().startsWith("["+tag);
    }
    boolean contains(String in){
        return in.toLowerCase().contains(tag);    
    }
    boolean ends(String in){        
        return in.toLowerCase().contains("/"+tag);
    }
    public static final Tag MAKE = new Tag("MAKE");
    public static final Tag REPEAT = new Tag("REPEAT");
    public static final Tag IF = new Tag("IF");
    public static final Tag ELSE = new Tag("ELSE");
    public static final Tag WAIT = new Tag("WAIT");
    public static final Tag VAR = new Tag("VAR");
    public static final Tag CALCULATE = new Tag("CALCULATE");
    public static final Tag LOAD = new Tag("LOAD");
    public static final Tag MOUSE = new Tag("MOUSE");
    public static final Tag NOW = new Tag("NOW");
    public static final Tag STOP = new Tag("STOP");
    public static final Tag DESCRIP = new Tag("::DESCRIP");
}