package mx.qbits.kepler.lco.service;

/**
 * Interface for Calculator service
 * 
 * @author garellano
 *
 */
public interface CalculatorSrv {
    /**
     * Return the total number of digits found in 
     * the factorial for a given number.<p/>
     * 
     * As an example, for 5, it returns 3, because 
     * 120 (the 5 fact) that contains 3 numbers: 1, 2 and 3
     * 
     * @param n A given number for it to calculate the fact, and
     * then, the length of such a number
     * 
     * @return String representing the len of the fact of a number
     */
    String countFact(int n);

    /**
     * Intenta convertir una cadena a entero.
     * Si no lo logra, regresa 0.
     * 
     * @param num Cadena a intenter convertir
     * @return entero asociado a la cadena o 0 si no se pudo convertir
     */
    int convert(String num);
}
