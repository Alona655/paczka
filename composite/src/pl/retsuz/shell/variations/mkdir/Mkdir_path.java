package pl.retsuz.shell.variations.mkdir;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.shell.gen.Command;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.specs.Mkdir;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

import java.io.File;

public class Mkdir_path extends CommandVariation {
    public Mkdir_path(ICommandVariation next, ICommand parent) {
        super(next, parent, "[a-zA-Z0-9.l\\/_]*");
    }

    @Override
    public void make(String params) {
        Composite currentElem = (Composite) this.getParent().getContext().getCurrent();

        String[] subParams;
        subParams = params.split("/");
        IComposite temElem = null;

        int index = 0;

        for (String param : subParams) {
            try {
                temElem = currentElem.findElementByPath(param);

                if (temElem != null) {
                    index++;
                    currentElem = (Composite) temElem;
                } else break;
            } catch (Exception e) {
                break;
            }
        }

        if (index <= subParams.length - 1) {
            for (int i = index; i < subParams.length; i++) {
                try {
                    temElem = new Composite();
                    temElem.setName(subParams[i]);
                    temElem.setParent(currentElem);
                    currentElem.addElement(temElem);
                    currentElem = (Composite) temElem;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Plik o takiej nazwie już istnieje");
        }
    }
}
