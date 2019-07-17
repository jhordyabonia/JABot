package core;

public class Var{
    public String name="";
    public VAR_TYPE type;
    public String value="";
    public enum VAR_TYPE{number,bool,string,compose};
    public Var(String n,VAR_TYPE t,String v){
        name=n;
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
    /*public Var opera(char o,Var in)throws Exception{
        String _name=name+" "+o+" "+in.name,_value;
        VAR_TYPE _type;
        switch(o){
            case '>':
                if(type!=VAR_TYPE.number){
                    throw new Exception("Formats no compatibles");
                }
                _type=VAR_TYPE.bool;
                _value = ""+(Double.parseDouble(value) > Double.parseDouble(in.value));
                break;
            case '0': //'>=':                    
                if(type!=VAR_TYPE.number){
                    throw new Exception("Formats no compatibles");
                }
                _type=VAR_TYPE.bool;
                _value = ""+(Double.parseDouble(value) >= Double.parseDouble(in.value));
                break;
            case '<':                        
                if(type!=VAR_TYPE.number){
                    throw new Exception("Formats no compatibles");
                }
                _type=VAR_TYPE.bool;
                _value = ""+(Double.parseDouble(value) < Double.parseDouble(in.value));
                break;
            case '.'://'<=':                                   
                if(type!=VAR_TYPE.number){
                    throw new Exception("Formats no compatibles");
                }
                _type=VAR_TYPE.bool;
                _value = ""+(Double.parseDouble(value) <= Double.parseDouble(in.value));                        
                break;
            case '=':                                   
                if(type!=VAR_TYPE.number){
                    throw new Exception("Formats no compatibles");
                }
                _type=VAR_TYPE.bool;
                _value = ""+(Double.parseDouble(value) == Double.parseDouble(in.value));                        
                break;
            case '-':
                break;
            case '+':
                return null;//suma(in);
            case '*':
                break;
            case '/':
                break;                
        }
        return this;
    }    */
    public static Var calcula(Var a, Var b,char op){
       
        String _name=a.name+op+b.name,_value="";
        switch(a.type){
            case number:
                VAR_TYPE _type = a.type;
                if(op=='-') _value = ""+ (Double.parseDouble(a.value) - Double.parseDouble(b.value));
                else if(op=='+')_value = ""+ (Double.parseDouble(a.value) + Double.parseDouble(b.value));
                else if(op=='*')_value = ""+ (Double.parseDouble(a.value) * Double.parseDouble(b.value));
                else if(op=='/')_value = ""+ (Double.parseDouble(a.value) / Double.parseDouble(b.value));
                else {
                    _type = VAR_TYPE.bool;
                    if(op=='=')
                      _value = (Double.parseDouble(a.value) == Double.parseDouble(b.value))?"true":"false";
                    else if(op=='>')
                      _value = (Double.parseDouble(a.value) > Double.parseDouble(b.value))?"true":"false";
                    else if(op=='<')
                      _value = (Double.parseDouble(a.value) < Double.parseDouble(b.value))?"true":"false";                 
                }
                return new Var(_name,_type,_value);
            case bool:
                if(op=='!') _value =  (!a.value.equals("true"))?"true":"false";
                else if(op=='|') _value = a.value.toUpperCase().equals(b.value.toUpperCase()) || a.value.equals("true")?a.value:"false";
                else  _value = a.value.toUpperCase().equals(b.value.toUpperCase()) && a.value.equals("true")?a.value:"false";
                return new Var(_name,a.type,_value);
            case string:
               // if(op=='+')                    
                    _value = a.value + b.value;
                //else if(op=='-')
                //    _value = a.value + b.value;
                return new Var(_name,a.type,_value);
            case compose:
                break;
        }
        return a;
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