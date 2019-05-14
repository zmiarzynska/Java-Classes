import java.util.Vector;


public class BetterPoint extends AbstractBetterPoint {
   private double NumberofDimension[]; // tablica wartosci dla poszczegolnych wymiarow

   private  Vector<String> hasla =new Vector <String>();
   private int counter=0;
   public BetterPoint(){

   }

    public void setDimensions(int dimensions){

        NumberofDimension=new double[dimensions];
    }


    public  int lockLevel(){


      return this.counter;
    }

    public int lock(String password){

       if(password==null) return counter;
        hasla.addElement(password);
        counter++;

         return counter;

    }


    public int unlock(String password){
      if(password==null) return counter;
      if(counter==0) return counter;
       if(hasla.elementAt(counter-1)==password){

        hasla.remove(counter-1);
           counter--;
       }
      return counter;

    }



    public boolean move(int dimension, double delta){

         if(counter!=0) return false;
        if(delta ==0) return false;
        NumberofDimension[dimension]+=delta;

        return true;
    }

    public boolean set(int dimension, double value){

        if(counter!=0) return false;

        NumberofDimension[dimension]=value;

        return true;
    }



    public double get(int dimension){

        return NumberofDimension[dimension];


    }




}
