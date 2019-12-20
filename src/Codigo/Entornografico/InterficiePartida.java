package Codigo.Entornografico;



import Codigo.Juego.Partida;
import Codigo.Juego.PartidaBD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static javax.swing.SwingUtilities.updateComponentTreeUI;

class InterficiePartida {
    private int cont = 10;
    private static boolean dificultad;
    private static boolean won;
    JFrame frame = new JFrame ("Nueva Partida");
    JPanel panel = (JPanel) frame.getContentPane ();
    GroupLayout diseny = new GroupLayout (panel);
    private Partida p1;

    //Barra del menu
    JMenuBar bar = new JMenuBar ();
    JMenu juego = new JMenu ("Juego");
    JMenuItem nPartidaMenu =new JMenuItem("Nueva Partida");
    JMenuItem cargarMenu =new JMenuItem("Cargar Partida");
    JMenuItem rendirse = new JMenuItem ("Surrender");

    /*Tirada*/
    JButton enviar = new JButton ("Send");
    JLabel res1 = new JLabel (new ImageIcon ("img\\TheWitcher\\Runes\\AARD.png", "-"));
    JLabel res2 = new JLabel (new ImageIcon ("img\\TheWitcher\\Runes\\QUEN.png", "-"));
    JLabel res3 = new JLabel (new ImageIcon ("img\\TheWitcher\\Runes\\IGNI.png", "-"));
    JLabel res4 = new JLabel (new ImageIcon ("img\\TheWitcher\\Runes\\YRDEN.png", "-"));
    JLabel res5 = new JLabel (new ImageIcon ("img\\TheWitcher\\Runes\\AXII.png", "-"));

    JTextField info = new JTextField("");

    /*Imagenes*/
    JLabel dragon = new JLabel (new ImageIcon ("img\\TheWitcher\\Creatures\\dragon.png", "0"));
    JLabel dragonTierra = new JLabel (new ImageIcon ("img\\TheWitcher\\Creatures\\dragonTierra.png", "1"));
    JLabel fenix = new JLabel (new ImageIcon ("img\\TheWitcher\\Creatures\\Fenix.png", "2"));
    JLabel lion = new JLabel (new ImageIcon ("img\\TheWitcher\\Creatures\\lion.png", "3"));
    JLabel sirena = new JLabel (new ImageIcon ("img\\TheWitcher\\Creatures\\Sirena.png", "4"));
    JLabel dragonChino = new JLabel (new ImageIcon ("img\\TheWitcher\\Creatures\\DragonChino.png", "5"));
    JLabel espantapajaros = new JLabel (new ImageIcon ("img\\TheWitcher\\Creatures\\espantapajaros.png", "6"));
    JLabel octopus = new JLabel (new ImageIcon ("img\\TheWitcher\\Creatures\\Octopus.png", "7"));
    JLabel renoAlado = new JLabel (new ImageIcon ("img\\TheWitcher\\Creatures\\renoalado.png", "8"));
    JLabel troll = new JLabel (new ImageIcon ("img\\TheWitcher\\Creatures\\troll.png", "9"));

    InterficiePartida( ) {
        p1 = new PartidaBD();
        frame.setSize (1550, 560);
        panel.setBackground (Color.WHITE);
        panel.setLayout (diseny);
        frame.setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE);
        won = false;

        String botonSeleccion = String.valueOf (JOptionPane.showConfirmDialog (frame, "¿Lo desea en modo facil?"));
        dificultad = botonSeleccion.equals ("0");
        ((PartidaBD)p1).setLevel (dificultad);
        //----------------------------Barra menu----------------------------//
        juego.add (nPartidaMenu);juego.add (cargarMenu);juego.add (rendirse);
        bar.add (juego);
        frame.setJMenuBar (bar);

