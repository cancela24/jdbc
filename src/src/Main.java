import java.sql.*;



public class Main {
    private Connection con=null;

    public void conectar() throws SQLException{
        String jdbc="jdbc:mysql://localhost:3306/prueba2";
        String user="root";
        String password="";
        con=DriverManager.getConnection(jdbc,user,password);
    }



    public void consulta1() throws SQLException{
        conectar();
        Statement statement=con.createStatement();
        ResultSet set=statement.executeQuery("SELECT * FROM TEMA where autor is null ");
        while (set.next()){
            String codigo=set.getString("codigo");
            String titulo=set.getString("titulo");
            int duracion=set.getInt("duracion");
            String autor=set.getString("autor");
            System.out.println(" codigo "+codigo+" titulo "+titulo+" duracion "+duracion);

        }
        set.close();
        statement.close();

    }

    public void consulta2() throws SQLException{
        conectar();
        Statement statement=con.createStatement();
        ResultSet set=statement.executeQuery("SELECT web FROM RADIO WHERE nombre = 'RNE1'");
        while (set.next()){
            String web=set.getString("web");
            System.out.println("web: " + web);
        }
        set.close();
        statement.close();
    }

    public void crearTable() throws SQLException{
        conectar();
        Statement statement=con.createStatement();
        String sql="CREATE TABLE RADIO (\n" +
                "\t\tnombre VARCHAR(120) NOT NULL,\n" +
                "\t\tdireccion VARCHAR(120) NOT NULL,\n" +
                "\t\tweb VARCHAR(120) NOT NULL,\n" +
                "\t\temail VARCHAR(120) NOT NULL,\n" +
                "\t\ttelefono INT(9) NOT NULL,\n" +
                "\t\tCONSTRAINT PK" +
                "" +
                "_RADIO PRIMARY KEY (nombre)\n" +
                "\t);\n";

        String sql2="CREATE TABLE EMISION (\n" +
                "\t\tradio VARCHAR(120) NOT NULL,\n" +
                "\t\tfechaHora DATE NOT NULL,\n" +
                "\t\ttema VARCHAR(120) NOT NULL,\n" +
                "\t\tCONSTRAINT PK_EMISION PRIMARY KEY (radio, fechaHora),\n" +
                "\t\t FOREIGN KEY (radio) REFERENCES radio(nombre),\n" +
                "    \t  FOREIGN KEY (tema) REFERENCES tema(codigo)\n" +
                ");\n";

        String sql3="CREATE TABLE TEMA (\n" +
                "\tcodigo VARCHAR(120) NOT NULL,\n" +
                "\ttitulo VARCHAR(120) NOT NULL,\n" +
                "\tduracion VARCHAR(120) NOT NULL,\n" +
                "\tautor VARCHAR(120),\n" +
                "\tCONSTRAINT PK_TEMA PRIMARY KEY (codigo)\n" +
                "    );";

        statement.executeUpdate(sql);
        statement.executeUpdate(sql3);
        statement.executeUpdate(sql2);
        statement.close();

    }

    public void insertarDatos() throws SQLException{
        String valueRadio="INSERT INTO RADIO (nombre, direccion, web, email, telefono) \n" +
                "\tVALUES ('Europa FM', 'Direccion de Onda Cero', 'https://www.ondacero.es', 'contacto@ondacero.es',\n" +
                "'918329456');";
        String valueRadio2="INSERT INTO RADIO (nombre, direccion, web, email, telefono)\n" +
                "VALUES ('RNE1', 'Direccion de RTVE', 'https://www.rtve.es/RNE1', 'contacto@rtve.es', '916547329')";
        //String valueTema="INSERT INTO TEMA (codigo, titulo, duracion, autor)\n" +
        //  "VALUES ('1' 'Back in Black', '300', 'AC-DC')\n";
        String valueTema2="INSERT INTO TEMA (codigo, titulo, duracion, autor)\n" +
                "VALUES ('2', 'Carnaval', '303', null)\n";
        conectar();
        Statement st= con.createStatement();
        st.executeUpdate(valueRadio);
        st.executeUpdate(valueRadio2);
        //st.executeUpdate(valueTema);
        st.executeUpdate(valueTema2);

    }
    public static void mostrarMenu(){
        System.out.println("1: Para crear las tablas ");
        System.out.println("2: Para insertar las tuplas en las tablas");
        System.out.println("3: Para mostar la primera consulta");
        System.out.println("4:Para mostrar la primera consulta");
        System.out.println("Teclee -1 para salir");
    }


}