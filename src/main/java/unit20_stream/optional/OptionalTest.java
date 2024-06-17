package unit20_stream.optional;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Stream;

public class OptionalTest {

    private static final Logger logger = LoggerFactory.getLogger(OptionalTest.class);
    
    @Test
    public void testList() {
        Waybill waybill = getWaybill();
        // optional获取list方法
        List<Waybill.Package> packages = Optional.ofNullable(waybill.getPackages()).orElse(Collections.emptyList());
        // filter和map
        Stream<List<Waybill.Good>> listStream = packages.stream().filter(aPackage -> aPackage != null && aPackage.getGoods() != null).map(Waybill.Package::getGoods);
        // flatMap(List<Waybill.Good>转为Waybill.Good)
        List<Waybill.Good> goods = listStream.flatMap(Collection::stream).filter(good -> good != null && good.getFirstName() != null).toList();
        logger.info(String.valueOf(goods.size()));
        logger.info("testList");
    }

    private Waybill getWaybill() {
        Waybill.Good good = new Waybill.Good();
        good.setFirstName("文件");
        good.setSecondName("文件类");
        Waybill.Good good2 = new Waybill.Good();
        good2.setFirstName("文件2");
        good2.setSecondName("文件类");

        Waybill.Package packageInfo = new Waybill.Package();
        packageInfo.setGoods(Arrays.asList(good, null, new Waybill.Good(), good2));

        Waybill waybill = new Waybill();
        waybill.setWaybillNo("SF001");
        waybill.setPackages(Arrays.asList(packageInfo, null, new Waybill.Package()));
        return waybill;
    }
}
