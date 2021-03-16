/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validaciones;

/**
 *
 * @author UZIEL
 */
public class ValidarNumero
{
    public static boolean validarNumero(String txt)
    {
        char [] caracteres = txt.toCharArray();
        
        for(char caracter: caracteres)
        {
            if(!Character.isDigit(caracter))
                return false;
        }
        return true;
    }
}
