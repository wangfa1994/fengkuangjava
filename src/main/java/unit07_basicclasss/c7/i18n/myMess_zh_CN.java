package unit07_basicclasss.c7.i18n;

import java.util.ListResourceBundle;

/**
 * @author alvin
 * @date 2020-05-24 20:11
 */
public class myMess_zh_CN extends ListResourceBundle {
    // 定义资源
    private final Object myData[][] = {
            {"msg","{0},你好！今天的日期是{1}"}
    };
    @Override
    protected Object[][] getContents() {
        return myData;
    }
}
