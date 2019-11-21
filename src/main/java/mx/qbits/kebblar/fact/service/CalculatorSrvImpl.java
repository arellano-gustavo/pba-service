package mx.qbits.kebblar.fact.service;

/**
 * Implementation service for Calculator interface.
 * 
 * @author garellano
 *
 */
public class CalculatorSrvImpl implements CalculatorSrv {

    @Override
    public String countFact(int n) {
        double res = 1.0;
        for(int i=1; i<=n; i++) {
            res += Math.log10(i);
        }
        return (int)res+"";
    }

    @Override
    public int convert(String num) {
        try {
            return Integer.parseInt(num);
        } catch (Exception e) {
            return 0;
        }
    }

}
