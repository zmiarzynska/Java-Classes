import java.util.*;


class GeometricShape implements GeometricShapeInterface {

    private ArrayList <czasDodania> listaPunktow;
    private int timer;
    private int prawidlowaLWymiarow;

    public GeometricShape() {

        listaPunktow = new ArrayList <czasDodania>();
        timer=0;
    }

    public int getNumberofDimensiona(Point punkt){
        int i=0;
                while(true){

                    try{
                        punkt.getPosition(i);
                    }
                    catch(ArrayIndexOutOfBoundsException e ){
                        return i;
            }
            i++;
        }
    }


    public void add(Point point) throws WrongNumberOfDimensionsException{

        if(point!=null) {

            if(listaPunktow.size()==0)   {
                czasDodania dodaj= new czasDodania(point,timer);
                listaPunktow.add(dodaj);
                prawidlowaLWymiarow=getNumberofDimensiona(point);
                timer++;
       }
       else{
          if(prawidlowaLWymiarow==getNumberofDimensiona(point)){

              czasDodania dodaj=new czasDodania(point,timer);
              timer++;
              listaPunktow.add(dodaj);

           }
           else{ throw new WrongNumberOfDimensionsException(prawidlowaLWymiarow,getNumberofDimensiona(point));}
          }


      }
    }


    public void remove(Point point) throws WrongArgumentException{

        if(point ==null) throw new WrongArgumentException(point);
        int x=-1;

        for(int i=0;i<listaPunktow.size();i++){
          if(listaPunktow.get(i).getPunkt().equals(point)) {
              x=i;
              break;
          }
        }

        if(x==-1) throw new WrongArgumentException(point);
        listaPunktow.remove(x);


    }


    public void addBefore(Point point, Point beforePoint)
            throws WrongArgumentException, WrongNumberOfDimensionsException{
        if(point!=null) {
            if(prawidlowaLWymiarow!=getNumberofDimensiona(point)) throw new WrongNumberOfDimensionsException(prawidlowaLWymiarow,getNumberofDimensiona(point));
            if(prawidlowaLWymiarow!=getNumberofDimensiona(beforePoint)) throw new WrongNumberOfDimensionsException(prawidlowaLWymiarow,getNumberofDimensiona(beforePoint));


            if(beforePoint==null) throw new WrongArgumentException(beforePoint);
            int x=-1;
            for(int i=0;i<listaPunktow.size();i++){
                if(listaPunktow.get(i).getPunkt().equals(beforePoint)) {
                    x=i;
                    break;
                }
            }

            if(x==-1) throw new WrongArgumentException(beforePoint);


            czasDodania dodaj=new czasDodania(point,timer);
            timer++;
            listaPunktow.add(x,dodaj);


        }




    }


    public void addAfter(Point point, Point afterPoint)
            throws WrongNumberOfDimensionsException, WrongArgumentException{
        if(point !=null) {
            if(afterPoint==null) throw new WrongArgumentException(afterPoint);
            if(prawidlowaLWymiarow!=getNumberofDimensiona(point)) throw new WrongNumberOfDimensionsException(prawidlowaLWymiarow,getNumberofDimensiona(point));
            if(prawidlowaLWymiarow!=getNumberofDimensiona(afterPoint)) throw new WrongNumberOfDimensionsException(prawidlowaLWymiarow,getNumberofDimensiona(afterPoint));


            int x=-1;

            for(int i=0;i<listaPunktow.size();i++){
                if(listaPunktow.get(i).getPunkt().equals(afterPoint)) {
                    x=i;
                }
            }

            if(x==-1) throw new WrongArgumentException(afterPoint);


            czasDodania dodaj=new czasDodania(point,timer);
            timer++;
            listaPunktow.add(x+1,dodaj);

        }



    }


    public Point removeBefore(Point beforePoint)
            throws NoSuchPointException, WrongNumberOfDimensionsException, WrongArgumentException{

        if(beforePoint!=null){
            if(prawidlowaLWymiarow!=getNumberofDimensiona(beforePoint)) throw new WrongNumberOfDimensionsException(prawidlowaLWymiarow,getNumberofDimensiona(beforePoint));

            int x=-1;

            for(int i=0;i<listaPunktow.size();i++){
                if(listaPunktow.get(i).getPunkt().equals(beforePoint)) {
                    x=i;
                    break;
                }
            }

            if(x==0)  throw new NoSuchPointException(beforePoint);

            if(x==-1) throw new WrongArgumentException(beforePoint);


            Point punkt;
            czasDodania stary;
            stary= listaPunktow.remove(x-1);

            punkt=stary.getPunkt();

            return punkt;

        } else return null;




    }



    public Point removeAfter(Point afterPoint)
            throws NoSuchPointException, WrongNumberOfDimensionsException, WrongArgumentException{


        if(afterPoint==null) return null;
        if(prawidlowaLWymiarow!=getNumberofDimensiona(afterPoint)) throw new WrongNumberOfDimensionsException(prawidlowaLWymiarow,getNumberofDimensiona(afterPoint));

        int x=-1;

        for(int i=0;i<listaPunktow.size();i++){
            if(listaPunktow.get(i).getPunkt().equals(afterPoint)) {
                x=i;
            }
        }

        if(x==(listaPunktow.size()-1)) throw new NoSuchPointException(afterPoint);

        if(x==-1) throw new WrongArgumentException(afterPoint);



        Point punkt;
        czasDodania stary;
        stary =listaPunktow.remove(x+1);
        punkt=stary.getPunkt();

        return punkt;


    }



    public List<Point> get(){
        if(listaPunktow.size()==0) return null;
        List <Point> tylkoPunkty = new ArrayList<>();

        for(int i=0;i<listaPunktow.size();i++){
            tylkoPunkty.add(listaPunktow.get(i).getPunkt());
        }

        return tylkoPunkty;

    }



    public Optional<Point> getByPosition(List<Integer> positions) throws WrongNumberOfDimensionsException{
        if(listaPunktow.size()==0) return null;
        if(positions==null) return null;
        if(prawidlowaLWymiarow!=positions.size()) throw new WrongNumberOfDimensionsException(prawidlowaLWymiarow,positions.size());
        int counter;
        czasDodania doOpcjonal=null;
        int czas=-1;
        Point punktLast=null;
        for(int j=0;j<listaPunktow.size();j++){
            counter =0;
            for(int i=0; i<positions.size();i++){

                 if(listaPunktow.get(j).getPunkt().getPosition(i)==(positions.get(i))) counter++;
            }
            if(counter==positions.size()){
                doOpcjonal=listaPunktow.get(j);
                if(doOpcjonal.getCzas()>=czas){
                    punktLast=doOpcjonal.getPunkt();
                    czas=doOpcjonal.getCzas();
                }
            }

        }

        if(punktLast!=null)return Optional.of(punktLast);
        else return Optional.empty();



    }


    public Set<Point> getSetOfPoints(){
        if(listaPunktow.size()==0) return null;

        Set <Point> unikalna =new LinkedHashSet <Point>();



        for(int i=0;i<listaPunktow.size();i++){

                unikalna.add(listaPunktow.get(i).getPunkt());

        }

        return unikalna;
    }


}


class czasDodania {


    private Point punkt;
    private int czas;

    czasDodania(Point punkt, int czas){
        setPunkt(punkt);
        setCzas(czas);
    }

    public void setCzas(int czas){
        this.czas=czas;
    }

    public int getCzas(){
        return czas;
    }

    public void setPunkt(Point punkt){
        this.punkt=punkt;
    }
    public Point getPunkt(){
        return punkt;
    }



}

