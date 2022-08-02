package com.moon.interview.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author Chanmoey
 * @date 2022年08月02日
 */
public class ProcessChinese {

    private static final String POETRY = "斜髻娇娥夜卧迟，梨花风静鸟栖枝。难将心事和人说，说与青天明月知。";

    public static void processChinese() {
        var charset = StandardCharsets.UTF_8;
        var bytes = POETRY.getBytes();

        int length = bytes.length;
        int index = 0;
        int step = 10;
        int bufLen = step + 1;

        var bbuf = ByteBuffer.allocate(bufLen);
        var cbuf = CharBuffer.allocate(bufLen);
        int diff = 0;
        while (index + step - diff <= length) {

            var buff = Arrays.copyOfRange(bytes, index, index + step - diff);
            index += step - diff;

            bbuf.put(buff);
            bbuf.flip();
            charset.newDecoder().decode(bbuf, cbuf, true);
            cbuf.flip();
            var tmp = new char[cbuf.length()];

            while (cbuf.hasRemaining()) {
                cbuf.get(tmp);
                System.out.println("new: " + new String(tmp));
            }
            diff = bbuf.limit() - bbuf.position();
            if (diff > 0) {
                var temp = Arrays.copyOfRange(bbuf.array(), bbuf.position(), bbuf.limit());
                bbuf.clear();
                bbuf.put(temp);
            } else {
                bbuf.clear();
            }
            cbuf.clear();
        }

        var buff = Arrays.copyOfRange(bytes, index, length);
        bbuf.put(buff);
        bbuf.flip();
        charset.newDecoder().decode(bbuf, cbuf, true);
        cbuf.flip();
        var tmp = new char[cbuf.length()];

        while (cbuf.hasRemaining()) {
            cbuf.get(tmp);
            System.out.println("new: " + new String(tmp));
        }
    }

    public static void main(String[] args) {
        ProcessChinese.processChinese();
    }
}
