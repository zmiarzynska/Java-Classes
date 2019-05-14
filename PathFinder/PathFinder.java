import java.util.*;

class PathFinder implements PathFinderInterface {
     LinkedHashMap <BusStopInterface,BusInterface> mapaLiniiPrzystankow;
    List <ArrayList <BusStopInterface>> wszystkieLinie;
LinkedHashMap <BusStopInterface,BusInterface> mapa;
    ArrayList <BusStopInterface> jednazLinii;
    ArrayList <BusStopInterface> jednazTras;
    List <ArrayList <BusStopInterface>> trasy;
   List < LinkedHashMap<BusStopInterface,BusInterface> > listaMap = new ArrayList <LinkedHashMap<BusStopInterface, BusInterface>>();
 List < LinkedHashMap<BusStopInterface,BusInterface> > listaMapDubel = new ArrayList <LinkedHashMap<BusStopInterface, BusInterface>>();

    PathFinder(){
        mapaLiniiPrzystankow =new LinkedHashMap <BusStopInterface,BusInterface>();
		wszystkieLinie = new ArrayList<ArrayList<BusStopInterface>>();
		trasy = new ArrayList<ArrayList<BusStopInterface>>();
    }

    public void addLine( BusLineInterface line, BusInterface bus ){
      mapaLiniiPrzystankow=new LinkedHashMap <BusStopInterface,BusInterface>();
        jednazLinii=new ArrayList<BusStopInterface>();
        for(int i=0;i<line.getNumberOfBusStops();i++){

            jednazLinii.add(line.getBusStop(i));
        mapaLiniiPrzystankow.put(line.getBusStop(i),bus);
        }

        wszystkieLinie.add(jednazLinii);
        listaMap.add(mapaLiniiPrzystankow);
    }

