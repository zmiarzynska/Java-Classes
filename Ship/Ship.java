import java.util.Vector;


public class Ship {
    private boolean shipwreck;
    private int size;
    private static ShipSizeLimit limit;
    private static int counter[];
    private static Vector<Ship> ifdrown=new Vector <Ship>();



    private Ship(int size ) {
        this.size=size;
        this.shipwreck=false;

        //System.out.println( "To ja klasa Konstruktor domyslny tworze statki, tylko ja moge" );
    }



    public static void setLimit(ShipSizeLimit limit) {

        Ship.limit = limit;
        Ship.counter = new int[limit.getNumberOfSizes()];
        for (int i = 0; i < limit.getNumberOfSizes(); ++i) counter[i] = limit.getLimit(i + 1);
    }



    public static Ship getShip(int size) {

         if(Ship.limit==null) return null;

         if(size<=0) return null;
         if(size>limit.getNumberOfSizes()) return null;  //gdy nie ma tak duzych okretow

         //warunek wrakow
        for(int i=0;i<ifdrown.size();i++){

            if((ifdrown.elementAt(i).size==size) &&(ifdrown.elementAt(i).shipwreck)){
                int a=counter[size-1];
                a=a+1;
                counter[size-1]=a; //ale chyba moze byc ++
                ifdrown.remove(i);
                i--;
            }

        }


        if(counter[size-1]>0){
            Ship s = new Ship(size);
            int a=counter[size-1];      //--
            a=a-1;
            counter[size-1]=a;
            ifdrown.addElement(s); //zapamietuje statek w wektorze
            return s;
        }


         return null;
        }


    public void shipwreck() {
        shipwreck = true;
        //System.out.println( "Jestem wrakiem" );
    }


    public boolean isShipwreck() {
        return shipwreck;
    }



}

