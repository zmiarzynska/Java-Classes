import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Stos <T>{

    private T arr[];
    private int top;
    private int capacity;

    Stos(int size)
    {
        arr = (T[])new Object[size];
        capacity = size;
        top = -1;
    }

    public void push(T x)
    {
        if (isFull())
        {
            System.out.println("Nie mozna dodac wiecej elementow do stosu.\n");
            System.exit(1);
        }


        arr[++top] = x;
    }

    public T pop()
    {

        if (isEmpty())
        {
            System.out.println("Nie ma wiecej elementow na stosie.");
            System.exit(1);
        }

        T pop=  peek();

        // decrease stack size by 1 and (optionally) return the popped element
        return arr[top--];
    }

    public T peek()
    {
        if (!isEmpty())
            return arr[top];
        else
            System.exit(1);

        return null;
    }

    public int size()
    {
        return top + 1;
    }


    public Boolean isEmpty()
    {
        return top == -1;
    }


    public Boolean isFull()
    {
        return top == capacity - 1;
    }

    public Boolean contains(T x){

        for(int i=0;i<capacity;++i){
            T  temp=arr[i];
            if(temp==x) return true;
        }

        return false;



    }


}

class CzytanieZPliku{
    FileReader fr = null;
    String linia = "";
    int i,j;
    int w1,w2;
    Graf graf;


    public void czytajPlik(Graf g) {
        this.graf=g;
        try {
            fr = new FileReader("dane.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Blad przy otwieraniu pliku.");
            System.exit(1);
        }

        BufferedReader bfr = new BufferedReader(fr);
        // ODCZYT KOLEJNYCH LINII Z PLIKU
        try {
            while((linia = bfr.readLine()) != null){

                if(graf.counter==0){
                    graf.iloscKrawedziWGrafie=Integer.parseInt(linia);
                    graf.counter++;
                    System.out.println(linia);

                }
                else{
                    System.out.println( linia);
                    graf.stosKrawedzi.push(linia);
                    i=linia.charAt(1)-48;
                    j=linia.charAt(3)-48;
                    w1=i;
                    w2=j;

                    if(graf.tablicaWierzcholkow[i-1]==0){
                        graf.tablicaWierzcholkow[i-1] = w1;
                        graf.iloscWierzcholkow++;

                    }

                    if(graf.tablicaWierzcholkow[j-1]==0){
                        graf.tablicaWierzcholkow[j-1] = w2;
                        graf.iloscWierzcholkow++;

                    }


                }
            }
        } catch (IOException e) {
            System.out.println("Blad przy odczytaniu pliku.");
            System.exit(2);
        }

        // ZAMYKANIE PLIKU
        try {
            fr.close();
        } catch (IOException e) {
            System.out.println("Blad przy zamykaniu pliku.");
            System.exit(3);
        }
    }


}

class Graf{

     MacierzSasiedztwa m;
     ListaSasiedztwa l;
     volatile int counter = 0;
     int iloscKrawedziWGrafie = 0;
     int iloscWierzcholkow = 0;
     int[] tablicaWierzcholkow = new int[10];
     Stos stosKrawedzi = new Stos(10);
     String linia = "";
     int i, j;




     void tworzMacierzListeSasiedztwa(){

        m=new MacierzSasiedztwa(iloscWierzcholkow);
        l=new ListaSasiedztwa(iloscWierzcholkow);

        while(!stosKrawedzi.isEmpty()){
            linia=(String)stosKrawedzi.pop();
            i=linia.charAt(1)-48;
            j=linia.charAt(3)-48;
            m.addSasiada(i,j);
            l.addKrawedz(i,j);

        }


         System.out.println("Drukuje macierz: ");
        for(int p=0;p<m.macierz[0].length;p++){
            for(int s=0;s<m.macierz.length;s++){
                System.out.print(m.macierz[p][s]);
            }
            System.out.println("");
        }


    }




}


class Obliczenia extends Graf {

    int iloscWierzcholkow;
    boolean[] color ;
    boolean[] visited ;
    int topologicSort[];

Obliczenia(int number){
    this.iloscWierzcholkow=number;
    color = new boolean[number+1];
     visited = new boolean[number+1];
     topologicSort=new int [number+1];

}


    public void setUnvisited(){
        for(int i=0;i<=iloscWierzcholkow;i++){
            color[i]=false;
            visited[i]=false;

        }
    }