    public void find( BusStopInterface from, BusStopInterface to, int transfers ){
    trasy.clear();
 mapa=new LinkedHashMap <BusStopInterface,BusInterface>();  
    if(wszystkieLinie!=null && trasy!=null){ 

     if(transfers==0){


            for(int i=0;i<wszystkieLinie.size();i++){

                if((wszystkieLinie.get(i).contains(from))&&(wszystkieLinie.get(i).contains(to))){

                    System.out.println("Odnaleziono możliwosc bez przesiadek");

                    jednazTras = new ArrayList<BusStopInterface>();
   						mapa.putAll(listaMap.get(i));

                    if(wszystkieLinie.get(i).indexOf(from)<wszystkieLinie.get(i).indexOf(to)){


                        for(int j=wszystkieLinie.get(i).indexOf(from);j<=wszystkieLinie.get(i).indexOf(to);j++){

                             jednazTras.add(wszystkieLinie.get(i).get(j));

                        }
                                             

                    }
                    else{
                        for(int j=wszystkieLinie.get(i).indexOf(to);j<=wszystkieLinie.get(i).indexOf(from);j++) {

                            jednazTras.add(wszystkieLinie.get(i).get(j));
							                                              
                        }

                    }
						

                        trasy.add(jednazTras);
						listaMapDubel.add(mapa);

                }
				else{ continue;}

            }
        }

       if(transfers==1){
            
            for(int i=0;i<wszystkieLinie.size();i++){

                if(wszystkieLinie.get(i).contains(from)){
                    for(int j=0; j<wszystkieLinie.size();j++){
                        if(i==j) continue;
                        if(wszystkieLinie.get(j).contains(to)){

         			    ArrayList <BusStopInterface> czescWspolna=new ArrayList<BusStopInterface>();

                            czescWspolna.addAll(wszystkieLinie.get(i));
                            czescWspolna.retainAll(wszystkieLinie.get(j));

                            if(czescWspolna.size()<1) System.out.println("Nie maja wspolnego przystanku");
							else{
                                jednazTras = new ArrayList<BusStopInterface>();
                                int indeksPFrom=0;
                                int indeksPTo=0;
									for(int p=0; p<wszystkieLinie.get(i).size();p++){
                                if(wszystkieLinie.get(i).get(p).equals(czescWspolna.get(0))) indeksPFrom=p;
                            }
								mapa.putAll(listaMap.get(i));
                            if(wszystkieLinie.get(i).indexOf(from)<indeksPFrom){
                                for(int p=wszystkieLinie.get(i).indexOf(from);p<indeksPFrom;p++){

                                    jednazTras.add(wszystkieLinie.get(i).get(p));
                                }

                                                
                            }
                            else{
                                for(int p=wszystkieLinie.get(i).indexOf(from);p<indeksPFrom;p--){

                                    jednazTras.add(wszystkieLinie.get(i).get(p));
                                }

                            }

                            for(int p=0; p<wszystkieLinie.get(j).size();p++){
                                if(wszystkieLinie.get(j).get(p).equals(czescWspolna.get(0))) indeksPTo=p;
                            }
							mapa.putAll(listaMap.get(j));
                            if(wszystkieLinie.get(j).indexOf(to)>indeksPTo){
                                for(int p=indeksPTo; p<=wszystkieLinie.get(j).indexOf(to);p++){

                                    jednazTras.add(wszystkieLinie.get(j).get(p));
                                }

                                                
                            }
                            else{
                                for(int p=indeksPTo; p<=wszystkieLinie.get(j).indexOf(to);p--){

                                    jednazTras.add(wszystkieLinie.get(j).get(p));
                                }

                            }

                            trasy.add(jednazTras);
							listaMapDubel.add(mapa);
								 }

                            
                        }
                    }
                }
            }

		
        } // koniec transfer 1

    if(transfers==2){
            for(int i=0;i<wszystkieLinie.size();i++) {

                if(wszystkieLinie.get(i).contains(from)) {

                    for(int j=0; j<wszystkieLinie.size();j++) {
                        if (i == j) continue;
                        if(wszystkieLinie.get(j).contains(to)){
                            ArrayList <BusStopInterface> czescWspolnaFrom=new ArrayList<BusStopInterface>();
                            ArrayList <BusStopInterface> czescWspolnaTo=new ArrayList<BusStopInterface>();



                              for(int p=0;p<wszystkieLinie.size();p++){
                                  if(p==i||p==j) continue;
                                  czescWspolnaFrom.addAll(wszystkieLinie.get(i));
                                  czescWspolnaTo.addAll(wszystkieLinie.get(j));

                                  czescWspolnaFrom.retainAll(wszystkieLinie.get(p));
                                  czescWspolnaTo.retainAll(wszystkieLinie.get(p));

                                  if(czescWspolnaFrom.size()>=1 && czescWspolnaTo.size()>=1){
                                      jednazTras = new ArrayList<BusStopInterface>();
                                      int indeksDoPrzesiadki=0;
                                      int indeksZprzesiadki=0;


                                      for(int s=0; s<wszystkieLinie.get(i).size();s++){
                                          if(wszystkieLinie.get(i).get(s).equals(czescWspolnaFrom.get(0))) indeksDoPrzesiadki=s;
                                      }
 										mapa.putAll(listaMap.get(i));
										mapa.putAll(listaMap.get(p));
										mapa.putAll(listaMap.get(j));

                                      if(wszystkieLinie.get(i).indexOf(from)<indeksDoPrzesiadki){
                                          for(int s=wszystkieLinie.get(i).indexOf(from);s<indeksDoPrzesiadki;s++){

                                              jednazTras.add(wszystkieLinie.get(i).get(s));
                                          }
          
                                          	
                                      }
                                      else{
                                          for(int s=wszystkieLinie.get(i).indexOf(from);s>indeksDoPrzesiadki;s--){

                                              jednazTras.add(wszystkieLinie.get(i).get(s));

                                          }
                                   

                                      }
 										
                                      if(wszystkieLinie.get(p).indexOf(czescWspolnaFrom.get(0))<wszystkieLinie.get(p).indexOf(czescWspolnaTo.get(0))){
                                      for(int s=wszystkieLinie.get(p).indexOf(czescWspolnaFrom.get(0));s<wszystkieLinie.get(p).indexOf(czescWspolnaTo.get(0));s++){
                                          jednazTras.add(wszystkieLinie.get(p).get(s));
											
                                      }

                                               

                                      }
                                      else {
                                          for(int s=wszystkieLinie.get(p).indexOf(czescWspolnaFrom.get(0));s>wszystkieLinie.get(p).indexOf(czescWspolnaTo.get(0));s--){
                                              jednazTras.add(wszystkieLinie.get(p).get(s));
											
												
                                          }

                                          


                                      }

                                      for(int s=0; s<wszystkieLinie.get(j).size();s++){
                                          if(wszystkieLinie.get(j).get(s).equals(czescWspolnaTo.get(0))) indeksZprzesiadki=s;
                                      }
											

                                      if(indeksZprzesiadki<wszystkieLinie.get(j).indexOf(to)){

                                          for(int s=indeksZprzesiadki; s<=wszystkieLinie.get(j).indexOf(to);s++){
                                              jednazTras.add(wszystkieLinie.get(j).get(s));

                                          }


                                      }
                                      else{
                                          for(int s=indeksZprzesiadki; s>=wszystkieLinie.get(j).indexOf(to);s--){
                                              jednazTras.add(wszystkieLinie.get(j).get(s));

                                          }

                                   
                                                
									}
                                     trasy.add(jednazTras);
							         listaMapDubel.add(mapa);
                                  }

 							
                              }


                        }

                    }
                }
            }
        } //konczy 2

        
           if (transfers == 3) {

            for(int i=0;i<wszystkieLinie.size();i++) {

                if(wszystkieLinie.get(i).contains(from)) {

                    for(int j=0; j<wszystkieLinie.size();j++) {
                        if (i == j) continue;

                        if(wszystkieLinie.get(j).contains(to)){




                            for(int p=0;p<wszystkieLinie.size();p++){
                                if(p==i||p==j) continue;
                                ArrayList <BusStopInterface> czescWspolna1=new ArrayList<BusStopInterface>();
                                ArrayList <BusStopInterface> czescWspolna2=new ArrayList<BusStopInterface>();
                                ArrayList <BusStopInterface> czescWspolna3=new ArrayList<BusStopInterface>();
                                czescWspolna1.addAll(wszystkieLinie.get(i));

                                czescWspolna1.retainAll(wszystkieLinie.get(p));

                                if(czescWspolna1.size()>=1){

                                    for(int r=0;r<wszystkieLinie.size();r++) {
                                        if (r == i || r == j || r == p) continue;
                                

                                        czescWspolna2.addAll(wszystkieLinie.get(p));
                                        czescWspolna2.retainAll(wszystkieLinie.get(r));

                                        if(czescWspolna2.size()>=1){

                                            czescWspolna3.addAll(wszystkieLinie.get(r));
                                            czescWspolna3.retainAll(wszystkieLinie.get(j));

                                            if(czescWspolna3.size()>=1){

                                                jednazTras = new ArrayList<BusStopInterface>();
                                                int indeks1From=0;
                                                int indeks3To=0;
                                                mapa.putAll(listaMap.get(i));
                                                mapa.putAll(listaMap.get(p));


                                                for(int s=0; s<wszystkieLinie.get(i).size();s++){
                                                    if(wszystkieLinie.get(i).get(s).equals(czescWspolna1.get(0))) indeks1From=s;
                                                }



                                                if(wszystkieLinie.get(i).indexOf(from)<indeks1From){
                                                    for(int s=wszystkieLinie.get(i).indexOf(from);s<indeks1From;s++)

                                                        jednazTras.add(wszystkieLinie.get(i).get(s));
                                                    
                                                }
                                                else{
                                                    for (int s = wszystkieLinie.get(i).indexOf(from); s > indeks1From; s--) 

                                                        jednazTras.add(wszystkieLinie.get(i).get(s));
                                                }


                                                if(wszystkieLinie.get(p).indexOf(czescWspolna1.get(0))<wszystkieLinie.get(p).indexOf(czescWspolna2.get(0))){
                                                    for(int s=wszystkieLinie.get(p).indexOf(czescWspolna1.get(0));s<wszystkieLinie.get(p).indexOf(czescWspolna2.get(0));s++){
                                                        jednazTras.add(wszystkieLinie.get(p).get(s));

                                                    }
                                                }
                                                else {
                                                    for(int s=wszystkieLinie.get(p).indexOf(czescWspolna1.get(0));s>wszystkieLinie.get(p).indexOf(czescWspolna2.get(0));s--){
                                                        jednazTras.add(wszystkieLinie.get(p).get(s));

                                                    }

                                                }

                                                mapa.putAll(listaMap.get(r));

                                                if(wszystkieLinie.get(r).indexOf(czescWspolna2.get(0))<wszystkieLinie.get(r).indexOf(czescWspolna3.get(0))){
                                                    for(int s=wszystkieLinie.get(r).indexOf(czescWspolna2.get(0));s<wszystkieLinie.get(r).indexOf(czescWspolna3.get(0));s++){
                                                        jednazTras.add(wszystkieLinie.get(r).get(s));

											

                                                    }
                                                }
                                                else {
                                                    for(int s=wszystkieLinie.get(r).indexOf(czescWspolna2.get(0));s>wszystkieLinie.get(r).indexOf(czescWspolna3.get(0));s--){
                                                        jednazTras.add(wszystkieLinie.get(r).get(s));

                                                    }

                                                }

                                                mapa.putAll(listaMap.get(j));

                                                for(int s=0; s<wszystkieLinie.get(j).size();s++){
                                                    if(wszystkieLinie.get(j).get(s).equals(czescWspolna3.get(0))) indeks3To=s;
                                                }

                                                if(indeks3To<wszystkieLinie.get(j).indexOf(to)){

                                                    for(int s=indeks3To; s<=wszystkieLinie.get(j).indexOf(to);s++){

                                           
                                                        jednazTras.add(wszystkieLinie.get(j).get(s));
														
                                                    }

                                                }
                                                else{
                                                    for(int s=indeks3To; s>=wszystkieLinie.get(j).indexOf(to);s--){

                                                        jednazTras.add(wszystkieLinie.get(j).get(s));}
											
                                                    }



                                                trasy.add(jednazTras);
                                                listaMapDubel.add(mapa);                                                    

                                                }// r ma przesiadke z to

                                        }// jesli z 1 przesiadki da sie do drugiej

                                    }// dla kolejnej przesiadki
                                }//jesli istnieje czesc wspolna i z p

                            }// dla pierwszej przesiadki

                        } //  znalazlam to

                    }//szukam to



                }//znalazlem from

            }//szukam from


        }



      }
    } // tu sie konczy find

    public int getNumerOfSolutions(){
if(trasy==null) return 0;
        int a=trasy.size();

        return a;}

    public int getBusStops( int solution ){
if(trasy==null) return 0;

        int a= trasy.get(solution).size();

        return a;
    }

    public BusStopInterface getBusStop( int solution, int busStop ){
if(trasy==null) return null;
        BusStopInterface busStopik=trasy.get(solution).get(busStop);

                        
        return busStopik;

    }

    public BusInterface getBus( int solution, int busStop ){
if(trasy==null) return null;
BusInterface busik=null;
        if(trasy.get(solution).indexOf(busStop)==trasy.get(solution).size()-1){

            BusStopInterface busStopik=trasy.get(solution).get(busStop-1);
 busik =listaMapDubel.get(solution).get(busStopik);
          

            return busik;
        }
        else {
            BusStopInterface busStopik = trasy.get(solution).get(busStop);

 busik =listaMapDubel.get(solution).get(busStopik);       



            return busik;
        }
    }
}
