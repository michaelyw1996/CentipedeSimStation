package Simulation;

import mvc.*;

public interface SimFactory extends AppFactory{
    public View getView(Model model);
}
