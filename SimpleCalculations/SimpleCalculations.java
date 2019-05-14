

class SimpleCalculations {

    public Point[] equidistantPoints(Point firstPoint, Point secondPoint, int points) {

        if(firstPoint==null) return null;

        if(secondPoint==null) return null;



        int wymiary=firstPoint.getNumberOfDimensions();
        Point[] tab= new Point[points];


        for(int j=0;j<points;j++){


            tab[j]=new Point();
            tab[j].setNumberOfDimensions(wymiary);



            for(int i=0;i<wymiary;i++){

                double n1=firstPoint.getPosition(i);
                double n2=secondPoint.getPosition(i);
                double odleglosc =(n2-n1)/(points+1);
                n1=n1+odleglosc*(j+1);

                tab[j].setPosition(i,n1);

            }
        }




        return tab;
    }



    public Point geometricCenter(Point[] points) {

        for(int p=0;p<points.length;p++){

            if(points[p]==null)return null;
        }


       int wymiary=points[0].getNumberOfDimensions();
       double ilosc=points.length;

       Point srodek= new Point();

        double suma = 0;

       for(int i=0;i<wymiary;i++){

          suma=0;

            for(int j=0;j<ilosc;j++) {


               suma+=points[j].getPosition(i);

           }

           srodek.setPosition(i,suma/ilosc);
       }






        return srodek;
    }


    public Point next(Point firstPoint, Point secondPoint, double distance) {

        if(firstPoint==null) return null;

        if(secondPoint==null) return null;


        int wymiary=firstPoint.getNumberOfDimensions();
        Point nextP = new Point();
        for(int i=0;i<wymiary;i++) {
           double  a= firstPoint.getPosition(i);
            double b= secondPoint.getPosition(i);

            if(a==b){
                nextP.setPosition(i,a);
            }
            else{
                if(a<b){
                    nextP.setPosition(i,b+distance);
                }
                else{
                    nextP.setPosition(i,b-distance);
                }
            }
        }


        return nextP;
    }


}
