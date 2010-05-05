package org.youthnet.hibernate.embedded.derby.util;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.util.UUID;

/**
 * User: karl
 * Date: 26-Mar-2010
 */

public class UuidConverter {

    public static byte[] convertUuidArrayToByteArray(UUID uuid) {
        byte[] bytes = new byte[16];

        byte[] firstBytes = new byte[8];
        byte[] secondBytes = new byte[8];
        ByteBuffer firstByteBuffer = ByteBuffer.wrap(firstBytes);
        ByteBuffer secondByteBuffer = ByteBuffer.wrap(secondBytes);

        LongBuffer firstLongBuffer = firstByteBuffer.asLongBuffer();
        LongBuffer secondLongBuffer = secondByteBuffer.asLongBuffer();

        firstLongBuffer.put(0, uuid.getMostSignificantBits());
        secondLongBuffer.put(0, uuid.getLeastSignificantBits());

        for (int i = 0; i < firstBytes.length; i++) {
            bytes[i] = firstBytes[i];
        }

        for (int i = 0; i < secondBytes.length; i++) {
            bytes[i + 8] = secondBytes[i];
        }

        return bytes;
    }

    public static UUID convertByteArrayToUuid(byte[] bytes) {
        byte[] firstBytes = new byte[8];
        byte[] secondBytes = new byte[8];

        for (int i = 0; i < firstBytes.length; i++) {
            firstBytes[i] = bytes[i];
        }
        for (int i = 0; i < secondBytes.length; i++) {
            secondBytes[i] = bytes[i + 8];
        }

        ByteBuffer firstByteBuffer = ByteBuffer.wrap(firstBytes);
        ByteBuffer secondByteBuffer = ByteBuffer.wrap(secondBytes);

        return new UUID(firstByteBuffer.getLong(), secondByteBuffer.getLong());
    }
}
