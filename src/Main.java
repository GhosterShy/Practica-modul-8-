interface ICommand {
    void execute();
    void undo();
}

class Light {
    public void on() {
        System.out.println("Свет включен");
    }

    public void off() {
        System.out.println("Свет выключен");
    }
}


class AirConditioner {
    public void on() {
        System.out.println("Кондиционер включен");
    }

    public void off() {
        System.out.println("Кондиционер выключен");
    }
}


class TV {
    public void on() {
        System.out.println("Телевизор включен");
    }

    public void off() {
        System.out.println("Телевизор выключен");
    }
}

class LightOnCommand implements ICommand {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

 class LightOffCommand implements ICommand {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}



class AirConditionerOnCommand implements ICommand {
    private AirConditioner ac;

    public AirConditionerOnCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.on();
    }

    @Override
    public void undo() {
        ac.off();
    }
}

class AirConditionerOffCommand implements ICommand {
    private AirConditioner ac;

    public AirConditionerOffCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.off();
    }

    @Override
    public void undo() {
        ac.on();
    }
}

class TVOnCommand implements ICommand {
    private TV tv;

    public TVOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.on();
    }

    @Override
    public void undo() {
        tv.off();
    }
}

class TVOffCommand implements ICommand {
    private TV tv;

    public TVOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.off();
    }

    @Override
    public void undo() {
        tv.on();
    }
}

class RemoteControl {
    private ICommand[] onCommands;
    private ICommand[] offCommands;
    private ICommand lastCommand;

    public RemoteControl() {
        onCommands = new ICommand[3];
        offCommands = new ICommand[3];

        // Пустая команда для предотвращения ошибок
        ICommand noCommand = new ICommand() {
            @Override
            public void execute() {}
            @Override
            public void undo() {}
        };
        for (int i = 0; i < 3; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        lastCommand = noCommand;
    }

    public void setCommand(int slot, ICommand onCommand, ICommand offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void pressOnButton(int slot) {
        onCommands[slot].execute();
        lastCommand = onCommands[slot];
    }

    public void pressOffButton(int slot) {
        offCommands[slot].execute();
        lastCommand = offCommands[slot];
    }

    public void pressUndoButton() {
        lastCommand.undo();
    }
}
class MacroCommand implements ICommand {
    private ICommand[] commands;

    public MacroCommand(ICommand[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (ICommand command : commands) {
            command.execute();
        }
    }

    @Override
    public void undo() {
        for (ICommand command : commands) {
            command.undo();
        }
    }
}




public class Main {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();

        Light livingRoomLight = new Light();
        AirConditioner ac = new AirConditioner();
        TV tv = new TV();

        ICommand lightOn = new LightOnCommand(livingRoomLight);
        ICommand lightOff = new LightOffCommand(livingRoomLight);
        ICommand acOn = new AirConditionerOnCommand(ac);
        ICommand acOff = new AirConditionerOffCommand(ac);
        ICommand tvOn = new TVOnCommand(tv);
        ICommand tvOff = new TVOffCommand(tv);


        remote.setCommand(0, lightOn, lightOff);
        remote.setCommand(1, acOn, acOff);
        remote.setCommand(2, tvOn, tvOff);


        System.out.println("Включаем устройства:");
        remote.pressOnButton(0);
        remote.pressOnButton(1);
        remote.pressOnButton(2);


        System.out.println("\nОтмена последней команды:");
        remote.pressUndoButton();


        System.out.println("\nВыполняем макрокоманду:");
        ICommand[] partyMode = {lightOn, acOn, tvOn};
        MacroCommand partyMacro = new MacroCommand(partyMode);
        partyMacro.execute();


        System.out.println("\nОтмена макрокоманды:");
        partyMacro.undo();

    }
}

