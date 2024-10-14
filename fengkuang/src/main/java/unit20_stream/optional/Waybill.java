package unit20_stream.optional;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Waybill implements Serializable {
    private String waybillNo;
    private List<Package> packages;

    @Data
    public static class Package implements Serializable {
        private List<Good> goods;
    }
    
    @Data
    public static class Good implements Serializable {
        private String firstName;
        private String secondName;
    }
}
