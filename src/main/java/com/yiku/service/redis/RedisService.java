//package com.yiku.service.redis;
//
//
//
//import com.yiku.common.dto.APIResultDTO;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//
///**
// * Created by roy on 16/11/21.
// */
//public interface RedisService {
//
//    boolean addStringToJedis(String key, String value);
//
//    String addStringToJedis(String key, String value, Integer cacheSeconds, Integer DBIndex);
//
//    boolean addListToJedis(String key, List<String> list, int cacheSeconds, int dbIndex);
//
//    APIResultDTO<Long> addToSetJedis(String key, int dbIndex, String... value);
//
//    APIResultDTO<Long> removeSetJedis(String key, int dbIndex, String... value);
//
//    boolean rpushDataToListJedis(String key, String data, int cacheSeconds, int dbIndex);
//
//    boolean rpushDataToListJedis(String key, String data);
//
//    boolean lpushDataToListJedis(String key, String data, int cacheSeconds, int dbIndex);
//
//    String lpopDataFromJedis(String key, int dbIndex);
//
//    String lpopDataFromJedis(String key);
//
//    String rpopDataFromJedis(String key, int dbIndex);
//
//    boolean lpushDataToListJedis(String key, List<String> batchData, int cacheSeconds, int dbIndex);
//
//    boolean rpushDataToListJedis(String key, List<String> batchData, int cacheSeconds, int dbIndex);
//
//    /**
//     * 将数据存入散列
//     * @param key
//     * @param map
//     * @param cacheSeconds key的失效期，如果为0。直接失效！
//     * @param dbIndex
//     * @return
//     */
//    boolean addHashMapToJedis(String key, Map<String, String> map, int cacheSeconds, int dbIndex);
//
//    /**
//     * 将数据存入散列
//     * @param key
//     * @param field
//     * @param value
//     * @param dbIndex
//     * @return
//     */
//    APIResultDTO<Long> addHashMapToJedis(String key, String field, String value, int dbIndex);
//    /**
//     * 删除 hashMap
//     * @param key
//     * @param fields
//     * @param dbIndex
//     * @return
//     */
//    APIResultDTO<Long> delHashMapToJedis(int dbIndex, String key, String... fields);
//
//    boolean updateHashMapToJedis(String key, String incrementField, long incrementValue, String dateField,
//                                 String dateValue, int dbIndex);
//
//    boolean exitValue(String key, String value, int dbIndex);
//
//    String getStringFromJedis(String key, Integer DB);
//
//    String getStringFromJedis(String key);
//
//    List<String> getListFromJedis(String key, int dbIndex);
//
//    Set<String> getSetFromJedis(String key, int dbIndex);
//
//    Map<String, String> getHashMapFromJedis(String key, int dbIndex);
//
//    String getHashMapValueFromJedis(String key, String field, int dbIndex);
//
//    Long getIdentifyId(String identifyName, int dbIndex);
//
//    Long delKeyFromJedis(String key, Integer DB);
//
//    long getLength(String key, int dbIndex);
//
//    long getLength(String key);
//
//    boolean existKey(String key, int dbIndex);
//
//    List<String> lrangeDataFromJedis(String key, int dbIndex, int start, int end);
//
//    Set<String> getAllKeysFromJedis(int dbIndex);
//
//    List<String> sortList(String key, int dbIndex);
//
//    String getKey(String key, int dbIndex);
//
//    String getLock(String lockName, long acquireTimeoutInMS, long lockTimeoutInMS) throws InterruptedException;
//
//    boolean relaseLock(String lockName, String identifier);
//
//    APIResultDTO<Long> addZSetJedis(String key, String value, double sorce, int dbIndex);
//
//    APIResultDTO<Long> addZSetJedis(String key, Map<String, Double> map, int dbIndex);
//
//    APIResultDTO<Long> delZSetJedis(String key, int dbIndex, String... members);
//
//    Set<String> getSortedSetRangeFromJedis(String key, int dbIndex, long min, long max);
//
//    /**
//     * 获取指定key的ZSet中分数区间的成员数量
//     * @param key
//     * @param min 最小分数 -inf和+inf分别表示Sorted-Sets中分数的最低值和最高值。
//     * @param max 最大分数 -inf和+inf分别表示Sorted-Sets中分数的最低值和最高值。
//     * @param methodName
//     * @return
//     */
//    long getZSetCount(String key, String min, String max, String methodName);
//
//    /**
//     * 获取指定key的zset中score分数值在min~max间的member对象(包括min、max)
//     * 通过正向排序获取指定位置的成员，即从低到高的顺序。
//     * @param key
//     * @param min 最小分数 -inf和+inf分别表示Sorted-Sets中分数的最低值和最高值。
//     * @param max 最大分数 -inf和+inf分别表示Sorted-Sets中分数的最低值和最高值。
//     * @param offset 偏移值
//     * @param count 返回数量
//     * @param methodName
//     * @return
//     */
//    Set<String> getZSetRangeByScoreFromJedis(String key, String min, String max, int offset, int count, String methodName);
//
//    /**
//     * 删除指定key的Map中的指定fields
//     * @param key
//     * @param fields
//     * @return
//     */
//    boolean hDelFromJedis(String key, String... fields);
//
//
//    /**
//     * 删除指定key的ZSet中的value值
//     * @param key
//     * @param values
//     * @return
//     */
//    boolean delZSet(String key, String[] values);
//
//    /**
//     * 从指定KEY中获取数据
//     * @param key
//     * @param dbIndex
//     * @return
//     */
//    Map<String, String> getHashMapFromComdJedis(String key, int dbIndex);
//
//    boolean removeHashJedis(String key, String mapkey, String mapvalue, int dbIndex);
//
//
//}
