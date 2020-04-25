import java.util.HashMap;
import java.util.Iterator;

public class Professions {
    protected Iterator iterator;
    protected HashMap<String,Float> professions;
    protected String nameJob;

    public void getProfessions() {
        for(String key: professions.keySet()){
            System.out.print(key);
            System.out.print(" - ");
            System.out.println(professions.get(key));
        }

    }
    
    public HashMap<String,Float> getMap(){
    	return professions;
    }
    
    public float getSalaryOf(String key) {
    	return professions.get(key);
    }

}
