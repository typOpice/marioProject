package jade;

//TODO
//  Complete the Joystick Callback function

public class JoystickListener {
    private static JoystickListener instince;

    private JoystickListener() {

    }

    public static JoystickListener get(){
        if(instince == null){
            instince = new JoystickListener();
        }
        return instince;
    }




}
