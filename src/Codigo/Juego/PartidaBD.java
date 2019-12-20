package Codigo.Juego;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PartidaBD extends Partida{
    private boolean finalizada;
    private int id;
    private boolean level;

    public PartidaBD(){
        Date diaCreacion=new Date(new GregorianCalendar( ).getTimeInMillis());
        Statement st;
        Connection con;
        String crearUsu="create user if not exists 'LocalUser'@'localhost' identified by 'MasterMind'";
        String permisosPartidaUsu="grant select, update, drop, insert  on Partidas to LocalUser identified by 'MasterMind'";
        String permisosTiradaUsu="grant select, update, drop, insert on Tiradas to LocalUser identified by 'MasterMind'";
        String crearBD="create database if not exists HSMirandaMasterMind";
        String tablaPartida="create table if not exists Partidas(" +
                                "id int auto_increment primary key," +
                                "numRandom varchar(5) not null," +
                                "level boolean not null,"+
                                "finalizada boolean not null," +
                                "fechaCreacion Date)";
        String tablaTirada="create table if not exists Tiradas(" +
                                "id int auto_increment primary key," +
                                "numTirada varchar(5) not null," +
                                "bienPos int not null," +
                                "malPos int not null," +
                                "tablaPos varchar(5) not null," +
                                "idPartida int not null," +
                "constraint `Tirada_PartidaFK` foreign key (idPartida) references Partidas (id))";
        try{
            //Cargar Controlador
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Crear objecto de conexion
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306",
                                            "root",
                                            "");
            //Crear objeto para ejecutar sentencias
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            //Sentencias sql
            System.err.println( ": Crear BD: " + st.executeUpdate(crearBD) + "\n");
            System.err.println(": Crear usuario local: "+st.executeUpdate(crearUsu) + "\n");
            System.err.println(": usar BD: "+st.executeUpdate("use HSMirandaMasterMind") + "\n");
            System.err.println(": crear tabla partida: "+st.executeUpdate(tablaPartida) + "\n");
            System.err.println( ": crear tabla tiradas: "+ st.executeUpdate(tablaTirada) + "\n");
            System.err.println(": Dar Permisos usuario: "+st.executeUpdate(permisosPartidaUsu) + "\n");
            System.err.println(": Dar Permisos usuario: "+st.executeUpdate(permisosTiradaUsu) + "\n");
            //Insertar la partida en BD
            ResultSet rs=null;
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306",
                                            "LocalUser",
                                            "MasterMind");
            rs=st.executeQuery("select * from Partidas");
            rs.moveToInsertRow();
            rs.updateString("numRandom",new String(numRand));
            rs.updateBoolean ("level",level);
            rs.updateBoolean("finalizada",finalizada);
            rs.updateDate("fechaCreacion",  diaCreacion);
            rs.insertRow();
            //Coger el id de la partida
            rs.last();
            id=rs.getInt("id");
            //Cerrar flujo de datos
            if(st!=null)st.close();
            if(con!=null)con.close();
        }catch(ClassNotFoundException e){
            System.err.println("controlador no encontrado\n"+e);
        }catch (SQLException e){
            verSQLException(e);
        }
    }

    public PartidaBD(int id){
        Statement st;
        Connection con;
        ResultSet rs;
        this.id=id;
        try {
            con = DriverManager.getConnection ("jdbc:mysql://localhost:3306",
                    "LocalUser",
                    "MasterMind");
            st = con.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            st.executeUpdate ("use HSMirandaMasterMind");
            rs= st.executeQuery ("select * from Partidas where id="+id);
            rs.first ();
            String numRandom=rs.getString ("numRandom");
            for(int i = 0; i<numRandom.length();i ++){
                numRand[i]= (byte)numRandom.charAt(i);
            }
            if(st!=null)st.close();
            if(con!=null)con.close();
        }catch (SQLException e){
            verSQLException (e);
        }
    }

    //Guardara si una partida esta finalizada o no
    public void setFinalizada(boolean finalizada){
        Statement st;
        Connection con;
        this.finalizada=finalizada;
        try{
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306",
                                            "LocalUser",
                                            "MasterMind");
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            st.executeUpdate("use HSMirandaMasterMind");
            st.executeUpdate("update Partidas set finalizada="+finalizada+" where id="+id);
            if(st!=null)st.close();
            if(con!=null)con.close();
        }catch (SQLException e){
            verSQLException(e);
        }
    }

    //Guardara en que nivel esta siendo jugada esta partida
    public void setLevel(boolean level){
        Statement st;
        Connection con;
        this.level=level;
        try{
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306",
                    "LocalUser",
                    "MasterMind");
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            st.executeUpdate("use HSMirandaMasterMind");
            st.executeUpdate("update Partidas set level="+level+" where id="+id);
            if(st!=null)st.close();
            if(con!=null)con.close();
        }catch (SQLException e){
            verSQLException(e);
        }
    }

    //Conseguiremos en que nivel estamos jugando esta partida
    public boolean getLevel(){
        boolean level=false;
        Statement st;
        Connection con;
        ResultSet rs;
        try {
            con = DriverManager.getConnection ("jdbc:mysql://localhost:3306",
                    "LocalUser",
                    "MasterMind");
            st = con.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            st.executeUpdate ("use HSMirandaMasterMind");
            rs= st.executeQuery ("select level from Partidas where id="+id);
            rs.first ();
            level= rs.getBoolean ("level");
            if(st!=null)st.close();
            if(con!=null)con.close();
        }catch (SQLException e){
            verSQLException (e);
        }
        return level;
    }


    //insertara una tirada en la tabala tiradas
    public void insertarTirada(String numTirada,String bien,String mal,String tablaPos){
        ResultSet rs;
        Statement st;
        Connection con;
        try{
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306",
                    "LocalUser",
                    "MasterMind");
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            st.executeUpdate("use HSMirandaMasterMind");
            rs=st.executeQuery("select * from Tiradas");
            rs.moveToInsertRow();
            rs.updateString("numTirada",numTirada);
            rs.updateInt("bienPos",Integer.parseInt(bien));
            rs.updateInt("malPos",Integer.parseInt(mal));
            rs.updateString("tablaPos",tablaPos);
            rs.updateInt("idPartida",this.id);
            rs.insertRow();
            if(st!=null)st.close();
            if(con!=null)con.close();
        }catch (SQLException e){
            verSQLException(e);
        }
    }

    //Cargara una tirada de partidas en es ta base de datos
    public static ArrayList<String> cargarPartidas(){
        String output = "";
        ArrayList<String> listaPartidas=new ArrayList<>();

        ResultSet rs;
        Statement st;
        Connection con;
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306",
                    "LocalUser",
                    "MasterMind");
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            st.executeUpdate("use HSMirandaMasterMind");
            rs = st.executeQuery("select * from Partidas");

            while(rs.next()){
                output += "|| id: "+rs.getInt("id");
                output += " || fecha creacion: "+rs.getDate("fechaCreacion") + " || ";
                output += " finalizada: " + ((rs.getBoolean("finalizada"))?"Si":"No") + " || ";
                if(rs.getBoolean("finalizada")) output += " numero random: "+rs.getInt("numRandom")+" ||";
                listaPartidas.add(output);
                output="";
            }

            if(st!=null)st.close();
            if(con!=null)con.close();
        }catch (SQLException e){
            verSQLException(e);
        }
        return listaPartidas;
    }

    //Cargara un lista de tiradas de una partida concreta
    public static ArrayList<String> cargarTirada(int id){
        StringBuilder output = new StringBuilder();
        ArrayList<String> listaTiradas=new ArrayList<>();
        ResultSet rs;
        Statement st;
        Connection con;
        try{
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306",
                    "LocalUser",
                    "MasterMind");
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            st.executeUpdate("use HSMirandaMasterMind");
            rs = st.executeQuery("select * from Tiradas where idPartida = "+id);
            while(rs.next()){
                output.append(rs.getString("numTirada")).append(" ");
                output.append(rs.getInt("bienPos")).append(" ");
                output.append(rs.getInt("malPos")).append(" ");
                output.append(rs.getString("tablaPos"));
                listaTiradas.add(output.toString());
                output = new StringBuilder();
            }
            st.close();
            con.close();
        }catch (SQLException e){
            verSQLException(e);
        }
        return listaTiradas;
    }

    //Borra la base datos al completo
    public static void borrarBD(){
        Statement st;
        Connection con;
        try{
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306",
                    "root",
                    "");
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            st.executeUpdate("Drop database HSMirandaMasterMind");
            System.err.println("La base datos ha sido borrada");
        }catch (SQLException e){
            e.printStackTrace(System.err);
            System.err.println(": SQLState: " + e.getSQLState() + "\n");
            System.err.println(": Error Code: " + e.getErrorCode() + "\n");
            System.err.println(": Message: " + e.getMessage() + "\n");
            Throwable t=e.getCause();
            while(t!=null) {
                System.err.println(": Cause: " + t);
                t=t.getCause();
            }
        }
    }


    //Nos devolvera el numero de tiradas realizadas en una partida concreta
    public static int numTiradas(int id){
        int num=0;
        Statement st;
        Connection con;
        ResultSet rs;
        try{
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306",
                    "LocalUser",
                    "MasterMind");
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            st.executeUpdate("use HSMirandaMasterMind");
            rs=st.executeQuery("select count(*) as num from Tiradas where idPartida="+id);
            rs.first();
            num=rs.getInt("num");
        }catch (SQLException e){
            verSQLException(e);
        }
        return num;
    }

    //Comprobar que la base de datos ha sido creada
    public static boolean cargarBD(){
        Statement st;
        Connection con;
        try{
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306",
                    "LocalUser",
                    "MasterMind");
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            st.executeUpdate("use HSMirandaMasterMind");
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    //Systema de control de errores de SQL
    private static  void verSQLException(SQLException ex) {
        ex.printStackTrace(System.err);
        System.err.println(": SQLState: " + ex.getSQLState() + "\n");
        System.err.println(": Error Code: " + ex.getErrorCode() + "\n");
        System.err.println(": Message: " + ex.getMessage() + "\n");
        Throwable t=ex.getCause();
        while(t!=null) {
            System.err.println(": Cause: " + t);
            t=t.getCause();
        }
    }
}
