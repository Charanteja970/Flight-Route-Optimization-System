public class Airport{
    private final String code;
    private final String name;
    public Airport(String code,String name){
        this.code=code;
        this.name=name;
    }

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public String getString(){
        return code+" ("+name+")";
    }
}