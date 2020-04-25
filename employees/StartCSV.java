import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import static java.lang.Float.parseFloat;


class OpenAndClearCSV extends OpenAndClear{
    private  Vector <String[]> VectorOfLines ;

    public void clear() throws IOException {
        String row;
        VectorOfLines = new Vector<>();
        while ((row=bufferToLines.readLine())!=null){
            row =row.replaceAll("\\s","");
            row =row.replaceAll("\"","");
            row =row.replaceAll(",",".");
            String[] data=row.split(";");
            VectorOfLines.addElement(data);

        }

    }

    public Vector <String[]>  getVectorOfLines() throws IOException {
        open(".\\src\\employees.csv");
        clear();
        closing();
        return VectorOfLines;
    }
}

class ProfessionsCSV extends Professions {

    private String amountSalary;
    public void setProfessions(Vector <String[]> vectorOfLines){
        iterator = vectorOfLines.listIterator();
        professions = new HashMap<>();
        amountSalary ="";
        while(iterator.hasNext()){
            String[] eachLineArray=(String[])iterator.next();
            nameJob=eachLineArray[3];
            amountSalary=eachLineArray[4];
            if(nameJob.equals("job")) continue;
            else if(!professions.containsKey(nameJob)){
                professions.put(nameJob,parseFloat(amountSalary));
            }
            else{
                float old=professions.get(nameJob);
                professions.replace(nameJob,(parseFloat(amountSalary)+old));
            }
        }
    }



}



class StartCSV{

    private static Vector<String[]> vectorOfLinesCSV;


    public static void main(String a[]) {
        try {
            OpenAndClearCSV simplifiedData;
            ProfessionsCSV employees;
            simplifiedData = new OpenAndClearCSV();
            vectorOfLinesCSV = simplifiedData.getVectorOfLines();
            employees = new ProfessionsCSV();
            employees.setProfessions(vectorOfLinesCSV);
            employees.getProfessions();
        } catch (IOException e) {
            System.out.println("Reading file failure");
        }

    }
}