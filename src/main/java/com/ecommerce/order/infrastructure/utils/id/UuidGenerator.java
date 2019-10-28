package com.ecommerce.order.infrastructure.utils.id;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

/**
 * Util class for generating unique IDs based on UUID.
 * <p>
 * 优点：本地生成，生成简单，性能好，没有高可用风险
 * 缺点：长度过长，存储冗余，且无序不可读，查询效率低
 */
public final class UuidGenerator {

    private static final Base64.Encoder encoder = Base64.getUrlEncoder();

    public static String newBase64Uuid() {
        UUID uuid = UUID.randomUUID();
        byte[] src = ByteBuffer.wrap(new byte[16])
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits())
                .array();
        return encoder.encodeToString(src).substring(0, 10);
    }

    public static String newUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
