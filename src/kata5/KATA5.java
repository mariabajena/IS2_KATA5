package kata5;

/**
 *
 * @author Maria
 */
public class KATA5 {
    
    public static void main(String[] args) {
        String URL = new String("jdbc:sqlite:C:\\Users\\Maria\\Documents\\NetBeansProjects\\KATA5\\src\\database.db");
        DataBase db = new DataBase(URL);
        db.open();
        
        db.selectPeople("SELECT * FROM PEOPLE");
        
        People people = new People("Fefo", "Martin", "Mecanica");
        db.insertPeople(people);
        
        System.out.println("* * * * *");
        
        db.selectPeople("SELECT * FROM PEOPLE");
        
        db.close();
    }
    
}
