import java.lang.reflect.Method;
import java.util.function.Consumer;

class Starter  implements Consumer <String> {

  //  static Consumer<String> consumerek = x -> {
  public void accept(String x){
  try{
      Class PobranaKlasa = Class.forName(x);
      System.out.println(x);

      Method[] wszystkieMetody=PobranaKlasa.getMethods();
      int counter=1;
      for(int i=0;i<wszystkieMetody.length;i++){

          System.out.print(+counter+" Nazwa metody: " +wszystkieMetody[i].getName() +"\n");
          System.out.println("   return: "+wszystkieMetody[i].getReturnType());
             counter++;
              Class[] parametry =wszystkieMetody[i].getParameterTypes();
              if(parametry.length==0|| parametry.length==1) {
                  if((wszystkieMetody[i].getAnnotation(MethodToStart.class)!=null)&&(wszystkieMetody[i].getAnnotation(MethodDisabled.class)==null)){
                     // Object pole=wszystkieMetody[i].getDefaultValue();
                      Object pole=wszystkieMetody[i].getAnnotation(MethodToStart.class).value();

                      if(wszystkieMetody[i].getAnnotation(StringParameter.class)!=null){
                          Object wartoscString=wszystkieMetody[i].getAnnotation(StringParameter.class).value();
                          if(wartoscString instanceof String){
                              for(int j=0;j<(int)pole;j++){
                                  wszystkieMetody[i].invoke(PobranaKlasa.newInstance(),wartoscString);
                              }
                          }

                      }
                      else if(parametry.length==0){ // gdyby zdarzyla sie mozliwosc parametru ale nie string
                          for(int j=0;j<(int)pole;j++){
                              wszystkieMetody[i].invoke(PobranaKlasa.newInstance());
                          }
                      }
                  }
              }
      }

  } catch(Exception exception){}

}
    static String nazwaKlasy="Starter";
    public static void main(String[] a){
        //consumerek.accept(nazwaKlasy);
        System.out.println("To sie wydrukowalo");

    }

}
