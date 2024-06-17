package unit15_javaio.c8.objstream;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Alvin.Li
 * 父类也需要实现序列化接口
 */
public class Human implements Serializable {
    private BigDecimal weight;
    private BigDecimal height;

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }
}
