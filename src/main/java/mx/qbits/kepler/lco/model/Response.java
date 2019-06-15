package mx.qbits.kepler.lco.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.vertx.core.http.HttpServerRequest;
import mx.qbits.kepler.lco.service.CalculatorSrv;
import mx.qbits.kepler.lco.service.CalculatorSrvImpl;

public class Response {
    private String num;
    private String localAddress;
    private String remoteAddress;
    private long time;
    
    public Response(HttpServerRequest request, String input) {
        // invoke service & compute time:
        CalculatorSrv calc = new CalculatorSrvImpl();
        long start = System.currentTimeMillis();
        int numero = calc.convert(input);
        String conteo = calc.countFact(numero);
        this. num = "El factorial de "+ numero + " tiene " + conteo + " digitos";
        this.time = System.currentTimeMillis() - start;
        this.localAddress = request.localAddress().host();
        this.remoteAddress = request.remoteAddress().host();
    }

    @Override
    public String toString() {
        return "Response [num=" + num + ", localAddress=" + localAddress + ", remoteAddress=" + remoteAddress + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((localAddress == null) ? 0 : localAddress.hashCode());
        result = prime * result + ((num == null) ? 0 : num.hashCode());
        result = prime * result + ((remoteAddress == null) ? 0 : remoteAddress.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Response other = (Response) obj;
        if (localAddress == null) {
            if (other.localAddress != null)
                return false;
        } else if (!localAddress.equals(other.localAddress))
            return false;
        if (num == null) {
            if (other.num != null)
                return false;
        } else if (!num.equals(other.num))
            return false;
        if (remoteAddress == null) {
            if (other.remoteAddress != null)
                return false;
        } else if (!remoteAddress.equals(other.remoteAddress))
            return false;
        return true;
    }

    public Map<String, Object> build() throws Exception {
        Map<String, String> info = new HashMap<String, String>();
        //info.put("RemoteAddress", request.remoteAddress().host()+":"+request.remoteAddress().port());
        info.put("Current Node IP", localAddress);
        info.put("Caller IP", remoteAddress);
        Date now = new Date();
        info.put("Call Timestamp", now.toString());
        info.put("Calc time", this.time+ " milisegundos");
        info.put("version", "3.1.4");
        
        Map<String, Object> resp = new HashMap<String, Object>();
        resp.put("result", num);
        resp.put("info", info);
        return resp;
    }
}
