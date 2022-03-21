package vip.jayson.util;

import org.apache.log4j.Logger;
import vip.jayson.gui.main.ATT;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LogConsole {
    private static final Logger log = Logger.getLogger(LogConsole.class);
    public static void info(String msg) {
        //ATT.textArea1.append(msg + "\n");
        //ATT.textArea1.setCaretPosition(ATT.textArea1.getText().length());
        log.info(msg);
    }
    public static void debug(String msg) {
        log.debug(msg);
    }
    public static void error(String msg) {
        log.error(msg);
    }
    public static void warn(String msg) {
        log.warn(msg);
    }
    /**
     * 进度条总长度
     */
    private static int TOTLE_LENGTH = 30;

    public static int getPercent(int firstNum, int secondNum){
        BigDecimal first = new BigDecimal(Double.toString(firstNum));
        BigDecimal second = new BigDecimal(Double.toString(secondNum));
        BigDecimal divide = first.divide(second, 2, RoundingMode.HALF_UP);
        return  (int) (Double.parseDouble(divide.toString()) * 100);

    }


}
