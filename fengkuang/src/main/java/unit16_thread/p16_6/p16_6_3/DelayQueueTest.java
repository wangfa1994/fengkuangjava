package unit16_thread.p16_6.p16_6_3;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列，就是放入队列中的数据要延时指定时间才能消费。
 */
public class DelayQueueTest {
}

class DelayedUser implements Delayed {
    private String name;
    private long avaibleTime;

    public DelayedUser(String name, long delayTime){
        this.name=name;
        //avaibleTime = 当前时间+ delayTime
        this.avaibleTime=delayTime + System.currentTimeMillis();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        //判断avaibleTime是否大于当前系统时间，并将结果转换成MILLISECONDS
        long diffTime= avaibleTime- System.currentTimeMillis();
        return unit.convert(diffTime,TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        //compareTo用在DelayedUser的排序
        return (int)(this.avaibleTime - ((DelayedUser) o).getAvaibleTime());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAvaibleTime() {
        return avaibleTime;
    }

    public void setAvaibleTime(long avaibleTime) {
        this.avaibleTime = avaibleTime;
    }
}