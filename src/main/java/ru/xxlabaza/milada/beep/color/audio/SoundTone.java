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
import javax.sound.sampled.SourceDataLine;

/**
 * @author Artem Labazin <xxlabaza@gmail.com>
 * @since 03.05.2016
 */
class SoundTone extends Sound {

    protected static Sound randomInstance () {
        Random random = new Random(System.currentTimeMillis());
        int hertz = (random.nextInt(20) + 1) * 50;
        int duration = random.nextInt((350 - 200) + 1) + 200;
        return new SoundTone(hertz, duration, 100.D);
    }

    private final int hertz;

    private final int duration;

    private final double volume;

    SoundTone (int hertz, int duration, double volume) {
        super();
        this.hertz = hertz;
        this.duration = duration;
        this.volume = volume;
    }

    @Override
    protected void playSound (SourceDataLine sourceDataLine, byte[] buffer) {
        for (int i = 0; i < duration * 8; i++) {
            double angle = i / (SAMPLE_RATE / hertz) * 2.0 * Math.PI;
            buffer[0] = (byte) (Math.sin(angle) * 127.0 * volume);
            sourceDataLine.write(buffer, 0, 1);
        }
    }
}
