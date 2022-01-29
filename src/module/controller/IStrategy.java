package module.controller;

import module.model.Dragon;
import java.awt.event.KeyEvent;


public interface IStrategy {
    
    public void controller(Dragon dragon, KeyEvent kevent);
    public void controllerReleased(Dragon dragon, KeyEvent kevent);
}