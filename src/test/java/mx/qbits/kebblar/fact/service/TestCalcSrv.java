/*
 * Copyright (c) 2017 Qbits and/or its affiliates. All rights reserved. Kepler is a TradeMark.
 *
 * Licencia:    Este  código  se encuentra  bajo la protección que
 *              otorga la Ley Federal del Derecho de Autor vigente.
 *              Adicionalmente,  queda  estrictamente  prohibido:
 *              modificar, copiar, donar, vender y/o distribuir el
 *              presente  código por cualquier medio electrónico o
 *              impreso sin el  permiso explícito y por escrito de
 *              de su autor.
 *
 * Proyecto:    Kepler
 * Paquete:     mx.qbits.kepler.catalog
 * Modulo:      CatalogosExcel
 * Tipo:        clase
 * Autor:       Gustavo Arellano (GAA)
 * Fecha:       21/04/2017
 * Version:     0.0.1
 *
 * Historia:    OFG: Se incorpora documentación 20170421_1256
 *
 */
package mx.qbits.kebblar.fact.service;

import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

public final class TestCalcSrv {
    private final static Logger logger = Logger.getLogger(TestCalcSrv.class);
    CalculatorSrv cs = new CalculatorSrvImpl();

    public TestCalcSrv() {
    }
    
    @Test
    public void testConcatenate() {
        String calc = cs.countFact(10);
        assertEquals("7", calc);
    }
    public void testCountFact() {
        int n = 10;
        int f = fact(n);
        logger.info("factorial de "+n+" es:"+f);
        String calc = cs.countFact(n);
        logger.info(calc);
    }
    
    private int fact(int n) {
        return (n<2)?1:n*fact(n-1);
    }
    
    public static void main(String...argv) {
        TestCalcSrv tcs = new TestCalcSrv();
        tcs.testCountFact();
    }
}