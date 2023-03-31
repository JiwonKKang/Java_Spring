package hello.typeconverter.converter;

import hello.typeconverter.IpPort;
import org.springframework.core.convert.converter.Converter;

public class IpPortToStringConverter implements Converter<IpPort, String> {
    @Override
    public String convert(IpPort source) {
        String ip = source.getIp();
        int port = source.getPort();
        return ip + ":" + port;
    }
}
