import java.util.*;

class ParallelCalculations implements ParallelCalculationsInterface,Runnable {

    private PointGeneratorInterface generator;
    private int liczbaWatkow;
    private  Vector<Thread> wektorWatkow=new Vector<>();
    private int[][] mojHistogram;
    private double[] geometryczny;
    private int liczbaPunktow=0;
    private Thread t;
    private volatile boolean bool=false;

    ParallelCalculations(){
        mojHistogram= new int[PointInterface.MAX_POSITION+1][PointInterface.MAX_POSITION+1];
        geometryczny=new double[2];
    }

    public void setPointGenerator(PointGeneratorInterface generator){
        this.generator=generator;

    }


    public void setNumberOfThreads(int threads){
        this.liczbaWatkow=threads;

    }


    public void start(){

        for(int i=0; i<liczbaWatkow;i++){
            t = new Thread(this);
            wektorWatkow.add(i,t);
            t.start();
        }

    }



    public void suspendCalculations(){


        if(wektorWatkow.size()>0){
            bool=false;

           wektorWatkow.clear();
        }

    }


    public void continueCalculations(){

        if(wektorWatkow.size()==0){
            bool=true;
            for(int i=0; i<liczbaWatkow;i++){
                t = new Thread(this);
                wektorWatkow.add(i,t);
                t.start();
            }

        }

    }


     public double[] getGeometricCenter(){

        double [] centrum= new double [2];
        centrum[0]=geometryczny[0]/liczbaPunktow;
        centrum[1]=geometryczny[1]/liczbaPunktow;


        return centrum;
    }


    public int[][] getHistogram(){

        return mojHistogram;
    }

   public void setValues(){


        PointInterface point;
        bool = true;


        while(bool){
            point =generator.getPoint();
           synchronized (mojHistogram){

                int[] pozycje=point.getPositions();
                mojHistogram[pozycje[0]][pozycje[1]]++;
                geometryczny[0]+=pozycje[0];
                geometryczny[1]+=pozycje[1];
                liczbaPunktow++;

            }



        }

    }

    @Override
    public void run() {
        setValues();
    }






}



