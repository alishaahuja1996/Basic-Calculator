// By: Fnu Alisha
// SE 311-001
// HW 4

// This class is part of the state pattern and represents an abstract State class
public abstract class State {
    //--Attributes--
    protected ContextController context;
    protected String previousState="none";

    //--Constructor--
    public State(ContextController context) {
        this.context = context;
    }

    //--Accessors and mutators--
    public ContextController getContext() {
        return context;
    }

    public void setContext(ContextController context) {
        this.context = context;
    }

    //--abstract methods--
    public abstract void getNextState(String value);
}
