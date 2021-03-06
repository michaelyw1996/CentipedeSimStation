/*
 * Edit History:
 * Michael Wong,3/30: Initialized and completed.
 * Vyvy, 4/15: Finalized.
 */
package simStation;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import mvc.*;

public class SimulationPanel extends AppPanel {
	public SimulationPanel(SimulationFactory factory) {
		super(factory);

		this.setLayout(new GridLayout(1, 2));

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(5, 1)); //("number of buttons", "rows")

		Panel p = new Panel();
		JButton b = new JButton("Start");
		b.addActionListener(this);
		p.add(b);
		buttons.add(p);

		p = new Panel();
		b = new JButton("Suspend");
		b.addActionListener(this);
		p.add(b);
		buttons.add(p);

		p = new Panel();
		b = new JButton("Resume");
		b.addActionListener(this);
		p.add(b);
		buttons.add(p);

		p = new Panel();
		b = new JButton("Stop");
		b.addActionListener(this);
		p.add(b);
		buttons.add(p);

		p = new Panel();
		b = new JButton("Stats");
		b.addActionListener(this);
		p.add(b);
		buttons.add(p);

		this.add(buttons);
		this.add(factory.getView(model));

	}

	public static void main(String[] args)
	{
		AppPanel panel = new SimulationPanel(new SimulationFactory());
		panel.display();
	}

}
