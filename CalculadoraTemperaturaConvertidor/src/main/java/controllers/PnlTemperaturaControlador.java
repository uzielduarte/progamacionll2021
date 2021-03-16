/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import validaciones.ValidarNumero;
import views.panels.PnlTemperatura;

/**
 *
 * @author UZIEL
 */
public class PnlTemperaturaControlador implements ActionListener
{
    PnlTemperatura pnlTemperatura;

    public PnlTemperaturaControlador(PnlTemperatura pnlTemperatura)
    {
        this.pnlTemperatura = pnlTemperatura;
        initComponent();
    }
    
    private void initComponent() {
    pnlTemperatura.getBtnConvertir().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equalsIgnoreCase("Convertir"))
        {
            String temperatura = (String) pnlTemperatura.getjComboBox1().getSelectedItem();
            if(temperatura == "De Celsius a")
            {
                double temperaturaCelsius, temperaturaFahrenheit;
                
                if(ValidarNumero.validarNumero(pnlTemperatura.getTxtTemperatura().getText()))
                {
                    temperaturaCelsius = Double.parseDouble(pnlTemperatura.getTxtTemperatura().getText());
                    temperaturaFahrenheit = (temperaturaCelsius * 9/5) + 32;
                    // OUTPUT
                    pnlTemperatura.getTxtConvertida().setText(String.valueOf(temperaturaFahrenheit));
                }
                else
                    pnlTemperatura.getTxtConvertida().setText("No numerico");
            }
            else if(temperatura == "De Fahrenheit a")
            {
                double temperaturaCelsius, temperaturaFahrenheit;
                
                if(ValidarNumero.validarNumero(pnlTemperatura.getTxtTemperatura().getText()))
                {
                    temperaturaFahrenheit = Double.parseDouble(pnlTemperatura.getTxtTemperatura().getText());
                    temperaturaCelsius = (temperaturaFahrenheit - 32) * 5/9;
                    // OUTPUT
                    pnlTemperatura.getTxtConvertida().setText(String.valueOf(temperaturaCelsius));
                }
                else
                    pnlTemperatura.getTxtConvertida().setText("No numerico");
            }            
        }
    }
}
