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

import java.lang.reflect.Field;
import java.util.Random;
import java.util.stream.Stream;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import lombok.SneakyThrows;

/**
 * @author Artem Labazin <xxlabaza@gmail.com>
 * @since 02.05.2016
 */
class ColorUtils {

    private static final Color[] COLORS;

    private static final Random RANDOM;

    static {
        COLORS = allColors();
        RANDOM = new Random(System.currentTimeMillis());
    }

    public static Background getRandomColorBackgorund () {
        return new Background(new BackgroundFill(getRandomColor(), CornerRadii.EMPTY, Insets.EMPTY));
    }

    public static Color getRandomColor () {
        int index = RANDOM.nextInt(COLORS.length);
        return COLORS[index];
    }

    @SneakyThrows
    private static Color[] allColors () {
        return Stream.of(Class.forName("javafx.scene.paint.Color").getFields())
                .map(ColorUtils::getStaticFieldValue)
                .map(it -> (Color) it)
                .toArray(Color[]::new);
    }

    @SneakyThrows
    private static Object getStaticFieldValue (Field field) {
        return field.get(null);
    }

    private ColorUtils () {
    }
}
