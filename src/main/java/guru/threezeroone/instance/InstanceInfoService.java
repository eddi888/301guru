package guru.threezeroone.instance;

import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class InstanceInfoService {

    public String getHostnameAndIP() {
        String ip = "unknown IP";
        String hostname = "unknown hostname";
        try {
            InetAddress address = InetAddress.getLocalHost();
            ip = address.getHostAddress();
            hostname = address.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostname+" / "+ip;
    }

}
