package Codigo.Entornografico;

import Codigo.Interficies.Imagenes;
import Codigo.Juego.Partida;
import Codigo.Juego.PartidaBD;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InterficieTirada implements Imagenes {
    JLabel num1 = new JLabel();JLabel num2 = new JLabel();
    JLabel num3 = new JLabel();JLabel num4 = new JLabel();
    JLabel num5 = new JLabel();

    JLabel bienPos = new JLabel();JLabel malPos = new JLabel();

    JLabel pos1 = new JLabel();JLabel pos2 = new JLabel();
    JLabel pos3 = new JLabel();JLabel pos4 = new JLabel();
    JLabel pos5 = new JLabel();

    public JPanel tirada(Partida p1, Icon i1, Icon i2, Icon i3, Icon i4, Icon i5) {
        JPanel panel = new JPanel();
        GroupLayout diseny = new GroupLayout(panel);
        panel.setLayout(diseny);
        panel.setBackground(Color.WHITE);
        byte[] numRes = new byte[5];

        ArrayList<JLabel> posiciones = new ArrayList<>();
        posiciones.add(pos1);posiciones.add(pos2);
        posiciones.add(pos3);posiciones.add(pos4);
        posiciones.add(pos5);

        String res = "";//String de la tirada
        res += ((ImageIcon) i1).getDescription();
        res += ((ImageIcon) i2).getDescription();
        res += ((ImageIcon) i3).getDescription();
        res += ((ImageIcon) i4).getDescription();
        res += ((ImageIcon) i5).getDescription();

        for(int i = 0; i < numRes.length; i++) numRes[i] = (byte) res.charAt(i);

        p1.addTirada(numRes);

        //Comprueba si se ha ganado con esa tirada
        new InterficiePartida(p1.winGame(numRes));

        num1.setIcon(getScaledImage(i1));
        num2.setIcon(getScaledImage(i2));
        num3.setIcon(getScaledImage(i3));
        num4.setIcon(getScaledImage(i4));
        num5.setIcon(getScaledImage(i5));

        String trocear = p1.toString(((PartidaBD)p1).getLevel());

        String tirada = ((ImageIcon) i1).getDescription() + ((ImageIcon) i2).getDescription() +
                ((ImageIcon) i3).getDescription() + ((ImageIcon) i4).getDescription() +
                ((ImageIcon) i5).getDescription();
        String numPos = "", bp, mp;

        if(((PartidaBD)p1).getLevel()){
            bienPos.setText(bp = (trocear.substring(trocear.length() - 10, trocear.length() - 9)));
            malPos.setText(mp = (trocear.substring(trocear.length() - 8, trocear.length() - 7)));
            numPos = trocear.substring(trocear.length() - 6);
            for(int i = 0; i < numPos.length(); i++){
                if(numPos.charAt(i) == '0') posiciones.get(i).setIcon(fatal);
                else if(numPos.charAt(i) == '1') posiciones.get(i).setIcon(bien);
                else if(numPos.charAt(i) == '2') posiciones.get(i).setIcon(mal);
            }
        }else {
            bienPos.setText(bp = (trocear.substring(trocear.length() - 4, trocear.length() - 3)));
            malPos.setText(mp = (trocear.substring(trocear.length() - 2, trocear.length() - 1)));
        }
        ((PartidaBD) p1).insertarTirada(tirada, bp, mp, numPos);

        GroupLayout.SequentialGroup hg = diseny.createSequentialGroup();
        hg.addComponent(num1);hg.addComponent(num2);
        hg.addComponent(num3);hg.addComponent(num4);
        hg.addComponent(num5);

        hg.addGap(5);
        hg.addComponent(bienPos);hg.addGap(10);hg.addComponent(malPos);
        hg.addGap(5);

        hg.addComponent(pos1);hg.addComponent(pos2);
        hg.addComponent(pos3);hg.addComponent(pos4);
        hg.addComponent(pos5);

        GroupLayout.ParallelGroup vg = diseny.createParallelGroup();
        vg.addComponent(num1);vg.addComponent(num2);
        vg.addComponent(num3);vg.addComponent(num4);
        vg.addComponent(num5);

        vg.addComponent(bienPos);vg.addComponent(malPos);

        vg.addComponent(pos1);vg.addComponent(pos2);
        vg.addComponent(pos3);vg.addComponent(pos4);
        vg.addComponent(pos5);

        diseny.setHorizontalGroup(hg);
        diseny.setVerticalGroup(vg);
        return panel;
    }

    public JPanel mostrarTirada(ArrayList<String> listaTiradas) {
        JPanel panel = new JPanel();
        GroupLayout diseny = new GroupLayout(panel);
        panel.setLayout(diseny);

        JLabel imag0 = new JLabel(dragon);JLabel imag1 = new JLabel(dragonTierra);
        JLabel imag2 = new JLabel(fenix);JLabel imag3 = new JLabel(lion);
        JLabel imag4 = new JLabel(sirena);JLabel imag5 = new JLabel(dragonChino);
        JLabel imag6 = new JLabel(espantapajaros);JLabel imag7 = new JLabel(octopus);
        JLabel imag8 = new JLabel(renoalado);JLabel imag9 = new JLabel(troll);

        JLabel neutro = new JLabel(igni);

        ArrayList<JLabel> imagenes = new ArrayList<>();//Lista de imagenes posibles
        imagenes.add(imag0);imagenes.add(imag1);imagenes.add(imag2);
        imagenes.add(imag3);imagenes.add(imag4);imagenes.add(imag5);
        imagenes.add(imag6);imagenes.add(imag7);imagenes.add(imag8);
        imagenes.add(imag9);imagenes.add(neutro);

        JLabel bien = new JLabel();JLabel mal = new JLabel();

        GroupLayout.ParallelGroup hg = diseny.createParallelGroup();
        GroupLayout.SequentialGroup vg = diseny.createSequentialGroup();

        for(String tirada : listaTiradas){

            String numTirada = tirada.substring(0,5);//Numero de tirada

            bien.setText(tirada.substring(5,7));//Bien posicionados
            mal.setText(tirada.substring(7,9));//Mal posicionados

            if(tirada.length()>9) {
                String numPos = tirada.substring(10);//Numero de posicionados
            }

            //Testeo de acciones
            JLabel meRindo=new JLabel(tirada);
            hg.addComponent(meRindo);
            vg.addComponent(meRindo);
        }

        diseny.setHorizontalGroup(hg);
        diseny.setVerticalGroup(vg);
        return panel;
    }

    //Metodo para redimencionar una imagen a un tamaño concreto
    private static ImageIcon getScaledImage (Icon srcImg) {
        Image img = ((ImageIcon)srcImg).getImage();
        Image newImg = img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}
