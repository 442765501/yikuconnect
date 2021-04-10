//package com.yiku.service.redis.impl;
//
//import com.yiku.common.constant.enums.response.CommonMessageEnum;
//import com.yiku.common.dto.APIResultDTO;
//import com.yiku.common.util.LogUtil;
//import com.yiku.common.util.StringUtil;
//import com.yiku.service.redis.RedisService;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.Transaction;
//import redis.clients.jedis.exceptions.JedisException;
//
//import javax.annotation.Resource;
//import java.util.*;
//
///**
// * Created by roy on 16/11/19.
// */
//@Service
//public class RedisServiceImpl implements RedisService {
//
//    private Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);
//
//    @Resource
//    private JedisPool jedisPool;
//
//
//    public JedisPool getJedisPool() {
//        return jedisPool;
//    }
//
//    public void setJedisPool(JedisPool jedisPool) {
//        this.jedisPool = jedisPool;
//    }
//
//    private Jedis getJedis() throws JedisException {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//        } catch (JedisException e) {
//            logger.error("failed:jedisPool getResource.", e);
//        }
//        return jedis;
//    }
//
//    private void release(Jedis jedis, Boolean isBroken) {
//        if(isBroken){
//            returnBrokenResource(jedis);
//        }else{
//            returnResource(jedis);
//        }
//    }
//
//    @Override
//    public String addStringToJedis(String key, String value, Integer cacheSeconds, Integer DBIndex) {
//        Jedis jedis = null;
//        boolean isBroken = false;
//        String lastVal = null;
//
//        if (!StringUtils.isBlank(value)) {
//            try {
//                jedis = this.getJedis();
//                jedis.select(DBIndex != null ? DBIndex : 0);
//                lastVal = jedis.set(key, value);
//                if (cacheSeconds > 0) {
//                    jedis.expire(key, cacheSeconds);
//                }
//            } catch (Exception e) {
//                isBroken = true;
//                logger.error("redis failed.", e);
//            } finally {
//                release(jedis,isBroken);
//            }
//        }
//
//        return lastVal;
//    }
//
//    @Override
//    public boolean addListToJedis(String key, List<String> list, int cacheSeconds, int dbIndex) {
//        if (list != null && list.size() > 0) {
//            Jedis jedis = null;
//            boolean isBroken = false;
//            try {
//                jedis = this.getJedis();
//                jedis.select(dbIndex > 0 ? dbIndex : 0);
//                if (jedis.exists(key)) {
//                    jedis.del(key);
//                }
//                for (String aList : list) {
//                    jedis.rpush(key, aList);
//                }
//                if (cacheSeconds > 0) {
//                    jedis.expire(key, cacheSeconds);
//                }
//            } catch (JedisException e) {
//                isBroken = true;
//                logger.error("redis failed.", e);
//            } catch (Exception e) {
//                isBroken = true;
//                logger.error("redis failed.", e);
//            } finally {
//                release(jedis,isBroken);
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public APIResultDTO<Long> addToSetJedis(String key, int dbIndex, String... value) {
//        APIResultDTO<Long> resultDTO = new  APIResultDTO<Long>();
//        resultDTO.setFlag(false);
//        Jedis jedis = null;
//        boolean isBroken = false;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            Long result = jedis.sadd(key, value);
//            resultDTO.setFlag(true);
//            resultDTO.setData(result);
//        } catch (Exception e) {
//            isBroken = true;
//            resultDTO.setStatusCode(CommonMessageEnum.CACHE_SAVE_ERROR.getCode());
//            resultDTO.setErrorMessage(CommonMessageEnum.CACHE_SAVE_ERROR.getMsg());
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return resultDTO;
//    }
//
//    @Override
//    public APIResultDTO<Long> removeSetJedis(String key, int dbIndex, String... value) {
//        APIResultDTO<Long> longResultDTO = new  APIResultDTO<>();
//        longResultDTO.setFlag(true);
//        Jedis jedis = null;
//        boolean isBroken = false;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            long result= jedis.srem(key, value);
//            longResultDTO.setData(result);
//        } catch (Exception e) {
//            isBroken = true;
//            longResultDTO.setErrorMessage(CommonMessageEnum.CACHE_DELETE_ERROR.getMsg());
//            longResultDTO.setStatusCode(CommonMessageEnum.CACHE_DELETE_ERROR.getCode());
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return longResultDTO;
//    }
//
//    @Override
//    public boolean lpushDataToListJedis(String key, String data, int cacheSeconds, int dbIndex) {
//        Jedis jedis = null;
//        boolean isBroken = false;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex);
//            jedis.lpush(key, data);
//            if (cacheSeconds > 0) {
//                jedis.expire(key, cacheSeconds);
//            }
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return true;
//    }
//
//    @Override
//    public List<String> lrangeDataFromJedis(String key, int dbIndex, int start, int end) {
//        Jedis jedis = null;
//        boolean isBroken = false;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex);
//            List<String> list = jedis.lrange(key, start, end);
//            return list;
//        } catch (Exception e) {
//            isBroken =true;
//            logger.error("redis lrangeDataFromJedis failed.", e);
//            return null;
//        }finally {
//            release(jedis,isBroken);
//        }
//    }
//
//    @Override
//    public Set<String> getAllKeysFromJedis(int dbIndex) {
//        Jedis jedis = null;
//        boolean isBroken = false;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex);
//            Set<String> set = jedis.keys("*");
//            return set;
//        } catch (Exception e) {
//            isBroken =true;
//            logger.error("redis lrangeDataFromJedis failed.", e);
//            return null;
//        }finally {
//            release(jedis,isBroken);
//        }
//    }
//
//    @Override
//    public boolean rpushDataToListJedis(String key, String data, int cacheSeconds, int dbIndex) {
//        Jedis jedis = null;
//        boolean isBroken = false;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            long result = jedis.rpush(key, data);
//            if (cacheSeconds > 0) {
//                jedis.expire(key, cacheSeconds);
//            }
//            if(0<result){
//             return true;
//            }
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return false;
//    }
//
//    @Override
//    public String rpopDataFromJedis(String key, int dbIndex) {
//        Jedis jedis = null;
//        boolean isBroken = false;
//        String value = null;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            if (jedis.exists(key)) {
//                value = jedis.rpop(key);
//                value = !StringUtils.isBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
//            }
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return value;
//    }
//
//    @Override
//    public boolean rpushDataToListJedis(String key, String data) {
//        return rpushDataToListJedis(key,data,0,0);
//    }
//
//    @Override
//    public String lpopDataFromJedis(String key) {
//        return lpopDataFromJedis(key,0);
//    }
//
//    @Override
//    public String lpopDataFromJedis(String key, int dbIndex) {
//        Jedis jedis = null;
//        boolean isBroken = false;
//        String value = null;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            if (jedis.exists(key)) {
//                value = jedis.lpop(key);
//                value = !StringUtils.isBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
//            }
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return value;
//    }
//
//    @Override
//    public boolean rpushDataToListJedis(String key, List<String> batchData, int cacheSeconds, int dbIndex) {
//        Jedis jedis = null;
//        boolean isBroken = false;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            jedis.del(key);
//            jedis.rpush(key, batchData.toArray(new String[batchData.size()]));
//            if (cacheSeconds > 0) {
//                jedis.expire(key, cacheSeconds);
//            }
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return true;
//    }
//
//    @Override
//    public boolean lpushDataToListJedis(String key, List<String> batchData, int cacheSeconds, int dbIndex) {
//        Jedis jedis = null;
//        boolean isBroken = false;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            jedis.del(key);
//            jedis.lpush(key, batchData.toArray(new String[batchData.size()]));
//            if (cacheSeconds > 0) {
//                jedis.expire(key, cacheSeconds);
//            }
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return true;
//    }
//
//    @Override
//    public boolean addHashMapToJedis(String key, Map<String, String> map, int cacheSeconds, int dbIndex) {
//        boolean isBroken = false;
//        Jedis jedis = null;
//        if (map != null && map.size() > 0) {
//            try {
//                jedis = this.getJedis();
//                jedis.select(dbIndex > 0 ? dbIndex : 0);
//                jedis.hmset(key, map);
//                if (cacheSeconds >= 0) {
//                    jedis.expire(key, cacheSeconds);
//                }
//            } catch (Exception e) {
//                isBroken = true;
//                logger.error("redis failed.", e);
//            } finally {
//                release(jedis, isBroken);
//            }
//        }
//        return !isBroken;
//    }
//
//    @Override
//    public  APIResultDTO<Long> addHashMapToJedis(String key, String field, String value, int dbIndex) {
//        APIResultDTO<Long> resultDTO = new  APIResultDTO<>();
//        resultDTO.setFlag(false);
//        Jedis jedis = null;
//        boolean isBroken = false;
//        long result;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            result = jedis.hset(key, field, value);
//            resultDTO.setData(result);
//            resultDTO.setFlag(true);
//        } catch (Exception e) {
//            isBroken = true;
//            resultDTO.setErrorMessage("redis failed.");
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return resultDTO;
//    }
//
//    @Override
//    public  APIResultDTO<Long> delHashMapToJedis( int dbIndex,String key, String... field) {
//        APIResultDTO<Long>  resultDTO = new  APIResultDTO<>();
//        resultDTO.setFlag(false);
//        Jedis jedis = null;
//        boolean isBroken = false;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            if(null != jedis){
//                long result =jedis.hdel(key,field);
//                resultDTO.setData(result);
//                resultDTO.setFlag(true);
//            }else{
//                logger.error("redis failed.");
//            }
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return resultDTO;
//    }
//
//    @Override
//    public boolean updateHashMapToJedis(String key, String incrementField, long incrementValue, String dateField,
//                                        String dateValue, int dbIndex) {
//        boolean isBroken = false;
//        Jedis jedis = null;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            jedis.hincrBy(key, incrementField, incrementValue);
//            jedis.hset(key, dateField, dateValue);
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return true;
//    }
//
//    @Override
//    public String getStringFromJedis(String key, Integer DB) {
//        LogUtil.info("getStringFromJedis","key:{}",key);
//        String value = null;
//        Jedis jedis = null;
//        boolean isBroken = false;
//        try {
//            jedis = this.getJedis();
//            jedis.select(DB != null ? DB : 0);
//            if (jedis.exists(key)) {
//                value = jedis.get(key);
//                value = !StringUtils.isBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
//            }
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return value;
//    }
//
//    @Override
//    public String getStringFromJedis(String key) {
//        return getStringFromJedis(key,0);
//    }
//
//    @Override
//    public boolean addStringToJedis(String key, String value) {
//       addStringToJedis(key,value,null,0);
//       return  true;
//    }
//
//    @Override
//    public List<String> getListFromJedis(String key, int dbIndex) {
//        List<String> list = null;
//        boolean isBroken = false;
//        Jedis jedis = null;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            if (jedis.exists(key)) {
//                list = jedis.lrange(key, 0, -1);
//            }
//        } catch (JedisException e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return list;
//    }
//
//    @Override
//    public Set<String> getSetFromJedis(String key, int dbIndex) {
//        Set<String> list = null;
//        boolean isBroken = false;
//        Jedis jedis = null;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            if (jedis.exists(key)) {
//                list = jedis.smembers(key);
//            }
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return list;
//    }
//
//    @Override
//    public Map<String, String> getHashMapFromJedis(String key, int dbIndex) {
//        Map<String, String> hashMap = null;
//        boolean isBroken = false;
//        Jedis jedis = this.getJedis();
//        hashMap = getStringStringMap(key, dbIndex, hashMap, isBroken, jedis);
//        return hashMap;
//    }
//
//    private Map<String, String> getStringStringMap(String key, int dbIndex, Map<String, String> hashMap, boolean isBroken, Jedis jedis) {
//        try {
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            hashMap = jedis.hgetAll(key);
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return hashMap;
//    }
//
//    @Override
//    public String getHashMapValueFromJedis(String key, String field, int dbIndex) {
//        String value = null;
//        boolean isBroken = false;
//        Jedis jedis = this.getJedis();
//        try {
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            if (jedis != null) {
//                if (jedis.exists(key)) {
//                    value = jedis.hget(key, field);
//                }
//            }
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return value;
//    }
//
//    @Override
//    public Long getIdentifyId(String identifyName, int dbIndex) {
//        boolean isBroken = false;
//        try (Jedis jedis = this.getJedis()) {
//            Long identify = null;
//            try {
//                jedis.select(dbIndex > 0 ? dbIndex : 0);
//                if (jedis != null) {
//                    identify = jedis.incr(identifyName);
//                    if (identify == 0) {
//                        return jedis.incr(identifyName);
//                    } else {
//                        return identify;
//                    }
//                }
//            } catch (Exception e) {
//                isBroken = true;
//                logger.error("redis failed.", e);
//            } finally {
//                release(jedis,isBroken);
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 删除某db的某个key值
//     *
//     * @param key
//     * @return
//     */
//    @Override
//    public Long delKeyFromJedis(String key, Integer DB) {
//        boolean isBroken = false;
//        Jedis jedis = this.getJedis();
//        long result = 0;
//        try {
//            jedis.select(DB != null ? DB : 0);
//            return jedis.del(key);
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return result;
//    }
//
//    /**
//     * 根据dbIndex flushDB每个shard
//     *
//     * @param dbIndex
//     */
//    public boolean flushDBFromJedis(int dbIndex) {
//        Jedis jedis = this.getJedis();
//        boolean isBroken = false;
//        try {
//            jedis.select(dbIndex);
//            jedis.flushDB();
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return !isBroken;
//    }
//
//    @Override
//    public String getKey(String key, int dbIndex) {
//        Jedis jedis = this.getJedis();
//        String result = StringUtil.EMPTY_STRING;
//        boolean isBroken = false;
//        try {
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            return jedis.get(key);
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return result;
//    }
//
//    @Override
//    public long getLength(String key, int dbIndex) {
//        Jedis jedis = this.getJedis();
//        boolean isBroken = false;
//        long result = 0;
//        try {
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            return jedis.llen(key);
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return result;
//    }
//
//    @Override
//    public long getLength(String key) {
//        return getLength(key,0);
//    }
//
//    @Override
//    public boolean existKey(String key, int dbIndex) {
//        Jedis jedis = this.getJedis();
//        boolean isBroken = false;
//        try {
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            return jedis.exists(key);
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return false;
//    }
//
//
//    private void returnBrokenResource(Jedis jedis) {
//        if (jedis == null) {
//            return;
//        }
//        try {
//            //容错
//            jedisPool.returnBrokenResource(jedis);
//        } catch (Exception e) {
//        	logger.error(e.getMessage(), e);
//        }
//    }
//    private void returnResource(Jedis jedis) {
//        if (jedis == null) {
//            return;
//        }
//        try {
//            jedisPool.returnResource(jedis);
//        } catch (Exception e) {
//        	logger.error(e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public List<String> sortList(String key, int dbIndex) {
//        Jedis jedis = this.getJedis();
//        boolean isBroken = false;
//        List<String> sortList = new ArrayList<>();
//        try {
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            return jedis.sort(key);
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return sortList;
//    }
//
//    @Override
//    public boolean exitValue(String key, String value, int dbIndex) {
//            Jedis jedis = null;
//            boolean isBroken = false;
//            try {
//                jedis = this.getJedis();
//                jedis.select(dbIndex > 0 ? dbIndex : 0);
//                if (!jedis.sismember(key,value)) {
//                    isBroken = true;
//                }
//            } catch (JedisException e) {
//                isBroken = true;
//                logger.error("redis failed.", e);
//            } catch (Exception e) {
//                isBroken = true;
//                logger.error("redis failed.", e);
//            } finally {
//                release(jedis,isBroken);
//            }
//            return true;
//
//    }
//
//    /**
//     * 分布式锁
//     * @param lockName 锁名字
//     * @param acquireTimeoutInMS 锁等待超时，防止线程饥饿，永远没有入锁执行代码的机会
//     * @param lockTimeoutInMS 锁失效时间
//     * @return 获得锁
//     * @throws InterruptedException 异常
//     */
//    @Override
//    public String getLock(String lockName, long acquireTimeoutInMS, long lockTimeoutInMS) throws InterruptedException  {
//        Jedis conn = null;
//        boolean broken = false;
//        String retIdentifier = null;
//        try {
//            conn = this.getJedis();
//            String identifier = UUID.randomUUID().toString();
//            String lockKey = "lock:" + lockName;
//            int lockExpire = (int) (lockTimeoutInMS / 1000);
//            long end = System.currentTimeMillis() + acquireTimeoutInMS;
//            while (System.currentTimeMillis() < end) {
//                if (conn.setnx(lockKey, identifier) == 1) {
//                    conn.expire(lockKey, lockExpire);
//                    retIdentifier = identifier;
//                }
//                if (conn.ttl(lockKey) == -1) {
//                    conn.expire(lockKey, lockExpire);
//                }
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException ie) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        } catch (JedisException je) {
//            if (conn != null) {
//                broken = true;
//                release(conn,broken);
//            }
//        } finally {
//            if (conn != null && !broken) {
//                release(conn,broken);
//            }
//        }
//        return retIdentifier;
//    }
//
//    /**
//     *
//     * tips：对lockName做watch，开启一个事务，删除以LockName为key的锁，删除后，此锁对于其他线程为可争抢的。
//     * @param lockName
//     * @param identifier
//     *
//     */
//    @Override
//    public boolean relaseLock(String lockName, String identifier){
//        Jedis conn = null;
//        boolean broken = false;
//        String lockKey = "lock:" + lockName;
//        boolean retFlag = false;
//        try {
//            conn = jedisPool.getResource();
//            while (true) {
//                conn.watch(lockKey);
//                if (identifier.equals(conn.get(lockKey))) {
//                    Transaction trans = conn.multi();
//                    trans.del(lockKey);
//                    List<Object> results = trans.exec();
//                    if (results == null) {
//                        continue;
//                    }
//                    retFlag = true;
//                }
//                conn.unwatch();
//                break;
//            }
//
//        } catch (JedisException je) {
//            if (conn != null) {
//                broken = true;
//                jedisPool.returnBrokenResource(conn);
//            }
//        } finally {
//            if (conn != null && !broken) {
//                jedisPool.returnResource(conn);
//            }
//        }
//        return retFlag;
//    }
//
//
//    @Override
//    public  APIResultDTO<Long> addZSetJedis(String key, String value, double sorce, int dbIndex){
//        Jedis jedis = null;
//        boolean isBroken = false;
//        APIResultDTO<Long> resultDTO = new  APIResultDTO<>();
//        resultDTO.setFlag(false);
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            resultDTO.setFlag(true);
//            resultDTO.setData(jedis.zadd(key,sorce,value));
//        } catch (Exception e) {
//            isBroken = true;
//            resultDTO.setErrorMessage("redis failed.");
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return resultDTO;
//    }
//
//    @Override
//    public  APIResultDTO<Long> addZSetJedis(String key, Map<String,Double> map, int dbIndex){
//        Jedis jedis = null;
//        boolean isBroken = false;
//        APIResultDTO<Long> resultDTO = new  APIResultDTO<>();
//        resultDTO.setFlag(false);
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            resultDTO.setFlag(true);
//            resultDTO.setData(jedis.zadd(key,map));
//        } catch (Exception e) {
//            isBroken = true;
//            resultDTO.setErrorMessage("redis failed.");
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return resultDTO;
//    }
//
//    @Override
//    public  APIResultDTO<Long> delZSetJedis(String key,int dbIndex, String... members) {
//        Jedis jedis = null;
//        boolean isBroken = false;
//        APIResultDTO<Long> resultDTO = new  APIResultDTO<>();
//        resultDTO.setFlag(false);
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            resultDTO.setFlag(true);
//            resultDTO.setData(jedis.zrem(key,members));
//        } catch (Exception e) {
//            isBroken = true;
//            resultDTO.setErrorMessage("redis failed.");
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis,isBroken);
//        }
//        return resultDTO;
//    }
//
//    @Override
//    public Set<String> getSortedSetRangeFromJedis(String key, int dbIndex, long min, long max) {
//        Jedis jedis = null;
//        boolean isBroken = false;
//        Set<String> set = null;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            return jedis.zrangeByScore(key, min , max);
//        } catch (Exception var9) {
//            isBroken = true;
//            logger.error("redis failed.", var9);
//        } finally {
//            this.release(jedis,isBroken);
//        }
//        return set;
//    }
//
//    @Override
//    public long getZSetCount(String key, String min, String max, String methodName) {
//        boolean isBroken = false;
//        Jedis jedis = null;
//        try {
//            jedis = this.getJedis();
//            return jedis.zcount(key, min, max);
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis, isBroken);
//        }
//        return 0;
//    }
//
//    @Override
//    public Set<String> getZSetRangeByScoreFromJedis(String key, String min, String max, int offset, int count,
//                                                    String methodName) {
//        Set<String> list = null;
//        boolean isBroken = false;
//        Jedis jedis = null;
//        try {
//            jedis = this.getJedis();
//            if (jedis.exists(key)) {
//                list = jedis.zrangeByScore(key, min, max, offset, count);
//            }
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis, isBroken);
//        }
//        return list;
//    }
//
//    @Override
//    public boolean hDelFromJedis(String key, String... fields) {
//        boolean isBroken = false;
//        Jedis jedis = null;
//        try {
//            jedis = this.getJedis();
//            jedis.hdel(key, fields);
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis, isBroken);
//        }
//        return !isBroken;
//    }
//
//    @Override
//    public boolean delZSet(String key, String[] values) {
//        boolean isBroken = false;
//        Jedis jedis = null;
//        try {
//            jedis = this.getJedis();
//            jedis.zrem(key, values);
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis, isBroken);
//        }
//        return !isBroken;
//    }
//
//   @Override
//    public Map<String, String> getHashMapFromComdJedis(String key, int dbIndex) {
//        Map<String, String> hashMap = null;
//        boolean isBroken = false;
//        Jedis jedis = this.getJedis();
//       hashMap = getStringStringMap(key, dbIndex, hashMap, isBroken, jedis);
//       return hashMap;
//    }
//
//    @Override
//    public boolean removeHashJedis(String key, String mapkey, String mapvalue, int dbIndex) {
//        Jedis jedis = null;
//        boolean isBroken = false;
//        try {
//            jedis = this.getJedis();
//            jedis.select(dbIndex > 0 ? dbIndex : 0);
//            jedis.hdel(key, mapkey, mapvalue);
//        } catch (Exception e) {
//            isBroken = true;
//            logger.error("redis failed.", e);
//        } finally {
//            release(jedis, isBroken);
//        }
//        return !isBroken;
//    }
//
//}
