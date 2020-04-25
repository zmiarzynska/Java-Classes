import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import static java.lang.Float.parseFloat;


class OpenAndClearJSON extends OpenAndClear {
    private  Vector<String> VectorOfLines;

    public void clear() throws IOException {
        String row;
        VectorOfLines = new Vector<>();
        while ((row = bufferToLines.readLine()) != null) {
            row = row.replaceAll("\\s", "");
            row = row.replaceAll("\"employees\":\\[", "");
            row = row.replaceAll("]", "");
            row = row.replaceAll("\\{", "");
            row = row.replaceAll("}", "");
            row = row.replaceAll("\"", "");
            row = row.replaceAll(",", ".");
            VectorOfLines.addElement(row);
        }

    }


    public  Vector<String> getVectorOfLines() throws IOException {
        open(".\\src\\employees.json");
        clear();
        closing();
        return VectorOfLines;
    }

}
class ProfessionsJSON extends Professions{

    private String amountSalary;
    public void setProfessions(Vector <String> vectorOfLines){
        iterator = vectorOfLines.listIterator();
        professions = new HashMap<>();
        String nameJobToComparison="";
        amountSalary="";
        while (iterator.hasNext()) {
            String iteratorString = (String) iterator.next();
            String[] data=iteratorString.split(":");
            nameJob=data[0];
            if (nameJob.equals("job")){
                nameJobToComparison=data[1];
            }
            if (nameJob.equals("salary")){
                amountSalary=data[1];
                if(!professions.containsKey(nameJobToComparison)){
                    professions.put(nameJobToComparison,parseFloat(amountSalary));
                }
                else{
                    float old=professions.get(nameJobToComparison);
                    professions.replace(nameJobToComparison,(parseFloat(amountSalary)+old));
                }
            }

        }
    }

}


class StartJSON {

   private static Vector <String> vectorOfLinesJSON;

    public static void main(String a[]) {
        try {
            OpenAndClearJSON simplifiedData = new OpenAndClearJSON();
            vectorOfLinesJSON = simplifiedData.getVectorOfLines();
            ProfessionsJSON employees = new ProfessionsJSON();
            employees.setProfessions(vectorOfLinesJSON);
            employees.getProfessions();

        } catch (IOException e) {
            System.out.println("Reading file failure");
        }


    }
}