    public boolean dwudzielnosc(ListaSasiedztwa l) {
        this.l = l;

        visited[0] = true;
        color[0] = true;
        return check(0);


    }

    boolean check(int v) {
        int y;
        for (int i = 0; i < l.tablicaWierzch.length; i++) {
            if (l.tablicaWierzch[v][i] != 0) {
                y = l.tablicaWierzch[v][i];
                if (visited[y] == false) {
                    visited[y] = true;
                    color[y] = !color[v];
                    if (!check(y)) return false;
                } else if (color[y] == color[v]) return false;
            }
        }
        return true;

    }

    public int sortowanieTopologiczne(int source,MacierzSasiedztwa m){


        boolean[] visited = new boolean[10];
        Stos dfsStos = new Stos(10);
        int pos = 1;
        int j;
        int element=source;

        int i = source;

        visited [source-1]=true;
        dfsStos.push(source);
        while (!dfsStos.isEmpty())
        {

            element= (int)dfsStos.peek();

            while (i <= iloscWierzcholkow )
            {

                if (m.macierz[element-1][i-1] == 1 && visited[i-1])
                {
                    if (dfsStos.contains(i))
                    {
                        System.out.println("Sortowanie topologicznie niemozliwe");
                        return -1;
                    }
                }
                if (m.macierz[element-1][i-1] == 1 && !visited[i-1])
                {
                    dfsStos.push(i);
                    visited[i-1]=true;
                    element = i;
                    i = 1;
                    continue;
                }
                i++;
            }
            j =(int)dfsStos.pop();
            topologicSort[pos++] = j;

            i = ++j;
        }

        return 1;
    }


    void printSort(){
        System.out.println("Sortowanie Topologiczne:");
        for(int x=topologicSort.length-1;x>0;x--){

            System.out.print(topologicSort[x]);
        }
    }

}

class MacierzSasiedztwa  {

    int size;
    Integer[][] macierz;
    MacierzSasiedztwa(int size){
        this.size=size;
        macierz=new Integer[size][size];
        for(int i=0;i<macierz[0].length;i++){
            for(int j=0;j<macierz.length;j++)
                macierz[i][j]=0;
        }
    }


    public void addSasiada(int x,int y){

        macierz[x-1][y-1]=1;

    }


}


class ListaSasiedztwa{
 int size;

    ListaSasiedztwa(int size){
        this.size=size+1;
        tablicaWierzch=new int[size+1][size+1];
    }

    private int position=0;
    public int [][] tablicaWierzch;
    /// na pozcjach [i][0] jest wierzcholek wyjscia na reszcie wejscia
    void addWierzcholek(int w){
        tablicaWierzch[position][0]=w;

        position++;
    }


    public   int addKrawedz(int w,int krawedz){

        for(int i=0;i<tablicaWierzch.length;++i){

            if(tablicaWierzch[i][0]==w){
                for(int j=0;j<tablicaWierzch[i].length;j++){
                    if(tablicaWierzch[i][j]==0){
                        tablicaWierzch[i][j]=krawedz;

                        return 1;
                    }
                }
            }
            else if(tablicaWierzch[i][0]==0){
                addWierzcholek(w);
                tablicaWierzch[i][1]=krawedz;
                return 0;
            }
        }
        return -1;
    }

    void printListe(){

        System.out.println("Drukuje liste: ");
        for(int i=0;i<tablicaWierzch.length;i++){
            for(int j=0;j<tablicaWierzch[i].length;j++){
                System.out.print(tablicaWierzch[i][j]);
            }
            System.out.println("");
        }
    }

}



class Start {





    public static void main(String args[]){


        CzytanieZPliku plik=new CzytanieZPliku();
        Graf g=new Graf();
        plik.czytajPlik(g);


        plik.graf.tworzMacierzListeSasiedztwa();
        plik.graf.l.printListe();
        Obliczenia noweObliczenia=new Obliczenia(plik.graf.iloscWierzcholkow);

        if(noweObliczenia.dwudzielnosc(plik.graf.l)) System.out.println("Graf jest dwudzielny");
        else System.out.println("Graf nie jest dwudzielny");
        noweObliczenia.setUnvisited();
        noweObliczenia.sortowanieTopologiczne(1,plik.graf.m);
        noweObliczenia.printSort();

    }




}