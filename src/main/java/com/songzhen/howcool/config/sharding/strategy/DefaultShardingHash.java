package com.songzhen.howcool.config.sharding.strategy;

public final class DefaultShardingHash {
    public static <T> int shardingDBValue(T shardingKey, int dbCount) {
        return Math.abs(hashCode(shardingKey)) % dbCount;
    }

    public static <T> int shardingTBValue(T shardingKey, int dbCount, int tbCount) {
        return Math.abs(hashCode(shardingKey) / dbCount) % tbCount;
    }

    public static <T> int hashCode(T original) {
        int h = 0;
        if (original == null) {
            return h;
        } else {
            if (original instanceof String) {
                String str = (String)original;
                if (str.length() > 0) {
                    char[] value = str.toCharArray();
                    char[] var4 = value;
                    int var5 = value.length;

                    for(int var6 = 0; var6 < var5; ++var6) {
                        char c = var4[var6];
                        h = 31 * h + c;
                    }
                }
            } else {
                h = original.hashCode();
            }

            return h == -2147483648 ? 0 : Math.abs(h);
        }
    }

    private DefaultShardingHash() {
    }
}