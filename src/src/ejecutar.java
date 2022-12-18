import java.sql.SQLException;
import java.util.Scanner;
public class ejecutar {
    public static void main(String[] args) throws SQLException {
        try{
            Main objeto=new Main();
            //objeto.crearTable();
            //objeto.insertarDatos();
            Scanner reader = new Scanner(System.in);
            int numero = 0;


            while (numero!=-1){
                Main.mostrarMenu();
                numero = reader.nextInt();
                switch (numero){
                    case 1:
                        objeto.crearTable(); break;
                    case 2:
                        objeto.insertarDatos(); break;
                    case 3:
                        objeto.consulta1(); break;
                    case 4:
                        objeto.consulta2(); break;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
