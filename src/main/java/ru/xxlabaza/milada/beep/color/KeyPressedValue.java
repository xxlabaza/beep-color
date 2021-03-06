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
package ru.xxlabaza.milada.beep.color;

import javafx.scene.layout.Background;
import lombok.Value;
import ru.xxlabaza.milada.beep.color.audio.Sound;

/**
 * @author Artem Labazin <xxlabaza@gmail.com>
 * @since 03.05.2016
 */
@Value
class KeyPressedValue {

    Sound sound;

    Background background;

}
