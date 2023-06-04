package io.arkvaer.algorithm.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 通用异常
 *
 * @author waver
 * @date 2023/6/4 下午8:37
 */
public class AlgException extends RuntimeException {
    public AlgException(String msg) {
        Logger global = Logger.getGlobal();
        global.log(Level.SEVERE, msg);
    }
}
