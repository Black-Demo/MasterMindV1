package Codigo.Juego;

import java.util.Arrays;

public class Tirada implements java.io.Serializable {
//-------------------------Declaracion de Datos-----------------------//
    private byte[] tablaPos={48,48,48,48,48};
    private byte[] numRes=new byte[5];
    private byte bienPos=0,malPos=0;
    //----------------------------Constructores---------------------------//
    public Tirada(){}
    public Tirada(byte[] numRand, byte[] numRes){
        this.numRes= Arrays.copyOf(numRes,5);
        goodPos(numRand);
        badPos(numRand);
    }
//----------------------Metodos de la clase---------------------------//
    /*--------------Metodos de la tirada-----------------------------*/
    /*Metodo que mira si los numeros estan bien posicionados*/
    private void goodPos(byte [] numRand){
        for(int i=0;i<numRand.length;i++){
            if(numRand[i]==numRes[i]){
                tablaPos[i]=49;//pone un 1
                bienPos++;
            }
        }
    }
    /*Metodo que mira si los numeros estan mal posicionados*/
    private void badPos(byte [] numRand){
        boolean input;
        for(int i=0;i<numRand.length;i++){
            input=true;
            for(int j=0;j<numRes.length && input;j++){
                if(tablaPos[j]==48 && numRand[i]==numRes[j] && !(tablaPos[i]==49)){
                    malPos++;
                    tablaPos[j]=50;//pone un 2
                    input=false;
                }
            }
        }
    }
    /*--------------Metodos sobreescritos de Object-------------------*/
    //Metodo toString
    @Override
    public String toString(){
        return new String(numRes)+"\t"+bienPos+"\t"+
                malPos+"\t"+new String(tablaPos);
    }
}