        //Horizontal Group
        GroupLayout.SequentialGroup hg = diseny.createSequentialGroup();
        //Grupo de Imagenes, respuestas y boton de enviar
        GroupLayout.ParallelGroup hg1 = diseny.createParallelGroup();
        //Grupo de Imagenes
        GroupLayout.SequentialGroup hg1_0 = diseny.createSequentialGroup();
        //Grupo de respuesta
        GroupLayout.SequentialGroup hg1_1 = diseny.createSequentialGroup();
        //Grupo de botones
        GroupLayout.SequentialGroup hg1_2 = diseny.createSequentialGroup();
        //Grupo de tiradas
        GroupLayout.ParallelGroup hg2 = diseny.createParallelGroup();

        //Grupo vertical
        GroupLayout.ParallelGroup vg=diseny.createParallelGroup();
        //Grupo de Imagenes, respuestas y boton de enviar
        GroupLayout.SequentialGroup vg1 = diseny.createSequentialGroup();
        //Grupo de Imagenes
        GroupLayout.ParallelGroup vg1_0 = diseny.createParallelGroup();
        //Grupo de respuesta
        GroupLayout.ParallelGroup vg1_1 = diseny.createParallelGroup();
        //Grupo de tiradas
        GroupLayout.ParallelGroup vg1_2 = diseny.createParallelGroup();
        //Grupo de tiradas
        GroupLayout.SequentialGroup vg2 = diseny.createSequentialGroup();

        //Añadir grupo horizontal
        hg1_0.addComponent(dragon);hg1_0.addComponent(dragonTierra);
        hg1_0.addComponent(fenix);hg1_0.addComponent(lion);
        hg1_0.addComponent(sirena);hg1_0.addComponent(dragonChino);
        hg1_0.addComponent(espantapajaros);hg1_0.addComponent(octopus);
        hg1_0.addComponent(renoAlado);hg1_0.addComponent(troll);

        hg1_1.addComponent(res1);hg1_1.addComponent(res2);hg1_1.addComponent(res3);
        hg1_1.addComponent(res4);hg1_1.addComponent(res5);

        hg1_2.addComponent(enviar);

        hg1.addGroup(hg1_0);hg1.addGroup(GroupLayout.Alignment.CENTER,hg1_1);hg1.addGroup(GroupLayout.Alignment.CENTER,hg1_2);

        //hg2.addComponent(test);

        hg.addGroup(hg1);hg.addGroup(hg2);
        //Añadir grupo vertical
        vg1_0.addComponent(dragon);vg1_0.addComponent(dragonTierra);
        vg1_0.addComponent(fenix);vg1_0.addComponent(lion);
        vg1_0.addComponent(sirena);vg1_0.addComponent(dragonChino);
        vg1_0.addComponent(espantapajaros);vg1_0.addComponent(octopus);
        vg1_0.addComponent(renoAlado);vg1_0.addComponent(troll);

        vg1_1.addComponent(res1);vg1_1.addComponent(res2);vg1_1.addComponent(res3);
        vg1_1.addComponent(res4);vg1_1.addComponent(res5);

        vg1_2.addComponent(enviar);

        vg1.addGap(50);vg1.addGroup(vg1_0);vg1.addGap(50);vg1.addGroup(vg1_1);vg1.addGap(50);vg1.addGroup(vg1_2);vg1.addGap(100);

        //vg2.addComponent(test);

        vg.addGroup(vg1);vg.addGroup(vg2);

        /*Action's*/
        dragon.addMouseListener (new DragAndDrop());
        dragon.setTransferHandler (new TransferHandler ("icon"));
        dragonTierra.addMouseListener (new DragAndDrop());
        dragonTierra.setTransferHandler (new TransferHandler ("icon"));
        fenix.addMouseListener (new DragAndDrop());
        fenix.setTransferHandler (new TransferHandler ("icon"));
        lion.addMouseListener (new DragAndDrop());
        lion.setTransferHandler (new TransferHandler ("icon"));
        sirena.addMouseListener (new DragAndDrop());
        sirena.setTransferHandler (new TransferHandler ("icon"));
        dragonChino.addMouseListener (new DragAndDrop());
        dragonChino.setTransferHandler (new TransferHandler ("icon"));
        espantapajaros.addMouseListener (new DragAndDrop());
        espantapajaros.setTransferHandler (new TransferHandler ("icon"));
        octopus.addMouseListener (new DragAndDrop());
        octopus.setTransferHandler (new TransferHandler ("icon"));
        renoAlado.addMouseListener (new DragAndDrop());
        renoAlado.setTransferHandler (new TransferHandler ("icon"));
        troll.addMouseListener (new DragAndDrop());
        troll.setTransferHandler (new TransferHandler ("icon"));

