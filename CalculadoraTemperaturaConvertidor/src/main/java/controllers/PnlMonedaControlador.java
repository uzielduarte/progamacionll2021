/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import validaciones.ValidarNumero;
import views.panels.PnlMoneda;

/**
 *
 * @author UZIEL
 */
public class PnlMonedaControlador implements ActionListener
{
    PnlMoneda pnlMoneda;

    public PnlMonedaControlador(PnlMoneda pnlMoneda)
    {
        this.pnlMoneda = pnlMoneda;
        initComponent();
    }
    
    private void initComponent(){
    pnlMoneda.getBtnCambiar().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equalsIgnoreCase("Cambiar"))
        {
            String moneda = (String) pnlMoneda.getjComboBox1().getSelectedItem();
            if(moneda == "De cordobas a dolar")
            {
                double cordobas, dolar;
                if(ValidarNumero.validarNumero((String) pnlMoneda.getTxtCantidad().getText()))
                {
                    cordobas = Double.parseDouble(pnlMoneda.getTxtCantidad().getText());
                    dolar = cordobas / 35;
                    // OUTPUT
                    pnlMoneda.getTxtCambio().setText(String.valueOf(dolar));
                }
                else
                    pnlMoneda.getTxtCambio().setText("Dato no numerico");
            }
            else if(moneda == "De dolar a cordobas")
            {
                double cordobas, dolar;
                if(ValidarNumero.validarNumero((String) pnlMoneda.getTxtCantidad().getText()))
                {
                    dolar = Double.parseDouble(pnlMoneda.getTxtCantidad().getText());
                    cordobas = dolar * 35;
                    // OUTPUT
                    pnlMoneda.getTxtCambio().setText(String.valueOf(cordobas));
                }
                else
                    pnlMoneda.getTxtCambio().setText("Dato no numerico");
            }
        }
    }
}
