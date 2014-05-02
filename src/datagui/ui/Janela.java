package datagui.ui;

import datagui.model.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.*;
import java.lang.*;


public class Janela extends JFrame {

    private JTextField txtData, txtDiaDaSemana;
    private JLabel lblDataPorExtenso;
    private JButton btDiaDaSemana, btDataPorExtenso,btLimpar;
    
    public Janela() {
        super("Data GUI");
        GridLayout gl = new GridLayout(4, 1);
        setLayout(gl);
        TrataEvento trataEvento = new TrataEvento();
        add(criarPainelInserirData(), BorderLayout.NORTH);
        add(criarPainelDiaDaSemana(), BorderLayout.AFTER_LAST_LINE);
        add(criarPainelDataPorExtensao(),  BorderLayout.AFTER_LAST_LINE);
        add(criarPainelBotoes()), );
       
        add(CriarPainelCentro(), BorderLayout.CENTER);
        add(CriarPainelBotoes(), BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        
        
        setSize(400, 300);
        setVisible(true);
        
    }
    private JPanel criarPainelInserirData() {
        JLabel lblData = new JLabel("Data(aaaa/mm/dd):");
        txtData = new JTextField(10);
        txtData.addActionListener(trataEvento);
        
        JPanel p = new JPanel();
        p.setBorder(new EmptyBorder(10,10,10,10));
        p.add(lblData); p.add(txtData);
       
        return p;
        
    }
    private JPanel criarPainelDiaDaSemana(){
        JLabel lblDiaSem = new JLabel("Dia da semana:");
        txtData = new JTextField(12);
        txtData.setEditable(false);
        
        JPanel p = new JPanel();
        p.setBorder( new EmptyBorder(10,10,10,10));
        p.add(lblDiaSem); p.add(txtData);
        
        return p;
    }
    
    private JPanel criarPainelDataPorExtenso(){
        JLabel lblDataPorExtenso = new JLabel();
        
        JPanel p = new JPanel();
        p.setBorder(new EmptyBorder(10,10,10,10));
        p.add(lblDataPorExtenso);
        
        return p;
    }
    private JButton criarBotaoDiaDaSemana(){
        JButton bt = new JButton("Dia da Semana");
        bt.setMnemonic(KeyEvent.VK_S);
        bt.setToolTipText("Determina o dia da semana");
        bt.addActionListener(trataEvento);
        
        return bt;
    }
    private JButton criarBotaoLimpar(){
        JButton bt = new JButton("Limpar");
        bt.setMnemonic(KeyEvent.VK_L);
        bt.setToolTipText("Limpa a data");
        bt.addActionListener(trataEvento);
        
        return bt;
    }
    private JButton criarBotaodataPorExtenso(){
        JButton bt = new JButton("Data por extenso");
        bt.setMnemonic(KeyEvent.VK_S);
        bt.setToolTipText("Imprime a data por extenso");
        bt.addActionListener(trataEvento);
        
        return bt;
    }
    /*
    private JPanel criarPainelBotoes(){
        JButton bt1 = new JButton("Dia da Semana");
        JButton bt2 = new JButton("Data por Extenso");
        JButton bt3 = new JButton("Limpar");
        
        JPanel p = new JPanel();
        p.setBorder(new EmptyBorder(10,10,10,10));
        p.add(bt1); p.add(bt2); p.add(bt3);
        
        return p;
        
    }*/
    
    private class TrataEvento implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                Object obj = e.getSource();
                if(obj== btDiaDaSemana){
                    txtDiaDaSemana.setText(getData().DiaDaSemana());
                    lblDataPorExtenso.setText(null);
                 } else if (obj == btDataPorExtenso){
                     lblDataPorExtenso.setText(getData().toString());
                 } else if (obj == btLimpar){
                     txtData.setText(null);
                     txtDiaDaSemana.setText(null);
                     lblDataPorExtenso.setText(null);
                     txtData.requestFocus();
                 } else if (obj == txtData){
                     txtDiaDaSemana.setText(getData().diaDaSemana());
                     lblDataPorExtenso.setText(getData().toString());
                 }
            } catch(DiaInvalidoException | MesInvalidoException ex){
                lblDataPorExtenso.setText(null);
                txtDiaDaSemana.setText(null);
                JOptionPane.showMessageDialog(Janela.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                txtData.requestFocus();
            } catch(NumberFormatException | ArrayIndexOutOfBounderiesException ex){
                JOptionPane.showMessageDialog(Janela.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                txtData.requestFocus();
            }
        }
    }

}
