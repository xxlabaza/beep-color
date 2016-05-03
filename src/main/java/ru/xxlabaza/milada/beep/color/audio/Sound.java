/* 
 * Copyright 2016 Artem Labazin <xxlabaza@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.xxlabaza.milada.beep.color.audio;

import java.util.Random;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import lombok.SneakyThrows;
import lombok.val;

/**
 * @author Artem Labazin <xxlabaza@gmail.com>
 * @since 03.05.2016
 */
public abstract class Sound {

    protected static final float SAMPLE_RATE;

    static {
        SAMPLE_RATE = 8000f;
    }

    public static Sound randomSound () {
        val random = new Random(System.currentTimeMillis());
        switch (random.nextInt(10)) {
        case 0:
            return SoundBang.randomInstance();
        case 1:
            return SoundLaser.randomInstance();
        case 2:
            return SoundWarp.randomInstance();
        default:
            return SoundTone.randomInstance();
        }
    }

    protected static Sound randomInstance () {
        throw new UnsupportedOperationException();
    }

    private final AudioFormat audioFormat;

    protected Sound () {
        audioFormat = new AudioFormat(
                SAMPLE_RATE, // sampleRate
                8, // sampleSizeInBits
                1, // channels
                true, // signed
                false // bigEndian
        );
    }

    @SneakyThrows
    public void play () {
        new Thread(() -> {
            byte[] buffer = new byte[1];
            try (SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(audioFormat)) {
                sourceDataLine.open(audioFormat);
                sourceDataLine.start();

                playSound(sourceDataLine, buffer);

                sourceDataLine.drain();
                sourceDataLine.stop();
            } catch (LineUnavailableException ex) {

            }
        }).start();
    }

    protected abstract void playSound (SourceDataLine sourceDataLine, byte[] buffer);

}
