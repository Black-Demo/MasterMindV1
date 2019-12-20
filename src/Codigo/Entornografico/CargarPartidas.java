package Codigo.Entornografico;

import Codigo.Juego.PartidaBD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.SwingUtilities.updateComponentTreeUI;

public class CargarPartidas {
    private JFrame frame=new JFrame("cargar Partida");

    private List partidas;
    private JPanel tiradas= new JPanel();

    private JButton jugarPartida=new JButton("Jugar");

    CargarPartidas(){
        //Comporbamos primero que haya una base de datos creada
        JPanel panel = (JPanel) frame.getContentPane();
        GroupLayout diseny = new GroupLayout(panel);
        if(PartidaBD.cargarBD()){
            frame.setSize(500, 500);
            panel.setBackground(Color.WHITE);
            panel.setLayout(diseny);

            jugarPartida.setEnabled(false);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            partidas = InterficiePartida.mostrarPartidas(PartidaBD.cargarPartidas());

            boolean[] finalizada = new boolean[1];

            GroupLayout.SequentialGroup hg = diseny.createSequentialGroup();
            GroupLayout.ParallelGroup vg = diseny.createParallelGroup();

            GroupLayout.ParallelGroup hg1 = diseny.createParallelGroup();
            GroupLayout.SequentialGroup hg1_1 = diseny.createSequentialGroup();
            GroupLayout.SequentialGroup vg1 = diseny.createSequentialGroup();
            GroupLayout.ParallelGroup vg1_1 = diseny.createParallelGroup();

            GroupLayout.ParallelGroup hg2 = diseny.createParallelGroup();
            GroupLayout.ParallelGroup hg2_1 = diseny.createParallelGroup();
            GroupLayout.SequentialGroup vg2 = diseny.createSequentialGroup();
            GroupLayout.SequentialGroup vg2_1 = diseny.createSequentialGroup();

            hg1_1.addComponent(partidas);
            vg1_1.addComponent(partidas);

            hg1.addGroup(hg1_1);
            JButton cargarPartida = new JButton("Cargar");
            hg1.addComponent(cargarPartida);
            hg2.addGroup(hg2_1);
            hg2.addComponent(jugarPartida);
            vg1.addGroup(vg1_1);
            vg1.addComponent(cargarPartida);
            vg2.addGroup(vg2_1);
            vg2.addComponent(jugarPartida);
            vg1.addGap(50);
            JButton volver = new JButton("volver al menu");
            hg1.addComponent(volver);
            vg1.addComponent(volver);

            hg.addGroup(hg1);
            hg.addGroup(hg2);
            vg.addGroup(vg1);
            vg.addGroup(vg2);

            diseny.setHorizontalGroup(hg);
            diseny.setVerticalGroup(vg);

            cargarPartida.addActionListener(e -> {
                PartidaBD p1 = new PartidaBD(partidas.getSelectedIndex()+1);
                String partida = partidas.getSelectedItem();
                //Limpia el panel para introducir los datos nuevos
                tiradas.removeAll();
                tiradas.repaint();
                    //Añade al panel las tiradas de una correspondiente partida
                tiradas = new InterficieTirada().mostrarTirada(PartidaBD.cargarTirada(partidas.getSelectedIndex() + 1));
                    //booleano que comprueba si la partida esta finalizada
                finalizada[0] = partida.substring(partida.indexOf("da:") + 4, partida.indexOf("da:") + 6).equals("Si");
                hg2_1.addComponent(tiradas);
                vg2_1.addComponent(tiradas);
                    //actualizar pantalla
                actualizarPantalla(frame);
                frame.repaint();
                if(! finalizada[0]) jugarPartida.setEnabled(true);
                else jugarPartida.setEnabled(false);
            });

            jugarPartida.addActionListener(e -> {
                new InterficiePartida(partidas.getSelectedItem());
                frame.dispose();
            });

            volver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new MasterMind();
                    frame.dispose();
                }
            });

            frame.setLocationRelativeTo(null);
            frame.setResizable(true);
            frame.setVisible(true);
        }else{
            //Si no hay una BD creada te da la opcion de comenzar una o volver al menu incial
            JLabel mostrar = new JLabel("Lo sentimos no tienes ninguna partida guardada");
            JButton aceptar = new JButton("Echemonos una");
            JButton paso = new JButton("Pues paso");

            frame.setSize(280, 150);
            panel.setBackground(Color.WHITE);
            panel.setLayout(diseny);

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            GroupLayout.ParallelGroup hg = diseny.createParallelGroup();
            GroupLayout.SequentialGroup vg = diseny.createSequentialGroup();

            GroupLayout.SequentialGroup hg1 = diseny.createSequentialGroup();
            GroupLayout.ParallelGroup vg1 = diseny.createParallelGroup();

            hg1.addGap(20);
            hg1.addComponent(aceptar);hg1.addGap(20);hg1.addComponent(paso);
            vg1.addComponent(aceptar);vg1.addComponent(paso);

            vg.addGap(20);
            hg.addComponent(mostrar);vg.addComponent(mostrar);
            vg.addGap(30);
            hg.addGroup(hg1);vg.addGroup(vg1);

            diseny.setHorizontalGroup(hg);
            diseny.setVerticalGroup(vg);

            //Actions
            aceptar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e) {
                    new InterficiePartida();
                    frame.dispose();
                }
            });

            paso.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e) {
                    new MasterMind();
                    frame.dispose();
                }
            });

            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
        }
    }

    private void actualizarPantalla(JFrame frame){
        JPanel temp= (JPanel) frame.getContentPane();
        updateComponentTreeUI(temp);
        temp.validate();
    }
}
