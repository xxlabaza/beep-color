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

import javax.sound.sampled.SourceDataLine;
import lombok.SneakyThrows;

/**
 * @author Artem Labazin <xxlabaza@gmail.com>
 * @since 03.05.2016
 */
class SoundLaser extends Sound {

    protected static Sound randomInstance () {
        return new SoundLaser(2);
    }

    private final int repeat;

    SoundLaser (int repeat) {
        super();
        this.repeat = repeat;
    }

    @Override
    @SneakyThrows
    protected void playSound (SourceDataLine sourceDataLine, byte[] buffer) {
        for (int j = 0, step = 10; j < repeat; j++) {
            for (int i = 0; i < 2000; i++) {
                buffer[0] = i % step > 0
                            ? 32
                            : (byte) 0;

                if (i % 250 == 0) {
                    step += 2;
                }
                sourceDataLine.write(buffer, 0, 1);
            }
            Thread.sleep(200);
        }
    }
}
