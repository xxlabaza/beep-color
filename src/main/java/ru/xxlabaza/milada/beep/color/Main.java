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

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.val;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;

/**
 * @author Artem Labazin <xxlabaza@gmail.com>
 * @since 02.05.2016
 */
public class Main extends Application {

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage stage) throws Exception {
        val pane = new Pane();
        pane.setBackground(ColorUtils.getRandomColorBackgorund());

        val primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        val scene = new Scene(pane, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());

        stage.setResizable(false);
        stage.addEventFilter(KEY_PRESSED, (event) -> {
                         ColorBeep.play(event.getCode(), pane);
                         event.consume();
                     });
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
    }
}
