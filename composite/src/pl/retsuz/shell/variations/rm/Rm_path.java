package pl.retsuz.shell.variations.rm;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Rm_path extends CommandVariation {
    public Rm_path(ICommandVariation next, ICommand parent) {
        super(next, parent, "[a-zA-Z0-9.l\\/_]*");
    }

    @Override
    public void make(String params) {
        Composite c = (Composite) (this.getParent().getContext().getCurrent());
        String path = params.substring(0, params.lastIndexOf("/"));
        String name = params.substring(params.lastIndexOf("/") + 1, params.length());
        try {
            IComposite elem = c.findElementByPath(path);
            if (Composite.class.isInstance((elem))) {
                this.getParent().getContext().setCurrent(elem);
                c = (Composite) (this.getParent().getContext().getCurrent());
                IComposite dir = c.findElementByPath(name);
                c.removeElement(dir);
                System.out.println("Plik został usunięty");
            }
        } catch (Exception e) {
            System.out.println("Cos poszlo nie tak");
        }
    }
}
