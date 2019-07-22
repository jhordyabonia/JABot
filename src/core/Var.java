package core;

public class Var{
    public String name="";
    public VAR_TYPE type;
    public String value="";
    public enum VAR_TYPE{number,bool,string,command};
    public Var(String n,VAR_TYPE t,String v){
        name=n;
        type=t;
        value=v;
    }
    public Var(VAR_TYPE t,String v){
        type=t;
        value=v;
    }
    public Var(String n,VAR_TYPE t){
        name=n;
        type=t;
        switch(t){
            case bool:
                value = "false" ;
                break;
            case number:
                value = "0";
                break;
        }
    }   
    public static Var calcula(Var a, Var b,char op){
       
        String _name=a.name+op+b.name,_value=""+a.value.split(" ").length,
               aa = a.value.split(" ")[0], bb= b.value.split(" ")[0];
       
        if(op == '#'){
            return new Var(_name,VAR_TYPE.number,_value);
        }
        int index = getIndex(op);
        if(index != -1){
            String tmp[]=a.value.split(" ");
            b.value = tmp[index];
            return b;
        }else switch(a.type){
            case number:
                VAR_TYPE _type = a.type;
                if(op=='-') _value = ""+ (Double.parseDouble(aa) - Double.parseDouble(bb));
                else if(op=='+')_value = ""+ (Double.parseDouble(aa) + Double.parseDouble(bb));
                else if(op=='*')_value = ""+ (Double.parseDouble(aa) * Double.parseDouble(bb));
                else if(op=='/')_value = ""+ (Double.parseDouble(aa) / Double.parseDouble(bb));
                else if(op=='^')_value = ""+ Math.sqrt(Double.parseDouble(aa));
                else {
                    _type = VAR_TYPE.bool;
                    if(op=='=')
                      _value = (Double.parseDouble(aa) == Double.parseDouble(bb))?"true":"false";
                    else if(op=='>')
                      _value = (Double.parseDouble(aa) > Double.parseDouble(bb))?"true":"false";
                    else if(op=='<')
                      _value = (Double.parseDouble(aa) < Double.parseDouble(bb))?"true":"false";                 
                }
                return new Var(_name,_type,_value);
            case bool:
                if(op=='!') _value =  (!aa.equals("true"))?"true":"false";
                else if(op=='|') _value = aa.toUpperCase().equals(bb.toUpperCase()) || aa.equals("true")?aa:"false";
                else  _value = aa.toUpperCase().equals(bb.toUpperCase()) && aa.equals("true")?aa:"false";
                return new Var(_name,a.type,_value);
            case string:
                if(op=='+') {                   
                    _value = a.value + b.value;
                }else if(op=='-'){
                     String data[]=a.value.split(" ");
                     index = Integer.parseInt(b.value);
                     data[index] = "";
                     _value = String.join(" ",data).trim();
                }else{
                    _type = VAR_TYPE.bool;
                    if(op=='='){
                        _value = a.value.equals(b.value)?"true":"false";
                    }else if(op=='>'){
                        _value = a.value.startsWith(b.value)?"true":"false";
                    }else if(op=='<'){
                        _value = a.value.endsWith(b.value)?"true":"false";
                    }                    
                }
                return new Var(_name,a.type,_value);
            case command:
                _value = "false";
                if(op=='?') {
                    try{
                        String option[]=a.value.split(" ");
                        if(option.length>0){
                            (new Bot()).Do(option[0],option.length>1?option[1]:null);
                             _value = "true";
                        }
                    }catch(Exception e){}
                }
                return new Var(_name,VAR_TYPE.bool,_value);
                
        }
        return a;
    }
    private static int getIndex(char op){
        try{             
             return Integer.parseInt(""+op);
        }catch(NumberFormatException e){
            switch(op){
                    case 'a': return 10;
                    case 'b': return 11;
                    case 'c': return 12;
                    case 'd': return 13;
                    case 'e': return 14;
                    case 'f': return 15;
                    case 'g': return 16;
                    case 'i': return 17;
                    case 'h': return 18;
                    case 'k': return 19;
                    case 'l': return 20;
                    case 'm': return 21;
                    case 'n': return 22;
                    case 'p': return 23;
                    case 'q': return 24;
                    case 'r': return 24;
                    case 's': return 25;
                    case 't': return 26;
                    case 'v': return 27;
                    case 'u': return 28;
                    case 'w': return 29;
                    case 'x': return 30;
                    case 'y': return 31;
                    case 'z': return 32;
                }
        }
        return -1;
    }
    public static void main(String []args){
        Var x = new Var("X", VAR_TYPE.number,"1"),
        y = new Var("Y",VAR_TYPE.number,"3"),
        b = new Var("B",VAR_TYPE.bool,"false"),
        a = new Var("A",VAR_TYPE.bool,"true");

        Var z=calcula(x,y,'+');
        Var c= calcula(a, y,'-');
        Var d= calcula(a, y,'+');
        Var e= calcula(a, b,'|');
        Var f= calcula(x, y,'/');
        Var g= calcula(x, y,'*');
        System.out.println(c.name+" "+c.value);
        System.out.println(z.name+" "+z.value);
        System.out.println(d.name+" "+d.value);
        System.out.println(e.name+" "+e.value);
        System.out.println(f.name+" "+f.value);
        System.out.println(g.name+" "+g.value);
    }
}