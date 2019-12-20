package Codigo.Entornografico;

import Codigo.Juego.PartidaBD;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MasterMind {
    //Ventana
    JFrame frame=new JFrame("Master Mind");
    JPanel panel=(JPanel)frame.getContentPane();
    GroupLayout diseny=new GroupLayout(panel);

    //Barra del menu
    JMenuBar bar=new JMenuBar();
    JMenu juego=new JMenu("Juego");

    //Pantalla de inicio
    JLabel titulo=new JLabel(new ImageIcon("img\\Title\\titulo.jpg", "titulo"));
    JMenuItem nPartidaMenu =new JMenuItem("Nueva Partida");
    JMenuItem cargarMenu =new JMenuItem("Cargar Partida");
    JMenuItem borrarBD =new JMenuItem("Borrar BD");
    JButton cargar=new JButton("Cargar Patida");
    JButton nPartida=new JButton("Nueva Partida");
    JButton opciones =new JButton("Opciones");

    public MasterMind(){
        //Por defecto
        frame.setSize(500,600);
        panel.setLayout(diseny);
        panel.setBackground(new Color(47,64,84));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //----------------------------Barra menu----------------------------//
        juego.add(nPartidaMenu);juego.add(cargarMenu);juego.add(borrarBD);
        bar.add(juego);frame.setJMenuBar(bar);
        //-------------------Pantalla de inicio---------------------//

        //Hz group
        GroupLayout.ParallelGroup hgIni=diseny.createParallelGroup();

        GroupLayout.SequentialGroup hg1Ini=diseny.createSequentialGroup();
        GroupLayout.SequentialGroup hgTitulo=diseny.createSequentialGroup();

        hg1Ini.addGap(25,50,75);
        hg1Ini.addComponent(cargar);hg1Ini.addGap(25,50,75);
        hg1Ini.addComponent(nPartida);hg1Ini.addGap(25,50,75);
        hg1Ini.addComponent(opciones);hg1Ini.addGap(25,50,75);
        hgTitulo.addComponent(titulo);
        hgIni.addGroup(GroupLayout.Alignment.CENTER,hgTitulo);hgIni.addGroup(hg1Ini);

        //Vt group
        GroupLayout.SequentialGroup vgIni=diseny.createSequentialGroup();

        GroupLayout.ParallelGroup vg1Ini=diseny.createParallelGroup();
        vg1Ini.addComponent(cargar);vg1Ini.addComponent(nPartida);
        vg1Ini.addComponent(opciones);

        vgIni.addGap(50,100,200);vgIni.addComponent(titulo);
        vgIni.addGap(50,100,200);vgIni.addGroup(vg1Ini);
        vgIni.addGap(50,100,200);

        diseny.setHorizontalGroup(hgIni);diseny.setVerticalGroup(vgIni);
        //Actions
        opciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        borrarBD.addActionListener(e -> PartidaBD.borrarBD());
        nPartida.addActionListener(new BotonPartida());
        nPartidaMenu.addActionListener(new BotonPartida());
        cargar.addActionListener(new BotonCarga());
        cargarMenu.addActionListener (new BotonCarga ());
        opciones.setEnabled(false);
        //Hacer visible
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    //Clase para crear una partida
    private class BotonPartida implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new InterficiePartida();
            frame.dispose();
        }
    }
    //Clase para crear la pantalla de cargar Partidas
    private class BotonCarga implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new CargarPartidas();
            frame.dispose();
        }
    }

    public static void main (String []args){ new MasterMind(); }
 }
