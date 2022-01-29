package module.controller;

import module.model.Dragon;
import java.awt.event.KeyEvent;


public class Controller implements IStrategy {

    @Override
    public void controller(Dragon dragon, KeyEvent kevent) {
    }

    @Override
    public void controllerReleased(Dragon dragon, KeyEvent kevent) {
        if(kevent.getKeyCode() == KeyEvent.VK_SPACE) {
            dragon.jump();
        }
    }
    
}