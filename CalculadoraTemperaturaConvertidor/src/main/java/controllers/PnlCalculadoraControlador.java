/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import validaciones.ValidarNumero;
import views.panels.PnlCalculadora;

/**
 *
 * @author UZIEL
 */
public class PnlCalculadoraControlador implements ActionListener
{
    private PnlCalculadora pnlCalculadora;
    
    public PnlCalculadoraControlador(PnlCalculadora pnlCalculadora)
    {
        this.pnlCalculadora = pnlCalculadora;
        initComponent();
    }
    
    private void initComponent() {
    pnlCalculadora.getBtnCalcular().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equalsIgnoreCase("Calcular"))
        {
            double n1, n2;
            if(ValidarNumero.validarNumero(pnlCalculadora.getTxtNumero1().getText()) && ValidarNumero.validarNumero(pnlCalculadora.getTxtNumero2().getText()))
            {
                n1 = Double.parseDouble(pnlCalculadora.getTxtNumero1().getText());
                n2 = Double.parseDouble(pnlCalculadora.getTxtNumero2().getText());

                pnlCalculadora.getTxtResultado().setText(String.valueOf(suma(n1, n2)));
            }
            else
                pnlCalculadora.getTxtResultado().setText("No numerico");
        }
    }
    
    private double suma(double a, double b)
    {
        return a + b;
    }
}
