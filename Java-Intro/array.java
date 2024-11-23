import java.util.ArrayList;

public class array {
    void main(String args[]){
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("Hello");
        arr.add("World");
        arr.add("!");
        for(String i: arr){
            System.out.println(i);
        }
    }
}
