package demo.lock;

import org.apache.commons.codec.language.bm.Lang;

import java.util.concurrent.locks.Lock;

/**
 * @Author: Niko Zhao
 * @Date: Create in 14:26 11/30/17
 * @Email: nikoz@synnex.com
 */
public interface GobalLock {
    boolean lock(Object key);
    boolean unLock(Object key);
    boolean expire(Object key, Long time);
}