        //Respuesta
        res1.addMouseListener (new DragAndDrop());
        res1.setTransferHandler (new TransferHandler ("icon"));
        res2.addMouseListener (new DragAndDrop());
        res2.setTransferHandler (new TransferHandler ("icon"));
        res3.addMouseListener (new DragAndDrop());
        res3.setTransferHandler (new TransferHandler ("icon"));
        res4.addMouseListener (new DragAndDrop());
        res4.setTransferHandler (new TransferHandler ("icon"));
        res5.addMouseListener (new DragAndDrop());
        res5.setTransferHandler (new TransferHandler ("icon"));

        enviar.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                cont--;
                JPanel t1 = new InterficieTirada().tirada (p1, res1.getIcon (), res2.getIcon (), res3.getIcon (), res4.getIcon (), res5.getIcon ());
                if((! won) && cont > 0){
                    hg2.addComponent (t1);
                    vg2.addComponent (t1);
                    actualizarPantalla(frame);
                }else if(cont <= 0){
                    JOptionPane.showMessageDialog (new JPanel (), "", "DERROTA",
                            JOptionPane.INFORMATION_MESSAGE, new ImageIcon ("img\\Title\\lose.gif"));
                    ((PartidaBD) p1).setFinalizada (true);
                    new MasterMind();
                    frame.dispose ();
                }
                if(won){
                    JOptionPane.showMessageDialog (new JPanel (), "", "VICTORIA",
                            JOptionPane.INFORMATION_MESSAGE, new ImageIcon ("img\\Title\\win.gif"));
                    ((PartidaBD) p1).setFinalizada (true);
                    new MasterMind ();
                    frame.dispose ();
                }
            }
        });

        diseny.setHorizontalGroup (hg);
        diseny.setVerticalGroup (vg);

        nPartidaMenu.addActionListener (new NuevaPartida());
        cargarMenu.addActionListener(new MenuCarga());
        rendirse.addActionListener (new Rendirse());

        frame.setLocationRelativeTo (null);
        frame.setResizable (false);
        frame.setVisible (true);
    }

    InterficiePartida(String partida) {
        frame.setSize (1550, 560);
        panel.setBackground (Color.WHITE);
        panel.setLayout (diseny);
        frame.setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE);

        int id = Integer.parseInt (partida.substring (7, 8));
        p1 = new PartidaBD (id);
        final JPanel[] t1 = new JPanel[1];
        won = false;
        t1[0] = new InterficieTirada().mostrarTirada(PartidaBD.cargarTirada(id));
        dificultad = ((PartidaBD)p1).getLevel();
        cont = 10- PartidaBD.numTiradas(id);

        //----------------------------Barra menu----------------------------//
        juego.add (nPartidaMenu);juego.add (cargarMenu);juego.add (rendirse);
        bar.add (juego);
        frame.setJMenuBar (bar);

        //Horizontal Group
        GroupLayout.SequentialGroup hg = diseny.createSequentialGroup();
        //Grupo de Imagenes, respuestas y boton de enviar
        GroupLayout.ParallelGroup hg1 = diseny.createParallelGroup();
        //Grupo de Imagenes
        GroupLayout.SequentialGroup hg1_0 = diseny.createSequentialGroup();
        //Grupo de respuesta
        GroupLayout.SequentialGroup hg1_1 = diseny.createSequentialGroup();
        //Grupo de botones
        GroupLayout.SequentialGroup hg1_2 = diseny.createSequentialGroup();
        //Grupo de tiradas
        GroupLayout.ParallelGroup hg2 = diseny.createParallelGroup();

        //Grupo vertical
        GroupLayout.ParallelGroup vg=diseny.createParallelGroup();
        //Grupo de Imagenes, respuestas y boton de enviar
        GroupLayout.SequentialGroup vg1 = diseny.createSequentialGroup();
        //Grupo de Imagenes
        GroupLayout.ParallelGroup vg1_0 = diseny.createParallelGroup();
        //Grupo de respuesta
        GroupLayout.ParallelGroup vg1_1 = diseny.createParallelGroup();
        //Grupo de tiradas
        GroupLayout.ParallelGroup vg1_2 = diseny.createParallelGroup();
        //Grupo de tiradas
        GroupLayout.SequentialGroup vg2 = diseny.createSequentialGroup();

        //Añadir grupo horizontal
        hg1_0.addComponent(dragon);hg1_0.addComponent(dragonTierra);
        hg1_0.addComponent(fenix);hg1_0.addComponent(lion);
        hg1_0.addComponent(sirena);hg1_0.addComponent(dragonChino);
        hg1_0.addComponent(espantapajaros);hg1_0.addComponent(octopus);
        hg1_0.addComponent(renoAlado);hg1_0.addComponent(troll);

        hg1_1.addComponent(res1);hg1_1.addComponent(res2);hg1_1.addComponent(res3);
        hg1_1.addComponent(res4);hg1_1.addComponent(res5);

        hg1_2.addComponent(enviar);

        hg1.addGroup(hg1_0);hg1.addGroup(GroupLayout.Alignment.CENTER,hg1_1);hg1.addGroup(GroupLayout.Alignment.CENTER,hg1_2);

        //hg2.addComponent(test);

        hg.addGroup(hg1);hg.addGroup(hg2);
        //Añadir grupo vertical
        vg1_0.addComponent(dragon);vg1_0.addComponent(dragonTierra);
        vg1_0.addComponent(fenix);vg1_0.addComponent(lion);
        vg1_0.addComponent(sirena);vg1_0.addComponent(dragonChino);
        vg1_0.addComponent(espantapajaros);vg1_0.addComponent(octopus);
        vg1_0.addComponent(renoAlado);vg1_0.addComponent(troll);

        vg1_1.addComponent(res1);vg1_1.addComponent(res2);vg1_1.addComponent(res3);
        vg1_1.addComponent(res4);vg1_1.addComponent(res5);

        vg1_2.addComponent(enviar);

        vg1.addGap(50);vg1.addGroup(vg1_0);vg1.addGap(50);vg1.addGroup(vg1_1);vg1.addGap(50);vg1.addGroup(vg1_2);vg1.addGap(100);

        //vg2.addComponent(test);

        vg.addGroup(vg1);vg.addGroup(vg2);

        /*Action's*/
        dragon.addMouseListener (new DragAndDrop());
        dragon.setTransferHandler (new TransferHandler ("icon"));
        dragonTierra.addMouseListener (new DragAndDrop());
        dragonTierra.setTransferHandler (new TransferHandler ("icon"));
        fenix.addMouseListener (new DragAndDrop());
        fenix.setTransferHandler (new TransferHandler ("icon"));
        lion.addMouseListener (new DragAndDrop());
        lion.setTransferHandler (new TransferHandler ("icon"));
        sirena.addMouseListener (new DragAndDrop());
        sirena.setTransferHandler (new TransferHandler ("icon"));
        dragonChino.addMouseListener (new DragAndDrop());
        dragonChino.setTransferHandler (new TransferHandler ("icon"));
        espantapajaros.addMouseListener (new DragAndDrop());
        espantapajaros.setTransferHandler (new TransferHandler ("icon"));
        octopus.addMouseListener (new DragAndDrop());
        octopus.setTransferHandler (new TransferHandler ("icon"));
        renoAlado.addMouseListener (new DragAndDrop());
        renoAlado.setTransferHandler (new TransferHandler ("icon"));
        troll.addMouseListener (new DragAndDrop());
        troll.setTransferHandler (new TransferHandler ("icon"));

        //Respuesta
        res1.addMouseListener (new DragAndDrop());
        res1.setTransferHandler (new TransferHandler ("icon"));
        res2.addMouseListener (new DragAndDrop());
        res2.setTransferHandler (new TransferHandler ("icon"));
        res3.addMouseListener (new DragAndDrop());
        res3.setTransferHandler (new TransferHandler ("icon"));
        res4.addMouseListener (new DragAndDrop());
        res4.setTransferHandler (new TransferHandler ("icon"));
        res5.addMouseListener (new DragAndDrop());
        res5.setTransferHandler (new TransferHandler ("icon"));


        hg2.addComponent(t1[0]);
        vg2.addComponent(t1[0]);

        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cont--;
                t1[0] = new InterficieTirada().tirada(p1, res1.getIcon(), res2.getIcon(), res3.getIcon(), res4.getIcon(), res5.getIcon());
                if ((!won) && cont > 0) {
                    hg2.addComponent(t1[0]);
                    vg2.addComponent(t1[0]);
                    InterficiePartida.this.actualizarPantalla(frame);
                } else if (cont <= 0) {
                    JOptionPane.showMessageDialog(new JPanel(), "", "DERROTA",
                            JOptionPane.INFORMATION_MESSAGE, new ImageIcon("img\\Title\\lose.gif"));
                    ((PartidaBD) p1).setFinalizada(true);
                    new MasterMind();
                    frame.dispose();
                }
                if (won) {
                    JOptionPane.showMessageDialog(new JPanel(), "", "VICTORIA",
                            JOptionPane.INFORMATION_MESSAGE, new ImageIcon("img\\Title\\win.gif"));
                    ((PartidaBD) p1).setFinalizada(true);
                    new MasterMind();
                    frame.dispose();
                }
            }
        });

        nPartidaMenu.addActionListener(new NuevaPartida());
        cargarMenu.addActionListener(new MenuCarga());
        rendirse.addActionListener(new Rendirse());

        diseny.setHorizontalGroup(hg);
        diseny.setVerticalGroup(vg);

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    InterficiePartida(boolean won) {
        InterficiePartida.won = won;
    }

    private class DragAndDrop implements MouseListener {
        @Override
        public void mouseClicked (MouseEvent e) {
        }

        @Override
        public void mousePressed (MouseEvent e) {
            JComponent jc = (JComponent) e.getSource();
            TransferHandler th = jc.getTransferHandler();
            th.exportAsDrag(jc, e, TransferHandler.COPY);
        }

        @Override
        public void mouseReleased (MouseEvent e) {
        }

        @Override
        public void mouseEntered (MouseEvent e) {
        }

        @Override
        public void mouseExited (MouseEvent e) {
        }
    }

    private class MenuCarga implements ActionListener{
        @Override
        public void actionPerformed (ActionEvent e) {
            new CargarPartidas();
            frame.dispose();
        }
    }

    private class NuevaPartida implements ActionListener{
        @Override
        public void actionPerformed (ActionEvent e){
            new InterficiePartida();
            frame.dispose();
        }
    }

    private class Rendirse implements ActionListener{
        @Override
        public void actionPerformed (ActionEvent e){
            JOptionPane.showMessageDialog (new JPanel (), "", "DERROTA",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon ("img\\Title\\lose.gif"));
            ((PartidaBD) p1).setFinalizada(true);
            new MasterMind ();
            frame.dispose ();
        }

    }

    static List mostrarPartidas(ArrayList<String> arrayPartidas) {
        List listaPartida = new List();
        for(String partida : arrayPartidas){
            listaPartida.add(partida);
        }
        return listaPartida;
    }

    private void actualizarPantalla(JFrame frame){
        JPanel temp= (JPanel) frame.getContentPane();
        updateComponentTreeUI(temp);
        temp.validate();
    }
}
