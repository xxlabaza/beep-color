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

/**
 * @author Artem Labazin <xxlabaza@gmail.com>
 * @since 03.05.2016
 */
class SoundWarp extends Sound {

    protected static Sound randomInstance () {
        return new SoundWarp(3);
    }

    private final int repeat;

    SoundWarp (int repeat) {
        super();
        this.repeat = repeat;
    }

    @Override
    protected void playSound (SourceDataLine sourceDataLine, byte[] buffer) {
        for (int j = 0; j < repeat; j++) {
            for (int i = 0, step = 25; i < 2000; i++) {
                if (i < 500) {
                    buffer[0] = i % step > 0
                                ? 32
                                : (byte) 0;
                    if (i % 25 == 0) {
                        step--;
                    }
                } else {
                    buffer[0] = i % step > 0
                                ? 16
                                : (byte) 0;
                    if (i % 50 == 0) {
                        step++;
                    }
                }
                sourceDataLine.write(buffer, 0, 1);
            }
        }
    }
}
