import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GeometricShape implements GeometricShapeInterface {

    private ArrayList <Point> listaPunktow;
    private ArrayList < ArrayList<Point> > historiaZmian;
    private ArrayList <Point> ostatniaZmiana;
    private int biezacaZmiana,doRedo;

    public GeometricShape() {
        ArrayList <Point> pierwszaZmiana;
        pierwszaZmiana = new ArrayList <Point>();
        listaPunktow = new ArrayList <Point>();
        historiaZmian = new ArrayList < ArrayList<Point> >();
        ostatniaZmiana =new ArrayList <Point>();
        historiaZmian.add(0,pierwszaZmiana);
        biezacaZmiana =0;
        doRedo=0;
    }


    /**
     * Metoda dodaje punkt. Punkt dodawany jest na koniec kolekcji.
     *
     * @param point dodawany punkt
     */
    public void add(Point point){

        if(point!=null) {


            listaPunktow.add(point);
            doRedo=0;
            ArrayList <Point> dodaj =new ArrayList <Point>();
            biezacaZmiana++;
            dodaj.addAll(listaPunktow);
            historiaZmian.add(biezacaZmiana, dodaj);
        }
    }

    /**
     * Metoda usuwa punkt, o ile taki istnieje. Jeśli w kolekcji punktów jest więcej
     * takich samych jak point, usuwany jest tylko pierwszy z nich.
     *
     * @param point punkt do usunięcia
     * @return true - punkt istniał w kolekcji i został usunięty, false - takiego
     *         punktu nie było i w związku z tym nie został usunięty.
     */
    public boolean remove(Point point){

        if(point ==null) return false;

        int x= listaPunktow.indexOf(point);

        if(x==-1) return false;

        listaPunktow.remove(x);
        doRedo=0;
        ArrayList <Point> dodaj =new ArrayList <Point>();
        biezacaZmiana++;
        dodaj.addAll(listaPunktow);
        historiaZmian.add(biezacaZmiana, dodaj);


        return true;

    }

    /**
     * Metoda dodaje punkt przed punktem beforePoint.
     *
     * @param point       dodawany punkt
     * @param beforePoint punkt, bezpośrednio przed którym nowy należy dodać
     * @return true - punkt odniesienia istniał i dodano nowy punkt prawidłowo,
     *         false - wskazanego punktu odniesienia nie było, dodanie nowego punktu
     *         nie było możliwe.
     */
    public boolean addBefore(Point point, Point beforePoint){

        if(point==null || beforePoint==null) return false;

        int x=listaPunktow.indexOf(beforePoint);

        if(x==-1) return false;

        listaPunktow.add(x,point);
        doRedo=0;
        ArrayList <Point> dodaj =new ArrayList <Point>();
        biezacaZmiana++;
        dodaj.addAll(listaPunktow);
        historiaZmian.add(biezacaZmiana, dodaj);


        return true;

    }

    /**
     * Metoda dodaje punkt za punktem afterPoint.
     *
     * @param point      dodawany punkt
     * @param afterPoint punkt, bezpośrednio za którym nowy należy dodać
     * @return true - punkt odniesienia istniał i dodano nowy punkt prawidłowo,
     *         false - wskazanego punktu odniesienia nie było, dodanie nowego punktu
     *         nie było możliwe.
     */
    public boolean addAfter(Point point, Point afterPoint){

        if(point ==null || afterPoint==null) return false;

        int x=listaPunktow.lastIndexOf(afterPoint);

        if(x==-1) return false;

        listaPunktow.add(x+1,point);
        doRedo=0;

        ArrayList <Point> dodaj =new ArrayList <Point>();
        biezacaZmiana++;
        dodaj.addAll(listaPunktow);
        historiaZmian.add(biezacaZmiana, dodaj);


        return true;

    }

    /**
     * Metoda usuwa punkt przed punktem beforePoint.
     *
     * @param beforePoint punkt istniejący bezpośrednio przed beforePoint należy
     *                    usunąć.
     * @return Gdy punkt odniesienia istniał oraz istniał punkt do usunięcia,
     *         zwracana jest referencja do usuniętego punktu. Gdy punkt odniesienia
     *         lub punkt przed nim nie istniał zwracany jest NULL.
     */
    public Point removeBefore(Point beforePoint){

        if(beforePoint==null) return null;

        int x=listaPunktow.indexOf(beforePoint);

        if(x==-1) return null;

        if(x==0) return null;

        Point punkt;
        punkt= listaPunktow.remove(x-1);

        doRedo=0;

        ArrayList <Point> dodaj =new ArrayList <Point>();
        biezacaZmiana++;
        dodaj.addAll(listaPunktow);
        historiaZmian.add(biezacaZmiana, dodaj);

        return punkt;


    }

    /**
     * Metoda usuwa punkt za punktem afterPoint.
     *
     * @param afterPoint punkt istniejący bezpośrednio po afterPoint należy usunąć.
     * @return Gdy punkt odniesienia istniał oraz istniał punkt do usunięcia,
     *         zwracana jest referencja do usuniętego punktu. Gdy punkt odniesienia
     *         lub punkt za nim nie istniał zwracany jest NULL.
     */
    public Point removeAfter(Point afterPoint){


        if(afterPoint==null) return null;

        int x=listaPunktow.lastIndexOf(afterPoint);

        if(x==-1) return null;

        if(x==(listaPunktow.size()-1)) return null;

        Point punkt;

        punkt=listaPunktow.remove(x+1);

        doRedo=0;

        ArrayList <Point> dodaj =new ArrayList <Point>();
        biezacaZmiana++;
        dodaj.addAll(listaPunktow);
        historiaZmian.add(biezacaZmiana, dodaj);

        return punkt;


    }

    /**
     * Metoda usuwa skutek ostatniego polecenia add, remove, addAfter, addBefore,
     * removeAfter lub removeBefore. Poprzez wielokrotne wykonywanie metody undo()
     * możliwe jest usunięcie wcześniejszych poleceń. Usunięciu podlegają wyłącznie
     * polecenia poprawnie zrealizowane, tych, które nie zmieniły stanu listy
     * punktów nie należy zapamietywać i uwzględniać w działaniu undo.
     *
     * @return true - polecenie undo przywróciło poprzedni stan obiektu. false -
     *         brak poleceń do usunięcia, undo nie zmieniło stanu obiektu.
     */
    public boolean undo(){

        if(biezacaZmiana<=0) return false;
        ostatniaZmiana.clear();
        ostatniaZmiana.addAll(listaPunktow);

        int x=historiaZmian.size();
        historiaZmian.remove(x-1);
        biezacaZmiana--;

        doRedo=1;

        listaPunktow=historiaZmian.get(biezacaZmiana);


         return true;
    }

    /**
     * Metoda przywraca stan systemu sprzed wykonania polecenia undo, które
     * bezpośrednio poprzedza wykonanie redo (uwaga: operacje odczytu stanu obiektu
     * mogą pojawić się pomiędzy undo a redo, podobnie jak inne operacje, które nie
     * zmienią stanu obiektu czyli np. błęde zlecnie addBefore)
     *
     * @return true - poprawnie przywrócono zmianę, którą usunięto wcześnie za
     *         pomocą undo, false - bezpośrednio przed redo nie było undo i
     *         polecenie nie mogło zadziałać prawidłowo.
     */
    public boolean redo(){

        if(doRedo==1){

            biezacaZmiana++;


            ArrayList <Point> dodaj =new ArrayList <Point>();

            dodaj.addAll(ostatniaZmiana);

            historiaZmian.add(biezacaZmiana, dodaj);

            listaPunktow=historiaZmian.get(biezacaZmiana);

            doRedo=0;

            return true;
        }

        return false;
    }

    /**
     * Metoda zwraca aktualną listę wszystkich punktów.
     *
     * @return lista punktów
     */
    public List<Point> get(){


        return listaPunktow;

    }

    /**
     * Metoda zwraca listę punktów bez kolejnych powtórzeń. Kolejność punktów w tej
     * kolekcji ma być taka sama, jak w przypadku list zwracanej za pomocą metody
     * get jednakże bez powtórzeń. Jeśli kolejność punktów zwracana za pomocą get to
     * np. P1, P2, P2, P3, P2, P1, P1, P1, P4 to metoda getUniq ma zwrócić: P1, P2,
     * P3, P2, P1 i P4, czyli usuwane są wszystkie kolejne wystąpienia danego
     * punktu. Powtórzenie punktu ma miejsce wtedy, gdy P1.equals(P2)=true. Uwaga:
     * metoda zwraca listę (nie zbiór) punktów, wciąż jeden punkt może wystąpić
     * wielokrotnie w wyniku.
     *
     * @return kolekcja punktów bez powtórzeń.
     */
    public List<Point> getUniq(){
        if(listaPunktow.size()==0) return null;
        ArrayList <Point> unikalna =new ArrayList <Point>();
        int counter =0;
        unikalna.add(0,listaPunktow.get(0));

       for(int i=1;i<listaPunktow.size();i++){

           if((unikalna.get(counter).equals(listaPunktow.get(i)))==false){
               unikalna.add(listaPunktow.get(i));
               counter++;
           }

       }
        return unikalna;
    }

    /**
     * Metoda zwraca mapę liczby występień punktów. Jako klucz w mapie występuje
     * punkt, jako wartość liczba wystąpień tego punktu w budowanym kształcie.
     *
     * @return mapa liczby wystąpien punktów.
     */
    public Map<Point, Integer> getPointsAsMap(){
        if(listaPunktow.size()==0) return null;
        int counter=0;
        Map <Point,Integer> wystapienia = new HashMap<Point,Integer>();
        Point x =listaPunktow.get(0);
        wystapienia.put(x,1);

        for(int i=1;i<listaPunktow.size();i++){


            if(wystapienia.containsKey(listaPunktow.get(i))){

                counter=wystapienia.get(listaPunktow.get(i));
                counter++;
                wystapienia.replace(listaPunktow.get(i),counter);

            }
            else{
                wystapienia.put(listaPunktow.get(i),1);
            }

        }



      return wystapienia;


    }


}
