package module.controller;

//This part used for control the dragon movement

import module.model.Dragon;
import java.awt.event.KeyEvent;

public class Controller implements IStrategy {

    @Override
    public void controller(Dragon dragon, KeyEvent kevent) {
    }

    @Override
    // This event is generated when a key is let up.
    public void controllerReleased(Dragon dragon, KeyEvent kevent) {
        if (kevent.getKeyCode() == KeyEvent.VK_SPACE) {
            dragon.jump();
        }
    }

}