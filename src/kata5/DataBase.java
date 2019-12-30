package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class DataBase {
    private String URL;
    private Connection connection = null;
    
    public DataBase(String URL){
        this.URL = URL;
    }

    void open(){
        try{
            this.connection = DriverManager.getConnection(this.URL);
            System.out.println("Base de Datos abierta.");
        }
        catch (SQLException exception){
            System.out.println("ERROR DataBase::open (SQLException)");
        }
    }
        
        void close(){
        try{
            if(this.connection != null)
                    this.connection.close();
            System.out.println("Base de Datos cerrada.");
        }
        catch (SQLException exception){
            System.out.println("ERROR DataBase::close (SQLException)");
        }
    }

    void selectPeople(String select) {
        String SQL = "SELECT * FROM PEOPLE";
        try{
            Statement statement = this.connection.createStatement();
            ResultSet resultset = statement.executeQuery(select);
            System.out.println("ID \t NAME \t APELLIDOS \t DEPARTAMENTO");
            while (resultset.next()){
                System.out.println(resultset.getInt("ID") + " \t" + 
                                   resultset.getString("name") + " \t"  +
                                   resultset.getString("apellidos") + " \t"  +
                                   resultset.getString("departamento") + " \t" 
                                   );
            }
        }
        catch (SQLException exception){
            System.out.println("ERROR DataBase::select (SQLExecute)");
        }
    }

    void insertPeople(People people) {
        String SQL = "INSERT INTO PEOPLE(NAME, apellidos, departamento) VALUES(?,?,?)";
        try{
            PreparedStatement preparedstatement = this.connection.prepareStatement(SQL);
            preparedstatement.setString(1, people.getName());
            preparedstatement.setString(2, people.getApellidos());
            preparedstatement.setString(3, people.getDepartamento());
            preparedstatement.executeUpdate();
        }
        catch(SQLException exception){
            System.out.println("ERROR DataBase::insertPeople" + exception.getMessage());
        }
    }
}

