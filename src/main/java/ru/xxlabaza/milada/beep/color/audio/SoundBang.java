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
class SoundBang extends Sound {

    protected static Sound randomInstance () {
        return new SoundBang();
    }

    private final Random random;

    SoundBang () {
        super();
        this.random = new Random();
    }

    @Override
    protected void playSound (SourceDataLine sourceDataLine, byte[] buffer) {
        boolean silence = true;
        for (int i = 0; i < 8000; i++) {
            while (random.nextInt() % 10 != 0) {
                buffer[0] = silence
                            ? 0
                            : (byte) Math
                        .abs(random.nextInt() % (int) (1. + 63. * (1. + Math.cos(i * Math.PI / 8000.))));
                i++;
                sourceDataLine.write(buffer, 0, 1);
            }
            silence = !silence;
        }
    }

}
