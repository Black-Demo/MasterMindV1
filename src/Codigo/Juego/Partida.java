package Codigo.Juego;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Partida implements java.io.Serializable{
//-------------------------Declaracion de Datos-----------------------//
    protected byte[] numRand=new byte[5];
    protected ArrayList<Tirada> tiradas = new ArrayList<Tirada>();
//----------------------------Constructores---------------------------//
    public Partida(){
        for(int i=0;i<numRand.length;i++)
            numRand[i]=(byte)(new Random().nextInt(10)+48);
        //String aa="01234";
    }
//----------------------Metodos de la clase---------------------------//
    public void addTirada (byte[] numRes){
        tiradas.add(new Tirada(numRand,numRes));
    }
    public boolean winGame (byte[] numRes){
        return(new String(numRand).equals(new String(numRes)));
    }
    /*--------------Metodos sobreescritos de Object-------------------*/
    //Metodo toString
    @Override
    public String toString(){
        return toString(true);
    }
    public String toString(boolean beginer){
        String res="";
        if(beginer){
            for(Iterator num = tiradas.iterator(); num.hasNext();){
                res+=(num.next())+"\n";
            }
        }else{
            String valor;
            for(Iterator num = tiradas.iterator(); num.hasNext();){
                valor=num.next().toString();
                res+=(valor.substring(0,valor.length()-6))+"\n";
            }
        }
        return res;
    }
}
