class MinKolejkaPriorytetowa<K extends Comparable<K>> {

    private K arr[];
    private int sizeNumber=1,maxRozmiar;

   public MinKolejkaPriorytetowa(){
       maxRozmiar=10;
       arr=(K[])new Comparable[maxRozmiar+1];

    }

    public MinKolejkaPriorytetowa(int rozmiar){

        arr=(K[])new Comparable[rozmiar+1];
        maxRozmiar=rozmiar;
    }

    MinKolejkaPriorytetowa(K[] t){
     maxRozmiar=t.length;
     arr=(K[])new Comparable[maxRozmiar+1];

   //   sizeNumber=t.length+1;
        for (int i=1;i<=maxRozmiar;i++) {
           wstawDo(t[i-1]);
        }

       // naprawKopiec(sizeNumber-1,1);
    }

    public void wstawDo(K wstawiany){
        if(sizeNumber<=maxRozmiar){
        //    System.out.println("czy tu  wgl wchodzi");

            arr[sizeNumber]=wstawiany;
            budujKopiec();
            sizeNumber++;

        } else System.out.println("Przekroczyles liczbe mozliwych elementow");
    }



    K min(){
       if(sizeNumber>1){
       return arr[1];
       }
       else return null;
    }


    K usunMin(){
        K temp = arr[1];
        arr[1]=arr[sizeNumber-1];
        arr[sizeNumber-1]=null;
        sizeNumber--;
        naprawKopiec(sizeNumber-1,1);
        System.out.print("Usuwam: ");
        return temp;
    }

    void naprawKopiec(int last, int first){
        K value=arr[first];
        int j;
        while(first<=last/2){
            j=2*first;
            if(j<last){
                if(wieksze(j,j+1)) j=j+1;

            }
            if(wieksze(j,first)){ break;}
            arr[first]=arr[j]; first=j;
        }
        arr[first]=value;

    }

    void budujKopiec(){
        int k=sizeNumber;
        while(k>1&&(wieksze(k/2,k))){
            podmien(k,k/2);
            k=k/2;
        }

    }

    void podmien(int a,int b){
        K temp=arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }

    public int size(){

        return sizeNumber-1;
    }

    private boolean wieksze(int p, int k){

           return arr[p].compareTo(arr[k])>0;
       //       else return false;
    }

    public void print(){
        for (int i=1;i<sizeNumber;i++) {
            System.out.println(arr[i]);
        }
    }

    public boolean isEmpty(){
       return sizeNumber>1;
    }


}



class Start{



    public static void main(String[] args){


        Integer liczby[]=new Integer[6];
        liczby[0]=new Integer(5);
        liczby[1]=new Integer(4);
        liczby[2]=new Integer(3);
        liczby[3]=new Integer(2);
        liczby[4]=new Integer(1);

        liczby[5]=new Integer(13);
        MinKolejkaPriorytetowa<Integer> nowaKolejka = new MinKolejkaPriorytetowa<Integer>(liczby);
        if(nowaKolejka.isEmpty()){System.out.println("NIEPUSTA");}else{System.out.println("PUSTA");}

        nowaKolejka.print();
        System.out.println(nowaKolejka.usunMin());
        System.out.println(nowaKolejka.usunMin());
        System.out.println(nowaKolejka.usunMin());
        nowaKolejka.wstawDo(new Integer(1));
        nowaKolejka.wstawDo(new Integer(6));
       // nowaKolejka.wstawDo(new Integer(5));
        nowaKolejka.wstawDo(new Integer(2));

        System.out.println(" ");
        nowaKolejka.print();
        System.out.println();
        System.out.println("Size "+nowaKolejka.size());
        if(nowaKolejka.isEmpty()){System.out.println("NIEPUSTA");}else{System.out.println("PUSTA");}


      /*  MinKolejkaPriorytetowa<Integer> nowaKolejka = new MinKolejkaPriorytetowa<Integer>();
        if(nowaKolejka.isEmpty()){System.out.println("NIEPUSTA");}else{System.out.println("PUSTA");} */

    }

}