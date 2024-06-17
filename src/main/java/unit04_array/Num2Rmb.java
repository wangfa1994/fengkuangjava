package unit04_array;

import java.util.Arrays;

public class Num2Rmb {
    private String[] hanArr = {"零","壹","贰","叁","肆",
            "伍","陆","柒","捌","玖"};
    private String[] unitArr = {"十","百","千"};

    /**
     * 得到整数和小数部分
     * @param num
     * @return
     */
    private String[] divide(double num){
        long zheng = (long)num;
        // 浮点数减去整数部分，得到小数部分，小数部分乘以100 后再取整得到2 位小数
        long xiao = Math.round((num - zheng) * 100);
        return new String[]{zheng + "", String.valueOf(xiao)};
    }

    /**
     * 把一个四位的数字字符串变成汉字字符串
     * @param numStr
     * @return
     */
    private String toHanStr(String numStr){
        String result = "";
        int numLen = numStr.length();
        for(int i=0; i< numLen; i++){
            int num = Integer.parseInt(numStr.charAt(i) + "");
            // 如果不是最后以位数字，而且数字不是零，则需要添加单位（千、百、十）
            if(i != numLen -1 && num != 0){
                result += hanArr[num] + unitArr[numLen - 2 - i];
            } else {
                result += hanArr[num];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Num2Rmb nr = new Num2Rmb();
        System.out.println(Arrays.toString(nr.divide(236711125.123)));
        System.out.println(nr.toHanStr("6109"));
    }
}
